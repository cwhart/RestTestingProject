package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemDaoJdbcTemplateImpl implements ItemDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ITEM
            = "insert into item (item_name, item_price, item_quantity) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_ITEM
            = "delete from item where item_id = ?";

    private static final String SQL_UPDATE_ITEM
            = "update item set item_name = ?, item_price = ?, item_quantity = ? where item_id = ? ";

    private static final String SQL_RETRIEVE_ITEM
            = "select * from item where item_id = ?";

    private static final String SQL_RETRIEVE_ALL_ITEMS
            = "select * from item";




    @Override
    public List<Item> retrieveAllItems()   {
        return jdbcTemplate.query(SQL_RETRIEVE_ALL_ITEMS, new ItemMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item createItem(int itemNum, Item item)   {
        jdbcTemplate.update(SQL_INSERT_ITEM,
                item.getItemName(),
                item.getItemPrice(),
                item.getItemQuantity());

        int itemId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        item.setItemID(itemId);

        return item;
    }

    @Override
    public void removeItem(int itemNum)   {
        jdbcTemplate.update(SQL_DELETE_ITEM, itemNum);
    }

    @Override
    public void updateItem(Item item)   {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                item.getItemName(),
                item.getItemPrice(),
                item.getItemQuantity(),
                item.getItemID());
    }

    @Override
    public Item retrieveSingleItem(int itemNo)   {
        try {
            return jdbcTemplate.queryForObject(SQL_RETRIEVE_ITEM, new ItemMapper(), itemNo);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }



    private static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item();
            item.setItemName(rs.getString("item_name"));
            item.setItemPrice(rs.getBigDecimal("item_price"));
            item.setItemQuantity(rs.getInt("item_quantity"));
            item.setItemID(rs.getInt("item_id"));
            return item;
        }
    }


}
