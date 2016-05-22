
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Metodos_XML_Estudiantes;
import modelo.Metodos_XML_Usuarios;
import vista.FRM_MantenimientoCursos;
import vista.FRM_MantenimientoEstudiantes;
import vista.FRM_Matricula;
import vista.FRM_VentanaPrincipal;
 
public class Controlador_FRM_VentanaPrincipal implements ActionListener{

    FRM_MantenimientoEstudiantes frm_MantenimientoEstufiantes;
    FRM_MantenimientoCursos frm_MantenimientoCursos;
    FRM_Matricula frm_Matricula;
    
    public Controlador_FRM_VentanaPrincipal()
    {
        frm_MantenimientoEstufiantes= new FRM_MantenimientoEstudiantes();
        frm_MantenimientoCursos= new FRM_MantenimientoCursos();
       // frm_Matricula =new FRM_Matricula(frm_MantenimientoEstufiantes,frm_MantenimientoCursos);
    }
    /*CONSTRUCTOR  XML. */
    public Controlador_FRM_VentanaPrincipal(Metodos_XML_Usuarios metodos_XML_Usuarios)
    {
        Metodos_XML_Estudiantes xML_Estudiantes = new Metodos_XML_Estudiantes();
        frm_MantenimientoEstufiantes = new FRM_MantenimientoEstudiantes(xML_Estudiantes);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Salir"))
        {
            System.exit(0);
        }
        if(e.getActionCommand().equals("Estudiantes"))
        {
            frm_MantenimientoEstufiantes.setVisible(true);    
        }
        if(e.getActionCommand().equals("Cursos"))
        {
            frm_MantenimientoCursos.setVisible(true);
        }
        if(e.getActionCommand().equals("Matricula"))
        {
            frm_Matricula.setVisible(true);
            frm_Matricula.colocarCodigo();
        }
    
    }    
}