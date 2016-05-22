
package controlador;

import BaseDatos.ConexionBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.MetodosUsuarios;
import modelo.Metodos_XML_Usuarios;
import vista.FRM_Login;
import vista.FRM_MantenimientoUsuarios;

public class Controlador_FRM_Usuario implements ActionListener {

    public ConexionBD conexion;
    boolean creado = false;

    MetodosUsuarios metodos;
    FRM_MantenimientoUsuarios frm_mantenimientoUsuarios;
    FRM_Login frm_login;
    public Metodos_XML_Usuarios metodos_XML_Usuarios;


    public Controlador_FRM_Usuario(FRM_MantenimientoUsuarios frm_mantenimientoUsuarios, FRM_Login frm_login) {
        metodos = new MetodosUsuarios();
        this.frm_login = frm_login;
        this.frm_mantenimientoUsuarios = frm_mantenimientoUsuarios;

        conexion = new ConexionBD();
        metodos_XML_Usuarios= new Metodos_XML_Usuarios();

    }

    public void actionPerformed(ActionEvent e) 
    {
        // CONDICION PARA REGRESAR A LA VENTANA PRINCIPAL
        if (e.getActionCommand().equals("Volver")) 
        {
            frm_mantenimientoUsuarios.dispose();
            frm_login.setVisible(true);
        }
        // CONDICION PARA AGREGAR USUARIOS A ARCHIVOS PLANOS
        if (e.getActionCommand().equals("Agregar") && frm_login.botones().equals("ArchivosPlanos")) 
        {
            if (frm_mantenimientoUsuarios.confirmarContrasenna()) 
            {
                metodos.agregarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
                frm_mantenimientoUsuarios.mostrarMensaje(metodos.existe());
                frm_mantenimientoUsuarios.limpiar();
                metodos.escribirEnArchivo();
                System.out.println("Entró a archivos planos.");
            } else 
            {
                frm_mantenimientoUsuarios.mostrarMensaje(metodos.noExiste());
                frm_mantenimientoUsuarios.limpiar2();
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("Agregar") && frm_login.botones().equals("BaseDeDatos")) {
            if (frm_mantenimientoUsuarios.confirmarContrasenna()) {
                conexion.registrarUsuarios(frm_mantenimientoUsuarios.devolverInformacion());
                conexion.registrarLogin(frm_login.devolverInformacionLogin());
                frm_mantenimientoUsuarios.mostrarMensaje("Usuario agregado");
                frm_mantenimientoUsuarios.limpiar();
                System.out.println("Entró a BD.");
            } else {
                frm_mantenimientoUsuarios.mostrarMensaje("Contraseñas no coinciden");
                frm_mantenimientoUsuarios.limpiar2();
            }
        }

        if (e.getActionCommand().equals("Consultar") && frm_login.botones().equals("ArchivosPlanos")) {
            buscarArchivosPlanos();
            System.out.println("Entró a archivos planos.");
        }
        if (e.getActionCommand().equalsIgnoreCase("Consultar") && frm_login.botones().equals("BaseDeDatos")) {
            buscarBaseDeDatos();
            System.out.println("Entró a BD.");
        }

        if (e.getActionCommand().equals("Modificar") && frm_login.botones().equals("ArchivosPlanos")) {
            metodos.modificarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
            frm_mantenimientoUsuarios.mostrarMensaje("El estudiante fue modificado de forma correcta.");
            frm_mantenimientoUsuarios.inicializarGUI();
            System.out.println("Entró a archivos planos.");
        }

        if (e.getActionCommand().equalsIgnoreCase("Modificar") && frm_login.botones().equals("BaseDeDatos")) {
            if (frm_mantenimientoUsuarios.confirmarContrasenna()) {
                conexion.modificarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
                frm_mantenimientoUsuarios.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                frm_mantenimientoUsuarios.inicializarGUI();
                System.out.println("Entró a BD.");
            } else {
                JOptionPane.showMessageDialog(frm_mantenimientoUsuarios, "Contraseñas no coinciden");
            }
        }

        if (e.getActionCommand().equals("Eliminar") && frm_login.botones().equals("ArchivosPlanos")) {
            //metodos.eliminarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
            frm_mantenimientoUsuarios.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
            frm_mantenimientoUsuarios.inicializarGUI();
            System.out.println("Entró a archivos planos.");
        }

        if (e.getActionCommand().equalsIgnoreCase("Eliminar") && frm_login.botones().equals("BaseDeDatos")) {
            if (frm_mantenimientoUsuarios.confirmarContrasenna()) {
                conexion.eliminarUsuario(frm_mantenimientoUsuarios.devolverNombreUsuario());
                frm_mantenimientoUsuarios.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
                frm_mantenimientoUsuarios.inicializarGUI();
                System.out.println("Entró a BD.");
            } else {
                JOptionPane.showMessageDialog(frm_mantenimientoUsuarios, "Contraseñas no coinciden");
            }
        }
        if (e.getActionCommand().equals("Limpiar") && frm_login.botones().equals("ArchivosPlanos")) {
            frm_mantenimientoUsuarios.inicializarGUI();
            System.out.println("Entró a AP.");
        }
        if (e.getActionCommand().equals("Limpiar") && frm_login.botones().equals("BaseDeDatos")) {
            frm_mantenimientoUsuarios.inicializarGUI();
            System.out.println("Entró a BD.");
        }
        
        // CONDICIONES PARA XML
        if(e.getActionCommand().equals("Agregar")&& frm_login.botones().equals("XML"))
        {
            if(frm_mantenimientoUsuarios.verificarContraseñas())
            {
                metodos_XML_Usuarios.agregarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
                frm_mantenimientoUsuarios.mostrarMensaje("El usuario fue agregado de forma correcta");
                frm_mantenimientoUsuarios.resetearGUI();
            }
            else
            {
                frm_mantenimientoUsuarios.mostrarMensaje("Contaseñas distintas\n\nVuelva a repetir la contraseña");
                frm_mantenimientoUsuarios.limpiarRepetirContraseña();
            }
        }
        if(e.getActionCommand().equals("Consultar")&& frm_login.botones().equals("XML"))
        {
            buscarXML();
        }
        if(e.getActionCommand().equals("Modificar")&& frm_login.botones().equals("XML"))
        {
            if(frm_mantenimientoUsuarios.verificarContraseñas())
            {
                metodos_XML_Usuarios.modificarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
                frm_mantenimientoUsuarios.mostrarMensaje("El usuario fue modificado de forma correcta");
                frm_mantenimientoUsuarios.resetearGUI();
            }
            else
            {
                frm_mantenimientoUsuarios.mostrarMensaje("Contaseñas distintas\n\nVuelva a repetir la contraseña");
                frm_mantenimientoUsuarios.limpiarRepetirContraseña();
            }
        }
        if(e.getActionCommand().equals("Eliminar")&& frm_login.botones().equals("XML"))
        {
            metodos_XML_Usuarios.eliminarUsuario(frm_mantenimientoUsuarios.devolverInformacion());
            frm_mantenimientoUsuarios.mostrarMensaje("El usuario fue eliminado de forma correcta");
            frm_mantenimientoUsuarios.resetearGUI();
        }
    }

    // METODOS BUSCAR
    public void buscarArchivosPlanos() {
        if (metodos.consultarUsuario(frm_mantenimientoUsuarios.devolverCedula())) {
            frm_mantenimientoUsuarios.mostrarInformacion(metodos.getArregloInformacion());
            frm_mantenimientoUsuarios.habilitarEdicion();
            frm_mantenimientoUsuarios.habiltarCampos();
            
        } else {
            frm_mantenimientoUsuarios.mostrarMensaje("La cédula buscada no se encuentra.");
            frm_mantenimientoUsuarios.usuario(true);
        }
    }

    public void buscarBaseDeDatos() {
        if (conexion.consultarUsuario(frm_mantenimientoUsuarios.devolverCedula())) {
            frm_mantenimientoUsuarios.mostrarInformacion(conexion.arregloInformacionUsuario);
            frm_mantenimientoUsuarios.habilitarEdicion();
            frm_mantenimientoUsuarios.habiltarCampos();
        } else {
            frm_mantenimientoUsuarios.mostrarMensaje("La cédula buscada no se encuentra.");
            frm_mantenimientoUsuarios.usuario(true);
        }
    }

    public void buscarXML()
    {
        if(metodos_XML_Usuarios.consultarUsuario(frm_mantenimientoUsuarios.devolverCedula()))
            {
                frm_mantenimientoUsuarios.mostrarInformacion(metodos_XML_Usuarios.getArregloInformacion());
                frm_mantenimientoUsuarios.habilitarEdicion();
                frm_mantenimientoUsuarios.habiltarCampos();
            }
            else
            {
                frm_mantenimientoUsuarios.mostrarMensaje("La cédula buscada no se encuentra");
                frm_mantenimientoUsuarios.habilitarAgregar();
                frm_mantenimientoUsuarios.usuario(true);
            }
    }
    public boolean iniciarSesion(String arreglo[]) {
        return conexion.iniciarSesion(arreglo);
    }
}
