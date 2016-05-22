
package modelo;

import BaseDatos.ConexionBD;
import java.io.File;
import java.util.ArrayList;

public class MetodosLogin {

    private ArrayList<Usuario> arrayUsuarioArchivos;
    private ArrayList<Usuario> arrayUsuarioXML;
    private ArrayList<Usuario> arrayUsuarioConexion;
    
    String arregloInformacionConsultada[] = new String[2];
    
    ArchivosUsuario archivosUsuario;
    Metodos_XML_Usuarios xml_usuario;
    ConexionBD conexionBD;
    
    private boolean existe = false;

    public MetodosLogin(ArchivosUsuario archivosUsuario) {
        arrayUsuarioArchivos = new ArrayList<Usuario>();
        
        this.archivosUsuario = archivosUsuario;
    }

    public boolean isExiste() {
        return existe;
    }

    public String comparar(String usuario, String contrasena) {
        arrayUsuarioArchivos = archivosUsuario.leerInformacionCompleta();
        
        String mensaje = "";
        for (int controlador = 0; controlador < arrayUsuarioArchivos.size(); controlador++) {
            if (!usuario.isEmpty()) {
                if (arrayUsuarioArchivos.get(controlador).getNombreUsuario().equals(usuario)) {
                    if (arrayUsuarioArchivos.get(controlador).getContrasena().equals(contrasena)) {
                        mensaje ="Usuario encontrado.";
                        existe = true;
                    }else{
                        mensaje ="Contraseña incorrecta.";
                        existe = false;
                    }
                }else{
                        mensaje ="Usuario incorrecto.";
                        existe = false;
                    }
            }else{
                mensaje = "No se ha ingresado un usuario.";
                existe = false;
            }
        }
        return mensaje;
    }
    
    public boolean existeArchivo(){
        boolean existe = false;
        File archivo = new File("usuarios.dato");
        if(archivo.exists()){
            System.out.println("Existe archivo usuario");
            existe = true;
        }else{
            System.out.println("No existe archivo usuario");
        }
        return existe;
    }
}