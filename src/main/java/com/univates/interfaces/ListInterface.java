package com.univates.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.univates.components.KButton;
import com.univates.components.KField;
import com.univates.components.KFrame;
import com.univates.components.KLabel;
import com.univates.components.KService;
import com.univates.components.KTable;
import com.univates.models.Filter;
import com.univates.models.Usuarios;

public class ListInterface extends KFrame
{
    private KLabel label_title = new KLabel("Listagem de Usuários", KLabel.TYPE.TITULO);

    private KLabel label_id      = new KLabel("Id", KLabel.TYPE.TEXTO);
    private KLabel label_nome    = new KLabel("Nome", KLabel.TYPE.TEXTO);
    private KLabel label_cpf     = new KLabel("CPF", KLabel.TYPE.TEXTO);
    private KLabel label_salario = new KLabel("Salário", KLabel.TYPE.TEXTO);

    private KField<Integer> field_id      = new KField<Integer>("id", 50, Integer.class);
    private KField<String>  field_nome    = new KField<String>("nome", 200, String.class);
    private KField<String>  field_cpf     = new KField<String>("cpf", 100, String.class);
    private KField<Double>  field_salario = new KField<Double>("salario", 100, Double.class);

    private KButton button_search = new KButton("Buscar");
    private KButton button_add    = new KButton("Cadastrar");

    KTable table = new KTable( new String[]{ "Id", "Nome", "CPF", "Salário" } );

    public ListInterface()
    {
        super("Listagem de Usuários", 800, 600);
        this.setComponentsProperties();
        this.setComponentsPosition();
        this.addComponents();
    }

    private void setComponentsProperties()
    {
        this.setMasks();
        this.updateTable(null);
        this.setActions();
    }

    private void setActions()
    {
        button_search.addActionListener(this::search);
        button_add   .addActionListener(this::showForm);
        this.callEditForm();
    }

    private void callEditForm()
    {
        table.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked( MouseEvent e)
            {
                int row = table.getSelectedRow();
                int col = 0;

                if ( e.getClickCount() == 2 && row >= 0 )
                {
                    int      id      = Integer.parseInt( table.getValueAt(row, col).toString() );
                    Usuarios usuario = new Usuarios().getObjectById(id);

                    FormInterface form    = new FormInterface( usuario );

                    form.setVisible(true);

                    form.addWindowListener(new java.awt.event.WindowAdapter()
                    {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent)
                        {
                            updateTable( null );
                        }
                    });
                }
            }
        });
    }

    private void updateTable( ArrayList<Filter> filters)
    {
        Usuarios usuarios = new Usuarios();
        ArrayList<Usuarios> usuarios_list = usuarios.getObjects(filters);

        table.removeAllRows();

        for ( Usuarios usuario : usuarios_list )
        {
            table.addRow( new Object[]{ usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getSalario() } );
        }
    }

    private void setMasks()
    {
        field_cpf.setMask( KService.MASK.CPF.getMask() );
    }

    private void showForm( ActionEvent event )
    {
        FormInterface form = new FormInterface( null );
        this.dispose();


        form.setVisible(true);

        form.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent)
            {
                updateTable( null );
            }
        });
    }

    private void search( ActionEvent event )
    {
        ArrayList<Filter> filters = new ArrayList<Filter>();

        String id      = field_id.getText().trim();
        String nome    = field_nome.getText().trim();
        String cpf     = field_cpf.getText().replace( ".", "").replace( "-", "").trim();
        String salario = field_salario.getText().trim();

        if ( !id.isEmpty()  )
        {
            filters.add( new Filter( "id", "=", id ) );
        }

        if ( !nome.isEmpty() )
        {
            filters.add( new Filter( "nome", "ILIKE" , nome ) );
        }

        if ( !cpf.isEmpty() )
        {
            filters.add( new Filter( "cpf", "=", cpf ) );
        }

        if ( !salario.isEmpty() )
        {
            filters.add( new Filter( "salario", "=", salario ) );
        }

        updateTable(filters);
    }

    public void addComponents()
    {
        this.add(label_title);
        this.add(label_id);
        this.add(label_nome);
        this.add(label_cpf);
        this.add(label_salario);
        this.add(field_id);
        this.add(field_nome);
        this.add(field_cpf);
        this.add(field_salario);
        this.add(button_search);
        this.add(button_add);
        this.add(table.getScroll());
    }

    public void setComponentsPosition()
    {
        this.label_title  .setPosition( 300, 20 );

        this.label_id     .setPosition( 50,  80 );
        this.label_nome   .setPosition( 150, 80 );
        this.label_cpf    .setPosition( 400, 80 );
        this.label_salario.setPosition( 550, 80 );

        this.field_id     .setPosition( 50,  120 );
        this.field_nome   .setPosition( 150, 120 );
        this.field_cpf    .setPosition( 400, 120 );
        this.field_salario.setPosition( 550, 120 );

        this.button_search.setPosition( 50,  180 );
        this.button_add   .setPosition( 150, 180 );
        this.table        .setBounds( 50,  250, 700, 200 );
    }
}
