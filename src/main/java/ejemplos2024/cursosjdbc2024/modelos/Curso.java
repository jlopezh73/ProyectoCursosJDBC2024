package ejemplos2024.cursosjdbc2024.modelos;

import java.sql.Date;


public class Curso {
    private int id;
    private String clave;
    private String nombre;
    private String descripcion;
    private String instructor;
    private int noHoras;
    private Date fechaInicio;
    private Date fechaTermino;
    private double costo;
    private byte []imagen;

    public Curso() {
    }

    public Curso(int id, String clave, String nombre, String descripcion, String instructor, int noHoras, Date fechaInicio, Date fechaTermino, double costo) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.instructor = instructor;
        this.noHoras = noHoras;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getNoHoras() {
        return noHoras;
    }

    public void setNoHoras(int noHoras) {
        this.noHoras = noHoras;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getScosto() {
        return "$ "+costo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "clave='" + clave + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
