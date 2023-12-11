package com.univates.components;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class KFrame extends JFrame
{
    private int largura = 800 ;
    private int altura  = 500 ;

    public KFrame( String title )
    {
        setTitle( title );
        setSize(this.largura, this.altura);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    public KFrame( String title, int largura, int altura )
    {
        setTitle( title );
        this.largura = largura;
        this.altura  = altura;
        setSize(this.largura, this.altura);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    public KFrame( )
    {
        setSize(this.largura, this.altura);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    public void display()
    {
        setVisible(true);
    }

    public void add( JMenuBar menu )
    {
        super.setJMenuBar(menu);
    }
}
