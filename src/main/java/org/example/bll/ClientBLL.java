package org.example.bll;

import org.example.bll.validators.*;
import org.example.dao.ClientDAO;
import org.example.dao.OrderDAO;
import org.example.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * @author Gaga Sergiu
 *  ClientBLL
 * it checks if the input is valid, and it implements the operations for the Client Table
 * @since 19 Apr, 2021
 *
 */
public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * @constructor
     * it initialzes the clientDAO and the list of validators
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new AgeValidator());
        validators.add(new NameValidator());
        validators.add(new PhoneValidator());
        clientDAO = new ClientDAO();
    }

    /**
     *  findById
     * it returns the client found in Client table whose id is given is a parameter
     * @param id
     * @return Client
     */
    public Client findById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return cl;
    }

    /**
     *  findAll
     * it returns a list with all the clients in the table
     * @return List<Client>
     */
    public List<Client> findAll() {
        List<Client> clients= new ArrayList<>();
        clients = clientDAO.findAll();
        if (clients == null) {
            throw new NoSuchElementException("The operation could not be performed");
        }
        return clients;
    }

    /**
     *  insert
     * it inserts into the Client table the client given as a parameter, if the operation succeeds it returns 0, else -1
     * @param cl
     * @return int
     * @throws IllegalAccessException
     */
    public int insert(Client cl) throws IllegalAccessException {
        for(Validator v: validators)
        {
            if(v.validate(cl)==-1)
            {
                return -1;
            }
        }
        clientDAO.insert(cl);
        return 0;
    }

    /**
     *  delete
     * it deletes from the Client table the client whose id is given as a parameter
     * @param id
     */
    public void delete(int id)
    {
        OrderDAO orderDAO=new OrderDAO();
        orderDAO.deleteOrder(0,id);
        clientDAO.delete(id);
    }

    /**
     *  update
     * it updates a client from the Client table with the values of the client given as a parameter , if the operation succeeds it returns 0, else -1
     * @param id
     * @param c
     * @return int
     * @throws IllegalAccessException
     */
    public int update(int id,Client c) throws IllegalAccessException {
        for(Validator v: validators)
        {
            if(v.validate(c)==-1)
            {
                return -1;
            }
        }
        clientDAO.update(id,c);
        return 0;
    }
}

