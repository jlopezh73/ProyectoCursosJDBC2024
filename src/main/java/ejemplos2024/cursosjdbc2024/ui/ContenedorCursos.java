package ejemplos2024.cursosjdbc2024.ui;

import ejemplos2024.cursosjdbc2024.helpers.CursosHelper;
import ejemplos2024.cursosjdbc2024.helpers.CursosImagenHelper;
import ejemplos2024.cursosjdbc2024.modelos.Curso;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;
import org.kordamp.ikonli.javafx.FontIcon;

public class ContenedorCursos extends Panel {
    private TableView<Curso> tablaCursos;
    private TableColumn<Curso, String> colClave;
    private TableColumn<Curso, String> colNombre;
    private TableColumn<Curso, String> colInstructor;
    private TableColumn<Curso, Integer> colNoHoras;
    private TableColumn<Curso, String> colCosto;
    private TableColumn<Curso, Date> colFechaInicio;
    private TableColumn<Curso, Date> colFechaTermino;
    private Button btnAgregar;
    private Button btnModificar;
    private Button btnEliminar;
    private VBox paneEdicionCursos;
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


    private ObservableList<Curso> listaCursos;

    public ContenedorCursos() {
        super("Registro y Edición de Cursos");
        getStyleClass().add("panel-primary");

        inicializarComponentes();
        cargarDatos();
        curso = null;
    }

    private void inicializarComponentes() {
        inicializarTabla();
        inicializarPanelBotones();
        inicializarPanelEdicionCurso();
    }

    private void inicializarTabla() {
        tablaCursos = new TableView<Curso>();
        tablaCursos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tablaCursos.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Curso>() {
            @Override
            public void onChanged(Change<? extends Curso> change) {
                var seleccionados =change.getList();

                if (seleccionados.size() > 0) {
                    curso = seleccionados.get(0);
                    btnModificar.setDisable(false);
                    btnEliminar.setDisable(false);
                } else {
                    btnModificar.setDisable(true);
                    btnEliminar.setDisable(true);
                }
            }
        });

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

        VBox contenedorCursos = new VBox();
        Label tituloCursos = new Label("Lista de Cursos Registrados");
        tituloCursos.getStyleClass().add("text-primary");
        tituloCursos.getStyleClass().add("h4");
        contenedorCursos.getChildren().add(tituloCursos);
        contenedorCursos.getChildren().add(tablaCursos);
        setBody(contenedorCursos);
    }

    private void inicializarPanelBotones() {
        HBox contenedorBotones = new HBox();
        contenedorBotones.setSpacing(5);

        btnAgregar = new Button("Agregar");
        btnAgregar.getStyleClass().add("btn");
        btnAgregar.getStyleClass().add("btn-primary");
        btnAgregar.setOnAction(evt -> {
            curso = null;
            datosFoto = null;
            cargarDatosCurso(curso);
            mostrarPanelDerecho();
        });
        btnModificar = new Button("Modificar");
        btnModificar.getStyleClass().add("btn");
        btnModificar.getStyleClass().add("btn-info");
        btnModificar.setOnAction(evt -> {
            cargarDatosCurso(curso);
            mostrarPanelDerecho();
        });

        btnEliminar = new Button("Eliminar");
        btnEliminar.getStyleClass().add("btn");
        btnEliminar.getStyleClass().add("btn-danger");
        btnEliminar.setOnAction(evt -> {
            if (eliminarCurso(curso)) {
                Alert alertaInformacion = new Alert(Alert.AlertType.INFORMATION);
                alertaInformacion.setTitle("Información");
                alertaInformacion.setHeaderText("El Curso fue eliminado de manera satiscactoria");
                alertaInformacion.setContentText(curso.getNombre());
                alertaInformacion.show();
            } else {
                Alert alertaError = new Alert(Alert.AlertType.ERROR);
                alertaError.setTitle("Información");
                alertaError.setHeaderText("El Curso no pudo ser eliminado");
                alertaError.setContentText(curso.getNombre());
                alertaError.show();
            }
        });

        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        contenedorBotones.getChildren().addAll(btnAgregar, btnModificar, btnEliminar);

        setFooter(contenedorBotones);
    }

    private void cargarDatosCurso(Curso curso) {
        if (curso == null) {
            txtClave.setText("");
            txtDescripcion.setText("");
            txtInstructor.setText("");
            txtNombre.setText("");
            dpFechaInicio.setValue(LocalDate.now().plusDays(1));
            dpFechaTermino.setValue(LocalDate.now().plusDays(31));
        } else {
            txtClave.setText(curso.getClave());
            txtDescripcion.setText(curso.getDescripcion());
            txtInstructor.setText(curso.getInstructor());
            txtNombre.setText(curso.getNombre());
            dpFechaTermino.setValue(curso.getFechaTermino().toLocalDate());
            dpFechaInicio.setValue(curso.getFechaInicio().toLocalDate());
            spCosto.getValueFactory().setValue(curso.getCosto());
            spNoHoras.getValueFactory().setValue(curso.getNoHoras());
            if (curso.getImagen() == null) {
                CursosImagenHelper cih = new CursosImagenHelper();
                curso.setImagen(cih.recuperarImagen(curso.getId()));
            }
            if (curso.getImagen() != null)
                ivFoto.setImage(new Image(new ByteArrayInputStream(curso.getImagen())));
            else
                ivFoto.setImage(null);
        }
    }

    private void inicializarPanelEdicionCurso() {
        paneEdicionCursos = new VBox();
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

        btnAsignarFoto = new Button("Cargar Imagen");
        btnAsignarFoto.setGraphic(new FontIcon("far-file-image:16:BLUE"));
        btnAsignarFoto.setOnAction(evt -> {
            buscarImagenes();
        });
        btnQuitarFoto = new Button("Quitar Imagen");
        btnQuitarFoto.setGraphic(new FontIcon("fas-eraser:16:BLUE"));
        btnQuitarFoto.setDisable(true);
        btnQuitarFoto.setOnAction(evt-> {
            quitarImagen();
        });

        HBox contenedorBotonesFoto = new HBox();
        contenedorBotonesFoto.setSpacing(10);
        contenedorBotonesFoto.getChildren().addAll(btnAsignarFoto, btnQuitarFoto);

        Button btnAceptar = new Button("Aceptar");
        btnAceptar.getStyleClass().add("btn");
        btnAceptar.getStyleClass().add("btn-primary");
        btnAceptar.getStyleClass().add("btn-sm");

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.getStyleClass().add("btn");
        btnCancelar.getStyleClass().add("btn-danger");
        btnCancelar.getStyleClass().add("btn-sm");

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
                contenedorFoto, contenedorBotonesFoto,
                panelBotonesCurso);

        scrollPanelCurso = new ScrollPane(contenedorCampos);
        scrollPanelCurso.setFitToWidth(true);

        Label tituloEducionCursos = new Label("Captura de los Datos de un Curso");
        tituloEducionCursos.getStyleClass().add("panel-heading");
        tituloEducionCursos.getStyleClass().add("text-default");
        tituloEducionCursos.getStyleClass().add("text-default");
        tituloEducionCursos.getStyleClass().add("h4");

        paneEdicionCursos.getChildren().add(tituloEducionCursos);
        paneEdicionCursos.getChildren().add(scrollPanelCurso);

    }

    private void quitarImagen() {
        ivFoto.setImage(null);
        btnQuitarFoto.setDisable(true);
        datosFoto = null;
    }

    private void buscarImagenes() {
        FileChooser dialogoAbrirArchivo = new FileChooser();
        dialogoAbrirArchivo.setTitle("Seleccionar imagen");
        dialogoAbrirArchivo.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes (*.jpg; *.jpeg; *.png)",
                                    "*.jpg", "*.jpeg","*.png"),
                new FileChooser.ExtensionFilter("Todos los archivos",
                                    "*.*"));
        File archivo = dialogoAbrirArchivo.showOpenDialog(null);
        if (archivo != null) {
            ivFoto.setImage(new Image(archivo.toURI().toString()));
            try {
                FileInputStream fis = new FileInputStream(archivo);
                datosFoto =fis.readAllBytes();
                btnQuitarFoto.setDisable(false);
            } catch (IOException e) {

            }
        }
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
        curso.setImagen(datosFoto);

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
        if (curso.getId() == 0)
            ch.agregarCurso(curso);
        else
            ch.modificarCurso(curso);

        if (curso.getImagen() != null) {
            CursosImagenHelper cih = new CursosImagenHelper();
            cih.asignarImagen(curso);
        }
        return true;
    }

    private boolean eliminarCurso(Curso curso) {
        Alert alertaError = new Alert(Alert.AlertType.CONFIRMATION);
        alertaError.setTitle("Advertencia");
        alertaError.setHeaderText("Esta acción no se puede deshacer. ¿Está seguro de querer eliminar el curso?");
        alertaError.setContentText(curso.getNombre());
        if (alertaError.showAndWait().get() == ButtonType.OK) {
            CursosHelper ch = new CursosHelper();
            ch.eliminarCurso(curso);
            listaCursos.remove(curso);
            return true;
        } else
            return false;
    }

    private void ocultarPanelDerecho() {
        setRight(null);
    }

    private void mostrarPanelDerecho() {
        scrollPanelCurso.setVvalue(0);
        setRight(paneEdicionCursos);
    }

    private void cargarDatos() {
        CursosHelper ch = new CursosHelper();
        List<Curso> cursos = ch.obtenerListaCursos();
        listaCursos = FXCollections.observableList(cursos);
        tablaCursos.setItems(listaCursos);
    }


}
