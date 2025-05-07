//AQUI REALIZAMOS LA CONEXION A LA BASE DE DATOS DE LOS USUARIOS 
package proyectocrudh;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conexionusuarios {
    
    Connection conexionusuario = null; 
    String usuario = "postgres";
    String contraseña = "hector2002";
    String base_de_datos = "usuarios";
    String ip = "localhost";
    String puerto = "5432";
    String direccion = "jdbc:postgresql://"+ip+":"+puerto+"/"+base_de_datos;
    
    public Connection conexionusuariosbd(){ 
        try{
            Class.forName("org.postgresql.Driver");
            conexionusuario = DriverManager.getConnection(direccion,usuario,contraseña);
            //JOptionPane.showMessageDialog(null,"Conexión de usuarios establecida");   
        }
        catch (HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"CONEXIÓN FALLIDA: "+e.toString());
        }  
        return conexionusuario;   
}
    public void cerrarConexion() {
    try {
        if (conexionusuario != null) {
            conexionusuario.close();
            JOptionPane.showMessageDialog(null, "CONEXIÓN CERRADA");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERROR AL CERRAR CONEXIÓN: " + ex.getMessage());
    }
}

    
    
    
}
