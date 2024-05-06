package ejemplos2024.cursosjdbc2024.ui;

import ejemplos2024.cursosjdbc2024.modelos.Curso;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class TarjetaCursoView extends Pane {
    private Label etiClave;
    private Label etiNombre;
    private Label etiInstructor;
    private Label etiInicio;
    private Label etiTermino;
    private Hyperlink verDetalles;
    private ImageView foto;
    private Curso curso;

    public TarjetaCursoView(Curso curso) {
        this.curso = curso;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        String imagenes[] = {"foto1.png","foto2.png","foto3.png","foto4.png","foto5.png","foto6.png","foto7.png","foto8.png"};

        this.setPrefSize(250, 180);
        this.setStyle("-fx-background-color: #fff; -fx-border-color: #aaa;");

        Rectangle recorte = new Rectangle();
        recorte.setWidth(248);
        recorte.setHeight(98);

        String nombreFoto = imagenes[(int) (Math.random()*8.0)];
        foto = new ImageView(new Image(getClass().getClassLoader().getResource(nombreFoto).toExternalForm()));
        foto.setPreserveRatio(true);
        foto.setFitWidth(248);
        foto.setLayoutY(0);
        foto.setLayoutX(0);
        foto.setClip(recorte);

        etiClave = new Label(curso.getClave());
        etiClave.setStyle("-fx-font-size: 7pt");
        etiClave.setLayoutY(100);
        etiClave.setLayoutX(10);

        etiNombre = new Label(curso.getNombre());
        etiNombre.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
        etiNombre.setLayoutY(110);
        etiNombre.setLayoutX(10);

        etiInicio = new Label(curso.getFechaInicio().toString());
        etiInicio.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold;");
        etiInicio.setLayoutY(135);
        etiInicio.setLayoutX(10);

        etiTermino = new Label(curso.getFechaTermino().toString());
        etiTermino.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold;");
        etiTermino.setLayoutY(135);
        etiTermino.setLayoutX(90);

        verDetalles = new Hyperlink("Ver detalles");
        verDetalles.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold;");
        verDetalles.setLayoutY(150);
        verDetalles.setLayoutX(10);


        getChildren().addAll(foto, etiClave, etiNombre, etiInicio, etiTermino, verDetalles);
    }

}
