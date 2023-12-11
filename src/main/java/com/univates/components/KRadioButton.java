package com.univates.components;

import javax.swing.JRadioButton;

public class KRadioButton extends JRadioButton
{
    public KRadioButton( String title )
    {
        super(title);
        super.setSize( this.getPreferredSize() );
    }

    public void setPosition(int x, int y)
    {
        super.setBounds(x, y, this.getWidth(), this.getHeight());
    }



}
