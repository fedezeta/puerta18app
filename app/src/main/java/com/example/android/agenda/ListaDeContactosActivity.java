package com.example.android.agenda;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by fedezeta on 05/05/2015.
 */

public class ListaDeContactosActivity extends ActionBarActivity {

    ArrayList<Contacto> listaDeContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_contactos);

        //Preparacion de Listview que requiere un Adapter para la informaci�n
        ListView contactosListView = (ListView) findViewById(R.id.contactosListView);

        listaDeContactos = (ArrayList<Contacto>)getIntent().getExtras().get("listaDeContactos");

        ContactoListViewAdapter adapter = new ContactoListViewAdapter(this, listaDeContactos);

        contactosListView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_de_contactos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void volverDeListaClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        this.startActivity(intent);


    }












//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putParcelableArrayList("listaDeContactos", listaDeContactos);
//        super.onSaveInstanceState(outState);
//    }
}
