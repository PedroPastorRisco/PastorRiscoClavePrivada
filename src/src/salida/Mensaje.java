package salida;

public class Mensaje {
    public static final String INSERTE_NOMBRE_FICHERO_CLAVE = "Introduce el nombre del fichero de la clave (sin extension)";
    public static final String INSERTE_NOMBRE_FICHERO_A_ENCRIPTAR = "Introduce el nombre del fichero a encriptar (con extension)";
    public static final String INSERTE_NOMBRE_FICHERO_ENCRIPTADO = "Introduce el nombre del fichero una vez desencriptado (sin extension)";
    public static final String INSERTE_NOMBRE_FICHERO_A_DESENCRIPTAR = "Introduce el nombre del fichero a desencriptar (con extension)";
    public static final String INSERTE_NOMBRE_FICHERO_DESENCRIPTADO = "Introduce el nombre del fichero desencriptado (sin extension)";
    public static final String INSERTE_NUMERO="Introduzca un numero del 0 al 3";
    public static final String PROGRAMA_FINALIZADO="Cerrando el programa...";
    public static final String CLAVE_HECHA = "Fichero generado con la clave";
    public static final String FICHERO_ENCRIPTADO = "Fichero encriptado hecho";
    public static final String FICHERO_DESENCRIPTADO = "Fichero desencriptado hecho";

    /**
     * Cabecera: public static void mostrarMenu()
     *
     * Descripcion: Este método se encarga de mostrar por pantalla el menú dle programa.
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Muestra el menú por pantalla
     *
     */
    public static void mostrarMenu(){
        System.out.println("""
                ----------------------------------------
                Bienvenido al Programa de Encriptacion
                y Desencriptacion de Pedro Pastor Risco
                Seleccione una opcion:
                1.- Generar Clave Privada.
                2.- Encriptar.
                3.- Desencriptar
                ____________________
                0.- Salir
                ----------------------------------------
                """);
    }

    /**
     * Cabecera: public static void mostrarMenuClave()
     *
     * Descripcion: Este metodo se encarga de mostrar el menu para seleccionar los algoritmos
     * de clave simetrica.
     *
     * Precondiciones: Ninguna
     *
     * Postcondiciones: Ninguna
     */
    public static void mostrarMenuClave(){
        System.out.println("""
                ----------------------------------------
                1.- AES
                2.- DES
                3.- DESede
                ----------------------------------------
                Escriba el algoritmo de clave simetrica que desee""");
    }
    /**
     * Cabecera: public static void mostrarString(String string)
     *
     * Descripción: Este metodo se encarga de mostrar por pantalla una string
     * que le pase por parámetro.
     *
     * Precondiciones:Ninguna
     * Postcondiciones:Muestra por pantalla la string.
     *
     * @param string String
     */
    public static void mostrarString(String string){
        System.out.println(string);
    }
}
