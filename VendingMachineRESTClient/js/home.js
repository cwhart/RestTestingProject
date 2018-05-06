$(document).ready(function () {

 
  //var runningBalance = 0;
  $('#total-money-in').val(0);
  $('#item').val('');
  $('#messages').val('');
  $('#change').val('');
  
  
  loadItems();
  

  $('#add-dollar').click(function (event) {
    //runningBalance += 100;
    var currentBalance = $('#total-money-in').val() * 100;
    currentBalance = (currentBalance + 100);
    currentBalance = currentBalance/100;
    num = parseFloat(currentBalance).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
    $('#total-money-in').val(num);
    
})

$('#add-quarter').click(function (event) {
  var currentBalance = $('#total-money-in').val() * 100;
    currentBalance = (currentBalance + 25);
    currentBalance = currentBalance/100;
    num = parseFloat(currentBalance).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
    $('#total-money-in').val(num);
  
})

$('#add-dime').click(function (event) {
  var currentBalance = $('#total-money-in').val() * 100;
    currentBalance = (currentBalance + 10);
    currentBalance = currentBalance/100;
    num = parseFloat(currentBalance).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
    $('#total-money-in').val(num);
  
})

$('#add-nickel').click(function (event) {
  var currentBalance = $('#total-money-in').val() * 100;
    currentBalance = (currentBalance + 5);
    currentBalance = currentBalance/100;
    num = parseFloat(currentBalance).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
    $('#total-money-in').val(num);
  
})

$('#make-purchase').click(function (event) {
  purchaseItem();
})

$('#return-change').click(function (event) {

  dispenseChange();
})

});

function loadItems() {
  
  var increment = 1;

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/items',
    success: function(itemArray) {
        $.each(itemArray, function(index, item) {

            var id = item.id;
            var name = item.name;
            var price = item.price.toFixed(2);
            var quantity = item.quantity;

            var row = '<div>';
                row += '<div>' + id + '</div>';
                row += '<div style="text-align: center"><div>' + name + '</div>';
                row += '<div> $' + price + '</div>';
                row += '<div>' + quantity + '</div>';
                row += '</div>';

                $('#item' + increment).empty();
                $('#item' + increment).append(row);
                increment += 1;

        });
    },
    error: function() {
      $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
    }
  });
}

function selectItem (itemNumber) {
  $('#item').val(itemNumber);
  $('#messages').val('');
  $('#change').val('');
}



function purchaseItem() {
  $('#errorMessages').empty();

  var balance = $('#total-money-in').val();
  var itemToPurchase = $('#item').val();

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/money/' + balance + '/item/' + itemToPurchase,
    success: function(data, status) {
      $('#messages').val("Thank you!!!");
      loadItems();
      $('#item').val('');

      var quarters = data.quarters;
      var dimes = data.dimes;
      var nickels = data.nickels;
      var pennies = data.pennies;

      var newBalance = quarters*25;
      newBalance += dimes*10;
      newBalance += nickels*5;
      newBalance += pennies;
      newBalance = newBalance/100;
      num = parseFloat(newBalance).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
      $('#total-money-in').val((num)).toFixed(2);

      
    },
    error: function(data) {
      //var thisMessage = data.responseText;
      //var messageText = data.responseText.message;
      var responseJSON = JSON.parse(data.responseText);
      
      $('#messages')

      
                //.text($('<li>')
                //.attr({class: 'list-group-item list-group-item-danger'})
                .val(responseJSON.message);
    }
  })

  $('#contactTableDiv').hide();
  $('#editFormDiv').show();
}

function dispenseChange() {
  var totalBalance = $('#total-money-in').val();
  totalBalance = totalBalance * 100;

  var quarters = 0;
  var dimes = 0;
  var nickels = 0;
  var pennies = 0;

  quarters = Math.floor((totalBalance/25));
  totalBalance = totalBalance%25;
  dimes = Math.floor(totalBalance/10);
  totalBalance = totalBalance%10;
  nickels = Math.floor(totalBalance/05);
  totalBalance = totalBalance%05;
  pennies = Math.floor(totalBalance/01);

  $('#change').val("Quarters: " + quarters
      + " Dimes: " + dimes
      + " Nickels: " + nickels
      + " Pennies: " + pennies);

  $('#total-money-in').val(0);
  $('#item').val('');
  $('#messages').val('');
 
}

