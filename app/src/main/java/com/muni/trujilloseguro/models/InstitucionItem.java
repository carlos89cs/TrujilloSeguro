package com.muni.trujilloseguro.models;

/**
 * Created by Carlos-cs on 24/06/2014.
 */
public class InstitucionItem {

    private String institucion,telefono;
    private int img;
    private int id;

    public InstitucionItem()
    {

    }

    public InstitucionItem(String institucion,String telefono,int img,int id)
    {
        this.institucion = institucion;
        this.telefono = telefono;
        this.img = img;
        this.id = id;
    }

    public String getInstitucion(){
        return institucion;
    }

    public void setInstitucion(String institucion){
        this.institucion = institucion;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public int getImg(){
        return img;
    }

    public void setImg(int img){
        this.img = img;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}
