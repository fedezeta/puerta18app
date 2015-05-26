package com.example.android.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fedezeta on 05/05/2015.
 */

public class ContactoListViewAdapter extends BaseAdapter {

    Context context;
    List<Contacto> listaDeContactos;

    public ContactoListViewAdapter(Context context, List<Contacto> contactos) {
        this.context = context;
        this.listaDeContactos = contactos;
    }

    @Override
    public int getCount() {
        return listaDeContactos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeContactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View previousView, ViewGroup parent) {
        View filaView = previousView;

        if (previousView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            filaView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // Set data into the view.
        TextView nombreListaTextView = (TextView) filaView.findViewById(R.id.nombreListaTextView);
        TextView telefonoListaTextView = (TextView) filaView.findViewById(R.id.telefonoListaTextView);

        Contacto contacto = this.listaDeContactos.get(position);
        nombreListaTextView.setText(contacto.getNombre());
        telefonoListaTextView.setText(contacto.getTelefono());
        //ivItem.setImageResource(contacto.getImage());

        return filaView;
    }
}
