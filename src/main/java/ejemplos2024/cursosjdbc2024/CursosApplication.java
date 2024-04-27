package ejemplos2024.cursosjdbc2024;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ejemplos2024.cursosjdbc2024.ui.ContenedorCursos;
import javafx.stage.StageStyle;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;


import java.io.IOException;

public class CursosApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //var root = new ContenedorCursos();
        var root = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
        Scene scene = new Scene(root.load(), 800, 500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Gestión de Cursos de la FEI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}