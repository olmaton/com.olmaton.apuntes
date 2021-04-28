package entidades;

import java.util.ArrayList;

/**
 *
 * @author olmaton
 */
public class Tipo {
    private int id;
    //private int id_relacion_usuario;
    private String nombre;
    private int signo;
    
    

    public Tipo(String nombre, int signo) {
        this.nombre = nombre;
        this.signo = signo;
    }

    public Tipo(int id) {
        this.id = id;
    }

    public Tipo() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getSigno() {
        return signo;
    }

    public void setSigno(int signo) {
        this.signo = signo;
    }
    
    public String getSignoFormat(){
        if(signo>0) return "Ingreso";
        else return "Egreso";
    }
    
    @Override
    public String toString() {
        return nombre;
    }

//    public int getId_relacion_usuario() {
//        return id_relacion_usuario;
//    }
//
//    public void setId_relacion_usuario(int id_relacion_usuario) {
//        this.id_relacion_usuario = id_relacion_usuario;
//    }
    public ArrayList<String[]> obtenerParametrosUrl(){
        String[] prmId = {"id",id+""};
        ArrayList retorno = new ArrayList();
        retorno.add(prmId);
        return retorno;
    }
    
}
