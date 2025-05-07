package proyectocrudh;

import java.awt.HeadlessException; //EXCEPCIÓN QUE SE LANZA SI EL SISTEMA NO TIENE DISPONIBLE UNA OPREACIÓN QUE REQUIERE VENTANA.
import java.sql.Connection; //INTERFAZ QUE DEFINE EL CONTRATO PARA UNA CONEXIÓN DE BD.
import java.sql.DriverManager; //PROPORCIONA MÉTODOS PARA ADMINISTRAR CONTROLADORES JDBC Y CONECTARSE A LA BD.
import java.sql.SQLException; //EXCEPCIÓN QUE SE LANA CUANDO OCURRE UN ERROR EN LA CONEXIÓN U OPERACIÓN.
import javax.swing.JOptionPane; //MUESTRA VENTANAS DE DIÁLOGO QUE CONTIENEN UN MENSAJE.

public class conexion {
    //ESTA VARIABLE ALMACENA LA CONEXIÓN A LA BASE DE DATOS:
    Connection conexion = null; 
    //ESTAS SON LAS VARIABLES QUE DECLARAMOS CON LOS DATOS DE POSTGRESQL:
    String usuario = "postgres"; //ALMACENA EL NOMBRE DE USUARIO.
    String contraseña = "hector2002"; //ALMACENA LA CONTRASEÑA.
    String base_de_datos = "registro"; //ALMACENA EL NOMBRE DE LA BD.
    String ip = "localhost"; //ALMACENA DE DIRECCIÓN IP DEL SERVIDOR DE LA BD.
    String puerto = "5432"; //ALACENA EL PUERTO EN EL QUE SE EJECUTA EL SERVIDOR.
    //ESTA VARIABLE CONCATENA LAS VARIABLES PARA FORMAR LA DIRECCIÓN URL COMPLETA:
    //LA CADENA JDBC SE UTILIZA PARA INDICAR QUE ESTÁ CONECTANDO A UNA BD UTILIZANDO EL CONTROLADOR JDBC.
    String direccion = "jdbc:postgresql://"+ip+":"+puerto+"/"+base_de_datos;
    //MÉTODO QUE DEVUELVE UN OBJETO CONECCTION QUE SE ENCARGA DE ESTABLECER LA CONEXIÓN CON LOS DATOS:
    public Connection establecerconexion(){ 
        try{
            //SE CARGA EL CONTROLADOR JDBC DE POSTGRESQL:
            Class.forName("org.postgresql.Driver");
            //ESTABLECEMOS LA CONEXIÓN CON LA BD UTILIZANDO LA DIRECCIÓN:
            conexion = DriverManager.getConnection(direccion,usuario,contraseña);
            //MOSTRAMOS EN PANTALLA EL MENSAJE DE QUE LA CONEXIÓN HA SIDO ESTABLECIDA:
            JOptionPane.showMessageDialog(null,"CONEXIÓN ESTABLECIDA");      
        }
        //ESTE ES UN BLOQUE DE CAPTURA QUE MANEJA CUALQUIER EXCEPCIÓN QUE SE PUEDA PRODUCIR:
        catch (HeadlessException | ClassNotFoundException | SQLException e){
            //SE MUESTRA EL MENSAJE INDICANDO QUE LA CONEXIÓN HA FALLADO:
            JOptionPane.showMessageDialog(null,"CONEXIÓN FALLIDA"+e.toString());
        }  
        //EL MÉTODO DEVUELVE EL OBJETO CONNECTION QUE SE HA CREADO PARA LA CONEXIÓN:
        return conexion;   
}}
