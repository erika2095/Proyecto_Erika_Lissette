/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import BaseDatos.ConexionBD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.ArchivosEstudiantes;
import modelo.Estudiante;
import modelo.MetodosEstudiantes;
import modelo.Metodos_XML_Cursos;
import modelo.Metodos_XML_Estudiantes;
import vista.FRM_Login;
import vista.FRM_MantenimientoEstudiantes;

public class Controlador_FRM_MantenimientoEstudiantes implements ActionListener{
    
    public MetodosEstudiantes metodosEstudiantes;
    FRM_MantenimientoEstudiantes frm_MantenimientoEstudiantes;
    ArchivosEstudiantes archivosEstudiantes;
    FRM_Login frm_Login;
    ConexionBD conexionBD = null;
    public Metodos_XML_Estudiantes metodos_XML_Estudiantes = null;

    public Controlador_FRM_MantenimientoEstudiantes(FRM_MantenimientoEstudiantes frm_MantenimientoEstudiantes, MetodosEstudiantes metodosEstudiante)
    {
        this.metodosEstudiantes = metodosEstudiante;
        this.frm_MantenimientoEstudiantes=frm_MantenimientoEstudiantes;
        archivosEstudiantes = new ArchivosEstudiantes();
        crearArchivo();
        
    }
    public Controlador_FRM_MantenimientoEstudiantes(FRM_MantenimientoEstudiantes frm_MantenimientoEstudiantes, Metodos_XML_Estudiantes xML_Estudiantes)
    {
        this.frm_MantenimientoEstudiantes=frm_MantenimientoEstudiantes;
        this.metodos_XML_Estudiantes = xML_Estudiantes;
    }
    public Controlador_FRM_MantenimientoEstudiantes(FRM_MantenimientoEstudiantes frm_MantenimientoEstudiantes, ConexionBD conexionBD)
    {
        this.frm_MantenimientoEstudiantes=frm_MantenimientoEstudiantes;
        this.conexionBD = conexionBD;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        //CONDICIONES
        if(e.getActionCommand().equals("Agregar"))
        {
            if(metodosEstudiantes != null)
            {
                metodosEstudiantes.agregarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue registrado de forma correcta");
                frm_MantenimientoEstudiantes.resetearGUI();
            }
            else if(metodos_XML_Estudiantes != null)
            {
                metodos_XML_Estudiantes.agregarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue registrado de forma correcta");
                frm_MantenimientoEstudiantes.resetearGUI();
            }
            else if(conexionBD != null)
            {
                conexionBD.registrarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue registrado de forma correcta");
                frm_MantenimientoEstudiantes.resetearGUI();
            }
            
        }
        if(e.getActionCommand().equals("Consultar") || e.getActionCommand().equals("Consulta_Rapida"))
        { 
            if(metodosEstudiantes != null)
            {
                buscarArchivosPlanos();
            }
            else if(metodos_XML_Estudiantes != null)
            {
                buscarXML();
            }
            else if(conexionBD != null)
            {
                buscarBaseDeDatos();
            }
        }
        if(e.getActionCommand().equals("Modificar"))
        {
            if(metodosEstudiantes != null)
            {
                metodosEstudiantes.modificarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                frm_MantenimientoEstudiantes.resetearGUI();
            }
            else if(conexionBD != null)
            {
                conexionBD.modificarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                frm_MantenimientoEstudiantes.resetearGUI();
            }
            else if(metodos_XML_Estudiantes != null)
            {
                metodos_XML_Estudiantes.modificarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.resetearGUI();
                frm_MantenimientoEstudiantes.mostrarMensaje("Se han modificado los datos");
            }
        }
        if(e.getActionCommand().equals("Eliminar"))
        {
            if(metodosEstudiantes != null)
            {
                metodosEstudiantes.eliminarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
                frm_MantenimientoEstudiantes.resetearGUI();
            }
            else if(conexionBD != null)
            {
                conexionBD.eliminarEstudiante(frm_MantenimientoEstudiantes.devolverCedula());
                frm_MantenimientoEstudiantes.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
                frm_MantenimientoEstudiantes.resetearGUI();
            }
            else if(metodos_XML_Estudiantes != null)
            {
                metodos_XML_Estudiantes.eliminarEstudiante(frm_MantenimientoEstudiantes.devolverInformacion());
                frm_MantenimientoEstudiantes.resetearGUI();
                frm_MantenimientoEstudiantes.mostrarMensaje("Se ha elimiando el estudiante");
            }
        }
    }
    public void buscarArchivosPlanos()
    {
        if(metodosEstudiantes.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula()))
            {
                frm_MantenimientoEstudiantes.mostrarInformacion(metodosEstudiantes.getArregloInformacion());
                frm_MantenimientoEstudiantes.habilitarEdicion();
            }
            else
            {
                frm_MantenimientoEstudiantes.mostrarMensaje("La cédula buscada no se encuentra.");
                frm_MantenimientoEstudiantes.habilitarAgregar();
            }
    }
    
    public void buscarBaseDeDatos()
    {
        if(conexionBD.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula()))
            {
                frm_MantenimientoEstudiantes.mostrarInformacion(conexionBD.arregloInformacionEstudiante);
                frm_MantenimientoEstudiantes.habilitarEdicion();
            }
            else
            {
                frm_MantenimientoEstudiantes.mostrarMensaje("La cédula buscada no se encuentra.");
                frm_MantenimientoEstudiantes.habilitarAgregar();
            }
    }
    
    public void buscarXML()
    {
        if(metodos_XML_Estudiantes.consultarEstudiante(frm_MantenimientoEstudiantes.devolverCedula()))
            {
                frm_MantenimientoEstudiantes.mostrarInformacion(metodos_XML_Estudiantes.getArregloInformacion());
                frm_MantenimientoEstudiantes.habilitarEdicion();
            }
            else
            {
                frm_MantenimientoEstudiantes.mostrarMensaje("La cédula buscada no se encuentra.");
                frm_MantenimientoEstudiantes.habilitarAgregar();
            }
    }
    
    public void crearArchivo()
    {
      archivosEstudiantes.leerEnElArchivo();
        
        if(archivosEstudiantes.devolverContador()==0)
       {
           archivosEstudiantes.crearArchivo();
       }
       
       else
       {
           metodosEstudiantes.leerArchivo(archivosEstudiantes.leerEnElArchivo());
       }
    }
   
    
    public void escribirInformacionEnElArchivo()
    {
        archivosEstudiantes.crearArchivo();
        ArrayList <Estudiante> lista = metodosEstudiantes.devolverLista();
        
        for (int i = 0; i < lista.size(); i++) 
        {
            archivosEstudiantes.escribirEnElArchivo(lista.get(i));
        }
    } 
}
