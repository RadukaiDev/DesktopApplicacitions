package perrichula;

import codigo.conexion;
import ventanas.login;

public class Perrichula {

    public static void main(String[] args) {
        
        conexion objetoconnectionusuarios = new conexion();
        objetoconnectionusuarios.establecerconexion();
        
         login objetoVista = new login ();
         objetoVista.setVisible(true);
        
    }}