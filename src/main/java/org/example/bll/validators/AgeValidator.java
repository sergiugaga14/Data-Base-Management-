package org.example.bll.validators;

import org.example.model.Client;

/**
 * @author Gaga Sergiu
 *  AgeValidator
 * @since 19 Apr, 2021
 *
 */
public class AgeValidator implements Validator<Client> {

    /**
     *
     * it checks if the age is valid, if yes, it returns 0, else it returns -1
     * @param t
     * @return int
     */
    public int validate(Client t) {

        if (t.getAge() <= 0) {
            return -1;
        }
        return 0;
    }

}