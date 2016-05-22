
package controlador;

import BaseDatos.ConexionBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ArchivosUsuario;
import modelo.MetodosLogin;
import modelo.Metodos_XML_Usuarios;
import vista.FRM_Login;
import vista.FRM_MantenimientoUsuarios;
import vista.FRM_VentanaPrincipal;

public class Controlador_FRM_Login implements ActionListener {

    FRM_Login frm_login;
    MetodosLogin metodosLogin;
    ArchivosUsuario archivosUsuario;
    FRM_VentanaPrincipal frm_ventanaPrincipal;
    FRM_MantenimientoUsuarios frm_mantenimientoUsuarios;
    Metodos_XML_Usuarios metodos_XML_Usuarios;

    public Controlador_FRM_Login(FRM_Login frm_login, ArchivosUsuario archivosUsuario, FRM_VentanaPrincipal frm_ventanaPrincipal, FRM_MantenimientoUsuarios frm_mantenimientoUsuarios,Metodos_XML_Usuarios metodosXML) {
        this.frm_login = frm_login;
        this.archivosUsuario = archivosUsuario;
        metodosLogin = new MetodosLogin(archivosUsuario);
        metodosLogin.existeArchivo();
        this.metodos_XML_Usuarios=metodosXML;
        this.frm_ventanaPrincipal = frm_ventanaPrincipal;
        this.frm_mantenimientoUsuarios = frm_mantenimientoUsuarios;
    }

    public void actionPerformed(ActionEvent e) 
    {
        frm_ventanaPrincipal.dondeGuardar(frm_login.botones());
        
        // CONDICION PARA ARCHIVOS PLANOS
        if(frm_login.botones().equals("ArchivosPlanos") || frm_login.botones().equals("BaseDeDatos") || frm_login.botones().equals("XML"))
        {
            frm_login.activarLogin();
        }
        if (e.getActionCommand().equals("Aceptar") && frm_login.botones().equals("ArchivosPlanos")) {
            frm_login.mensaje(metodosLogin.comparar(frm_login.getJtNombreUsuario(), frm_login.getJpfContrasena()));
            if (metodosLogin.isExiste()) {
                
                frm_ventanaPrincipal.setVisible(true);
                System.out.println("Guardar en: " + this.frm_ventanaPrincipal.guardar());
                frm_login.dispose();
                System.out.println("Entró a AP LOG");
            } else {
                frm_login.limpiar();
            }
        }
        
        // CONDICIONES PARA XML
        if(e.getActionCommand().equals("Aceptar")&& frm_login.botones().equals("XML"))
        {
             metodos_XML_Usuarios = new Metodos_XML_Usuarios(); //Solo se crea la instancia si se marco el RadioButton del xml
            
            String nombre = frm_login.getJtNombreUsuario();
            String contrasenia = frm_login.getJpfContrasena();
            
            /*Se consulta el usuario */
            if(metodos_XML_Usuarios.consultarUsuarioRegistrado(nombre))
            {
                System.out.println("Encontro el usuario");
                
                /*Se consultala contrasenia */
                if(contrasenia.equals(metodos_XML_Usuarios.getArregloUsuarios()[1]))
                {
                    System.out.println("Usuario y Contrasenias coinciden");
                    frm_login.dispose();
                    
                    /*Hace la instancia de la ventana principal. Se hace nueva y se hace visible. */
                    FRM_VentanaPrincipal frm_ventanaPrincipal = new FRM_VentanaPrincipal(metodos_XML_Usuarios);
                    frm_ventanaPrincipal.setVisible(true);
                }
            }
            else
            {
                System.out.println("No se encontro el usuario");
            }
        }
        
        // CONDICIONES PARA BASE DE DATOS
        if (e.getActionCommand().equalsIgnoreCase("Aceptar") && frm_login.botones().equals("BaseDeDatos")) 
        {
            System.out.println("Guardar en: " + this.frm_ventanaPrincipal.guardar());

            iniciarSesion();
            System.out.println("Entró a BD LOG");
        }
        
        // CONDICIONES PARA ENTRAR A REGISTRO DE USUARIOS Y OPCION DE SALIR
        if (e.getActionCommand().equals("Registro")) 
        {
            frm_mantenimientoUsuarios.setVisible(true);
            frm_login.dispose();
        }
        if (e.getActionCommand().equals("Cancelar")) {
            System.exit(0);
        }
    }

    public void mostrarIniciarSesion() 
    {
        if (frm_mantenimientoUsuarios.controlador_FRM_Usuario.conexion.consultarUsuarios()) 
        {
            frm_mantenimientoUsuarios.mostrarMensaje("No existen ningún Usuario, será redirigido a la ventana Usuarios");
            frm_ventanaPrincipal.setVisible(true);
            frm_mantenimientoUsuarios.setVisible(true);
        } else 
        {
            frm_login.setVisible(true);
        }
    }

    public void iniciarSesion() 
    {
        if (frm_mantenimientoUsuarios.controlador_FRM_Usuario.conexion.iniciarSesion(frm_login.devolverInformacionDeInicioDeSesion())) {
            frm_ventanaPrincipal.setVisible(true);
            if (frm_mantenimientoUsuarios.controlador_FRM_Usuario.conexion.devolverTipo().equalsIgnoreCase("Usuario")) 
            {
                frm_ventanaPrincipal.opcionesUsuario();
            }
            frm_login.hide();
        } else 
        {
            frm_mantenimientoUsuarios.mostrarMensaje("Contraseña o usuario incorrecto");
        }
    }
}
