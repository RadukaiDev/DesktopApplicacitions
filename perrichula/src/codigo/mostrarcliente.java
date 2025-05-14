
package codigo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class mostrarcliente {
    
    int id_cliente;
    String dueño;
    String nombre;
    String raza;
    int edad;
    String alergia;
    
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String duenio) {
        this.dueño = duenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }
    
    //Class Select 
    public void mostrarcliente(JTable parammostrarcliente){
        
        conexion objetoconnectionusuarios = new conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Sentencia sql
        
        String sql = "";
        
        modelo.addColumn("id_cliente");
        modelo.addColumn("dueño");
        modelo.addColumn("nombre");
        modelo.addColumn("raza");
        modelo.addColumn("edad");
        modelo.addColumn("alergia");
        
        parammostrarcliente.setModel(modelo);
        
        sql = "select * from cliente;";
        
        String [] datos = new String[6];
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
                
                modelo.addRow(datos);
            }
            
            parammostrarcliente.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
}
    //Class insert 
    
    public void insertarcliente(JTextField paramduenio,JTextField paramnombre,JTextField paramraza,JTextField paramedad,JTextField alergia){
    
        setDueño(paramduenio.getText());
        setNombre(paramnombre.getText());
        setRaza(paramraza.getText());
        setEdad(Integer.parseInt(paramedad.getText()));
        setAlergia(alergia.getText());
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "insert into cliente (dueño,nombre,raza,edad,alergia)values(\n" +
    "?,\n" +
    "?,\n" +
    "?,\n" +
    "?,\n" +
    "?\n" +
    ");";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            cs.setString(1, getDueño());
            cs.setString(2, getNombre());
            cs.setString(3, getRaza());
            cs.setInt(4, getEdad());
            cs.setString(5, getAlergia());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Inserción Completada");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    }
    
    //Class Seleccionar
    
    public void seleccionarcliente(JTable parammostrarcliente,JTextField paramidcliente,JTextField paramduenio,JTextField paramnombre,JTextField paramraza,JTextField paramedad,JTextField alergia) {
    
        try {
            
            int fila = parammostrarcliente.getSelectedRow();
            
            if(fila>=0){
            
                paramidcliente.setText(parammostrarcliente.getValueAt(fila,0).toString());
            
                paramduenio.setText(parammostrarcliente.getValueAt(fila,1).toString());
                
                paramnombre.setText(parammostrarcliente.getValueAt(fila,2).toString());
                
                paramraza.setText(parammostrarcliente.getValueAt(fila,3).toString());
                
                paramedad.setText(parammostrarcliente.getValueAt(fila,4).toString());
                
                alergia.setText(parammostrarcliente.getValueAt(fila,5).toString());
                
                
            }
            else{
            JOptionPane.showMessageDialog(null,"Fila no seleccionada");
            }
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
    
    }
    
    //Class Update
    
    public void actualizarcliente(JTextField paramidcliente,JTextField paramduenio,JTextField paramnombre,JTextField paramraza,JTextField paramedad,JTextField alergia){
    
        setId_cliente(Integer.parseInt(paramidcliente.getText()));
        setDueño(paramduenio.getText());
        setNombre(paramnombre.getText());
        setRaza(paramraza.getText());
        setEdad(Integer.parseInt(paramedad.getText()));
        setAlergia(alergia.getText());
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "update cliente set dueño=?,nombre=?,raza=?,edad=?,alergia=? where cliente.id_cliente=?";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            cs.setString(1, getDueño());
            cs.setString(2, getNombre());
            cs.setString(3, getRaza());
            cs.setInt(4, getEdad());
            cs.setString(5, getAlergia());
            cs.setInt(6, getId_cliente());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Actualización Completada");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    }
    
    //Class Delete
    
        public void eliminarcliente(JTextField paramidcliente){
    
        setId_cliente(Integer.parseInt(paramidcliente.getText()));
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "delete from cliente where cliente.id_cliente=?";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            cs.setInt(1, getId_cliente());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Eliminado");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    }
    
    
}
