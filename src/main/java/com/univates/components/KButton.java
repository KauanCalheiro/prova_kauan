package com.univates.components;

import java.util.LinkedList;

import javax.swing.JButton;

public class KButton extends JButton
{
    public enum DIRECTION
    {
        ROW,
        COLUMN,
    }

    private static LinkedList<JButton> buttons = new LinkedList<JButton>();

    private static JButton   last_row_button;
    private static JButton   last_column_button;
    private static int       start_x_position;
    private static int       start_y_position;
    private static boolean   use_start_position = false;
    private static DIRECTION display_direction  = DIRECTION.COLUMN;

    private int width  = 100;
    private int height = 30;
    private int x      = 10;
    private int y      = 10;

    public KButton( String button_text )
    {
        super( button_text );
        super.setBounds(setNextXPosition(), setNextYPosition() , 0, 0);
        super.setSize( this.getPreferredSize() );
        this.setAuxiliaryVariables();
    }

    public KButton( String button_text, int width, int height )
    {
        super( button_text );
        super.setBounds(setNextXPosition(), setNextYPosition(), width, height);
        this.setAuxiliaryVariables();
    }

    private int setNextXPosition()
    {
        if(
            last_column_button == null                    ||
            display_direction  == DIRECTION.ROW ||
            use_start_position
        )
        {
            use_start_position = false;
            if( start_x_position > 0 )
            {
                return start_x_position;
            }
            else
            {
                return 10;
            }
        }
        else
        {
            return last_column_button.getX() + last_column_button.getWidth() + 10;
        }
    }

    private int setNextYPosition()
    {
        if(
            last_row_button   == null                       ||
            display_direction == DIRECTION.COLUMN ||
            use_start_position
        )
        {
            use_start_position = false;
            if( start_y_position > 0 )
            {
                return start_y_position;
            }
            else
            {
                return 10;
            }
        }
        else
        {
            return last_row_button.getY() + last_row_button.getHeight() + 10;
        }
    }

    public static void setStartPosition( int x, int y )
    {
        if( x > 0 )
        {
            start_x_position = x;
            use_start_position = true;
        }
        if( y > 0 )
        {
            start_y_position = y;
            use_start_position = true;
        }
    }

    public void setPosition( int x, int y )
    {
        this.x = x;
        this.y = y;
        super.setBounds(x, y, this.width, this.height);
    }

    public void setSize( int width, int height )
    {
        this.width  = width;
        this.height = height;
        super.setBounds(this.x, this.y, width, height);
    }

    private void setAuxiliaryVariables()
    {
        if( KButton.display_direction == DIRECTION.ROW )
        {
            last_row_button = this;
        }
        else
        {
            last_column_button = this;
        }

        this.x      = this.getX();
        this.y      = this.getY();
        this.width  = this.getWidth();
        this.height = this.getHeight();

        buttons.add( this );
    }

    public static void setDisplayDirection( DIRECTION display_direction )
    {
        KButton.display_direction = display_direction;
    }

    public void alignBottom( int height )
    {
        this.y = (height - 38 ) - this.getHeight() - 10;
        super.setBounds(this.x, y, this.width, this.height);
    }

    public void alignTop( )
    {
        this.y = 10;
        super.setBounds(this.x, this.y, this.width, this.height);
    }

    public void alignLeft( )
    {
        this.x = 10;
        super.setBounds(this.x, this.y, this.width, this.height);
    }

    public void alignRight( int width )
    {
        this.x = ( width - 15 ) - this.getWidth() - 10;
        super.setBounds(this.x, this.y, this.width, this.height);
    }

    public void alignCenterH( int width )
    {
        this.x = ( ( (width - 15 ) - this.getWidth() ) / 2 );
        super.setBounds(this.x, this.y, this.width, this.height);
    }

    public void alignCenterV( int height )
    {
        this.y = ( ( (height - 38 ) ) / 2 ) - ( this.getHeight() / 2 );
        super.setBounds(this.x, this.y, this.width, this.height);
    }

}
