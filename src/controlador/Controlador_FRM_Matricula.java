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
import modelo.ArchivoMatriculas;
import modelo.Matricula;
import modelo.MetodosCursos;
import modelo.MetodosEstudiantes;
import modelo.MetodosMatricula;
import modelo.Metodos_XML_Matricula;
import vista.FRM_Login;
import vista.FRM_MantenimientoCursos;
import vista.FRM_MantenimientoEstudiantes;
import vista.FRM_Matricula;

public class Controlador_FRM_Matricula implements ActionListener{
    
    MetodosCursos metodosCursos;
    MetodosEstudiantes metodosEstudiantes;
    public MetodosMatricula metodosMatricula;
    FRM_Matricula frm_matricula;
    boolean encontroEstudiante=false;
    boolean encontroCurso=false;
    ArchivoMatriculas archivoMatricula;
    FRM_Login frm_Login;
    Metodos_XML_Matricula metodos_XML;
    ConexionBD conexionBD;
    
    public Controlador_FRM_Matricula(FRM_MantenimientoEstudiantes frm_MantenimientoEstufiantes,FRM_MantenimientoCursos frm_MantenimientoCursos,FRM_Matricula frm_matricula)
    {
        this.metodosCursos=frm_MantenimientoCursos.controlador.metodosCursos;
        this.metodosEstudiantes=frm_MantenimientoEstufiantes.controlador_FRM_MantenimientoEstudiantes.metodosEstudiantes;
        this.frm_matricula=frm_matricula;
        metodosMatricula=new MetodosMatricula(metodosEstudiantes,metodosCursos,this, frm_matricula);
        archivoMatricula= new ArchivoMatriculas();
        conexionBD = new ConexionBD();
        metodos_XML = new Metodos_XML_Matricula(frm_matricula);
        crearArchivo();
    }
    public Controlador_FRM_Matricula(FRM_MantenimientoEstudiantes frm_MantenimientoEstudiantes, FRM_MantenimientoCursos frm_MantenimientoCursos,
            FRM_Matricula frm_Matricula, ConexionBD conexion)          
    {
        this.conexionBD=conexion;
        this.frm_matricula=frm_Matricula;
    }
    public void actionPerformed(ActionEvent e)
    {
        // CONDICIONES PARA ARCHIVOS PLANOS
        if(e.getActionCommand().equals("ConsultaRapidaCedula"))
        {
            if(metodosEstudiantes.consultarEstudiante(frm_matricula.devolverCedula()))
            {
                String arreglo[]=metodosEstudiantes.getArregloInformacion();
                frm_matricula.colocarNombreEstudiante(arreglo[0]);
                encontroEstudiante=true;
            }
            else
            {
                frm_matricula.mostrarMensaje("El estudiante no se encuentra, favor dirigirse al módulo de Mantenimiento Estudiantes");
            }
        }
        if(e.getActionCommand().equals("ConsultaRapidaSigla"))
        {
            if(metodosCursos.consultarCurso(frm_matricula.devolverSigla()))
            {
                String arreglo[]=metodosCursos.getArregloInformacion();
                frm_matricula.colocarNombreCurso(arreglo[0]);
                encontroCurso=true;
            }
            else
            {
                frm_matricula.mostrarMensaje("El curso no se encuentra, favor dirigirse al módulo de Mantenimiento Cursos");
            }
        }
        if(e.getActionCommand().equals("Agregar"))
        {
            frm_matricula.agregarInformacionTabla();
            frm_matricula.limpiarSigla();
        }
        if(e.getActionCommand().equals("Finalizar"))
        {
            String arreglo[]=new String[3];
            for(int contador=0;contador<frm_matricula.getCantidadFilas();contador++)
            {
                arreglo[0]=frm_matricula.devolverCodigo();
                arreglo[1]=frm_matricula.devolverDato(contador,1);
                arreglo[2]=frm_matricula.devolverDato(contador,3);
                metodosMatricula.agregarMatricula(arreglo);
            }
            frm_matricula.colocarCodigo();
            frm_matricula.resetearVentana();
            encontroEstudiante=false;
            encontroCurso=false;
        }
        if(e.getActionCommand().equals("Consultar"))
        {
            if(metodosMatricula.consultarMatricula(frm_matricula.devolverCodigo()))
            {
                frm_matricula.habilitarOpciones();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"La matricula buscada NO existe");
            }
        }
        if(e.getActionCommand().equals("Eliminar"))
        {
            metodosMatricula.eliminarMatricula(frm_matricula.getMatriculaSeleccionada());
            frm_matricula.colocarCodigo();
            frm_matricula.resetearVentana();
        }
        if(e.getActionCommand().equals("Modificar"))
        {
            frm_matricula.colocarCodigo();
            frm_matricula.resetearVentana();
        }
        verificarConsultas();
        
        // CONDICIONES PARA BASES DE DATOS
//        if(e.getActionCommand().equals("ConsultaRapidaCedula"))
//        {
//            if(conexionBD.consultarEstudiante(frm_matricula.devolverCedula()))
//            {
//                String arreglo[]=conexionBD.arregloInformacionEstudiante;
//                frm_matricula.colocarNombreEstudiante(arreglo[0]);
//                encontroEstudiante=true;
//            }
//            else
//            {
//                frm_matricula.mostrarMensaje("El estudiante no se encuentra, favor dirigirse al módulo de Mantenimiento Estudiantes");
//            }
//        }
//        if(e.getActionCommand().equals("ConsultaRapidaSigla"))
//        {
//            if(conexionBD.consultarCurso(frm_matricula.devolverSigla()))
//            {
//                String arreglo[]=conexionBD.arregloInformacionCurso;
//                frm_matricula.colocarNombreCurso(arreglo[0]);
//                encontroCurso=true;
//            }
//            else
//            {
//                frm_matricula.mostrarMensaje("El curso no se encuentra, favor dirigirse al módulo de Mantenimiento Cursos");
//            }
//        }
//        if(e.getActionCommand().equals("Agregar"))
//        {
//            frm_matricula.agregarInformacionTabla();
//            frm_matricula.limpiarSigla();
//        }
//        if(e.getActionCommand().equals("Finalizar"))
//        {
//            String arreglo[]=new String[3];
//            for(int contador=0;contador<frm_matricula.getCantidadFilas();contador++)
//            {
//                arreglo[0]=frm_matricula.devolverCodigo();
//                arreglo[1]=frm_matricula.devolverDato(contador,1);
//                arreglo[2]=frm_matricula.devolverDato(contador,3);
//                conexionBD.registrarMatricula(arreglo);
//            }
//            frm_matricula.colocarCodigo();
//            frm_matricula.resetearVentana();
//            encontroEstudiante=false;
//            encontroCurso=false;
//        }
//        if(e.getActionCommand().equals("Consultar"))
//        {
//            if(conexionBD.consultarMatriculaDetallada(frm_matricula.devolverCodigo(), frm_matricula))
//            {
//                frm_matricula.habilitarOpciones();
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(null,"La matricula buscada NO existe");
//            }
//        }
//        if(e.getActionCommand().equals("Eliminar"))
//        {
//            String codigo=frm_matricula.devolverCodigo();
//            String sigla=frm_matricula.devolverSigla();
//            conexionBD.eliminarMatriculaDetallada(codigo);
//            frm_matricula.colocarCodigo();
//            frm_matricula.resetearVentana();
//        }
//        if(e.getActionCommand().equals("Modificar"))
//        {
//            frm_matricula.colocarCodigo();
//            frm_matricula.resetearVentana();
//        }
//        verificarConsultas();
        
        // CONDICIONES PARA XML
    }
    public void verificarConsultas()
    {
        if(encontroEstudiante && encontroCurso)
        {
            this.frm_matricula.habilitarAgregar();
        }
    }
    
     public void crearArchivo()
    {
      archivoMatricula.leerEnElArchivo();
        
        if(archivoMatricula.devolverContador()==0)
       {
           archivoMatricula.crearArchivo();
       }
       
       else
       {
           metodosMatricula.leerArchivo(archivoMatricula.leerEnElArchivo());
       }
    }
   
    
    public void escribirInformacionEnElArchivo()
    {
        archivoMatricula.crearArchivo();
        ArrayList <Matricula> lista = metodosMatricula.devolverLista();
        
        for (int i = 0; i < lista.size(); i++) 
        {
               archivoMatricula.escribirEnElArchivo(lista.get(i));
            }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
