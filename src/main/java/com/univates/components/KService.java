package com.univates.components;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class KService
{
    public enum MASK
    {
        CPF("###.###.###-##"),
        TELEFONE("(##) # ####-####"),
        CEP("#####-###");

        private String mask;

        MASK(String mask)
        {
            this.mask = mask;
        }

        public String getMask()
        {
            return this.mask;
        }
    }

    public static String CpfMask()
    {
        return "###.###.###-##";
    }

    public static String CepMask()
    {
        return "#####-###";
    }

    public static String TelefoneMask()
    {
        return "(##) # ####-####";
    }

    public static String MoneyMask(int number_length)
    {
        String currencySymbol = "R$";
        StringBuilder mask = new StringBuilder(currencySymbol + " ");

        for (int i = 0; i < number_length; i++)
        {
            if ( i%3 == 0 && i != 0)
            {
                mask.append(".");
            }

            mask.append("#");
        }

        return mask.toString();
    }


    public static boolean validadeEmail( KField<String> email_input )
    {
        String email = email_input.getText();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        if( !email.matches( regex ) )
        {
            KMessage.errorMessage("Email inválido!");
            email_input.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean validadeNome( KField<String> nome_input )
    {
        String nome = nome_input.getText().trim();
        String regex = "^[A-Za-z]+\\s[A-Za-z]+$";

        if( !nome.matches( regex ) )
        {
            KMessage.errorMessage("Devem ser informados o primeiro e o último nome!");
            nome_input.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean validadeTelefone( KField<String> telefone_input )
    {
        String telefone = telefone_input.getText();
        String regex = "^\\([0-9]{2}\\)\\s[0-9]{4,5}-[0-9]{4}$";

        if( !telefone.matches( regex ) )
        {
            KMessage.errorMessage("O telefone deve ser informado no formato (00) 00000-0000 ou (00) 0000-0000!");
            telefone_input.requestFocus();
            return false;
        }
        return true;
    }

    public static String hashSenha( String senha )
    {
        try
        {
            MessageDigest md        = MessageDigest.getInstance( "SHA-256" );
            byte[]        hash_bytes = md.digest( senha.getBytes() );

            return Base64.getEncoder().encodeToString(hash_bytes);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isSenhaAlredyHashed(String senha)
    {
        return senha.length() == 44;
    }
}
