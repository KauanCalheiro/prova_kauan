package com.univates.components;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class KTable extends JTable
{
    private DefaultTableModel model;

    public KTable( String[] columns )
    {
        this( columns, false );
    }

    public KTable( String[] columns, boolean is_cell_editable )
    {
        super( new DefaultTableModel( new Object[][]{}, columns )
        {
            @Override
            public boolean isCellEditable( int row, int column )
            {
                return is_cell_editable;
            }
        });

        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setResizingAllowed(false);

        this.model = (DefaultTableModel) this.getModel();
    }

    public void addRow( Object[] row )
    {
        this.model.addRow( row );
    }

    public void addRows( ArrayList<Object[]> rows )
    {
        for ( Object[] row : rows )
        {
            this.model.addRow( row );
        }
    }

    public void removeRow( int row )
    {
        this.model.removeRow( row );
    }

    public void removeAllRows()
    {
        while( this.model.getRowCount() > 0 )
        {
            this.model.removeRow( 0 );
        }
    }

    public void setValueAt( Object value, int row, int column )
    {
        this.model.setValueAt( value, row, column );
    }

    public Object getValueAt( int row, int column )
    {
        return this.model.getValueAt( row, column );
    }

    public JScrollPane getScroll()
    {
        JScrollPane scroll = new JScrollPane( this );

        scroll.setBounds( this.getX(), this.getY(), this.getWidth(), this.getHeight() );

        return scroll;
    }

}
