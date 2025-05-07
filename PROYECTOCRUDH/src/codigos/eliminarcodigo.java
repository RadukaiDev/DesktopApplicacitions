
package codigos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import proyectocrudh.conexion;


public class eliminarcodigo {
  
    
      int codigo;
    String nombre;
    String apellido_p;
    String apellido_m;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_p() {
        return apellido_p;
    }

    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }

    public String getApellido_m() {
        return apellido_m;
    }

    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }
    
        public void mostrar (JTable paramTabla){
     
        //DEBEMOS LLAMAR A NUESTRA CONEXION PARA CONECTAR CON POSTGRESQL
        conexion objetoconnection = new conexion();
        //INCORPORAMOS UN MODELO PARA QUE EL PROGRAMA SEPA EN DONDE MOSTRAR LOS DATOS
        DefaultTableModel modelo = new DefaultTableModel();
        //CREAMOS NUESTRA VARIABLE SQL CON LOS VALORES VACIOS, PUES NOSOTROS VAMOS A MOSTRAR
        String sql ="";
        //AGREGAMOS LOS NOMBRES DE LAS COLUMNAS QUE CONTENDRAN LOS VALORES QUE ESTARÁN MOSTRANDOCE
        modelo.addColumn("id");
        modelo.addColumn("nombre");
        modelo.addColumn("apellido_p");
        modelo.addColumn("apellido_m");
        //AGREGAMOS AL ARAMETRO LOS MODELOS QUE INGRESAMOS 
        paramTabla.setModel(modelo);
        //REALIZAMOS NUESTRA CONSULTA
        sql = "select * from trabajadores";
        //CREAMOS UN ARREGLO EN DONDE AGREGAMOS LA CANTIDAD DE DATOS QUE VAMOS A INCORPORAR EN UNA FILA
        String [] datos = new String [4];
        //PARA EJECUTAR NUESTRA CONSULTA
        Statement st;
        
        try{
            //ESTABLECEMOS NUESTRA CONEXION PARA LLAMAR LA TABLA DE POSTGRESQL
        st = objetoconnection.establecerconexion().createStatement();
        //EL RESULTADO LO GUARDAMOS AQUI
        ResultSet rs  = st.executeQuery(sql);
        //SI NOS DEVUELVE LOS VALORES, DEBEMOS GUARDAR LOS VALORES EN EL ARREGLO
        while(rs.next()){
        datos[0] = rs.getString(1);
        datos[1] = rs.getString(2);
        datos[2] = rs.getString(3);
        datos[3] = rs.getString(4);
        //VOLVEMOS A INCORPORAR NUESTRO MODELO PARA AGREGAR OTRA FILA
        modelo.addRow(datos);
        }
        paramTabla.setModel(modelo);
        
        } catch(Exception e){
        //EN DADO CASO DE HABER UN ERROR, NOS MOSTRARÁ EL SIGUIENTE ERROR
            JOptionPane.showMessageDialog(null,"ERROR:"+e.toString());
        }
        
    }
        
    
     public void seleccionar(JTable paramTabla,JTextField paramCodigo,JTextField paramNombre,JTextField paramApellidoP,JTextField paramApellidoM){
    
        try {
            
            int fila = paramTabla.getSelectedRow();
            
            if (fila>=0) {
                paramCodigo.setText(paramTabla.getValueAt(fila,0).toString());
                paramNombre.setText(paramTabla.getValueAt(fila,1).toString());
                paramApellidoP.setText(paramTabla.getValueAt(fila,2).toString());
                paramApellidoM.setText(paramTabla.getValueAt(fila,3).toString());
            } else {
            JOptionPane.showMessageDialog(null,"FILA NO SELECCIONADA");
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"ERROR:"+e.toString());
            
        }
        
    }
     
     
      public void eliminar (JTextField paramCodigo){
       
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        
        conexion objetoconnection = new conexion();
        //EL SIGNO DE INTERROGACION ES PORQUE SON PARAMETROS DINAMICOS
        String consulta = "delete from trabajadores where id = ?;";
        
        try {
            CallableStatement cs = objetoconnection.establecerconexion().prepareCall(consulta); 
            cs.setInt(1,getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"SE ELIMINÓ CORRECTAMENTE");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR:"+e.toString());
        }
        
    }
     
}
