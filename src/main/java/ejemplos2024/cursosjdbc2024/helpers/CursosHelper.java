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
            String fechaInicio = String.format("%4d-%02d-%02d",
                    curso.getFechaInicio().getYear()+1900,
                    curso.getFechaInicio().getMonth()+1,
                    curso.getFechaInicio().getDate()
                    );
            String fechaTermino = String.format("%4d-%02d-%02d",
                    curso.getFechaTermino().getYear()+1900,
                    curso.getFechaTermino().getMonth()+1,
                    curso.getFechaTermino().getDate()
            );
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

    public boolean modificarCurso(Curso curso) {
        try {
            Connection conn = ConexionDBCursos.obtenerInstancia();
            Statement inst = conn.createStatement();
            String fechaInicio = String.format("%4d-%02d-%02d",
                    curso.getFechaInicio().getYear()+1900,
                    curso.getFechaInicio().getMonth()+1,
                    curso.getFechaInicio().getDate()
            );
            String fechaTermino = String.format("%4d-%02d-%02d",
                    curso.getFechaTermino().getYear()+1900,
                    curso.getFechaTermino().getMonth()+1,
                    curso.getFechaTermino().getDate()
            );
            String instSQL = String.format(
                    "update curso set Clave='%s', Nombre='%s', Descripcion='%s', NoHoras=%d, FechaInicio='%s', FechaTermino='%s', Costo=%f, Instructor='%s' where id = %d",
                    curso.getClave(), curso.getNombre(), curso.getDescripcion(), curso.getNoHoras(), fechaInicio, fechaTermino,
                    curso.getCosto(), curso.getInstructor(), curso.getId()
            );
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarCurso(Curso curso) {
        try {
            Connection conn = ConexionDBCursos.obtenerInstancia();
            Statement inst = conn.createStatement();
            String instSQL = String.format(
                    "delete from curso  where id = %d", curso.getId()
            );
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
