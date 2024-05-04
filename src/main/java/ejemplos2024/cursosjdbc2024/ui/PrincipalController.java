package ejemplos2024.cursosjdbc2024.ui;

import ejemplos2024.cursosjdbc2024.helpers.CursosHelper;
import ejemplos2024.cursosjdbc2024.modelos.Curso;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.util.List;

public class PrincipalController {
    @FXML
    private Hyperlink btnCursos;
    @FXML
    private ImageView fotoUsuario;
    @FXML
    private TilePane cursosView;

    public void initialize() {
        Rectangle rectangle = new Rectangle();
        rectangle.setArcWidth(48);
        rectangle.setArcHeight(48);
        rectangle.setHeight(48);
        rectangle.setWidth(48);
        fotoUsuario.setClip(rectangle);

        CursosHelper ch = new CursosHelper();
        List<Curso> listaCursos = ch.obtenerListaCursos();
        cursosView.setHgap(10);
        cursosView.setVgap(10);
        cursosView.setPrefColumns(3);
        cursosView.setPadding(new Insets(10));

        for (Curso curso : listaCursos) {
            var cursoView = new TarjetaCursoView(curso);
            cursosView.getChildren().add(cursoView);
        }
    }
    @FXML
    public void cursosOnAction() {
        Stage ventanaCursos = new Stage();

        var root = new ContenedorCursos();

        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        ventanaCursos.setTitle("Edición de Cursos");
        ventanaCursos.setScene(scene);
        ventanaCursos.show();
    }
}
