package com.univates.components;

import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JLabel;

public class KLabel extends JLabel
{
    public static enum TYPE
    {
        TITULO,
        TEXTO
    }
    private LinkedList<KLabel> labels = new LinkedList<KLabel>();

    private Font         titulo = new Font("Arial", Font.BOLD, 24);
    private Font         texto  = new Font("Arial", Font.PLAIN, 16);

    private TYPE         type;

    public KLabel( String label_text, TYPE label_type )
    {
        super( label_text );
        this.type = label_type;

        if( label_type == TYPE.TITULO )
        {
            this.setFont( this.titulo );
        }
        else
        {
            this.setFont( this.texto );
        }

        super.setSize( this.getPreferredSize() );

        if( isMultiline() )
        {
            this.setMultipleKLabels( label_type );
        }
    }

    @Override
    public void setText( String text )
    {
        super.setText( text );
        super.setSize( this.getPreferredSize() );
    }


    private void setMultipleKLabels(TYPE label_type)
    {
        String[] lines = this.getText().split("\n");
        int y = this.getY();
        for( String line : lines )
        {
            KLabel label = new KLabel( line, label_type );
            label.setPosition( this.getX(), y );
            y += label.getHeight();
            this.labels.add( label );
        }
    }

    public void setPosition( int x, int y )
    {
        super.setBounds(x, y, this.getWidth(), this.getHeight());
    }

    public boolean isMultiline()
    {
        return this.getText().contains("\n");
    }

    public LinkedList<KLabel> getLabels()
    {
        return this.labels;
    }

    public TYPE getType()
    {
        return this.type;
    }
}
