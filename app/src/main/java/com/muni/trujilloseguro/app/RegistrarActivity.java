package com.muni.trujilloseguro.app;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;

import com.muni.trujilloseguro.components.MyEditText;
import com.muni.trujilloseguro.util.Validacion;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegistrarActivity extends ActionBarActivity {

    MyEditText etNombre,etEmail,etMovil,etContrasenia;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b2533")));
        inicializarControles();
        //btnRegistrar.setEnabled(false);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validacionesRegistrar()==6)
                {
                    Toast.makeText(getApplicationContext(), "Se registró correctamente", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "LLene todos los campos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void inicializarControles()
    {
        btnRegistrar = (Button) findViewById(R.id.btn_registrar);
        etNombre = (MyEditText) findViewById(R.id.et_nombre);
        etEmail = (MyEditText) findViewById(R.id.et_email);
        etMovil = (MyEditText) findViewById(R.id.et_movil);
        etContrasenia = (MyEditText)findViewById(R.id.et_contrasenia);
    }

    public int validacionesRegistrar()
    {
        int c=6;
        Validacion v1 = new Validacion();

        if(!v1.validarEmail(etEmail))
        {
            etEmail.setError("Email Inválido");c--;
        }
        if(!v1.validarNombreCantidad(etNombre))
        {
            etNombre.setError("Mínimo 3 caracteres");c--;
        }
        if(!v1.validarNombre(etNombre))
        {
            etNombre.setError("Solo Letras");c--;
        }
        if(!v1.validarCelularCantidad(etMovil))
        {
            etMovil.setError("Mínimo 9 numeros");c--;
        }
        if(!v1.validarCelular(etMovil))
        {
            etMovil.setError("Solo numeros");c--;
        }
        if(!v1.validarContrasenia(etContrasenia))
        {
            etContrasenia.setError("Mínimo 6 caracteres");c--;
        }
        return c;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registrar, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
