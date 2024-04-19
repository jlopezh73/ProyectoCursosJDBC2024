package ejemplos2024.cursosjdbc2024.helpers;

import ejemplos2024.cursosjdbc2024.modelos.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursosHelper {
    public List<Curso> obtenerListaCursos() {
        try {
            Connection conn = ConexionDBCursos.obtenerInstancia();

            Statement inst = conn.createStatement();
            ResultSet resultadoCursos = inst
                     .executeQuery("select * from curso order by nombre");
            List<Curso> listaCursos = new ArrayList<Curso>();
            while(resultadoCursos.next()) {
                int id = resultadoCursos.getInt("ID");
                String clave = resultadoCursos.getString("Clave");
                String nombre = resultadoCursos.getString("Nombre");
                String descripcion = resultadoCursos.getString("Descripcion");
                String instructor = resultadoCursos.getString("Instructor");
                int noHoras = resultadoCursos.getInt("NoHoras");
                Date fechaInicio = resultadoCursos.getDate("FechaInicio");
                Date fechaTermino = resultadoCursos.getDate("FechaTermino");
                double costo = resultadoCursos.getDouble("Costo");

                Curso curso = new Curso(id, clave, nombre, descripcion, instructor, noHoras, fechaInicio,
                        fechaTermino, costo);
                listaCursos.add(curso);
            }
            return listaCursos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean agregarCurso(Curso curso) {
        try {
            Connection conn = ConexionDBCursos.obtenerInstancia();
            Statement inst = conn.createStatement();
            String fechaInicio = "2024-03-16";
            String fechaTermino = "2024-04-15";
            String instSQL = String.format(
                    "insert into curso (Clave, Nombre, Descripcion, NoHoras, FechaInicio, FechaTermino, Costo, Instructor)"+
                    " values ('%s', '%s', '%s', %d, '%s', '%s', %f, '%s')",
                    curso.getClave(), curso.getNombre(), curso.getDescripcion(), curso.getNoHoras(), fechaInicio, fechaTermino,
                    curso.getCosto(), curso.getInstructor()
                    );
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
