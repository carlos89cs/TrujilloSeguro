package com.muni.trujilloseguro.util;

import com.muni.trujilloseguro.components.MyEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Carlos-cs on 30/06/2014.
 */
public class Validacion {


    public boolean validarEmail(MyEditText edt)
    {
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        String et_email = edt.getText().toString();

        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(et_email);

        return matcher.matches();
    }



    public boolean validarContrasenia(MyEditText edt)
    {

        String et_pass = edt.getText().toString();
        if(et_pass != null && et_pass.length()>=6 ) {
            return true;
        }
        return false;
    }

    public boolean validarCelular(MyEditText edt)
    {

        String et_telefono = edt.getText().toString();
        if(et_telefono.matches("[0-9]*"))
        {
            return true;
        }
        return false;
    }



    public void validarEmaill(MyEditText edt) throws  NumberFormatException
    {
        String et_email = edt.getText().toString();
        if(!et_email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"));
        {
            edt.setError("Email inválido");
        }
    }

    public boolean validarNombre(MyEditText edt)
    {
        String et_nombre = edt.getText().toString();
        if(et_nombre.matches("[a-zA-Z]+"))
        {
            return true;
        }
        return false;
    }

    public boolean validarNombreCantidad(MyEditText edt)
    {
        String et_nombre = edt.getText().toString();
        if(et_nombre.length()>=3)
        {
            return true;
        }
        return false;
    }

    public boolean validarCelularCantidad(MyEditText edt)
    {
        String et_telefono = edt.getText().toString();
        if(et_telefono.length()>=9)
        {
            return true;
        }
        return false;
    }




}
