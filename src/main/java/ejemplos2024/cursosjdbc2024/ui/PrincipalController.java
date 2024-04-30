package ejemplos2024.cursosjdbc2024.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class PrincipalController {
    @FXML
    private Hyperlink btnCursos;
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
