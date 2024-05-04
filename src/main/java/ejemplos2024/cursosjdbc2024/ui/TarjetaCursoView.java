package ejemplos2024.cursosjdbc2024.ui;

import ejemplos2024.cursosjdbc2024.modelos.Curso;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TarjetaCursoView extends VBox {
    private Label etiClave;
    private Label etiNombre;
    private Label etiInstructor;
    private Label etiInicio;
    private Label etiTérmino;
    private Hyperlink verDetalles;
    private ImageView foto;
    private Curso curso;

    public TarjetaCursoView(Curso curso) {
        this.curso = curso;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        this.setPrefSize(250, 180);
        this.setStyle("-fx-background-color: #fff; -fx-border-color: #aaa;");
    }

}
