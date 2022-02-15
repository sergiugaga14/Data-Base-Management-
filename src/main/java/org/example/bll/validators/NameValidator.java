package org.example.bll.validators;

import org.example.model.Client;
/**
 * @author Gaga Sergiu
 *  NameValidator
 * @since 19 Apr, 2021
 *
 */
public class NameValidator implements Validator<Client>{
    /**
     *  validate
     * it checks if the name is valid, if yes, it returns 0, else it returns -1
     * @param client
     * @return
     */
    @Override
    public int validate(Client client) {
        if(!client.getName().matches("[a-zA-Z ]+"))
            return -1;
        return 0;
    }
}