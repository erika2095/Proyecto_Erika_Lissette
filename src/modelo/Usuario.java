
package modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String cedula, nombreCompleto, nombreUsuario, contrasena, repetirContrasena,tipo;

    public Usuario(String cedula, String nombreCompleto, String nombreUsuario, String contrasena, String repetirContrasena, String tipo) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.repetirContrasena = repetirContrasena;
        this.tipo = tipo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRepetirContrasena() {
        return repetirContrasena;
    }

    public void setRepetirContrasena(String repetirContrasena) {
        this.repetirContrasena = repetirContrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getInformacion(){
        return "Cédula: "+getCedula()+".\nNombre Completo: "+getNombreCompleto()+".\nNombre Usuario: "+getNombreUsuario()+".\nContraseña: "+getContrasena()+".\nRepetir Contraseña: "+getRepetirContrasena()+".\nTipo: "+getTipo()+".";
    }
}