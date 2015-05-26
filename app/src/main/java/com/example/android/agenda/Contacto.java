package com.example.android.agenda;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by fedezeta on 05/05/2015.
 */
public class Contacto implements Parcelable {

    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setTelefono(telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getNombre());
        dest.writeString(getApellido());
        dest.writeString(getTelefono());
    }

    public Contacto(Parcel in){
        this.nombre= in.readString();
        this.apellido = in.readString();
        this.telefono = in.readString();
    }

    public static final Parcelable.Creator<Contacto> CREATOR = new Parcelable.Creator<Contacto>() {
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };
}
