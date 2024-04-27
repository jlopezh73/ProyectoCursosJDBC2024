package ejemplos2024.cursosjdbc2024.helpers;

import ejemplos2024.cursosjdbc2024.modelos.Curso;
import ejemplos2024.cursosjdbc2024.modelos.Usuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuariosHelper {
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
    public Usuario validarUsuario(String correoElectronico, String password) {
        try {
            var md5 = MessageDigest.getInstance("MD5");
            byte []digest = password.getBytes();
            byte []hashb = md5.digest(digest);
            password = bytesToHex(hashb).toLowerCase();
            correoElectronico = correoElectronico
                    .replaceAll("'", "")
                    .replaceAll(" ", "");

            Connection conn = ConexionDBCursos.obtenerInstancia();

            String instSQL = String.format("select * from usuario where correoElectronico='%s' and password='%s'", correoElectronico,  password);

            Statement inst = conn.createStatement();
            ResultSet resul = inst.executeQuery(instSQL);
            Usuario usuario = null;
            if (resul.next()) {
                usuario = new Usuario();
                usuario.setCorreoElectronico(resul.getString("correoElectronico"));
                usuario.setPaterno(resul.getString("paterno"));
                usuario.setMaterno(resul.getString("materno"));
                usuario.setNombre(resul.getString("nombre"));
                usuario.setPuesto(resul.getString("puesto"));
                usuario.setActivo(resul.getBoolean("activo"));
            }
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    public byte[] recuperarImagen(int idCurso) {
        try {
            byte []resultado = null;
            Connection conn = ConexionDBCursos.obtenerInstancia();
            String instSQL =
                    String.format("SELECT Imagen FROM curso_imagen WHERE IDCurso = %d", idCurso);
            Statement inst = conn.createStatement();
            ResultSet resul = inst.executeQuery(instSQL);
            if (resul.next())
                resultado = resul.getBytes("Imagen");
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }
}
