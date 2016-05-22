/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

public class MetodosCursos {
    
    private ArrayList <Cursos> arrayCursos;
    ArchivoCursos archivoCurso;
   
    String arregloInformacionConsultada[]=new String[3];
    
    public MetodosCursos()
    {
        arrayCursos=new ArrayList <Cursos>();
        archivoCurso = new ArchivoCursos();
    }
    public void escribirEnArchivo() {
        archivoCurso.crearArchivo();
        for (int contador = 0; contador < arrayCursos.size(); contador++) {
            archivoCurso.escribirEnElArchivo(arrayCursos.get(contador));
        }
    }
    public void agregarCurso(String informacion[])
    {
        Cursos temporal=new Cursos(informacion[0], informacion[1], Integer.parseInt(informacion[2]), informacion[3]);
        arrayCursos.add(temporal);
        
    }
    public void mostrarInformacion()
    {
        for(int contador=0;contador<arrayCursos.size();contador++)
        {
            //System.out.println(arrayCursos.get(contador).getInformacion());
        
        }
    
    }
    public boolean consultarCurso(String sigla)
    {
        boolean existe=false;
        
        for(int contador=0;contador<arrayCursos.size();contador++)
        {
            if(arrayCursos.get(contador).getSigla().equals(sigla))
            {
                arregloInformacionConsultada[0]=arrayCursos.get(contador).getNombre();
                arregloInformacionConsultada[1]=""+arrayCursos.get(contador).getCreditos();
                arregloInformacionConsultada[2]=arrayCursos.get(contador).getHorario();
                existe=true;
            }
        
        }
        return existe;
    
    }
    public void modificarCurso(String arreglo[])
    {
        for(int contador=0;contador<arrayCursos.size();contador++)
        {
            if(arrayCursos.get(contador).getSigla().equals(arreglo[0]))
            {
                arrayCursos.get(contador).setNombre(arreglo[1]);
                arrayCursos.get(contador).setCreditos(Integer.parseInt(arreglo[2]));
                arrayCursos.get(contador).setHorario(arreglo[3]);
            }
        }
    }
    public void eliminarCurso(String arreglo[])
    {
        for(int contador=0;contador<arrayCursos.size();contador++)
        {
            if(arrayCursos.get(contador).getSigla().equals(arreglo[0]))
            {
                arrayCursos.remove(contador);
            }
        }
    }
    public String[] getArregloInformacion()
    {
        return this.arregloInformacionConsultada;
    } 
    
    ///****Archivos**////
    public ArrayList<Cursos> getInformacionEnCursos()
    {
        return arrayCursos;
    }
    
    public void guardarInformacionDeArchivos(ArrayList<Cursos> lista)
    {
        for (int i = 0; i < lista.size(); i++) {
            arrayCursos.add(lista.get(i));
            
        }
    }
    
   public ArrayList<Cursos> devolverLista()
   {
       return arrayCursos;
   }
   
   public void leerArchivo(ArrayList<Cursos> lista)
   {
       for (int i = 0; i < lista.size(); i++)
       {
           arrayCursos.add(lista.get(i));
       }
   }
    
    
}
