package com.univates;

import com.univates.interfaces.ListInterface;
import com.univates.models.Dao;

public class Main
{
    public static void main(String[] args)
    {
        Dao.setDb(Dao.DB_POSTGRES);
        Dao.setShowSql(true);

        ListInterface list_view = new ListInterface();
        list_view.display();

    }
}