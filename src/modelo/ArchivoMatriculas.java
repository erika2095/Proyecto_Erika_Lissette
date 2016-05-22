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

public class ArchivoMatriculas {
    
     ObjectOutputStream archivoSalida;
    ObjectInputStream archivoEntrada;
    int contador=0;
    
    public void crearArchivo()
    {
        try
        {
            archivoSalida=new ObjectOutputStream(new FileOutputStream("matriculas.dat"));
            System.out.println("Archivo creado");
        }
        
        catch(Exception e)
        {
            System.out.println("Error al crear la matricula"+e);

        }
    }
    
    public void escribirEnElArchivo(Matricula matricula)
    {
        try
        {
            archivoSalida.writeObject(matricula);
            System.out.println("Matricula agregada");

        }
        
        catch(Exception e)
        {
            System.out.println("Error al agregar la matricula "+e);

        }
    }
    
    public ArrayList<Matricula> leerEnElArchivo()
    {
        ArrayList<Matricula> lista = new ArrayList<Matricula>();
        
        try
        {
            archivoEntrada=new ObjectInputStream(new FileInputStream("matriculas.dat"));
            
            while(true)
            {
                lista.add((Matricula) archivoEntrada.readObject());
                contador++;
            }
        }
        catch(Exception e)
        {
             System.out.println("Fin del archivo "+e);

        }
        return lista;
    }
    
    public int devolverContador()
    {
        return contador;
    }
    
}
