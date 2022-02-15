package org.example.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Gaga Sergiu
 * @class GUI
 * @since 19 Apr, 2021
 *
 */
public class GUI implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton client = new JButton("Client Management");
    JButton product = new JButton("Product Management");
    JButton order=new JButton("Order Management");

    /**
     * @constructor
     * it initializes the elements from the GUI interface
     */
    public GUI() {


        client.setBounds(140, 100, 200, 50);
        client.setFocusable(false);
        client.addActionListener(this);

        product.setBounds(140, 200, 200, 50);
        product.setFocusable(false);
        product.addActionListener(this);

        order.setBounds(140, 300, 200, 50);
        order.setFocusable(false);
        order.addActionListener(this);

        panel.add(client);
        panel.add(product);
        panel.add(order);
        panel.setSize(500, 500);
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(60, 179, 150));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(500, 150, 500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    /**
     *  actionPerformed
     * it handles the actions of the buttons
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == client) {

            try {
                new ClientGUI();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        if (e.getSource() == product) {
            try {
                new ProductGUI();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        if (e.getSource() == order) {

            try {
                new OrderGUI();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
    }
}