module ejemplos2024.cursosjdbc2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires org.kordamp.ikonli.javafx;


    opens ejemplos2024.cursosjdbc2024 to javafx.fxml;
    opens ejemplos2024.cursosjdbc2024.modelos to javafx.fxml;
    exports ejemplos2024.cursosjdbc2024;
    exports ejemplos2024.cursosjdbc2024.modelos;
}