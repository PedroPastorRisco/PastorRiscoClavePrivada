package gestora;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Cifrado3DES {
    public static final String TERMINACION_ENCRIPTADO = ".encript";

    /**
     * <b>Cabecera: </b>public static void cifrar(String nombreFichero, String algoritmo) </br>
     * <b>Descripcion: </b>Metodo principal que sera llamado por la gestora</br>
     * <b>Precondiciones: </b>nombreFichero y algoritmo diferentes de null </br>
     * <b>Postcondiciones: </b>Nose</br>
     * </br>
     * @param nombreFichero
     * @param algoritmo
     */
    public static void cifrar(String nombreFichero, String algoritmo){
        byte valorClave[] = incializarValorClave(nombreFichero);
        Cipher cifrado = metodoRandom1(nombreFichero, algoritmo, valorClave);
        metodoRandom2(nombreFichero,cifrado);
    }

    /**
     * <b>Cabecera: </b>private static byte[] incializarValorClave(String nombreFichero)</br>
     * <b>Descripcion: </b>Metodo para inicializar el valor clave </br>
     * <b>Precondiciones: </b>nombreFichero diferente de null </br>
     * <b>Postcondiciones: </b>valor clave actualizado</br>
     * </br>
     * @param nombreFichero
     * @return byte[]
     */
    private static byte[] incializarValorClave(String nombreFichero){
        byte[] valorClave = null;

        try (FileInputStream fisClave = new FileInputStream(nombreFichero)) {
            valorClave = fisClave.readAllBytes();
        } catch (FileNotFoundException e) {
            System.out.printf("ERROR: no existe fichero de clave %s\n.", nombreFichero);
        } catch (IOException e) {
            System.out.printf("ERROR: de E/S leyendo clave de fichero %s\n.", nombreFichero);
        }
        return valorClave;
    }

    /**
     * <b>Cabecera: </b>private static Cipher metodoRandom1(String nombreFichero, String algoritmo,byte[] valorClave)</br>
     * <b>Descripcion: </b>Metodo random 1</br>
     * <b>Precondiciones: </b>nombreFichero, algoritmo y valorClave diferentes de null </br>
     * <b>Postcondiciones: </b>Cipher devuelto</br>
     * </br>
     * @param nombreFichero
     * @param algoritmo
     * @param valorClave
     * @return Cipher
     */
    private static Cipher metodoRandom1(String nombreFichero, String algoritmo,byte[] valorClave) {
        SecretKeySpec keySpec = new SecretKeySpec(valorClave, algoritmo);
        SecretKeyFactory keyFactory;
        SecretKey clave;
        Cipher cifrado = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(algoritmo);
            clave = keyFactory.generateSecret(keySpec);
            cifrado = Cipher.getInstance(algoritmo);
            cifrado.init(Cipher.ENCRYPT_MODE, clave);
        }catch (InvalidKeySpecException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return cifrado;
    }

    /**
     * <b>Cabecera: </b>private static void metodoRandom2(String nombreFichero, Cipher cifrado)</br>
     * <b>Descripcion: </b>Metodo random 2</br>
     * <b>Precondiciones: </b>nombreFichero y cifrado diferentes de null </br>
     * <b>Postcondiciones: </b>None</br>
     * </br>
     * @param nombreFichero
     * @param cifrado
     */
    private static void metodoRandom2(String nombreFichero, Cipher cifrado){
        try (FileInputStream fis = new FileInputStream(nombreFichero);
             FileOutputStream fos = new FileOutputStream(nombreFichero + TERMINACION_ENCRIPTADO);
             BufferedInputStream is = new BufferedInputStream(fis);
             BufferedOutputStream os = new BufferedOutputStream(fos)){
            byte[] buff = new byte[cifrado.getBlockSize()];
            while(is.read(buff) != -1) {
                os.write(cifrado.update(buff));
            }
            os.write(cifrado.doFinal());
        } catch (IOException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
