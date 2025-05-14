/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author torzz
 */
public class mostrarpagos {
    
    int id_pago; 
    int id_cliente;
    String dueño;
    String nombre_servicio;
    Double precio; 
    Double pago; 
    int id_servicio;

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }
    

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
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

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }
    
 //Class Select 
    public void mostrarpago(JTable parammostrarpago){
        
        conexion objetoconnectionusuarios = new conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Sentencia sql
        
        String sql = "";
        
        modelo.addColumn("id_pago");
        modelo.addColumn("id_servicio");
        modelo.addColumn("fecha");
        modelo.addColumn("id_cliente");
        modelo.addColumn("dueño");
        modelo.addColumn("nombre_servicio");
        modelo.addColumn("precio");
        modelo.addColumn("pago");
        
        parammostrarpago.setModel(modelo);
        
        sql = "SELECT * FROM vista_pago;";
        
        String [] datos = new String[8];
        Statement st;
        
        try {
            
            st=objetoconnectionusuarios.establecerconexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                //id
                datos[0]=rs.getString(1);
                //dueño
                datos[1]=rs.getString(2);
                //nombre
                datos[2]=rs.getString(3);
                //raza
                datos[3]=rs.getString(4);
                //edad
                datos[4]=rs.getString(5);
                //alergia
                datos[5]=rs.getString(6);
                
                datos[6]=rs.getString(7);
                
                datos[7]=rs.getString(8);
                
                modelo.addRow(datos);
            }
            
            parammostrarpago.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
}
    
    //Cambio
    
    public void mostrarcambio(JTable parammostrarcambio){
        
        conexion objetoconnectionusuarios = new conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Sentencia sql
        
        String sql = "";
        
        modelo.addColumn("cambio");
      
        
        parammostrarcambio.setModel(modelo);
        
        sql = "SELECT s.precio - p.pago AS cambio\n" +
"FROM pago p\n" +
"JOIN servicio s ON p.id_servicio = s.id_servicio\n" +
"ORDER BY p.id_pago DESC\n" +
"LIMIT 1;";
        
        String [] datos = new String[1];
        Statement st;
        
        try {
            
            st=objetoconnectionusuarios.establecerconexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                datos[0]=rs.getString(1);
                
                
                modelo.addRow(datos);
            }
            
            parammostrarcambio.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
}
    
    
    //mostrar servicios
    public void mostrarserviciomini(JTable parammostrarserviciomini){
        
        conexion objetoconnectionusuarios = new conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Sentencia sql
        
        String sql = "";
        
        
        modelo.addColumn("id_servicio");
        modelo.addColumn("nombre_servicio");
        
        parammostrarserviciomini.setModel(modelo);
        
        sql = "SELECT id_servicio,nombre_servicio FROM servicio;";
        
        String [] datos = new String[2];
        Statement st;
        
        try {
            
            st=objetoconnectionusuarios.establecerconexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                datos[0]=rs.getString(1);
                
                datos[1]=rs.getString(2);
                
                
                modelo.addRow(datos);
            }
            
            parammostrarserviciomini.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
}
    
     //Class insert 
    
    public void insertarpago(JTextField paramid_cliente,JTextField paramid_servicio,JTextField parampago){
    
       
        setId_cliente(Integer.parseInt(paramid_cliente.getText()));
        setId_servicio(Integer.parseInt(paramid_servicio.getText()));
        setPago(Double.parseDouble(parampago.getText()));
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "INSERT INTO pago (id_cliente, id_servicio, pago)VALUES (?,?,?);";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            cs.setInt(1, getId_cliente());
            cs.setInt(2, getId_servicio());
            cs.setDouble(3, getPago());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Inserción Completada");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    }
    
    
    //Class Seleccionar
    
    public void seleccionarpago(JTable parammostrarpago,JTextField paramid_pago,JTextField paramid_cliente,JTextField paramid_servicio,JTextField parampago) {
    
        try {
            
            int fila = parammostrarpago.getSelectedRow();
            
            if(fila>=0){
            
                paramid_pago.setText(parammostrarpago.getValueAt(fila,0).toString());
            
                paramid_cliente.setText(parammostrarpago.getValueAt(fila,3).toString());
                
                paramid_servicio.setText(parammostrarpago.getValueAt(fila,1).toString());
                
                parampago.setText(parammostrarpago.getValueAt(fila,7).toString());
   
                
            }
            else{
            JOptionPane.showMessageDialog(null,"Fila no seleccionada");
            }
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
    
    }
    
    //Class Update
    
    public void actualizarpago(JTextField paramid_pago,JTextField paramid_cliente,JTextField paramid_servicio,JTextField parampago){
    
        setId_pago(Integer.parseInt(paramid_pago.getText()));
        setId_cliente(Integer.parseInt(paramid_cliente.getText()));
        setId_servicio(Integer.parseInt(paramid_servicio.getText()));
        setPago(Double.parseDouble(parampago.getText()));
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "update pago set id_cliente=?,id_servicio=?,pago=? where id_pago=?";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            
            cs.setInt(1, getId_cliente());
            cs.setInt(2, getId_servicio());
            cs.setDouble(3, getPago());
            cs.setInt(4, getId_pago());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Actualización Completada");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    }
    
     //Class Delete
    
        public void eliminarpago(JTextField paramid_pago){
    
        setId_pago(Integer.parseInt(paramid_pago.getText()));
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "delete from pago where id_pago=?";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            cs.setInt(1, getId_pago());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Eliminado");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    }
    
    
}
