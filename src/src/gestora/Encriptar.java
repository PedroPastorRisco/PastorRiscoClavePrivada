package gestora;

import salida.Mensaje;
import validacion.Validacion;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encriptar {
    public static final String TERMINACION_ENCRIPTADO = ".encript";

    /**
     * Cabecera: public static void cifrar(String ficheroClave, String algoritmo)
     *
     * Descripcion: Este metodo se encarga de cifrar un fichero con una clave
     *
     * Precondiciones: Ninguna
     * Postcondiciones: Genera el fichero cifrado
     * @param ficheroClave String
     * @param algoritmo String
     */
    public static void cifrar(String ficheroClave, String algoritmo){
        byte valorClave[] = incializarValorClave(ficheroClave);
        Cipher cifrado = crearCifrado(algoritmo, valorClave);
        hacerFicheroCifrado(Validacion.validarNombreFicheroAEncriptar(),Validacion.validarNombreFicheroEncriptado(),cifrado);
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
     * Cabecera:private static Cipher crearCifrado(String algoritmo, byte[] valorClave)
     * Descripcion: Este metodo se encarga de generar el cifrado
     * Precondiciones:nombreFichero, algoritmo y valorClave diferentes de null
     * Postcondiciones: Cipher devuelto
     *
     * @param algoritmo String
     * @param valorClave String
     * @return Cipher cifrado
     */
    private static Cipher crearCifrado(String algoritmo, byte[] valorClave) {
        SecretKeySpec keySpec = new SecretKeySpec(valorClave, algoritmo);
        SecretKey clave;
        Cipher cifrado = null;

        try {
            clave = new SecretKeySpec(keySpec.getEncoded(), algoritmo);
            cifrado = Cipher.getInstance(algoritmo);
            cifrado.init(Cipher.ENCRYPT_MODE, clave);
        }catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return cifrado;
    }

    /**
     * Cabecera: private static void hacerFicheroCifrado(String inputFichero,String outputFichero, Cipher cifrado)
     * Descripcion: Metodo para hacer el fichero cifrado
     * Precondiciones: nombreFichero y cifrado diferentes de null
     * Postcondiciones: Devuelve el fichero cifrado
     *
     * @param inputFichero String
     * @param outputFichero String
     * @param cifrado Cipher
     */
    private static void hacerFicheroCifrado(String inputFichero,String outputFichero, Cipher cifrado){
        File fi = new File(inputFichero), fo = new File(outputFichero + TERMINACION_ENCRIPTADO);

        try (FileInputStream fis = new FileInputStream(fi);
             FileOutputStream fos = new FileOutputStream(fo)){
            byte[] buffer = new byte[64];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] output = cifrado.update(buffer, 0, bytesRead);
                if (output != null) {
                    fos.write(output);
                }
            }
            byte[] outputBytes = cifrado.doFinal();
            if (outputBytes != null) {
                fos.write(outputBytes);
            }
            Mensaje.mostrarString(Mensaje.FICHERO_ENCRIPTADO);
        } catch (IOException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
