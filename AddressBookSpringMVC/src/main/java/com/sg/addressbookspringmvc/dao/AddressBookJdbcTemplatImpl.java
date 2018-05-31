package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.dto.Address;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressBookJdbcTemplatImpl implements AddressBookDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ADDRESS
            = "insert into addresses (first_name, last_name, street_address, city, state, zip) "
            + "values (?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ADDRESS
            = "delete from addresses where address_id = ?";

    private static final String SQL_UPDATE_ADDRESS
            = "update addresses set first_name = ?, last_name = ?, street_address = ?, "
            + "city = ?, state = ?, zip = ? where address_id = ?";

    private static final String SQL_SELECT_ADDRESS_BY_ID
            = "select * from addresses where address_id = ?";

    private static final String SQL_SELECT_ALL_ADDRESSES
            = "select * from addresses";



    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreetAddress(),
                address.getCity(),
                address.getState(),
                address.getZipCode());

        int addressId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        address.setAddressId(addressId);
    }

    @Override
    public void removeAddress(int addressId) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, addressId);
    }

    @Override
    public void updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreetAddress(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getAddressId());
    }

    @Override
    public Address getAddress(int addressId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_ID, new AddressMapper(), addressId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES, new AddressMapper());
    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address address = new Address();
            address.setAddressId(rs.getInt("address_id"));
            address.setFirstName(rs.getString("first_name"));
            address.setLastName(rs.getString("last_name"));
            address.setStreetAddress(rs.getString("street_address"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZipCode(rs.getString("zip"));
            return address;

        }
    }
}
