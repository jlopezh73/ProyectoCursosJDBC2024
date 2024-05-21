package ejemplos2024.cursosjdbc2024.ui;

import ejemplos2024.cursosjdbc2024.helpers.UsuariosHelper;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
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
    private Stage loginStage=null;
    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }
    @FXML
    public void botonOnAction() {
        validarUsuario();
    }

    private void validarUsuario() {
        var sCorreo = correoElectronico.getText().toString();
        var sPassword = password.getText().toString();
        UsuariosHelper uh = new UsuariosHelper();
        var usuario = uh.validarUsuario(sCorreo, sPassword);


        if (usuario != null) {
            try {
                loginStage.hide();

                Stage ventanaPrincipal = new Stage();

                var root = new FXMLLoader(getClass().getClassLoader().getResource("Principal.fxml"));

                Scene scene = new Scene(root.load(), 800, 600);
                //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                scene.getStylesheets().add(getClass().getClassLoader().getResource("principal.css")
                        .toExternalForm());
                ventanaPrincipal.setTitle("Gestión de Cursos de la FEI");
                ventanaPrincipal.widthProperty().addListener(evt -> {
                    var controlador = (PrincipalController)root.getController();
                    controlador.ajustarAncho(((ReadOnlyDoubleProperty) evt).getValue());
                });
                ventanaPrincipal.setScene(scene);
                ventanaPrincipal.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {

        }
    }
}
