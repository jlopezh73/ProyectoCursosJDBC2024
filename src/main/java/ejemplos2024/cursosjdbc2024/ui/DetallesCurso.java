package ejemplos2024.cursosjdbc2024.ui;

import ejemplos2024.cursosjdbc2024.helpers.CursosImagenHelper;
import ejemplos2024.cursosjdbc2024.modelos.Curso;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

public class DetallesCurso extends VBox {
    private Label etiClave;
    private TextField txtClave;
    private Label etiNombre;
    private TextField txtNombre;
    private Label etiDescripcion;
    private TextArea txtDescripcion;
    private Label etiInsructor;
    private TextField txtInstructor;
    private Label etiNoHoras;
    private Spinner<Integer> spNoHoras;
    private Label etiCosto;
    private Spinner<Double> spCosto;
    private Label etiFechaInicio;
    private DatePicker dpFechaInicio;
    private Label etiFechaTermino;
    private DatePicker dpFechaTermino;
    private Curso curso;
    private BorderPane contenedor;
    private ScrollPane scrollPanelCurso;
    private Label etiFoto;
    private ImageView ivFoto;
    private Button btnAsignarFoto;
    private Button btnQuitarFoto;
    private byte[] datosFoto;

    public DetallesCurso(Curso curso) {
        this.curso = curso;
        inicializarPanelEdicionCurso();
        cargarDatosCurso(curso);
    }

    private void cargarDatosCurso(Curso curso) {
            txtClave.setText(curso.getClave());
            txtClave.setEditable(false);
            txtDescripcion.setText(curso.getDescripcion());
            txtDescripcion.setEditable(false);
            txtInstructor.setText(curso.getInstructor());
            txtInstructor.setEditable(false);
            txtNombre.setText(curso.getNombre());
            txtNombre.setEditable(false);
            dpFechaTermino.setValue(curso.getFechaTermino().toLocalDate());
            dpFechaTermino.setDisable(true);
            dpFechaInicio.setValue(curso.getFechaInicio().toLocalDate());
            dpFechaInicio.setDisable(true);
            spCosto.getValueFactory().setValue(curso.getCosto());
            spCosto.setDisable(true);
            spNoHoras.getValueFactory().setValue(curso.getNoHoras());
            spNoHoras.setDisable(true);
            if (curso.getImagen() == null) {
                CursosImagenHelper cih = new CursosImagenHelper();
                curso.setImagen(cih.recuperarImagen(curso.getId()));
            }
            if (curso.getImagen() != null)
                ivFoto.setImage(new Image(new ByteArrayInputStream(curso.getImagen())));
            else
                ivFoto.setImage(null);
    }
    private void inicializarPanelEdicionCurso() {
        setMinWidth(250);

        VBox contenedorCampos = new VBox();
        contenedorCampos.setSpacing(5);
        contenedorCampos.setPadding(new Insets(10, 30, 10, 10));
        LinearGradient linearGradient =
                new LinearGradient(0, 0, 0, 1, true,
                        CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE),new Stop(1,Color.DARKGRAY));

        contenedorCampos.setBackground(new Background(new BackgroundFill(linearGradient, null, null)));

        etiClave = new Label("Clave");
        txtClave = new TextField("");
        txtClave.setPromptText("Clave del curso");

        etiNombre = new Label("Nombre");
        txtNombre = new TextField("");
        txtNombre.setPromptText("Nombre del curso");

        etiDescripcion = new Label("Descripción");
        txtDescripcion = new TextArea("");
        txtDescripcion.setPromptText("Descripción del curso");
        txtDescripcion.setPrefColumnCount(20);
        txtDescripcion.setPrefRowCount(3);

        etiInsructor = new Label("Instructor");
        txtInstructor = new TextField("");
        txtInstructor.setPromptText("Instructor");

        etiNoHoras= new Label("No. Horas");
        spNoHoras = new Spinner<Integer>(10, 80, 20);
        spNoHoras.setPromptText("Instructor");

        etiCosto= new Label("Costo del curso");
        spCosto = new Spinner<Double>(100.0, 5000.0, 1000.0);
        spCosto.setPromptText("Instructor");

        etiFechaInicio= new Label("Fecha de Inicio");
        dpFechaInicio = new DatePicker(LocalDate.now().plusDays(1));
        dpFechaInicio.setPromptText("DD/MM/AAAA");

        etiFechaTermino= new Label("Fecha de Inicio");
        dpFechaTermino = new DatePicker(LocalDate.now().plusDays(31));
        dpFechaTermino.setPromptText("DD/MM/AAAA");

        etiFoto = new Label("Imagen del Curso", new FontIcon("far-image:32"));
        etiFoto.setAlignment(Pos.CENTER);
        etiFoto.setTextAlignment(TextAlignment.CENTER);
        etiFoto.setGraphicTextGap(10);

        ivFoto = new ImageView();
        ivFoto.setFitHeight(150);
        ivFoto.setFitWidth(240);
        ivFoto.setPreserveRatio(true);

        StackPane contenedorFoto = new StackPane();
        contenedorFoto.setAlignment(Pos.CENTER);

        Border borde = new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(5.0)));
        contenedorFoto.setBorder(borde);
        contenedorFoto.getChildren().addAll( etiFoto, ivFoto);



        contenedorCampos.getChildren().addAll(etiClave, txtClave, etiNombre, txtNombre,
                etiDescripcion, txtDescripcion, etiInsructor, txtInstructor, etiNoHoras, spNoHoras,
                etiCosto, spCosto, etiFechaInicio, dpFechaInicio, etiFechaTermino, dpFechaTermino,
                contenedorFoto);

        scrollPanelCurso = new ScrollPane(contenedorCampos);
        scrollPanelCurso.setFitToWidth(true);

        Label tituloEducionCursos = new Label("Captura de los Datos de un Curso");
        tituloEducionCursos.getStyleClass().add("panel-heading");
        tituloEducionCursos.getStyleClass().add("text-default");
        tituloEducionCursos.getStyleClass().add("text-default");
        tituloEducionCursos.getStyleClass().add("h4");

        getChildren().add(tituloEducionCursos);
        getChildren().add(scrollPanelCurso);

    }
}
