package ejemplos2024.cursosjdbc2024;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ejemplos2024.cursosjdbc2024.ui.ContenedorCursos;

import java.io.IOException;

public class CursosApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var root = new ContenedorCursos();
        Scene scene = new Scene(root, 1000, 400);
        stage.setTitle("Gestión de Cursos de la FEI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}