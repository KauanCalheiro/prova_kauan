package com.univates.components;

import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class KMenu extends JMenuBar
{
    LinkedList<JMenuItem> lista_menu_iten = new LinkedList<>();

    public KMenu ( String title, String[] itens )
    {
        JMenu menu = new JMenu(title);

        this.add(menu);

        for (String iten : itens)
        {
            JMenuItem menu_item = new JMenuItem(iten);
            this.lista_menu_iten.add(menu_item);
            menu.add(menu_item);
        }
    }

    public JMenuItem getMenuItem( int index )
    {
        return this.lista_menu_iten.get(index);
    }
}
