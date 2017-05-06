package Controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.layout.HBox;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;

import javafx.scene.control.ToggleGroup;

import javafx.event.ActionEvent;

import javafx.scene.control.ToggleButton;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.RadioButton;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;

import javafx.scene.layout.Pane;

public class TelaDadosAlunoController {
	@FXML
	private AnchorPane aPaneTelaDadosAlun;
	@FXML
	private Pane paneDadosAluno;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblNome;
	@FXML
	private TextField txtResponsavel;
	@FXML
	private Label lblTelefone;
	@FXML
	private Label lblMatricula;
	@FXML
	private TextField txtEndereco;
	@FXML
	private TextField txtCpf;
	@FXML
	private Label lblDataNasc;
	@FXML
	private HBox hbCrud;
	@FXML
	private Button btnAlterar;
	@FXML
	private Button btnSalvar;
	@FXML
	private Label lblEnderco;
	@FXML
	private TextField txtCurso;
	@FXML
	private DatePicker dateDataNasc;
	@FXML
	private Label lblCpf;
	@FXML
	private TextField txtCity;
	@FXML
	private HBox hbSexo;
	@FXML
	private RadioButton rbMasculino;
	@FXML
	private ToggleGroup groupSexo;
	@FXML
	private RadioButton rbFeminino;
	@FXML
	private Label lblCity;
	@FXML
	private TextField txtNome;
	@FXML
	private Label lblResponsavel;
	@FXML
	private TextField txtTelefone;
	@FXML
	private Label lblCurso;
	@FXML
	private Label lblSexo;
	@FXML
	private TextField txtMatricula;
	@FXML
	private TextField txtEmail;
	@FXML
	private HBox hbAbrirCancel;
	@FXML
	private Button btnAbrirNovoPoc;
	@FXML
	private Button btnCancelar;
	@FXML
	private TableView tabProceAluno;
	@FXML
	private TableColumn tblBuscAlun;
	@FXML
	private TableColumn colunProtocologo;
	@FXML
	private TableColumn colunNome;
	@FXML
	private TableColumn colunStatus;
	@FXML
	private ToggleButton btnBuscar;

	// Event Listener on Button[#btnAlterar].onAction
	@FXML
	public void editarProcesso(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnSalvar].onAction
	@FXML
	public void salvarProcesso(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnAbrirNovoPoc].onAction
	@FXML
	public void abrirNovoProcesso(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ToggleButton[#btnBuscar].onAction
	@FXML
	public void buscarAluno(ActionEvent event) {
		// TODO Autogenerated
	}
}
