
package codigos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import proyectocrudh.conexionusuarios;

public class usuarioscodigo {

       int codigo;
    String usuario;
    String clave;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
       public void mostrar (JTable paramTabla){
     
        //DEBEMOS LLAMAR A NUESTRA CONEXION PARA CONECTAR CON POSTGRESQL
        conexionusuarios objetoconnectionusuarios = new conexionusuarios();
        //INCORPORAMOS UN MODELO PARA QUE EL PROGRAMA SEPA EN DONDE MOSTRAR LOS DATOS
        DefaultTableModel modelo = new DefaultTableModel();
        //CREAMOS NUESTRA VARIABLE SQL CON LOS VALORES VACIOS, PUES NOSOTROS VAMOS A MOSTRAR
        String sql ="";
        //AGREGAMOS LOS NOMBRES DE LAS COLUMNAS QUE CONTENDRAN LOS VALORES QUE ESTARÁN MOSTRANDOCE
        modelo.addColumn("id");
        modelo.addColumn("usuario");
        modelo.addColumn("clave");
        
        //AGREGAMOS AL ARAMETRO LOS MODELOS QUE INGRESAMOS 
        paramTabla.setModel(modelo);
        //REALIZAMOS NUESTRA CONSULTA
        sql = "select * from users";
        //CREAMOS UN ARREGLO EN DONDE AGREGAMOS LA CANTIDAD DE DATOS QUE VAMOS A INCORPORAR EN UNA FILA
        String [] datos = new String [3];
        //PARA EJECUTAR NUESTRA CONSULTA
        Statement st;
        
        try{
            //ESTABLECEMOS NUESTRA CONEXION PARA LLAMAR LA TABLA DE POSTGRESQL
        st = objetoconnectionusuarios.conexionusuariosbd().createStatement();
        //EL RESULTADO LO GUARDAMOS AQUI
        ResultSet rs  = st.executeQuery(sql);
        //SI NOS DEVUELVE LOS VALORES, DEBEMOS GUARDAR LOS VALORES EN EL ARREGLO
        while(rs.next()){
        datos[0] = rs.getString(1);
        datos[1] = rs.getString(2);
        datos[2] = rs.getString(3);
        //VOLVEMOS A INCORPORAR NUESTRO MODELO PARA AGREGAR OTRA FILA
        modelo.addRow(datos);
        }
        paramTabla.setModel(modelo);
        
        } catch(Exception e){
        //EN DADO CASO DE HABER UN ERROR, NOS MOSTRARÁ EL SIGUIENTE ERROR
            JOptionPane.showMessageDialog(null,"ERROR:"+e.toString());
        }
        
    }
    
       
       public void insertar (JTextField paramUsuario, JTextField paramContraseña){
        
        setUsuario(paramUsuario.getText());
        setClave(paramContraseña.getText());
        
        
        proyectocrudh.conexionusuarios objetoconnectionusuarios = new proyectocrudh.conexionusuarios();
        
        String consulta = "insert into users (usuario,clave) values (?,?);";
        
        try {
            CallableStatement cs = objetoconnectionusuarios.conexionusuariosbd().prepareCall(consulta); 
            cs.setString(1,getUsuario());
            cs.setString(2,getClave());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"SE INSERTÓ CORRECTAMENTE");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR:"+e.toString());
        }
        
    }
       
      
        public void seleccionar(JTable paramTabla,JTextField paramCodigo,JTextField paramUsuario,JTextField paramContraseña){
    
        try {
            
            int fila = paramTabla.getSelectedRow();
            
            if (fila>=0) {
                paramCodigo.setText(paramTabla.getValueAt(fila,0).toString());
                paramUsuario.setText(paramTabla.getValueAt(fila,1).toString());
                paramContraseña.setText(paramTabla.getValueAt(fila,2).toString());
                
            } else {
            JOptionPane.showMessageDialog(null,"FILA NO SELECCIONADA");
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"ERROR:"+e.toString());
            
        }
        
    }
     
     
      public void eliminar (JTextField paramCodigo){
       
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        
        conexionusuarios objetoconnectionusuarios = new conexionusuarios();
        //EL SIGNO DE INTERROGACION ES PORQUE SON PARAMETROS DINAMICOS
        String consulta = "delete from users where id = ?;";
        
        try {
            CallableStatement cs = objetoconnectionusuarios.conexionusuariosbd().prepareCall(consulta); 
            cs.setInt(1,getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"SE ELIMINÓ CORRECTAMENTE");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR:"+e.toString());
        }
      }
      
      
      public void actualizar (JTextField paramCodigo,JTextField paramUsuario, JTextField paramContraseña){
       
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setUsuario(paramUsuario.getText());
        setClave(paramContraseña.getText());
        
        conexionusuarios objetoconnectionusuarios = new conexionusuarios();
        //EL SIGNO DE INTERROGACION ES PORQUE SON PARAMETROS DINAMICOS
        String consulta = "update users set usuario = ?,clave = ? where id = ?;";
        
        try {
            CallableStatement cs = objetoconnectionusuarios.conexionusuariosbd().prepareCall(consulta); 
            cs.setString(1,getUsuario());
            cs.setString(2,getClave());
            cs.setInt(3,getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"SE ACTUALIZÓ CORRECTAMENTE");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR:"+e.toString());
        }
        
    }
      
      
      
      
      
    }
    
    
 

