/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ArchivoCursos {
    
    ObjectOutputStream archivoSalida;
    ObjectInputStream archivoEntrada;
    ArrayList<Cursos> arrayCursos;
    int contador = 0;

    public ArchivoCursos() {
        
    }
    
    public void crearArchivo()
    {
        try
        {
            archivoSalida = new ObjectOutputStream(new FileOutputStream("cursos.dat"));
            System.out.println("Archivo creado");
           
        }
        catch(Exception e)
        {
            System.out.println("Error al crear archivo");
            
        }
    }
    
    public void escribirEnElArchivo(Cursos curso)
    {
        try
        {
            archivoSalida.reset();
            archivoSalida.writeObject(curso);
            System.out.println("Curso ingresado");
           
        }
        catch(Exception e)
        {
            System.out.println("Error al escribir en archivo"+e);
        }
    }
    
    public ArrayList<Cursos> leerEnElArchivo()
    {
        arrayCursos = new ArrayList<Cursos>();
        try
        {
            archivoEntrada = new ObjectInputStream(new FileInputStream("cursos.dat"));
            
            while(true)
            {
                arrayCursos.add((Cursos)archivoEntrada.readObject());
                contador++;
            }
        }
        catch(Exception e)
        {
            System.out.println("Fin del archivo"+e);
        }
        return arrayCursos;
    }
    
    
   public int devolverContador()
   {
       return contador;
   }
   
}
