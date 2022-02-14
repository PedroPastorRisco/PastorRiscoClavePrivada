package gestora;

import salida.Mensaje;
import validacion.Validacion;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Desencriptar {
    public static final String TERMINACION_DESENCRIPTADO = ".decript";

    /**
     * Cabecera: public static void descifrar(String ficheroClave, String algoritmo)
     *
     * Descripcion: Este metodo se encarga de descifrar un fichero con una clave
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Genera el fichero descifrado
     * @param ficheroClave String
     * @param algoritmo String
     */
    public static void descifrar(String ficheroClave, String algoritmo){
        byte valorClave[] = incializarValorClave(ficheroClave);
        Cipher cifrado = crearDescifrado(algoritmo, valorClave);
        hacerFicheroDescifrado(Validacion.validarNombreFicheroADesencriptar(),Validacion.validarNombreFicheroDesencriptado(),cifrado);
    }

    /**
     * Cabecera: private static byte[] incializarValorClave(String nombreFichero)
     * Descripcion: Metodo para inicializar el valor clave
     * Precondiciones: nombreFichero diferente de null
     * Postcondiciones: valor clave actualizado
     *
     * @param nombreFichero String
     * @return byte[] valor clave
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
     * Cabecera:private static Cipher crearDescifrado(String algoritmo, byte[] valorClave)
     * Descripcion: Este metodo se encarga de generar el cifrado
     * Precondiciones:nombreFichero, algoritmo y valorClave diferentes de null
     * Postcondiciones: Cipher devuelto
     *
     * @param algoritmo String
     * @param valorClave String
     * @return Cipher cifrado
     */
    private static Cipher crearDescifrado(String algoritmo, byte[] valorClave) {
        SecretKeySpec keySpec = new SecretKeySpec(valorClave, algoritmo);
        SecretKey clave;
        Cipher cifrado = null;

        try {
            clave = new SecretKeySpec(keySpec.getEncoded(), algoritmo);
            cifrado = Cipher.getInstance(algoritmo);
            cifrado.init(Cipher.DECRYPT_MODE, clave);
        }catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return cifrado;
    }

    /**
     * Cabecera: private static void hacerFicheroDescifrado(String inputFichero, String outputFichero, Cipher cifrado)
     * Descripcion: Metodo para hacer el fichero descifrado
     * Precondiciones: nombreFichero y cifrado diferentes de null
     * Postcondiciones: Devuelve el fichero descifrado
     *
     * @param inputFichero String
     * @param outputFichero String
     * @param cifrado Cipher
     */
    private static void hacerFicheroDescifrado(String inputFichero, String outputFichero, Cipher cifrado){
        File fi = new File(inputFichero), fo = new File(outputFichero + TERMINACION_DESENCRIPTADO);

            try (FileInputStream fis = new FileInputStream(fi);
                 FileOutputStream fos = new FileOutputStream(fo);
                 BufferedInputStream is = new BufferedInputStream(fis);
                 BufferedOutputStream os = new BufferedOutputStream(fos)) {
                byte[] buff = new byte[cifrado.getBlockSize()];
                while(is.read(buff) != -1) {
                    os.write(cifrado.update(buff));
                }
                os.write(cifrado.doFinal());
                Mensaje.mostrarString(Mensaje.FICHERO_DESENCRIPTADO);
            } catch (IOException | IllegalBlockSizeException | BadPaddingException e) {
                e.printStackTrace();
            }
    }
}
