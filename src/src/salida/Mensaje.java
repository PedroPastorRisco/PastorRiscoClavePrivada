package salida;

public class Mensaje {
    public static final String INSERTE_NUMERO="Introduzca un numero del 0 al 5";
    public static final String PROGRAMA_FINALIZADO="Cerrando el programa...";
    public static final String FICHEROS_CREADOS="Ficheros creados correctamente";
    public static final String FICHEROS_NO_CREADOS="Ha habido un error en la creacion, contácte con su profesor más cercano.";
    public static final String INSERTE_NOMBRE="Por favor, introduzca el nombre del cliente (No puede ser mayor de 25 caracteres).";
    public static final String INSERTE_APELLIDOS="Por favor, introduzca los apellidos del cliente (No puede ser mayor de 25 caracteres).";
    public static final String INSERTE_DNI="Por favor, introduzca el dni del cliente. Recuerda que debe tener 9 caracteres, 8 numeros y su letra correspondiente";
    public static final String LONGITUD_DNI_NO_VALIDA="La longitud del dni introducido no es valida, recuerda que debe tener 9 caracteres.";
    public static final String NUMERO_DNI_NO_VALIDO="El numero introducido no puede tener caracteres que no sean numeros.";
    public static final String LETRA_DNI_VALIDA="La letra introducida no corresponde con el numero introducido.";
    public static final String INSERTE_DIRECCION="Por favor, introduzca la direccion del cliente (No puede ser mayor de 30 caracteres).";
    public static final String INSERTE_NUMERO_TELEFONO="Por favor, introduzca el numero de telefono del cliente.";
    public static final String CLIENTE_NO_ENCONTRADO="El dni que ha introducido no corresponde a ningún cliente guardado o se corresponde con un cliente eliminado";
    public static final String CLIENTE_BORRADO="El cliente se ha borrado correctamente";
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
