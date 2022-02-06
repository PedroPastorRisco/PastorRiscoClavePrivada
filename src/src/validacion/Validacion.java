package validacion;

import gestora.GeneracionClaveSimetrica3DES;
import gestora.Gestora;
import salida.Mensaje;

import java.util.Scanner;

public class Validacion {
    static Scanner tecladoInt=new Scanner(System.in);
    static Scanner teclado=new Scanner(System.in);


    /**
     * Cabecera: ppublic static int validarNumero0al5()
     *
     * Descripcion: Este metodo se encarga de validar un numero insertado por el usuario.
     * Este debe ser 0,1,2 o 3.
     *
     * Precondiciones: Ninguna
     * Postcondciones: Te devuelve un numero que es un 0,1,2 o 3
     * @return int respuesta
     */
    public static int validarNumero0al3(){
        int respuesta;

        do{
            Mensaje.mostrarString(Mensaje.INSERTE_NUMERO);
            respuesta=tecladoInt.nextInt();
        }while((respuesta!=1)&&(respuesta!=2)&&(respuesta!=3)&&(respuesta!=0));
        return respuesta;
    }
     public static String validarAlgoritmoClaveSimetrica(){
        String respuesta;

        do{
            Mensaje.mostrarMenuClave();
            respuesta = teclado.next();
        }while(!respuesta.equals(GeneracionClaveSimetrica3DES.CLAVE_AES)
                &&!respuesta.equals(GeneracionClaveSimetrica3DES.CLAVE_DES)
                &&!respuesta.equals(GeneracionClaveSimetrica3DES.CLAVE_DESede));
        return respuesta;
     }
     public static String validarString(){
        return teclado.next();
     }
}
