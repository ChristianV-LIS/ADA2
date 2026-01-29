public class Encriptamiento {

    public static String encriptar(String texto) {
        return String.valueOf(texto.hashCode());
    }
}