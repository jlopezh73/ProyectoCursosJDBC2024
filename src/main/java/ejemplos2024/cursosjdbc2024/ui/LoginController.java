package ejemplos2024.cursosjdbc2024.ui;

import ejemplos2024.cursosjdbc2024.helpers.UsuariosHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kordamp.bootstrapfx.BootstrapFX;

public class LoginController {
    @FXML
    public TextField correoElectronico;
    @FXML
    public PasswordField password;
    @FXML
    public Button iniciarSesion;
    @FXML
    public void botonOnAction() {
        var sCorreo = correoElectronico.getText().toString();
        var sPassword = password.getText().toString();
        UsuariosHelper uh = new UsuariosHelper();
        var usuario = uh.validarUsuario(sCorreo, sPassword);
        if (usuario != null) {
            Stage ventanaCursos = new Stage();

            var root = new ContenedorCursos();

            Scene scene = new Scene(root.load(), 800, 600);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            ventanaCursos.setTitle("Gestión de Cursos de la FEI");
            ventanaCursos.setScene(scene);
            ventanaCursos.show();
        } else {

        }
    }
}
