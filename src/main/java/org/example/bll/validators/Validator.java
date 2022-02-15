package org.example.bll.validators;
/**
 * @author Gaga Sergiu
 * Validator
 * @since 19 Apr, 2021
 *
 */
public interface Validator<T> {
    /**
     * validate
     * @param t
     * @return int
     */
    public int validate(T t);
}