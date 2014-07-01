package com.muni.trujilloseguro.app;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.muni.trujilloseguro.components.MyEditText;


import com.muni.trujilloseguro.util.Validacion;
import com.muni.trujilloseguro.gps.GPSTracker;

public class InicioActivity extends ActionBarActivity {

    Button btnIngresar;
    MyEditText etEmail,etPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b2533")));


        inicializarControles();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validacionesInicio();
                if(etEmail.getText().toString().equals("carlos@gmail.com") && etPassword.getText().toString().equals("1234567"))
                {
                    getExtras();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void validacionesInicio()
    {
        Validacion v1 = new Validacion();

        if(!v1.validarEmail(etEmail))
        {
            etEmail.setError("Email Inválido");
        }
        if(!v1.validarContrasenia(etPassword))
        {
            etPassword.setError("Contraseña Inválido");
        }

    }

    public void inicializarControles()
    {
        btnIngresar = (Button) findViewById(R.id.btn_registrar);
        etEmail = (MyEditText) findViewById(R.id.et_email);
        etPassword = (MyEditText) findViewById(R.id.et_password);
    }

    public void getExtras(){

            Intent intent = new Intent().setClass(getApplicationContext(),MenuActivity.class);
            startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_cuenta:
                Intent intent = new Intent(InicioActivity.this,RegistrarActivity.class);
                startActivity(intent);
                break;
            case R.id.action_contrasenia:
                Toast.makeText(this,"Recuperar Contraseña",Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;

    }

}
