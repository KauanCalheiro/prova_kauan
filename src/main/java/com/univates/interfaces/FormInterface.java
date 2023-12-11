package com.univates.interfaces;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;

import com.univates.components.KButton;
import com.univates.components.KField;
import com.univates.components.KLabel;
import com.univates.components.KMessage;
import com.univates.models.Usuarios;

public class FormInterface extends JDialog
{

    private KLabel label_title = new KLabel("Cadastro de Usuários", KLabel.TYPE.TITULO);

    private KLabel label_nome    = new KLabel("Nome", KLabel.TYPE.TEXTO);
    private KLabel label_cpf     = new KLabel("CPF", KLabel.TYPE.TEXTO);
    private KLabel label_salario = new KLabel("Salário", KLabel.TYPE.TEXTO);
    private KLabel label_senha   = new KLabel("Senha", KLabel.TYPE.TEXTO);

    private KField<String>  field_nome    = new KField<String>("nome", 200, String.class);
    private KField<String>  field_cpf     = new KField<String>("cpf", 100, String.class);
    private KField<Double>  field_salario = new KField<Double>("salario", 100, Double.class);
    private KField<String>  field_senha   = new KField<String>("senha", 100, String.class);

    private KButton button_save   = new KButton("Salvar");
    private KButton button_delete = new KButton("Excluir");
    private KButton button_cancel = new KButton("Voltar");

    private boolean is_update;
    private Usuarios usuario;


    public FormInterface( Usuarios usuario )
    {
        setSize(800, 600);
        setTitle("Cadastro de Usuários");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);
        setLayout(null);

        this.is_update = usuario != null;
        this.usuario   = usuario;

        this.setComponentsProperties();
        this.setComponentsPosition();
        this.addComponents();
    }

    private void setComponentsProperties()
    {
        this.setMasks();
        this.setActions();
        this.setFieldValues();
    }

    private void store( ActionEvent e )
    {
        try
        {
            String nome    = field_nome.val() ;
            String cpf     = field_cpf.val().replace( ".", "").replace( "-", "").trim();
            double salario = field_salario.val();
            String senha   = field_senha.val();

            Usuarios usuario;

            if ( this.is_update )
            {
                usuario = this.usuario;

                usuario.setNome( nome );
                usuario.setCpf( cpf );
                usuario.setSalario( salario );

                if ( senha != null && !senha.isEmpty() )
                {
                    usuario.setSenha( senha );
                }
            }
            else
            {
                usuario = new Usuarios( nome, cpf, senha, salario );
            }

            usuario.store();

            this.dispose();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }

    private void delete( ActionEvent e )
    {
        if( KMessage.questionMessage( "Deseja realmente excluir este registro?" ) )
        {
            try
            {
                this.usuario.delete( this.usuario.getId() );
                this.dispose();
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }

    private void setFieldValues()
    {
        if( this.is_update )
        {
            field_nome   .setValue( this.usuario.getNome() );
            field_cpf    .setValue( this.usuario.getCpf() );
            field_salario.setValue( this.usuario.getSalario() );
        }
    }

    private void setMasks()
    {
        field_cpf.setMask("###.###.###-##");
    }

    private void setActions()
    {
        button_save.addActionListener(this::store);
        button_delete.addActionListener(this::delete);
        button_cancel.addActionListener(e -> this.dispose());
    }

    private void setComponentsPosition()
    {
        label_title.setBounds( 10, 10, 780, 30 );

        label_nome.setBounds( 120, 50, 100, 30 );
        field_nome.setBounds( 120, 80, 100, 30 );

        label_cpf.setBounds( 230, 50, 100, 30 );
        field_cpf.setBounds( 230, 80, 100, 30 );

        label_salario.setBounds( 340, 50, 100, 30 );
        field_salario.setBounds( 340, 80, 100, 30 );

        label_senha.setBounds( 450, 50, 100, 30 );
        field_senha.setBounds( 450, 80, 100, 30 );

        button_save.setBounds( 10, 120, 100, 30 );
        button_delete.setBounds( 120, 120, 100, 30 );
        button_cancel.setBounds( 230, 120, 100, 30 );

        // label_title.setPosition( 10, 10 );

        // label_id.setPosition( 10, 50 );
        // field_id.setPosition( 10, 80 );

        // label_nome.setPosition( 120, 50 );
        // field_nome.setPosition( 120, 80 );

        // label_cpf.setPosition( 230, 50 );
        // field_cpf.setPosition( 230, 80 );

        // label_salario.setPosition( 340, 50 );
        // field_salario.setPosition( 340, 80 );

        // label_senha.setPosition( 450, 50 );
        // field_senha.setPosition( 450, 80 );

        // button_save.setPosition( 10, 120 );
        // button_delete.setPosition( 120, 120 );
        // button_cancel.setPosition( 230, 120 );
    }

    private void addComponents()
    {
        this.add(label_title);
        this.add(label_nome);
        this.add(label_cpf);
        this.add(label_salario);
        this.add(label_senha);
        this.add(field_nome);
        this.add(field_cpf);
        this.add(field_salario);
        this.add(field_senha);
        this.add(button_save);
        this.add(button_cancel);

        if( this.is_update )
        {
            this.add(button_delete);
        }
    }
}
