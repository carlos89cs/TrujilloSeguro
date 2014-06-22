package com.muni.trujilloseguro.app;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InicioActivity extends ActionBarActivity {


    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        //getSupportActionBar().hide();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f29900")));
       // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d89427")));
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        inicializarControles();
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InicioActivity.this,MenuActivity.class);
                startActivity(intent);

            }
        });
    }

    public void inicializarControles()
    {
        btnIngresar = (Button) findViewById(R.id.btn_registrar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
        */

        switch (item.getItemId())
        {
            case R.id.action_cuenta:
                Intent intent = new Intent(InicioActivity.this,RegistrarActivity.class);
                startActivity(intent);
                //Toast.makeText(this,"Crear Cuenta",Toast.LENGTH_SHORT).show();
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
