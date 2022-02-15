package org.example.presentation;

import org.example.bll.ProductBLL;
import org.example.dao.ProductDAO;
import org.example.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * @author Gaga Sergiu
 * @class ProductGUI
 *  it implements the GUI for the Product operations
 * @since 19 Apr, 2021
 *
 */
public class ProductGUI implements ActionListener {
    private ProductDAO productDAO = new ProductDAO();
    private ProductBLL productBLL = new ProductBLL();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton showProducts = new JButton("Show Products");
    private JButton search = new JButton("Search product");
    private JButton search2 = new JButton("Search");
    private JButton insert = new JButton("Insert a new product");
    private JButton delete = new JButton("Delete a product");
    private JButton edit = new JButton("Edit a product");
    private JTable table;
    private JScrollPane sp = new JScrollPane();
    private JLabel select = new JLabel("Select id:");
    private JComboBox box = new JComboBox();
    private JLabel label1 = new JLabel("Name:");
    private JLabel label2 = new JLabel("Price:");
    private JLabel label3 = new JLabel("Stock:");
    private JTextField name = new JTextField();
    private JTextField price = new JTextField();
    private JTextField stock = new JTextField();
    private JButton update = new JButton("Update");
    private JButton insert2 = new JButton("Insert");
    private JButton delete2 = new JButton("Delete");
    /**
     * @constructor
     * it initializes the elements from the ProductGUI interface
     */
    public ProductGUI() throws IllegalAccessException {
        sp.setBounds(300, 10, 600, 550);
        sp.setVisible(false);

        showProducts.setBounds(10, 10, 200, 50);
        showProducts.setFocusable(false);
        showProducts.addActionListener(this);

        search.setBounds(10, 70, 200, 50);
        search.setFocusable(false);
        search.addActionListener(this);


        insert.setBounds(10, 130, 200, 50);
        insert.setFocusable(false);
        insert.addActionListener(this);

        delete.setBounds(10, 190, 200, 50);
        delete.setFocusable(false);
        delete.addActionListener(this);

        edit.setBounds(10, 250, 200, 50);
        edit.setFocusable(false);
        edit.addActionListener(this);

        select.setBounds(500, 10, 150, 50);
        select.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        box.setBounds(620, 10, 50, 40);
        search2.setBounds(680, 10, 100, 40);
        search2.setFocusable(false);
        search2.addActionListener(this);

        delete2.setBounds(680, 10, 100, 40);
        delete2.setFocusable(false);
        delete2.addActionListener(this);
        delete2.setVisible(false);

        name.setBounds(500, 100, 280, 50);
        name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label1.setBounds(400, 100, 150, 50);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        panel.add(name);
        panel.add(label1);


        label2.setBounds(400, 160, 150, 50);
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        price.setBounds(500, 160, 280, 50);
        price.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(price);
        panel.add(label2);

        label3.setBounds(400, 220, 150, 50);
        label3.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        stock.setBounds(500, 220, 280, 50);
        stock.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(stock);
        panel.add(label3);

        setVisible(false);

        update.setBounds(550, 340, 200, 50);
        update.setFocusable(false);
        update.addActionListener(this);
        update.setVisible(false);

        insert2.setBounds(550, 340, 200, 50);
        insert2.setFocusable(false);
        insert2.addActionListener(this);
        insert2.setVisible(false);
        panel.add(delete2);
        panel.add(insert2);
        panel.add(update);
        panel.add(search2);
        panel.add(box);
        panel.add(select);
        panel.add(search);
        panel.add(showProducts);
        panel.add(insert);
        panel.add(delete);
        panel.add(edit);
        panel.setSize(1000, 700);
        panel.add(sp);
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(60, 179, 150));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(300, 75, 1000, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void setVisible(boolean cond) {
        box.setVisible(cond);
        select.setVisible(cond);
        search2.setVisible(cond);
        label1.setVisible(cond);
        label2.setVisible(cond);
        label3.setVisible(cond);
        name.setVisible(cond);
        stock.setVisible(cond);
        price.setVisible(cond);
        name.setText("");
        price.setText("");
        stock.setText("");
    }
    /**
     *  actionPerformed
     * it handles the actions of the buttons
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            sp.setVisible(false);
            initializeBox();
            insert2.setVisible(false);
            setVisible(true);
            update.setVisible(false);
            delete2.setVisible(false);

        }
        if (e.getSource() == showProducts) {
            setVisible(false);
            update.setVisible(false);
            insert2.setVisible(false);
            delete2.setVisible(false);
            try {
                table = productDAO.createTable((ArrayList<Product>) productDAO.findAll(),new Product());
                sp.setViewportView(table);

                sp.setVisible(true);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }

        }
        if (e.getSource() == insert) {
            sp.setVisible(false);
            update.setVisible(false);
            insert2.setVisible(true);
            setVisible(true);
            box.setVisible(false);
            select.setVisible(false);
            search2.setVisible(false);
            delete2.setVisible(false);

        }
        if (e.getSource() == delete) {
            sp.setVisible(false);
            update.setVisible(false);
            setVisible(false);
            insert2.setVisible(false);
            initializeBox();
            box.setVisible(true);
            select.setVisible(true);
            delete2.setVisible(true);

        }
        if (e.getSource() == edit) {
            sp.setVisible(false);
            initializeBox();
            setVisible(true);
            update.setVisible(true);
            insert2.setVisible(false);
            delete2.setVisible(false);


        }
        if (e.getSource() == search2) {
            int id;
            String s = (String) box.getSelectedItem();
            id = Integer.valueOf(s);
            Product c = productDAO.findById(id);
            name.setText(c.getName());
            price.setText("" + c.getPrice());
            stock.setText("" + c.getStock());

        }
        if (e.getSource() == update) {
            int id;
            String s = (String) box.getSelectedItem();
            id = Integer.valueOf(s);
            Product c = new Product(name.getText(), Integer.valueOf(price.getText()), Integer.valueOf(stock.getText()));
            try {
                if (productBLL.update(id, c)!=-1)
                JOptionPane.showMessageDialog(null, "The record was edited successfully");
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        if (e.getSource() == insert2) {
            Product pr = new Product(name.getText(), Integer.valueOf(price.getText()), Integer.valueOf(stock.getText()));
            try {
                if (productBLL.insert(pr) == -1)
                    JOptionPane.showMessageDialog(null, "The client could not be inserted");
                else {
                    JOptionPane.showMessageDialog(null, "The record was inserted successfully");
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        if (e.getSource() == delete2) {
            int id;
            String s = (String) box.getSelectedItem();
            id = Integer.valueOf(s);
            productBLL.delete(id);
            JOptionPane.showMessageDialog(null, "The record was deleted successfully");
        }
    }

    private void initializeBox() {
        box.removeAllItems();
        ArrayList<Product> products = (ArrayList<Product>) productDAO.findAll();
        ArrayList<String> ids = new ArrayList<>();
        int i = 0;
        for (Product c : products) {
            ids.add(new String("" + c.getId()));
            box.addItem(ids.get(i));
            i++;
        }
    }
}

