package com.example.android.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fedezeta on 05/05/2015.
 * Added a new line in comment
 */

public class MainActivity extends ActionBarActivity {

    ArrayList<Contacto> listaDeContactos;
    EditText nombreEditText;
    EditText apellidoEditText;
    EditText telefonoEditText;
    TextView infoTextView;
    TextView listaSimpleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if (savedInstanceState == null){
        //if (getLastCustomNonConfigurationInstance() == null) {
            listaDeContactos = new ArrayList<Contacto>();
        //} else {
            //listaDeContactos = savedInstanceState.getParcelableArrayList("listaDeContactos");
        //    listaDeContactos = (ArrayList<Contacto>)getLastCustomNonConfigurationInstance();
        //}

        //Inicializar controles
        //Se "castea" la clase View a EditText = Class Cast
        nombreEditText = (EditText)findViewById(R.id.nombreEditText);
        apellidoEditText = (EditText)findViewById(R.id.apellidoEditText);
        telefonoEditText = (EditText)findViewById(R.id.telefonoEditText);
        infoTextView = (TextView)findViewById(R.id.infoTextView);

        listaSimpleTextView = (TextView)findViewById(R.id.listaSimpleTextView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void agregarClick(View view) {

        String nombreIngresado = nombreEditText.getText().toString();
        String apellidoIngresado = apellidoEditText.getText().toString();
        String telefonoIngresado = telefonoEditText.getText().toString();

        if (!nombreIngresado.isEmpty()
                && !apellidoIngresado.isEmpty()
                && !telefonoIngresado.isEmpty()) {

            Contacto resultadoDeBusqueda = telefonoExiste(telefonoIngresado);
            if (resultadoDeBusqueda == null) {
                Contacto nuevoContacto = new Contacto(nombreIngresado, apellidoIngresado, telefonoIngresado);
                listaDeContactos.add(nuevoContacto);
                infoTextView.setText("Contacto nuevo agregado.");
                limpiarCampos();
            } else {
                infoTextView.setText("Telefono ya existe a nombre de: " +
                        resultadoDeBusqueda.getNombre() + " " +
                        resultadoDeBusqueda.getApellido() + ". " +
                        "Actualizando contacto...");
                resultadoDeBusqueda.setNombre(nombreIngresado);
                resultadoDeBusqueda.setApellido(apellidoIngresado);
            }
            actualizarListaSimple();

        } else {
            infoTextView.setText("Todos los campos son requeridos");
        }
    }

    public void buscarClick(View view) {
        String nombreIngresado = nombreEditText.getText().toString();

        if (!nombreIngresado.isEmpty()) {
            boolean encontrado = false;
            for (Contacto contactoEnLista : listaDeContactos) {
                if (contactoEnLista.getNombre().contains(nombreIngresado)) {
                    encontrado = true;
                    nombreEditText.setText(contactoEnLista.getNombre());
                    apellidoEditText.setText(contactoEnLista.getApellido());
                    telefonoEditText.setText(contactoEnLista.getTelefono());

                    infoTextView.setText(contactoEnLista.getNombre()
                            + " " + contactoEnLista.getApellido()
                            + " " + contactoEnLista.getTelefono());
                }
            }
            if (encontrado == false) {
                infoTextView.setText("No se encontró contacto con ese nombre.");
            }
        } else {
            infoTextView.setText("Ingrese nombre para buscar.");
        }
    }

    public void limpiarClick(View v) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        nombreEditText.setText("");
        apellidoEditText.setText("");
        telefonoEditText.setText("");
    }

    private Contacto telefonoExiste(String telefono) {
        for (Contacto contactoEnLista : listaDeContactos) {
            if (contactoEnLista.getTelefono().equals(telefono)) {
                return contactoEnLista;
            }
        }
        return null;
    }

    public void verListaClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ListaDeContactosActivity.class);
        intent.putExtra("listaDeContactos", listaDeContactos);
        startActivity(intent);
    }

    private void actualizarListaSimple() {
        listaSimpleTextView.setText("");
        String textoTemporal = "";
        for (Contacto contacto : listaDeContactos) {
            textoTemporal = listaSimpleTextView.getText().toString();
            listaSimpleTextView.setText(textoTemporal
                    + contacto.getNombre() + " "
                    + contacto.getApellido() + " "
                    + contacto.getTelefono() + "\r\n");
        }

    }





//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putParcelableArrayList("listaDeContactos", listaDeContactos);
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle inState) {
//        super.onRestoreInstanceState(inState);
//        listaDeContactos = inState.getParcelableArrayList("listaDeContactos");
//    }

//    @Override
//    protected void onPause()  {
//        super.onPause();      // Store values between instances here
//        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//
//        editor.putStringSet("listaDeContactos", listaDeContactos);
//        //Commit to storage
//        editor.commit();
//    }
//
//    @Override
//    public void onResume() {
//
//    }

//  Get preferences
//  SharedPreferences preferences = getPreferences(MODE_PRIVATE);
// (preferences.getString("Name", null));

//    @Override
//    public Object onRetainCustomNonConfigurationInstance()  {
//        return listaDeContactos;
//    }

}
