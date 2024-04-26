package ejemplos2024.cursosjdbc2024.helpers;

import ejemplos2024.cursosjdbc2024.modelos.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuariosHelper {
    public Usuario validarUsuario(String correoElectronico, String password) {
        try {
            Connection conn = ConexionDBCursos.obtenerInstancia();
            String instSQL =
                    "select * from ";

            PreparedStatement inst = conn.prepareStatement(instSQL);
            inst.setInt(1, curso.getId());
            inst.setBytes(2, curso.getImagen());
            inst.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
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
