package gestora;

import salida.Mensaje;
import validacion.Validacion;

public class Gestora {

    public static void controlarMenu(){
        boolean continuar = true;

        do{
            Mensaje.mostrarMenu();
            switch (Validacion.validarNumero0al3()){
                case 1 -> GenerarClave.generarClave();
                case 2 -> Encriptar.cifrar(Validacion.validarNombreFicheroClave(),Validacion.validarAlgoritmoClaveSimetrica());
                case 3 -> Desencriptar.descifrar(Validacion.validarNombreFicheroClave(),Validacion.validarAlgoritmoClaveSimetrica());
                case 0 -> {
                    Mensaje.mostrarString(Mensaje.PROGRAMA_FINALIZADO);
                    continuar = false;
                }
            }
        }while(continuar);
    }
}
