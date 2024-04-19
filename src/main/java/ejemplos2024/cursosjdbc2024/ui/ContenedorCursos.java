package ejemplos2024.cursosjdbc2024.ui;

import ejemplos2024.cursosjdbc2024.helpers.CursosHelper;
import ejemplos2024.cursosjdbc2024.modelos.Curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class ContenedorCursos extends BorderPane {
    private TableView<Curso> tablaCursos;
    private TableColumn<Curso, String> colClave;
    private TableColumn<Curso, String> colNombre;
    private TableColumn<Curso, String> colInstructor;
    private TableColumn<Curso, Integer> colNoHoras;
    private TableColumn<Curso, String> colCosto;
    private TableColumn<Curso, Date> colFechaInicio;
    private TableColumn<Curso, Date> colFechaTermino;
    private TitledPane paneCursos;
    private TitledPane paneOperacionesCursos;
    private Button btnAgregar;
    private Button btnModificar;
    private Button btnEliminar;
    private TitledPane paneEdicionCursos;
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


    private ObservableList<Curso> listaCursos;

    public ContenedorCursos() {
        inicializarComponentes();
        crearPanelEdicionCurso();
        cargarDatos();
        curso = null;
    }

    private void crearPanelEdicionCurso() {
        paneEdicionCursos = new TitledPane();
        paneEdicionCursos.setText("Captura de los Datos de un Curso");
        paneEdicionCursos.setMinWidth(250);

        VBox contenedorCampos = new VBox();
        contenedorCampos.setSpacing(5);
        contenedorCampos.setPadding(new Insets(10, 30, 10, 10));
        LinearGradient linearGradient =
                 new LinearGradient(0, 0, 0, 1, true,
                          CycleMethod.NO_CYCLE, new Stop(0,Color.WHITE),new Stop(1,Color.DARKGRAY));

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

        Button btnAceptar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(evt -> {
            ocultarPanelDerecho();
        });
        btnAceptar.setOnAction(evt -> {
            validarGuardarDatos();
        });

        HBox panelBotonesCurso = new HBox();
        panelBotonesCurso.setSpacing(10);
        panelBotonesCurso.setPadding(new Insets(20));
        panelBotonesCurso.getChildren().addAll(btnAceptar, btnCancelar);


        contenedorCampos.getChildren().addAll(etiClave, txtClave, etiNombre, txtNombre,
                etiDescripcion, txtDescripcion, etiInsructor, txtInstructor, etiNoHoras, spNoHoras,
                etiCosto, spCosto, etiFechaInicio, dpFechaInicio, etiFechaTermino, dpFechaTermino,
                panelBotonesCurso);
        paneEdicionCursos.setContent(new ScrollPane(contenedorCampos));


    }

    private void validarGuardarDatos() {
        ZoneId defaultZoneId = ZoneId.systemDefault();

        if (curso == null)
            curso = new Curso();
        curso.setClave(txtClave.getText());
        curso.setNombre(txtNombre.getText());
        curso.setDescripcion(txtDescripcion.getText());
        curso.setInstructor(txtInstructor.getText());
        var costo = spCosto.getValue();
        var horas = spNoHoras.getValue();
        curso.setCosto(costo);
        curso.setNoHoras(horas);
        curso.setFechaInicio(java.sql.Date.valueOf(dpFechaInicio.getValue()));
        curso.setFechaTermino(java.sql.Date.valueOf(dpFechaTermino.getValue()));

        if (validarCurso(curso) && guardarCurso(curso)) {
            ocultarPanelDerecho();
        } else {

        }
    }

    private boolean validarCurso(Curso curso) {
        String errores = "";
        boolean hayErrores=false;

        if (curso.getClave().isEmpty()) {
            errores += "* La clave del curso no puede estar vacía\n";
            hayErrores = true;
        }

        if (curso.getNombre().isEmpty()) {
            errores += "* El nombre del curso no puede estar vacía\n";
            hayErrores = true;
        }

        if (curso.getInstructor().isEmpty()) {
            errores += "* El instructor del curso no puede estar vacía\n";
            hayErrores = true;
        }

        if (hayErrores) {
            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error");
            alertaError.setHeaderText("Existen errores en los datos:");
            alertaError.setContentText(errores);
            alertaError.show();
        }

        return !hayErrores;
    }

    private boolean guardarCurso(Curso curso) {
        CursosHelper ch = new CursosHelper();
        ch.agregarCurso(curso);
        return true;
    }

    private void ocultarPanelDerecho() {
        setRight(null);
    }

    private void cargarDatos() {
        CursosHelper ch = new CursosHelper();
        List<Curso> cursos = ch.obtenerListaCursos();
        listaCursos = FXCollections.observableList(cursos);
        tablaCursos.setItems(listaCursos);
    }

    private void inicializarComponentes() {
        tablaCursos = new TableView<Curso>();

        colClave = new TableColumn<Curso, String>("Clave");
        colClave.setCellValueFactory(
                new PropertyValueFactory<Curso, String>("clave"));

        colNombre = new TableColumn<Curso, String>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<Curso, String>("nombre"));
        colNombre.setMinWidth(200);

        colInstructor = new TableColumn<Curso, String>("Instructor");
        colInstructor.setCellValueFactory(new PropertyValueFactory<Curso, String>("instructor"));
        colInstructor.setMinWidth(200);

        colNoHoras = new TableColumn<Curso, Integer>("No. Horas");
        colNoHoras.setCellValueFactory(new PropertyValueFactory<Curso, Integer>("noHoras"));


        colCosto = new TableColumn<Curso, String>("Costo");
        colCosto.setCellValueFactory(
                new PropertyValueFactory<Curso, String>("scosto"));
        colCosto.setMinWidth(100);
        colCosto.setStyle("-fx-alignment: CENTER-RIGHT;");

        colFechaInicio = new TableColumn<Curso, Date>("Fecha de Inicio");
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<Curso, Date>("fechaInicio"));

        colFechaTermino = new TableColumn<Curso, Date>("Fecha de Término");
        colFechaTermino.setCellValueFactory(new PropertyValueFactory<Curso, Date>("fechaTermino"));

        tablaCursos.getColumns().addAll(colClave, colNombre, colInstructor,
                colNoHoras, colCosto, colFechaInicio, colFechaTermino);

        paneCursos = new TitledPane();
        paneCursos.setText("Lista de Cursos Registrados");
        paneCursos.setContent(tablaCursos);
        paneCursos.setCollapsible(false);

        HBox contenedorBotones = new HBox();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(evt -> {
            curso = null;
            setRight(paneEdicionCursos);
        });
        btnModificar = new Button("Modificar");
        btnEliminar = new Button("Eliminar");
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        contenedorBotones.getChildren().addAll(btnAgregar, btnModificar, btnEliminar);

        paneOperacionesCursos = new TitledPane();
        paneOperacionesCursos.setText("Operaciones sobre cursos");
        paneOperacionesCursos.setContent(contenedorBotones);
        paneOperacionesCursos.setCollapsible(false);

        setCenter(paneCursos);
        setBottom(paneOperacionesCursos);
    }
}
