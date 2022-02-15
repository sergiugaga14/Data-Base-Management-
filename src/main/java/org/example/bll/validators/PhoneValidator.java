package org.example.bll.validators;

import org.example.model.Client;
/**
 * @author Gaga Sergiu
 * @since 19 Apr, 2021
 *
 */
public class PhoneValidator implements Validator<Client>{
    /**
     *  validate
     * it checks if the phone number is valid, if yes, it returns 0, else it returns -1
     * @param client
     * @return int
     */
    @Override
    public int validate(Client client) {
        if(client.getPhoneNumber().length()!=10 || !client.getPhoneNumber().matches("[0-9]+"))
            return  -1;

        return 0;
    }
}