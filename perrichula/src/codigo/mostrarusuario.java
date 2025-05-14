
package codigo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class mostrarusuario {

    int id_empleado;
    String clave; 
    
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
    public void mostrarusuario(JTable parammostrarusuario){
        
        
        conexion objetoconnectionusuarios = new conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Sentencia sql
        
        String sql = "";
        
        modelo.addColumn("id_empleado");
        modelo.addColumn("clave");
       
        
        parammostrarusuario.setModel(modelo);
        
        sql = "select * from empleado;";
        
        String [] datos = new String[2];
        Statement st;
        
        try {
            
            st=objetoconnectionusuarios.establecerconexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                //id
                datos[0] = Integer.toString(rs.getInt(1));
                //clave
                datos[1]=rs.getString(2);


                
                modelo.addRow(datos);
            }
            
            parammostrarusuario.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"error line :"+e.toString());
        }
        
}
    
    
    //Class insert 
    
    public void insertarusuario(JTextField paramclave){
    
        setClave(paramclave.getText());
       
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "insert into empleado(clave)values(?);";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            cs.setString(1, getClave());
        
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Inserción Completada");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error de inserción:"+e.toString());
        }
        
    }
    
    
    //Class Seleccionar
    
    public void seleccionarusuario(JTable parammostrarusuario,JTextField paramid_empleado,JTextField paramclave) {
    
        try {
            
            int fila = parammostrarusuario.getSelectedRow();
            
            if(fila>=0){
            
                paramid_empleado.setText(parammostrarusuario.getValueAt(fila,0).toString());
            
                paramclave.setText(parammostrarusuario.getValueAt(fila,1).toString());

            }
            else{
            JOptionPane.showMessageDialog(null,"Fila no seleccionada");
            }
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
    
    }
    
    //Class Update
    /*
    public void actualizarusuario(JTextField paramid_empleado,JTextField paramclave){
    
        setId_empleado(Integer.parseInt(paramid_empleado.getText()));    
        setClave(paramclave.getText());
      
        
        conexion objetoconnectionusuarios = new conexion();
        
       String consulta = "update empleado set clave=? where id_empleado=?";

        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            
            cs.setInt(1, getId_empleado());
            cs.setString(2, getClave());

           
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Actualización Completada");
            
            
        } catch (Exception e) {
           // e.printStackTrace();
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    } */
    public void actualizarusuario(JTextField paramid_empleado, JTextField paramclave) {
    setId_empleado(Integer.parseInt(paramid_empleado.getText()));
    setClave(paramclave.getText());

    conexion objetoconnectionusuarios = new conexion();

    String consulta = "update empleado set clave=? where id_empleado=?";

    try {
        CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);

        cs.setString(1, getClave());
        cs.setInt(2, getId_empleado());

        cs.execute();

        JOptionPane.showMessageDialog(null, "Actualización Completada");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "error: " + e.toString());
    }
}

    
    //Class Delete
    
        public void eliminarusuario(JTextField paramid_empleado){
    
        setId_empleado(Integer.parseInt(paramid_empleado.getText()));
        
        conexion objetoconnectionusuarios = new conexion();
        
        String consulta = "delete from empleado where empleado.id_empleado=?";
        
        try {
            
            CallableStatement cs = objetoconnectionusuarios.establecerconexion().prepareCall(consulta);
            cs.setInt(1, getId_empleado());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Eliminado");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:"+e.toString());
        }
        
    }

    
}
