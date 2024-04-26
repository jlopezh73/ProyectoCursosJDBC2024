package ejemplos2024.cursosjdbc2024.modelos;

public class Usuario {
    private String correoElectronico;
    private String paterno;
    private String materno;
    private String nombre;
    private String Puesto;
    private boolean activo;

    public Usuario() {

    }

    public Usuario(String correoElectronico, String paterno, String materno, String nombre, String puesto, boolean activo) {
        this.correoElectronico = correoElectronico;
        this.paterno = paterno;
        this.materno = materno;
        this.nombre = nombre;
        Puesto = puesto;
        this.activo = activo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
