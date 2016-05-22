
package modelo;

import vista.FRM_MantenimientoEstudiantes;

public class Verificar 
{
    FRM_MantenimientoEstudiantes ventanaEstudiante;
    public Verificar()
    {
        
    }
    
    public boolean verificarNumero(String texto)
    {
        boolean esNumero=true;
        for(int contador=0;contador<texto.length();contador++)
        {
            if(Character.isLetter(texto.charAt(contador)))
            {
                esNumero=false; 
            }
        }
        return esNumero;
    }
    
    public boolean verificarTexto(String texto) // revisar
    {
        boolean esNumero=true;
        
        for(int contador=0;contador<texto.length();contador++)
        {
            if(Character.isLetter(texto.charAt(contador)))
            {
                esNumero=false;
                System.out.println("Solo contiene letras"); 
            }
            else
            {
                System.out.println("Contiene numeros");
            }
        }
        return esNumero;
    }
    
    public boolean verificarSimbolo(String texto)
    {
        if(texto.contains("@") && texto.contains("."))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
