package org.example.presentation;

import org.example.bll.ClientBLL;
import org.example.dao.ClientDAO;
import org.example.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * @author Gaga Sergiu
 * @class ClientGUI
 * it implements the GUI for the Client operations
 * @since 19 Apr, 2021
 *
 */
public class ClientGUI implements ActionListener {
    private ClientDAO clientDAO = new ClientDAO();
    private ClientBLL clientBll = new ClientBLL();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton showClients = new JButton("Show Clients");
    private JButton search = new JButton("Search client");
    private JButton search2 = new JButton("Search");
    private JButton insert = new JButton("Insert new Client");
    private JButton delete = new JButton("Delete a client");
    private JButton edit = new JButton("Edit a Client");
    private JTable table;
    private JScrollPane sp = new JScrollPane();
    private JLabel select = new JLabel("Select id:");
    private JComboBox box = new JComboBox();
    private JLabel label1 = new JLabel("Name:");
    private JLabel label2 = new JLabel("Email:");
    private JLabel label3 = new JLabel("Phone:");
    private JLabel label4 = new JLabel("Age:");
    private JTextField name = new JTextField();
    private JTextField email = new JTextField();
    private JTextField phone = new JTextField();
    private JTextField age = new JTextField();
    private JButton update = new JButton("Update");
    private JButton insert2 = new JButton("Insert");
    private JButton delete2 = new JButton("Delete");
    /**
     * @constructor
     * it initializes the elements from the ClientGUI interface
     */
    public ClientGUI() throws IllegalAccessException {
        sp.setBounds(300, 10, 600, 550);
        sp.setVisible(false);

        showClients.setBounds(10, 10, 200, 50);
        showClients.setFocusable(false);
        showClients.addActionListener(this);

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
        email.setBounds(500, 160, 280, 50);
        email.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(email);
        panel.add(label2);

        label3.setBounds(400, 220, 150, 50);
        label3.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        phone.setBounds(500, 220, 280, 50);
        phone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(phone);
        panel.add(label3);


        label4.setBounds(400, 280, 150, 50);
        label4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        age.setBounds(500, 280, 280, 50);
        age.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(age);
        panel.add(label4);
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
        panel.add(showClients);
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
        label4.setVisible(cond);
        name.setVisible(cond);
        phone.setVisible(cond);
        email.setVisible(cond);
        age.setVisible(cond);
        name.setText("");
        email.setText("");
        phone.setText("");
        age.setText("");
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
        if (e.getSource() == showClients) {
            setVisible(false);
            update.setVisible(false);
            insert2.setVisible(false);
            delete2.setVisible(false);
            try {
                table = clientDAO.createTable((ArrayList<Client>) clientDAO.findAll(),new Client());
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
            Client c = clientDAO.findById(id);
            name.setText(c.getName());
            email.setText(c.getEmail());
            phone.setText(c.getPhoneNumber());
            age.setText("" + c.getAge());
        }
        if (e.getSource() == update) {
            int id;
            String s = (String) box.getSelectedItem();
            id = Integer.valueOf(s);
            Client c = new Client(name.getText(), email.getText(), phone.getText(), Integer.valueOf(age.getText()));
            try {
                if(clientBll.update(id, c)!=-1)
                    JOptionPane.showMessageDialog(null, "The record was edited successfully");
                else {
                    JOptionPane.showMessageDialog(null, "The record could not be edited, some of the fields are not valid");
                }
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }
        if (e.getSource() == insert2) {
            Client cl = new Client(name.getText(), email.getText(), phone.getText(), Integer.valueOf(age.getText()));
            try {
                if (clientBll.insert(cl) == -1)
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
            clientBll.delete(id);
            JOptionPane.showMessageDialog(null, "The record was deleted successfully");
        }
    }

    private void initializeBox() {
        box.removeAllItems();
        ArrayList<Client> clients = (ArrayList<Client>) clientDAO.findAll();
        ArrayList<String> ids = new ArrayList<>();
        int i = 0;
        for (Client c : clients) {
            ids.add(new String("" + c.getId()));
            box.addItem(ids.get(i));
            i++;
        }
    }
}

