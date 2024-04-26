package ejemplos2024.cursosjdbc2024.helpers;

import ejemplos2024.cursosjdbc2024.modelos.Curso;
import ejemplos2024.cursosjdbc2024.modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuariosHelper {
    public Usuario validarUsuario(String correoElectronico, String password) {
        try {
            Connection conn = ConexionDBCursos.obtenerInstancia();
            
            String instSQL = String.format("select * from usuario where correoElectronico='%s' and password='%s'", correoElectronico,  password);

            PreparedStatement inst = conn.prepareStatement(instSQL);
            ResultSet resul = inst.executeQuery();
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
