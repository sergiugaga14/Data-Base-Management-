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
 * @author Gaga Sergiu
 *  ProductBLL
 * it checks if the input is valid, and it implements the operations for the Product Table
 * @since 19 Apr, 2021
 *
 */
public class ProductBLL {

    private ProductDAO productDAO;

    /**
     * @constructor
     * it initializes the productDAO object
     */
    public ProductBLL() {

        productDAO = new ProductDAO();
    }

    /**
     *  findById
     * it returns the product found in Product table whose id is given is a parameter
     * @param id
     * @return
     */
    public Product findById(int id) {
        Product pr = productDAO.findById(id);
        if (pr == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return pr;
    }

    /**
     *  findAll
     *  it returns a list with all the products in the table
     * @return
     */
    public List<Product> findAll() {
        List<Product> products= new ArrayList<>();
        products = productDAO.findAll();
        if (products == null) {
            throw new NoSuchElementException("The operation could not be performed");
        }
        return products;
    }

    /**
     *  insert
     * it inserts into the Product table the order given as a parameter, if the operation succeeds it returns 0, else -1
     * @param prod
     * @return
     * @throws IllegalAccessException
     */
    public int insert(Product prod) throws IllegalAccessException {
        if(prod.getPrice()>=0 && prod.getStock()>=0)
        productDAO.insert(prod);
        else
        {
            JOptionPane.showMessageDialog(null,"The price and stock have to be greater or equal to 0!");
            return -1;
        }
        return 0;
    }

    /**
     * delete
     * it deletes from the Product table the product whose id is given as a parameter
     * @param id
     */
    public void delete(int id)
    {
        OrderDAO orderDAO=new OrderDAO();
        orderDAO.deleteOrder(1,id);
        productDAO.delete(id);
    }

    /**
     *  update
     * it updates a product from the Product table with the values of the product given as a parameter , if the operation succeeds it returns 0, else -1
     * @param id
     * @param pr
     * @return
     * @throws IllegalAccessException
     */
    public int update(int id, Product pr) throws IllegalAccessException {
        if(pr.getPrice()>=0 && pr.getStock()>=0)
            productDAO.update(id,pr);
        else
        {
            JOptionPane.showMessageDialog(null,"The price and stock have to be greater or equal to 0!");
            return -1;
        }
        return 0;
    }
}
