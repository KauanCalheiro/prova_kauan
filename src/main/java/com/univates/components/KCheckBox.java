package com.univates.components;

import javax.swing.JCheckBox;

public class KCheckBox extends JCheckBox
{
    public KCheckBox( String title )
    {
        super(title);
        super.setSize( this.getPreferredSize() );
    }

    public void setPosition(int x, int y)
    {
        super.setBounds(x, y, this.getWidth(), this.getHeight());
    }
}
