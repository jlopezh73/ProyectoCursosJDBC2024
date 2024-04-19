package ejemplos2024.cursosjdbc2024.helpers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDBCursos {
    private static Connection conn=null;
    protected ConexionDBCursos() {

    }
    public static Connection obtenerInstancia() throws Exception {
        if (conn == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/cursos?user=cursos&password=123456");
        }
        return conn;
    }

}
