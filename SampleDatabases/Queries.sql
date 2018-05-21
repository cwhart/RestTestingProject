-- Query to see what promotion is associated with reservation #4
Select PromoType.Description, PromoRate.Type, PromoRate.Rate, Promo.StartDate as PromoStart, 
	Promo.EndDate as PromoEnd, Reservation.StartDate as CheckInDate, Reservation.EndDate as CheckOutDate
from reservation
inner join Promo
on Reservation.PromoID=Promo.PromoID
inner join PromoType
on Promo.PromoTypeID=PromoType.PromoTypeID
inner join PromoRate
on PromoType.PromoRateID=PromoRate.PromoRateID
where ReservationID=4;

-- Query to see what add-ons are available for reservation 4, and what they cost.
select Type, Price, StartDate, EndDate
from addon
inner join AddOnRate
on AddOn.AddOnID=AddOnRate.AddOnID
where StartDate <= (select StartDate from Reservation where ReservationID=4)
and EndDate >= (select EndDate from Reservation where ReservationID=4);

-- Query to calculate charges for selected add-on and add a row to addonbilldetail table.
-- Guest has chosen Room Service - Champagne

INSERT INTO AddOnBillDetail (PromoID, AddOnRateID, Price, BillingID, TransactionDate, TaxID, TaxAmount)
VALUES(
-- PromoID
	(select PromoID from Reservation where ReservationID=4),
-- AddOnRateID
    (Select AddOnRateID
    from AddOnRate
    inner join AddOn
    on AddOn.AddOnID=AddOnRate.AddOnID
    where AddOn.Type='Room Service - Champagne'
    and StartDate <= (select StartDate from Reservation where ReservationID=4)
    and EndDate >= (select EndDate from Reservation where ReservationID=4)),
-- Price
    @Price:=(Select Price
    from AddOnRate
    inner join AddOn
    on AddOn.AddOnID=AddOnRate.AddOnID
    where AddOn.Type='Room Service - Champagne'
    and StartDate <= (select StartDate from Reservation where ReservationID=4)
    and EndDate >= (select EndDate from Reservation where ReservationID=4)),
    (Select BillingID from Billing where ReservationID=4),
-- Transaction Date
    '2018/10/4',
-- TaxID
    (select TaxID from Tax where Type='WA Meals Tax'),
-- Tax Amount
     (Select Rate * .01 * @Price as TaxAmount
		from Tax where Type='WA Meals Tax')
);


-- Query to calculate total AddOn Cost, total Room cost, total taxes for AddOns and Rooms, and
-- then total up the whole bill.
select @TotalAddOns:=(select SUM(Price) 
from addonbilldetail
where BillingID=4) as TotalAddOnCost, 
@TotalRoom:=(select SUM(Price)
from roombilldetail
where BillingID=4)  as TotalRoomCost,
@AddOnTax:=(select SUM(TaxAmount)
from addonbilldetail
where BillingID=4) as TotalAddonTax,
@RoomTax:=(select SUM(TaxAmount)
from roombilldetail
where BillingID=4) as TotalRoomTax,
(@TotalAddOns + @TotalRoom + @AddOnTax + @RoomTax) as BillTotal;

-- Query to get a list of itemized charges for AddOns
select AddOnBillDetail.TransactionDate, AddOn.Type, AddOnBillDetail.Price, AddOnBillDetail.TaxAmount
from AddOnBillDetail
inner join AddOnRate
on AddOnBillDetail.AddOnRateID=AddOnRate.AddOnRateID
inner join AddOn
on AddOnRate.AddOnID=AddOn.AddOnID
where BillingID=4;

-- Query to get a list of itemized room charges.
select RoomBillDetail.TransactionDate, Room.RoomType, RoomBillDetail.Price, RoomBillDetail.TaxAmount
from RoomBillDetail
inner join RoomRate
on RoomBillDetail.RoomRateID=RoomRate.RoomRateID
inner join Room
on RoomRate.RoomID=Room.RoomID
where BillingID=4;

-- select Promo Code, PromoDescription, Promo Rate, Promo Type($ or %), Room price, room discounted
-- rate, tax amount, total room price for each line entry.
select PromoType.PromoCode, 
	PromoType.Description, 
    case when pr.Rate is null then 0 else pr.Rate end as PromoRate, 
	pr.Type, 
    rbd.Price, 
    @RoomRate:=case when pr.type is null then rbd.Price 
		else case when pr.type='$' then rbd.price-pr.Rate
        else case when pr.type='%' then rbd.price - (rbd.price * .01 * pr.Rate) end end end as DiscountedPrice, 
    @Taxes:=rbd.TaxAmount as Tax,
    @RoomRate + @Taxes  as TotalRoomCharges 
from RoomBillDetail rbd
left join roomrate
on rbd.RoomRateID=roomrate.RoomRateID
left join promo
on rbd.PromoID=promo.PromoID
left join promotype
on promo.PromoTypeID=promoType.promoTypeID
left join promorate pr
on promoType.promoRateID=pr.promoRateID
where BillingID=4;