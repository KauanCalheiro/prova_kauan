package com.univates.components;

import java.util.LinkedList;

import javax.swing.ButtonGroup;

public class KRadioGroup extends ButtonGroup
{
    private LinkedList<KRadioButton> lista_radio_button = new LinkedList<>();

    public KRadioGroup()
    {
        super();
    }

    public KRadioGroup( String[] titles )
    {
        super();

        for (String title : titles)
        {
            KRadioButton radio_button = new KRadioButton(title);
            this.add(radio_button);
            this.lista_radio_button.add(radio_button);
        }
    }

    public KRadioGroup( KRadioButton[] radio_buttons )
    {
        super();

        for (KRadioButton radio_button : radio_buttons)
        {
            this.add(radio_button);
            this.lista_radio_button.add(radio_button);
        }
    }

    public LinkedList<KRadioButton> getRadioButtons()
    {
        return lista_radio_button;
    }

    public KRadioButton getSelectedRadioButton()
    {
        for (KRadioButton radio_button : lista_radio_button)
        {
            if (radio_button.isSelected())
            {
                return radio_button;
            }
        }
        return null;
    }
}
