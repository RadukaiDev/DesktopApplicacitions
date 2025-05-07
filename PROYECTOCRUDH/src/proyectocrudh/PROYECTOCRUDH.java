package proyectocrudh;

public class PROYECTOCRUDH {
 
    public static void main(String[] args) {
        //LLAMAMOS A LA CONEXIÓN USUARIOS
        conexionusuarios objetoconnectionusuarios = new conexionusuarios();
        objetoconnectionusuarios.conexionusuariosbd();
        //LLAMAMOS A LA CONEXIÓN REGISTRO
        conexion objetoconnection = new conexion();
        //LLAMAMOS A LA VENTANA PRINCIPAL LOGIN
        login objetoVista = new login ();
        //HACEMOS VISIBLE LA VENTANA
        objetoVista.setVisible(true);
    }
}
