package org.example.dao;

import org.example.connection.ConnectionFactory;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Gaga Sergiu
 *  AbstractDAO
 * - it implements the methods that make the queries through reflection techniques
 * @Since: Apr 19, 2021
 *
 *
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * @constructor
     * it initializes the type field
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     *  createSelectQuery
     * -it creates the query for finding an item by id
     * @param field
     * @return String
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("shopdb."+type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     *  createSelectQueryAll
     * -it creates the query for finding all the items from a table
     * @return String
     */
    private String createSelectQueryAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("shopdb."+type.getSimpleName());
        return sb.toString();
    }

    /**
     *  createInsertQuery
     *-it creates the query for inserting an item
     * @param t
     * @return String
     * @throws IllegalAccessException
     */
    private String createInsertQuery(T t) throws IllegalAccessException {
        StringBuilder sb=new StringBuilder();
        StringBuilder values=new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append("shopdb."+type.getSimpleName()+"(");
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            sb.append(field.getName()+", ");
            T value;
            value= (T) field.get(t);
            if(field.getType().getSimpleName().equals("String"))
            {
                values.append("\"").append(value).append("\"");
            }
            else values.append(value);
            values.append(", ");
        }
        sb.delete(sb.length()-2,sb.length());
        values.delete(values.length()-2, values.length());
        sb.append(")");
        sb.append(" VALUES(").append(values).append(")");
        // System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     *  createUpdateQuery
     * -it creates the query for updating an item which has the id given as a parameter
     * @param id
     * @param t
     * @return String
     * @throws IllegalAccessException
     */
    private StringBuilder createUpdateQuery(int id, T t) throws IllegalAccessException {
        StringBuilder sb=new StringBuilder();
        sb.append ("UPDATE ").append("shopdb."+type.getSimpleName()).append(" SET ");
        ArrayList<Field> fields=new ArrayList<>();
        fields.addAll(Arrays.asList(t.getClass().getDeclaredFields()));
        for(int i = 1; i< t.getClass().getDeclaredFields().length; i++)
        {
            fields.get(i).setAccessible(true);
            sb.append(fields.get(i).getName()+" = ");
            T value;
            value= (T) fields.get(i).get(t);
            if(fields.get(i).getType().getSimpleName().equals("String"))
            {
                sb.append("\"").append(value).append("\" , ");
            }
            else sb.append(value).append(", ");
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append(" WHERE ID = ").append(id);
        return sb;
    }

    /**
     *  findAll
     * it executes to query to select all the items from the table
     * @return List<T>
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQueryAll();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *  findById
     * it executes to query to select the item from the table whose is given as a parameter
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *  createObjects
     * it returns the list of objects create from the ResultSet
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * insert
     * it executes the insert query
     * @param t
     * @throws IllegalAccessException
     */
    public void insert(T t) throws IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }

    /**
     * delete
     * it creates and executes the delete query
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder sb=new StringBuilder();
        sb.append ("DELETE FROM ").append("shopdb."+type.getSimpleName()).append(" WHERE ID = ").append(id);
        String query=sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     *  update
     * it executes the update query
     * @param id
     * @param t
     * @throws IllegalAccessException
     */
    public void update(int id, T t) throws IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder sb = createUpdateQuery(id, t);
        String query=sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     *  createTable
     * it creates a table from a list of objects given as parameter
     * @param list
     * @param t
     * @return JTable
     * @throws IllegalAccessException
     */
    public JTable createTable(ArrayList<T> list,T t) throws IllegalAccessException {
        String[] column=new String[t.getClass().getDeclaredFields().length];
        String[][] data=new String[list.size()][t.getClass().getDeclaredFields().length];
        int index=0;
        for(Field field: t.getClass().getDeclaredFields())
        { field.setAccessible(true);
            column[index]=field.getName();
            index++;
        }
        index=0;
        for(T x: list)
        {   int index2=0;
            for(Field field: x.getClass().getDeclaredFields())
            {   field.setAccessible(true);
                T value= (T) field.get(x);
                data[index][index2]=value.toString();
                index2++;
            }
            index++;
        }
        JTable table=new JTable(data,column);
        return table;
    }


}



