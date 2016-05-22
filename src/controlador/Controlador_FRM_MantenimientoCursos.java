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
import modelo.ArchivoCursos;
import modelo.Cursos;
import modelo.MetodosCursos;
import modelo.Metodos_XML_Cursos;
import vista.FRM_Login;
import vista.FRM_MantenimientoCursos;
import vista.FRM_VentanaPrincipal;

public class Controlador_FRM_MantenimientoCursos implements ActionListener{

    FRM_MantenimientoCursos frm_mantenimientoCursos;
    public MetodosCursos metodosCursos;
    ArchivoCursos archivoCursos;
    FRM_Login frm_Login;
    FRM_VentanaPrincipal frm_ventanaPrincipal;
    ConexionBD conexionBD = null;
    public Metodos_XML_Cursos metodos_XML_Cursos = null;
    
     public Controlador_FRM_MantenimientoCursos(FRM_MantenimientoCursos frm_MantenimientoCurso, MetodosCursos metodosCursos)
    {
        this.metodosCursos = metodosCursos;
        this.frm_mantenimientoCursos=frm_mantenimientoCursos;
        archivoCursos = new ArchivoCursos();
        crearArchivo();
        
    }
    public Controlador_FRM_MantenimientoCursos(FRM_MantenimientoCursos frm_MantenimientoCurso, Metodos_XML_Cursos metodos_XML_Cursos)
    {
        this.frm_mantenimientoCursos=frm_MantenimientoCurso;
        this.metodos_XML_Cursos = metodos_XML_Cursos;
    }
    public Controlador_FRM_MantenimientoCursos(FRM_MantenimientoCursos frm_MantenimientoCurso, ConexionBD conexionBD)
    {
        this.frm_mantenimientoCursos=frm_MantenimientoCurso;
        this.conexionBD = conexionBD;
    }
    
    public void actionPerformed(ActionEvent e)
    {        
        //CONDICIONES
        if(e.getActionCommand().equals("Agregar"))
        {
            if(metodosCursos != null)
            {
                metodosCursos.agregarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue registrado de forma correcta");
                frm_mantenimientoCursos.resetearGUI();
            }
            else if(metodos_XML_Cursos != null)
            {
                metodos_XML_Cursos.agregarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue registrado de forma correcta");
                frm_mantenimientoCursos.resetearGUI();
            }
            else if(conexionBD != null)
            {
                conexionBD.registrarEstudiante(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue registrado de forma correcta");
                frm_mantenimientoCursos.resetearGUI();
            }
            
        }
        if(e.getActionCommand().equals("Consultar") || e.getActionCommand().equals("Consulta_Rapida"))
        { 
            if(metodosCursos != null)
            {
                buscarArchivoPlano();
            }
            else if(metodos_XML_Cursos != null)
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
            if(metodosCursos != null)
            {
                metodosCursos.modificarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                frm_mantenimientoCursos.resetearGUI();
            }
            else if(conexionBD != null)
            {
                conexionBD.modificarEstudiante(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue modificado de forma correcta.");
                frm_mantenimientoCursos.resetearGUI();
            }
            else if(metodos_XML_Cursos != null)
            {
                metodos_XML_Cursos.modificarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.resetearGUI();
                frm_mantenimientoCursos.mostrarMensaje("Se han modificado los datos");
            }
        }
        if(e.getActionCommand().equals("Eliminar"))
        {
            if(metodosCursos != null)
            {
                metodosCursos.eliminarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
                frm_mantenimientoCursos.resetearGUI();
            }
            else if(conexionBD != null)
            {
                conexionBD.eliminarEstudiante(frm_mantenimientoCursos.devolverSigla());
                frm_mantenimientoCursos.mostrarMensaje("El estudiante fue eliminado de forma correcta.");
                frm_mantenimientoCursos.resetearGUI();
            }
            else if(metodos_XML_Cursos != null)
            {
                metodos_XML_Cursos.eliminarCurso(frm_mantenimientoCursos.devolverInformacion());
                frm_mantenimientoCursos.resetearGUI();
                frm_mantenimientoCursos.mostrarMensaje("Se ha elimiando el estudiante");
            }
        }
    
    }
    public void buscarArchivoPlano()
    {
        if(metodosCursos.consultarCurso(frm_mantenimientoCursos.devolverSigla()))
            {
                frm_mantenimientoCursos.mostrarInformacion(metodosCursos.getArregloInformacion());
                frm_mantenimientoCursos.habilitarEdicion();
            }
            else
            {
                frm_mantenimientoCursos.mostrarMensaje("La sigla buscada no se encuentra.");
                frm_mantenimientoCursos.habilitarAgregar();
            }
    }
    
    public void buscarBaseDeDatos()
    {
      if(conexionBD.consultarCurso(frm_mantenimientoCursos.devolverSigla()))
            {
                frm_mantenimientoCursos.mostrarInformacion(conexionBD.arregloInformacionCurso);
                frm_mantenimientoCursos.habilitarEdicion();
            }
            else
            {
                frm_mantenimientoCursos.mostrarMensaje("La sigla buscada no se encuentra.");
                frm_mantenimientoCursos.habilitarAgregar();
            }
    }
    
    public void buscarXML() 
    {
        if(metodos_XML_Cursos.consultarCurso(frm_mantenimientoCursos.devolverSigla()))
            {
                frm_mantenimientoCursos.mostrarInformacion(metodos_XML_Cursos.getArregloInformacion());
                frm_mantenimientoCursos.habilitarEdicion();
            }
            else
            {
                frm_mantenimientoCursos.mostrarMensaje("La sigla buscada no se encuentra.");
                frm_mantenimientoCursos.habilitarAgregar();
            }
    }
  
    ///*****Archivos***////
    public void crearArchivo()
    {
      archivoCursos.leerEnElArchivo();
        
       if(archivoCursos.devolverContador()==0)
       {
           archivoCursos.crearArchivo();
       }
       else
       {
           metodosCursos.leerArchivo(archivoCursos.leerEnElArchivo());
       }
    }
    public void escribirInformacionEnElArchivo()
    {
        archivoCursos.crearArchivo();
        ArrayList <Cursos> lista = metodosCursos.devolverLista();
        
        for (int i = 0; i < lista.size(); i++) 
        {
            archivoCursos.escribirEnElArchivo(lista.get(i));
        }
    }
    
    
}
