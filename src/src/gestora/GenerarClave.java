package gestora;

import salida.Mensaje;
import validacion.Validacion;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class GenerarClave {

    public static final String CLAVE_AES="AES";
    public static final String CLAVE_DES="DES";
    public static final String CLAVE_DESede="DESede";
    
    private static String algoritmoClaveSimetrica;
    private static String nomFichClave;
    private static final String ALGORITMO_GEN_NUM_ALEAT = "SHA1PRNG";

    /**
     * Cabecera:public static void generarClave()
     *
     * Descripcion: Este metodo se encarga de generar el fichero con la clave simetrica
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Genera el fichero con la clave
     */
    public static void generarClave(){
        determinarClaveSimetrica();
        determinarFicheroClaveSimetrica();
        escribirKeyEnElFichero(determinarClaseKey(generarSecretKeyFactory(),generarKey()));
    }
    /**
     * Cabecera: private static void determinarClaveSimetrica()
     *
     * Descripcion: Este metodo se encarga de determinar el algoritmo de clave simetrica
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Determina el algoritmo de la clave simetrica
     */
    private static void determinarClaveSimetrica(){
        algoritmoClaveSimetrica = Validacion.validarAlgoritmoClaveSimetrica();
    }
    /**
     * Cabecera: private static void determinarFicheroClaveSimetrica()
     *
     * Descripcion: Este metodo se encarga de determinar el nombre del fichero
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Determina el nombre del fichero
     */
    private static void determinarFicheroClaveSimetrica(){
        nomFichClave = Validacion.validarNombreFicheroClave();
    }
    /**
     * Cabecera: private static SecretKey generarKey()
     *
     * Descripcion: Este metodo se encarga de generar una SecretKey en base a los algoritmos determinados.
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Devuelve la SecretKey
     */
    private static SecretKey generarKey(){
        SecretKey clave = null;

        try {
            KeyGenerator genClaves = KeyGenerator.getInstance(algoritmoClaveSimetrica);
            SecureRandom srand = SecureRandom.getInstance(ALGORITMO_GEN_NUM_ALEAT);
            genClaves.init(srand);
            clave = genClaves.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return clave;
    }
    /**
     * Cabecera: private static SecretKeyFactory generarSecretKeyFactory()
     *
     * Descripcion: Este metodo se encarga de generar una SecretKeyFactory en base a los algoritmos determinados
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Devuelve el SecretKeyFactory
     */
    private static SecretKeyFactory generarSecretKeyFactory(){
        SecretKeyFactory keySpecFactory = null;

        try {
            if(!algoritmoClaveSimetrica.equals(CLAVE_AES)){
                keySpecFactory = SecretKeyFactory.getInstance(algoritmoClaveSimetrica);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keySpecFactory;
    }
    /**
     * Cabecera: private static byte[] determinarClaseKey(SecretKeyFactory keySpecFactory,SecretKey clave)
     *
     * Descripcion: Este metodo se encarga de determinar que clase hay que utilizar para generar el valorClave.
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Devuelve el valor clave en forma de array de bytes
     */
    private static byte[] determinarClaseKey(SecretKeyFactory keySpecFactory,SecretKey clave){
        byte[] valorClave = null;

        switch(algoritmoClaveSimetrica){
            case CLAVE_AES -> valorClave = usarAesKeySpec(clave);
            case CLAVE_DES ->  valorClave = usarDesKeySpec(keySpecFactory,clave);
            case CLAVE_DESede -> valorClave = usarDesedeSpec(keySpecFactory,clave);
        }
        return valorClave;
    }
    /**
     * Cabecera:private static byte[] usarDesedeSpec(SecretKeyFactory keySpecFactory,SecretKey clave)
     *
     * Descripcion: Este metodo se encarga de generar el valor clave usando DESedeKeySpec
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Devuelve un valorClave en forma de array de bytes
     */
    private static byte[] usarDesedeSpec(SecretKeyFactory keySpecFactory,SecretKey clave) {
        byte[] valorClave = null;

        try {
            DESedeKeySpec DESede = (DESedeKeySpec) keySpecFactory.getKeySpec(clave, DESedeKeySpec.class);
            valorClave = DESede.getKey();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return valorClave;
    }
    /**
     * Cabecera: private static byte[] usarAesKeySpec(SecretKeyFactory keySpecFactory,SecretKey clave)
     *
     * Descripcion: Este metodo se encarga de generar el valor clave usando PBEKeySpec
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Devuelve un valorClave en forma de array de bytes
     */
    private static byte[] usarAesKeySpec(SecretKey clave) {
        byte[] valorClave = clave.getEncoded();

        SecretKeySpec skeySpec = new SecretKeySpec(valorClave, CLAVE_AES);
        return skeySpec.getEncoded();
    }
    /**
     * Cabecera: private static byte[] usarDesKeySpec(SecretKeyFactory keySpecFactory,SecretKey clave)
     *
     * Descripcion: Este metodo se encarga generar el valor clave usando DESKeySpec
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Devuelve un valorClave en forma de array de bytes
     */
    private static byte[] usarDesKeySpec(SecretKeyFactory keySpecFactory,SecretKey clave) {
        byte[] valorClave = null;

        try {
            DESKeySpec DES = (DESKeySpec) keySpecFactory.getKeySpec(clave,DESKeySpec.class);
            valorClave = DES.getKey();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return valorClave;
    }
    /**
     * Cabecera: private static void escribirKeyEnElFichero(byte[] valorClave)
     *
     * Descripcion: Este metodo se encarga de escribir un valor clave en el fichero
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Escribe en el fichero un array de bytes correspondiente al valorClave
     */
    private static void escribirKeyEnElFichero(byte[] valorClave){
        try(FileOutputStream fos = new FileOutputStream(nomFichClave)) {
            fos.write(valorClave);
            Mensaje.mostrarString(Mensaje.CLAVE_HECHA);
        } catch (IOException e) {
            System.out.println("Error de E/S escribiendo clave en fichero");
            e.printStackTrace();
        }
    }
}
