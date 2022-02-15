package org.example.model;
/**
 * @author Gaga Sergiu
 * Client
 * it represents the class that contains the same fields as the Client Table
 * @since 19 Apr, 2021
 *
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private int age;
    public Client()
    {

    }
    public Client(int id, String name, String email, String phoneNumber, int age) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }
    public Client( String name, String email, String phoneNumber, int age) {
        super();

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
