
package vista;

import BaseDatos.ConexionBD;
import controlador.Controlador_FRM_MantenimientoEstudiantes;
import javax.swing.JOptionPane;
import modelo.MetodosEstudiantes;
import modelo.Metodos_XML_Estudiantes;

public class FRM_MantenimientoEstudiantes extends javax.swing.JFrame {

   
    public Controlador_FRM_MantenimientoEstudiantes controlador_FRM_MantenimientoEstudiantes;
    FRM_Login frm_login;
    String guardar;
    
    public FRM_MantenimientoEstudiantes() 
    {
        initComponents();
        setVisible(false);
        this.setLocation(250, 200);
        this.panel_Botones1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
        this.panel_InformacionBasica1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
    }
     /* CONSTRUCTOR ARCHIVOS*/
    public FRM_MantenimientoEstudiantes(MetodosEstudiantes metodosEstudiante) {
        initComponents();
        setVisible(false);
        this.setLocation(250, 200);
        
        controlador_FRM_MantenimientoEstudiantes = new Controlador_FRM_MantenimientoEstudiantes(this, metodosEstudiante);
        this.panel_Botones1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
        this.panel_InformacionBasica1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
    }
    /*CONSTRUCTOR XML. */
    public FRM_MantenimientoEstudiantes(Metodos_XML_Estudiantes xML_Estudiantes)
    {
        initComponents();
        setVisible(false);
        this.setLocation(250, 200);
        controlador_FRM_MantenimientoEstudiantes = new Controlador_FRM_MantenimientoEstudiantes(this, xML_Estudiantes);
        
        this.panel_Botones1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
        this.panel_InformacionBasica1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
    }
    
    /*CONSTRUCTOR BD*/
    public FRM_MantenimientoEstudiantes(ConexionBD conexionBD)
    {
        initComponents();
        setVisible(false);
        this.setLocation(250, 200);
        controlador_FRM_MantenimientoEstudiantes = new Controlador_FRM_MantenimientoEstudiantes(this, conexionBD);
        
        this.panel_Botones1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
        this.panel_InformacionBasica1.agregarEventos(controlador_FRM_MantenimientoEstudiantes);
    }
    
    public void dondeGuardar(String guardar)
    {
        this.guardar = guardar;
    }
    
    public String guardar()
    {
        return guardar;
    }
    public String[] devolverInformacion()
    {
        return this.panel_InformacionBasica1.devolverInformacion();
    }
    public String devolverCedula()
    {
        return this.panel_InformacionBasica1.devolverCedula();
    
    }
    public void mostrarInformacion(String arreglo[])
    {
        this.panel_InformacionBasica1.mostrarInformacion(arreglo);
    }
    public void mostrarMensaje(String mensaje)
    {
        JOptionPane.showMessageDialog(null,mensaje);
    }
    public void habilitarAgregar()
    {
        this.panel_Botones1.habilitarAgregar();
        this.panel_InformacionBasica1.habilitarCampos();
    }
    public void resetearGUI()
    {
        this.panel_Botones1.deshabilitarBotones();
        this.panel_InformacionBasica1.deshabilitarCampos();
    }
    public void habilitarEdicion()
    {
        this.panel_Botones1.habilitarEdicion();
        this.panel_InformacionBasica1.habilitarEdicion();
    }
    public void limpiar(){
        this.panel_InformacionBasica1.limpiar();
    }
    public void habilitarOpciones(){
        this.panel_InformacionBasica1.habilitarOpciones();
    }
    
    public void deshabilitarCedula(){
        this.panel_InformacionBasica1.deshabilitarCedula();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Botones1 = new vista.Panel_Botones();
        panel_InformacionBasica1 = new vista.Panel_InformacionBasica();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_InformacionBasica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_InformacionBasica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_Botones1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
        controlador_FRM_MantenimientoEstudiantes.escribirInformacionEnElArchivo();
    }//GEN-LAST:event_formComponentHidden

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.Panel_Botones panel_Botones1;
    private vista.Panel_InformacionBasica panel_InformacionBasica1;
    // End of variables declaration//GEN-END:variables
}