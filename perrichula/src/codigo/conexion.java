
package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexion {
    
    Connection conectar = null;
    
    String usuario = "postgres";
    String contraseña = "hector2002";
    String bd = "perrichula";
    String ip = "localhost";    
    String puerto = "5432";    
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd; 
    
    public Connection establecerconexion(){
    
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
            
            JOptionPane.showMessageDialog(null,"Conexión establecida");
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error: "+e);
        }
    return conectar;
    }

}
