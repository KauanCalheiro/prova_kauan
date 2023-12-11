package com.univates.models;

import java.util.ArrayList;

import com.univates.components.KService;

public class Usuarios extends Model<Usuarios>
{
    private int    id;
    private String nome;
    private String cpf;
    private String senha;
    private double salario;


    public Usuarios(String nome, String cpf, String senha, double salario)
    {
        super();

        this.id      = super.getNextId();
        this.nome    = nome;
        this.cpf     = cpf;
        this.salario = salario;
        this.setSenha(senha);
    }

    public Usuarios()
    {
        super();
    }

    public int getId()
    {
        return this.id;
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getCpf()
    {
        return this.cpf;
    }

    public String getSenha()
    {
        return this.senha;
    }

    public double getSalario()
    {
        return this.salario;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public void setCpf( String cpf )
    {
        this.cpf = cpf;
    }

    public void setSenha( String senha )
    {
        this.senha = KService.isSenhaAlredyHashed(senha) ? senha : KService.hashSenha(senha);
    }

    public void setSalario( double salario )
    {
        this.salario = salario;
    }

    public static Usuarios getUsuarioByCpf( String cpf )
    {
        ArrayList<Filter> filtros = new ArrayList<Filter>();

        filtros.add( new Filter("cpf", " = ", cpf) );

        Usuarios usuario = new Usuarios().getObject( filtros );

        return usuario;
    }

    public void store()
    {
        super.store( this.id );
    }

    @Override
    public String toString()
    {
        return "Usuarios [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", salario=" + salario + "]";
    }
}
