package org.example.presentation;

import org.example.bll.OrderBLL;
import org.example.dao.ClientDAO;
import org.example.dao.OrderDAO;
import org.example.dao.ProductDAO;
import org.example.model.Client;
import org.example.model.Order;
import org.example.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Gaga Sergiu
 * OrderGUI
 *  it implements the GUI for the Order operations
 * @since 19 Apr, 2021
 *
 */
public class OrderGUI implements ActionListener {
    private OrderDAO orderDAO = new OrderDAO();
    private OrderBLL orderBLL = new OrderBLL();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton showOrders = new JButton("Show Orders");
    private JButton insert = new JButton("Make an order");
    private JTable table;
    private JScrollPane sp = new JScrollPane();

    private JLabel label1 = new JLabel("Select Client id:");
    private JLabel label2 = new JLabel("Select Product id:");
    private JLabel label3 = new JLabel("Quantity:");
    private JTextField clientId = new JTextField();
    private JTextField productId =new JTextField();
    private JTextField quantity = new JTextField();
    private JButton insert2 = new JButton("Insert");
    private JComboBox box2 = new JComboBox();
    private JComboBox box3 = new JComboBox();
    private JButton bill=new JButton("Generate Bill");
    /**
     * @constructor
     * it initializes the elements from the OrderGUI interface
     */
    public OrderGUI() throws IllegalAccessException {
        sp.setBounds(300, 10, 600, 550);
        sp.setVisible(false);

        showOrders.setBounds(10, 70, 200, 50);
        showOrders.setFocusable(false);
        showOrders.addActionListener(this);

        insert.setBounds(10, 130, 200, 50);
        insert.setFocusable(false);
        insert.addActionListener(this);

        bill.setBounds(10, 190, 200, 50);
        bill.setFocusable(false);
        bill.addActionListener(this);


        box2.setBounds(650, 100, 100, 50);
        label1.setBounds(400, 100, 250, 50);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        panel.add(box2);
        panel.add(label1);


        label2.setBounds(400, 160, 250, 50);
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        box3.setBounds(650, 160, 100, 50);
        panel.add(bill);
        panel.add(box3);
        panel.add(label2);
        box2.setVisible(false);
        box3.setVisible(false);

        label3.setBounds(400, 220, 200, 50);
        label3.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        quantity.setBounds(650, 220, 100, 50);
        quantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(quantity);
        panel.add(label3);

        setVisible(false);



        insert2.setBounds(650, 280, 100, 50);
        insert2.setFocusable(false);
        insert2.addActionListener(this);
        insert2.setVisible(false);

        panel.add(insert2);
        panel.add(showOrders);
        panel.add(insert);
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

        label1.setVisible(cond);
        label2.setVisible(cond);
        label3.setVisible(cond);
        quantity.setVisible(cond);
        box2.setVisible(cond);
        box3.setVisible(cond);
        quantity.setText("");

    }
    /**
     * @method actionPerformed
     * it handles the actions of the buttons
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == showOrders) {
            setVisible(false);
            insert2.setVisible(false);

            try {
                table = orderDAO.createTable((ArrayList<Order>) orderDAO.findAll(),new Order());
                sp.setViewportView(table);

                sp.setVisible(true);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }

        }
        if (e.getSource() == insert) {
            sp.setVisible(false);
            initializeBox(box2,0);
            initializeBox(box3,1);
            insert2.setVisible(true);
            setVisible(true);


        }

        if (e.getSource() == insert2) {
            String s2=box2.getSelectedItem().toString();
            String s3=box3.getSelectedItem().toString();
            int clientId=Integer.valueOf(s2);
            int productId=Integer.valueOf(s3);
            Order pr = new Order(clientId,productId,Integer.valueOf(quantity.getText()));
            try {
                if (orderBLL.insert(pr) == -1)
                    JOptionPane.showMessageDialog(null, "The client could not be inserted");
                else {
                    JOptionPane.showMessageDialog(null, "The record was inserted successfully");
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        if(e.getSource()==bill)
        {  sp.setVisible(false);
            setVisible(false);
            insert2.setVisible(false);
            ProductDAO productDAO= new ProductDAO();
            ClientDAO clientDAO=new ClientDAO();
            ArrayList<Order> orders= (ArrayList<Order>) orderDAO.findAll();
            for(Order ord: orders)
            {   String path="Bill"+ord.getId();
                createFile(path);
                String content="";
                content=content+"Buyer: "+clientDAO.findById(ord.getClientId()).getName()+"\n";
                content=content+"Product: "+productDAO.findById(ord.getProductId()).getName()+"\n";
                content=content+"Quantity: "+ord.getQuantity()+"\n";
                content=content+"Total Price: "+productDAO.findById(ord.getProductId()).getPrice()*ord.getQuantity()+"\n";
                writeFile(path,content);
            }
            JOptionPane.showMessageDialog(null,"The bills were generated successfully!");
        }

    }

    private void initializeBox(JComboBox box,int sw) {
        box.removeAllItems();
        if(sw==1)
        {
            ProductDAO productDAO=new ProductDAO();
            ArrayList<Product> products = (ArrayList<Product>) productDAO.findAll();
            ArrayList<String> ids = new ArrayList<>();
            int i = 0;
            for (Product c : products) {
                ids.add(new String("" + c.getId()));
                box.addItem(ids.get(i));
                i++;
            }
        }
        else
        {
            ClientDAO clientDAO=new ClientDAO();
            ArrayList<Client> clients = (ArrayList<Client>) clientDAO.findAll();
            ArrayList<String> ids = new ArrayList<>();
            int i = 0;
            for (Client c :clients ) {
                ids.add(new String("" + c.getId()));
                box.addItem(ids.get(i));
                i++;
            }
        }


    }

    /**
     * @method createFile
     * it creates a file
     * @param s
     */
    public void createFile(String s)
    {
        try {
            File myObj = new File(s);
            myObj.createNewFile();
        } catch (IOException e) {

        }
    }

    /**
     * @method writeFile
     * it writes in a file
     * @param s
     * @param content
     */
    public void writeFile(String s,String content)
    {
        try {
            FileWriter myWriter = new FileWriter(s);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
        }
    }

}
