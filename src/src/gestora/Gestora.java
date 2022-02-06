package gestora;

import salida.Mensaje;
import validacion.Validacion;

public class Gestora {

    public static void controlarMenu(){
        boolean continuar = true;
        Mensaje.mostrarMenu();
        do{
            switch (Validacion.validarNumero0al3()){
                case 1 -> GeneracionClaveSimetrica3DES.generarClave();
                case 2 -> encriptarFichero();
                case 3 -> desencriptarFichero();
                case 0 -> {
                    Mensaje.mostrarString(Mensaje.PROGRAMA_FINALIZADO);
                    continuar = false;
                }
            }
        }while(continuar);
    }
}
