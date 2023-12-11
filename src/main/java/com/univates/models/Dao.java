package com.univates.models;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.univates.components.KMessage;

import java.io.FileInputStream;


public class Dao
{
    protected static Connection connection;
    protected static boolean    show_sql    = false;
    protected static int        selected_db = 1;

    public static final int DB_POSTGRES = 1;

    public Dao()
    {
        try
        {
            if(Dao.connection == null)
            {
                setSelectedDb();
            }
        }
        catch (Exception e)
        {
            KMessage.errorMessage("Erro ao conectar com o banco de dados.\r\n\r\n" + e.getMessage());
        }
    }

    public static boolean closeConnection()
    {
        try
        {
            if (connection != null)
            {
                connection.close();
            }
            return true;
        }
        catch (SQLException e)
        {
            // throw new RuntimeException("Erro ao fechar a conexão com o banco de dados.");
            return false;
        }
    }

    public static Connection getConnection()
    {
        return Dao.connection;
    }

    public static void setShowSql( boolean show_sql )
    {
        Dao.show_sql = show_sql;
    }

    protected void printQuery( String sql )
    {
        if (show_sql)
        {
            System.out.println(sql);
        }
    }

    public static int setDb( int selected_db )
    {
        return Dao.selected_db = selected_db;
    }

    protected void setSelectedDb()
    {
        String host, port, database, url, user, password;
        try
        {
            switch (Dao.selected_db)
            {
                case DB_POSTGRES:
                    Properties  properties     = new Properties();
                    InputStream arquivo_config = new FileInputStream("src/main/resources/db.properties");

                    properties.load(arquivo_config);

                    Class.forName("org.postgresql.Driver");

                    host     = properties.getProperty("host");
                    port     = properties.getProperty("port");
                    database = properties.getProperty("database");
                    user     = properties.getProperty("user");
                    password = properties.getProperty("password");
                    url      = "jdbc:postgresql://" + host + ":" + port + "/" + database;

                    Dao.connection = DriverManager.getConnection(url, user, password);
                break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
