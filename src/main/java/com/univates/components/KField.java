package com.univates.components;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class KField<T> extends JFormattedTextField
{
    private  Class<T> type;
    private boolean required = false;

    public KField( String name, int size, Class<T> type )
    {
        super.setName( name );
        super.setSize(size, 22);
        this.type = type;
    }

    public void setRequired( boolean is_required )
    {
        this.required = is_required;
    }

    public boolean isRequired()
    {
        return this.required;
    }

    public void setValue( String value )
    {
        this.setText( value );
    }

    public void setValue( int value )
    {
        this.setText( String.valueOf( value ) );
    }

    public void setValue( double value )
    {
        this.setText( String.valueOf( value ) );
    }

    @SuppressWarnings("unchecked")
    public T val()
    {
        if ( this.getText().isEmpty() )
        {
            return null;
        }

        switch ( this.type.getName() )
        {
            case "java.lang.Integer":
                return (T)Integer.valueOf( Integer.parseInt( this.getText().trim() ) );
            case "java.lang.Double":
                return (T) Double.valueOf(Double.parseDouble( this.getText().trim() ));
            case "java.lang.String":
                return (T) this.getText().trim().toString();
            default:
                return null;
        }
    }

    public boolean isValidInput()
    {
        try
        {
            if( this.required && this.getText().isEmpty() )
            {
                throw new Exception( "O campo " + this.getName() + " é obrigatório." );
            }
            if( this.type == Integer.class )
            {
                Integer.parseInt( this.getText() );
            }
            else if( this.type == Double.class )
            {
                Double.parseDouble( this.getText() );
            }

            return true;
        }
        catch( Exception e )
        {
            KMessage.errorMessage( e.getMessage() );
            return false;
        }
    }

    public void setSize( int width, int height )
    {
        if( width > 0 )
        {
            this.setBounds( super.getX(), super.getY(), width, super.getHeight());
        }
        if( height > 0 )
        {
            this.setBounds( super.getX(), super.getY(), super.getWidth(), height);
        }
    }

    public void setPosition(int x, int y)
    {
        super.setBounds( x, y, super.getWidth(), super.getHeight());
    }

    public int getIntValue()
    {
        try
        {
            return Integer.parseInt( this.getText() );
        }
        catch( Exception e )
        {
            KMessage.errorMessage( "O campo " + this.getName() + " deve ser um número inteiro." );
        }
        return 0;
    }

    public int getIntValueMinVal( int min_value)
    {
        try
        {
            int value = Integer.parseInt( this.getText() );
            if( value < min_value )
            {
                throw new Exception( );
            }
            return value;
        }
        catch( Exception e )
        {
            KMessage.errorMessage( "O campo " + this.getName() + " deve ser um número inteiro maior ou igual a " + min_value + "." );
        }
        return 0;
    }

    public void setMask( String mask )
    {
        try
        {
            this.setFormatterFactory( new DefaultFormatterFactory( new MaskFormatter( mask ) ) );
        }
        catch( Exception e )
        {
            KMessage.errorMessage( "Erro ao aplicar a máscara no campo " + this.getName() + "." );
        }
    }
}
