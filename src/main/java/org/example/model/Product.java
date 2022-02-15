package org.example.model;
/**
 * @author Gaga Sergiu
 *  Product
 *  * it represents the class that contains the same fields as the Product Table
 * @since 19 Apr, 2021
 *
 */
public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;
    public Product()
    {

    }
    public Product(int id, String name, int price, int stock) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public Product( String name, int price, int stock) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

