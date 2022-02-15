package org.example.bll;


import org.example.bll.validators.Validator;
import org.example.dao.OrderDAO;
import org.example.dao.ProductDAO;
import org.example.model.Client;
import org.example.model.Order;
import org.example.model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 *
 * @author Gaga Sergiu
 *  OrderBLL
 * it checks if the input is valid, and it implements the operations for the Order Table
 * @since 19 Apr, 2021
 *
 */
public class OrderBLL {

    private OrderDAO orderDAO;

    /**
     * @constructor
     * it initializes the orderDAO object
     */
    public OrderBLL() {


        orderDAO = new OrderDAO();
    }

    /**
     * findById
     * it returns the order found in the Order table whose id is given is a parameter
     * @param id
     * @return int
     */
    public Order findById(int id) {
        Order ord = orderDAO.findById(id);
        if (ord == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return ord;
    }

    /**
     *  findAll
     * it returns the all the orders from the Order table
     * @return List<Order>
     */
    public List<Order> findAll() {
        List<Order> orders= new ArrayList<>();
        orders = orderDAO.findAll();
        if (orders == null) {
            throw new NoSuchElementException("The operation could not be performed");
        }
        return orders;
    }

    /**
     *  insert
     *  it inserts into the Order table the order given as a parameter, if the operation succeeds it returns 0, else -1
     * @param ord
     * @return int
     * @throws IllegalAccessException
     */
    public int insert(Order ord) throws IllegalAccessException {
        ProductDAO productDAO=new ProductDAO();
        Product pr= productDAO.findById(ord.getProductId());
        if(ord.getQuantity()<=0)
        {
            JOptionPane.showMessageDialog(null,"The quantity has to be positive!");
            return -1;

        }
        if(pr.getStock()>=ord.getQuantity() ) {
            pr.setStock(pr.getStock()-ord.getQuantity());
            productDAO.update(ord.getProductId(),pr);
            orderDAO.insert(ord);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Not enough products on stock!");
            return-1;
        }
        return 0;
    }

    /**
     *  delete
     * it deletes from the Order table the order whose id is given as a parameter
     * @param id
     */
    public void delete(int id)
    {
        orderDAO.delete(id);
    }

    /**
     *  update
     * it updates a client from the Order table with the values of the order given as a parameter , if the operation succeeds it returns 0, else -1
     * @param id
     * @param ord
     * @throws IllegalAccessException
     */
    public void update(int id, Order ord) throws IllegalAccessException {
        orderDAO.update(id,ord);
    }
}

