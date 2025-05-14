
package codigo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class mostrarservicio {
    
    
    //variables
    int id_servicio;
    String nombre_servicio;
    Double precio;
    String descripcion;
    
    //getters and setters

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void mostrarservicio(JTable parammostrarservicio){
        
        
        conexion objetoconnectionusuarios = new conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Sentencia sql
        
        String sql = "";
        
        modelo.addColumn("id_servicio");
        modelo.addColumn("nombre_servicio");
        modelo.addColumn("precio");
        modelo.addColumn("descripcion");
        
        parammostrarservicio.setModel(modelo);
        
        sql = "select * from servicio;";
        
        String [] datos = new String[4];
        Statement st;
        
        try {
            
            st=objetoconnectionusuarios.establecerconexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                //id
                datos[0]=rs.getString(1);
                //servicio
                datos[1]=rs.getString(2);
                //precio
                datos[2]=rs.getString(3);
                //desc
                datos[3]=rs.getString(4);

                
                modelo.addRow(datos);
            }
            
            parammostrarservicio.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error line :"+e.toString());
        }
        
}
    
    //Class insert 
    
    public void insertarservicio(JTextField paramnombreservicio,JTextField paramprecio,JTextField paramdescripcion){
    
        setNombre_servicio(paramnombreservicio.getText());
        setPrecio(Double.parseDouble(paramprecio.getText()));
        setDescripcion(paramdescripcion.getText());
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "insert into servicio(nombre_servicio,precio,descripcion)values(?,?,?);";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            cs.setString(1, getNombre_servicio());
            cs.setDouble(2, getPrecio());
            cs.setString(3, getDescripcion());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Inserción Completada");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error de inserción:"+e.toString());
        }
        
    }
    
    //Class Seleccionar
    
    public void seleccionarservicio(JTable parammostrarservicio,JTextField paramidservicio,JTextField paramnombreservicio,JTextField paramprecio,JTextField paramdescripcion) {
    
        try {
            
            int fila = parammostrarservicio.getSelectedRow();
            
            if(fila>=0){
            
                paramidservicio.setText(parammostrarservicio.getValueAt(fila,0).toString());
            
                paramnombreservicio.setText(parammostrarservicio.getValueAt(fila,1).toString());
                
                paramprecio.setText(parammostrarservicio.getValueAt(fila,2).toString());
                
                paramdescripcion.setText(parammostrarservicio.getValueAt(fila,3).toString());

         
            }
            else{
            JOptionPane.showMessageDialog(null,"Fila no seleccionada");
            }
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
    
    }
    
    //Class Update
    
    public void actualizarservicio(JTextField paramidservicio,JTextField paramnombreservicio,JTextField paramprecio,JTextField paramdescripcion){
    
        setId_servicio(Integer.parseInt(paramidservicio.getText()));    
        setNombre_servicio(paramnombreservicio.getText());
        setPrecio(Double.parseDouble(paramprecio.getText()));
        setDescripcion(paramdescripcion.getText());
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "update servicio set nombre_servicio=?,precio=?,descripcion=? where servicio.id_servicio=?";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            
            cs.setString(1, getNombre_servicio());
            cs.setDouble(2, getPrecio());
            cs.setString(3, getDescripcion());
            cs.setInt(4, getId_servicio());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Actualización Completada");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    }
    
    //Class Delete
    
        public void eliminarservicio(JTextField paramidservicio){
    
        setId_servicio(Integer.parseInt(paramidservicio.getText()));
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "delete from servicio where servicio.id_servicio=?";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            cs.setInt(1, getId_servicio());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Eliminado");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    }
    
    
}
