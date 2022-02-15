package org.example.dao;

import org.example.connection.ConnectionFactory;
import org.example.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
/**
 * @author Gaga Sergiu
 *  class OrderDAO
 *  * it extends AbstractDAO class
 * @since 19 Apr, 2021
 *
 */
public class OrderDAO extends AbstractDAO<Order> {
    /**
     *  deleteOrder
     * it does some specific queries, it deletes the order that belong to a specific client or product
     * @param type
     * @param id
     */
    public void deleteOrder(int type,int id)
    {   String query;
        if(type==0)
        {
            query="DELETE FROM shopdb.order where clientId="+id;

        }
        else
        {
            query="DELETE FROM shopdb.order where productId="+id;

        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Order" + "DAO:SpecialDelete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}