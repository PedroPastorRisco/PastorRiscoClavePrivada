package validacion;

import gestora.GenerarClave;
import salida.Mensaje;

import java.util.InputMismatchException;
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
        int respuesta = 4;

        do{
            try{
                Mensaje.mostrarString(Mensaje.INSERTE_NUMERO);
                respuesta=tecladoInt.nextInt();
            }catch(InputMismatchException e){
                tecladoInt.next();
            }
        }while((respuesta!=1)&&(respuesta!=2)&&(respuesta!=3)&&(respuesta!=0));
        return respuesta;
    }
     public static String validarAlgoritmoClaveSimetrica(){
        String respuesta;

        do{
            Mensaje.mostrarMenuClave();
            respuesta = teclado.next();
        }while(!respuesta.equals(GenerarClave.CLAVE_AES)
                &&!respuesta.equals(GenerarClave.CLAVE_DES)
                &&!respuesta.equals(GenerarClave.CLAVE_DESede));
        return respuesta;
     }
     public static String validarString(){
        return teclado.next();
     }
     public static String validarNombreFicheroClave(){
        Mensaje.mostrarString(Mensaje.INSERTE_NOMBRE_FICHERO_CLAVE);
        return validarString()  + ".raw";
     }
     public static String validarNombreFicheroAEncriptar(){
         Mensaje.mostrarString(Mensaje.INSERTE_NOMBRE_FICHERO_A_ENCRIPTAR);
         return validarString();
     }
     public static String validarNombreFicheroEncriptado(){
         Mensaje.mostrarString(Mensaje.INSERTE_NOMBRE_FICHERO_ENCRIPTADO);
         return validarString();
     }
    public static String validarNombreFicheroADesencriptar(){
        Mensaje.mostrarString(Mensaje.INSERTE_NOMBRE_FICHERO_A_DESENCRIPTAR);
        return validarString();
    }
    public static String validarNombreFicheroDesencriptado(){
        Mensaje.mostrarString(Mensaje.INSERTE_NOMBRE_FICHERO_DESENCRIPTADO);
        return validarString();
    }
}
