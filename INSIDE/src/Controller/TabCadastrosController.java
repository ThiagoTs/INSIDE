package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.scene.control.TreeTableColumn;

import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.controlsfx.control.Notifications;

import com.sun.javafx.scene.layout.region.BorderStyleConverter;


import Modelo.Administrador;
import Modelo.Documentos;
import Modelo.Processo;
import Negocio.AdmNegocio;
import Negocio.DocumentosNegocio;
import application.Main;
import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.ToggleButton;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.control.Tab;

import javafx.scene.control.RadioButton;

public class TabCadastrosController implements Initializable {
	@FXML
	private AnchorPane aPaneCadastro;
	@FXML
	private TabPane tabPaneCad;
	@FXML
	private Tab tabTipoProc;
	@FXML
	private AnchorPane aPaneTipoProc;
	@FXML
	private HBox hbDocumento;
	@FXML
	private ComboBox comboDocumentos;
	@FXML
	private ToggleButton btnAddDocTab;
	@FXML
	private HBox hbCrudProc;
	@FXML
	private Button btnExcluirProc;
	@FXML
	private Button btnAlterarProc;
	@FXML
	private Button btnSalvarProc;
	@FXML
	private TableColumn<Documentos,String> colunDocs;
	@FXML
	private TableView<Documentos> tblDocs;
	@FXML
	private TableColumn<Documentos,String> colunCadDoc;
	@FXML
	private TableView<Documentos> tblCadDoc;
	@FXML
	private TableColumn<Administrador,String> colunCadFluxo;
	@FXML
	private TableView<Administrador> tblCadFluxo;
	@FXML
	private HBox hbBuscaResp;
	@FXML
	private TextField txtBuscResp;
	@FXML
	private ToggleButton btnAddResp;
	@FXML
	private TextField txtNomeProc;
	@FXML
	private Text textCadTipoProc;
	@FXML
	private TextArea txtDescricao;
	@FXML
	private ComboBox comboTiposProc;
	@FXML
	private Tab tabUsuario;
	@FXML
	private AnchorPane aPaneUser;
	@FXML
	private ComboBox comboTipoUser;
	@FXML
	private TextField txtTelefone;
	@FXML
	private HBox hbSexo;
	@FXML
	private RadioButton rbMasculino;
	@FXML
	private ToggleGroup groupSexo;
	@FXML
	private RadioButton rbFeminino;
	@FXML
	private TextField txtMatricula;
	@FXML
	private TextField txtEmail;
	@FXML
	private ComboBox comboCargo;
	@FXML
	private HBox hbCrud;
	@FXML
	private Button btnAlterarUser;
	@FXML
	private Button btnSalvarUser;
	@FXML
	private TextField txtCpf;
	@FXML
	private ComboBox comboDepart;
	@FXML
	private TextField txtNome;
	@FXML
	private Text textCadUser;
	@FXML
	private TextField txtUser;
	@FXML
	private TextField txtBuscaUser;
	@FXML
	private TextField txtEnderco;
	@FXML
	private ToggleButton btnBuscaUser;
	@FXML
	private ComboBox comboTipoBuscaUser;
	@FXML
	private Tab tabDocumentos;
	@FXML
	private AnchorPane aPaneDoc;
	@FXML
	private TextField txtNomeDoc;
	@FXML
	private HBox hbCrudDoc;
	@FXML
	private Button btnAlterarDoc;
	@FXML
	private Button btnSalvarDoc;
	@FXML
	private ToggleButton btnAddDoc;
	@FXML
	private TextField txtId;
	@FXML
	private Button btnExcluirUser;

	List<Administrador> listAdm =  new ArrayList();
	AdmNegocio admNegocio = new AdmNegocio();
	Administrador adm = new Administrador();
	Main main = null;
	Processo proc = new Processo();
	Documentos doc = new Documentos();
	ObservableList<Documentos> docView = null;
	List<Documentos> docList = new ArrayList<>();
	List<Documentos> docList2 = new ArrayList<>();
	DocumentosNegocio docNegocio = new DocumentosNegocio();
	int cont=0;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherTipoPesquisa();
		preencherDepartamento();
		preencherTipoUser();
		preencherTipoCargo();
		btnAlterarUser.setDisable(true);
		populaViewDocs();
		populaComboDoc();
	}



	@FXML
	public void salvarProcesso(ActionEvent event) {

	}

	private void pegaValores(Administrador adm) {

		adm.setNome(txtNome.getText());
		adm.setCpf(txtCpf.getText());
		adm.setLogin(txtUser.getText());
		adm.setTelefone(txtTelefone.getText());
		adm.setMatricula(txtMatricula.getText());
		RadioButton rb = new RadioButton();
		rb =(RadioButton) groupSexo.getSelectedToggle();
		adm.setSexo(rb.getText());
		adm.setEmail(txtEmail.getText());
		adm.setCargo(comboCargo.getValue().toString());
		adm.setDepartamento(comboDepart.getValue().toString());
		adm.setTipoUser(comboTipoUser.getValue().toString());
		if(adm.getId()==0){
			adm.setSenha("123456");
		}
	}

	@FXML
	public void salvarUsuario() throws SQLException {
		String validar;
		boolean validacao = false;
		pegaValores(adm);
		validacao = validarCampos();
		if(validacao) {
			if (adm.getId() == 0) {
				validar = admNegocio.salvar(adm);
				if(validar.equals("salvo")) {
					String msg = "Usu�rio inserido!";
					exibeMensagem(msg);
					limpaCampos();
					btnExcluirUser.setDisable(true);
				}else{
					exibeMensagem(validar);
				}
			} else {
				validar = admNegocio.editar(adm);
				if (validar.equals("salvo")) {

					String msg = "Objeto editado com sucesso!";
					exibeMensagem(msg);

					limpaCampos();
					btnExcluirUser.setDisable(true);
				}else{
					exibeMensagem(validar);
				}
			}
		}

	}
	@FXML
	public void editarUsuario(ActionEvent event) {
		String msg = "Usu�rio pronto para edi��o!";
		exibeMensagem(msg);
		btnSalvarUser.setDisable(false);
		btnAlterarUser.setDisable(true);
		btnExcluirUser.setDisable(true);
		ablitarCamposUser();
	}

	public List<Administrador> listarAdms(){
		this.listAdm= admNegocio.listarAdms();
		return listAdm;
	}

	@FXML
	public void buscarUsuarios(ActionEvent  event)throws Exception {
		verificarTipoBusca();
		setarValores();
		btnAlterarUser.setDisable(false);
		txtBuscaUser.setText("");
		comboTipoBuscaUser.setValue("Selecione um item");

	}
	public void verificarTipoBusca(){
		adm = new Administrador();
		listAdm = new ArrayList();
		String tipo = comboTipoBuscaUser.getValue().toString();
		listAdm = listarAdms();

		if(tipo.equals("Nome")){
			listAdm.forEach( adm2 -> {
				if(adm2.getNome().equals(txtBuscaUser.getText())){
					this.adm=adm2;
				}
			}	);
		}else{
			if(tipo.equals("CPF")){
				listAdm.forEach( adm2 -> {
					if(adm2.getCpf().equals(txtBuscaUser.getText())){
						this.adm=adm2;
					}
				}	);
			}else{
				if(tipo.equals("Matr�cula")){
					listAdm.forEach( adm2 -> {
						if(adm2.getMatricula().equals(txtBuscaUser.getText())){
							this.adm=adm2;
						}
					}	);
				}
			}
		}

	}
	public boolean validarCampos(){

		String nome =  txtNome.getText();
		String cpf = txtCpf.getText();
		String Usuario = txtUser.getText();
		String endereco =  txtUser.getText();
		String matricula =  txtMatricula.getText();
		RadioButton rb = new RadioButton();
		rb = (RadioButton) groupSexo.getSelectedToggle();
		String email = txtEmail.getText();
		String cargo = (String) comboCargo.getSelectionModel().getSelectedItem();
		String Departamento = (String) comboDepart.getSelectionModel().getSelectedItem();
		String TipoUser = (String) comboTipoUser.getSelectionModel().getSelectedItem();

		List<Control>  controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(nome.equals("") || nome == null){
			sb.append("O nome n�o pode estar vazio!. \n");
			controls.add(txtNome);
		}
		if(cpf.equals("") || cpf == null){
			sb.append("O CPF n�o pode estar vazio!. \n");
			controls.add(txtCpf);
		}
		if(Usuario.equals("") || Usuario == null){
			sb.append("O usu�rio n�o pode estar vazio!. \n");
			controls.add(txtUser);
		}
		if(endereco.equals("") || endereco == null){
			sb.append("O endere�o n�o pode estar vazio!. \n");
			controls.add(txtEnderco);
		}
		if(matricula.equals("") || matricula == null){
			sb.append("A matr�cula n�o pode estar vazio!. \n");
			controls.add(txtMatricula);
		}
		if(rb.equals("") || rb == null){
			sb.append("Selecione o sexo!. \n");
			controls.add(rbMasculino);
			controls.add(rbFeminino);
		}
		if(email.equals("") || email == null){
			sb.append("O E-mail n�o pode estar vazio!. \n");
			controls.add(txtEmail);
		}
		if(cargo.equals("") || cargo == null){
			sb.append("Selecione um cargo! . \n");
			controls.add(comboCargo);
		}
		if(Departamento.equals("") || Departamento == null){
			sb.append("Selecione um departamento! . \n");
			controls.add(comboDepart);
		}
		if(TipoUser.equals("") || TipoUser == null){
			sb.append("Selecione o tipo de usu�rio! . \n");
			controls.add(comboTipoUser);
		}
		if(!sb.equals("")) {
			exibeMensagem(sb.toString());
			animaCamposValidados(controls);
		}

		return sb.toString().isEmpty();
	}
	public void setarValores() {

		txtNome.setText(adm.getNome());
		txtCpf.setText(adm.getCpf());
		txtUser.setText(adm.getLogin());
		txtTelefone.setText(adm.getTelefone());
		txtMatricula.setText(adm.getMatricula());
		if(adm.getSexo().equals("Masculino")) {
			groupSexo.selectToggle(rbMasculino);
		}else{
			groupSexo.selectToggle(rbFeminino);
		}
		txtEmail.setText(adm.getEmail());
		comboCargo.setValue(adm.getCargo());
		comboDepart.setValue(adm.getDepartamento());
		comboTipoUser.setValue(adm.getTipoUser());

		txtNome.setEditable(false);
		txtCpf.setEditable(false);
		txtUser.setEditable(false);
		txtTelefone.setEditable(false);
		rbFeminino.setDisable(true);
		rbMasculino.setDisable(true);
		txtMatricula.setEditable(false);
		txtEmail.setEditable(false);
		comboCargo.setDisable(true);
		comboDepart.setDisable(true);
		comboTipoUser.setDisable(true);
		btnSalvarUser.setDisable(true);
		btnExcluirUser.setDisable(false);

	}
	public void ablitarCamposUser(){

		txtNome.setEditable(true);
		txtCpf.setEditable(true);
		txtUser.setEditable(true);
		txtTelefone.setEditable(true);
		rbFeminino.setDisable(false);
		rbMasculino.setDisable(false);
		txtMatricula.setEditable(true);
		txtEmail.setEditable(true);
		comboCargo.setDisable(false);
		comboDepart.setDisable(false);
		comboTipoUser.setDisable(false);
	}

	public void limpaCampos() {

		txtNome.setText("");
		txtCpf.setText("");
		txtUser.setText("");
		txtTelefone.setText("");
		txtMatricula.setText("");
		txtEmail.setText("");
		comboCargo.setValue("Selecione um item");
		comboDepart.setValue("Selecione um item");
		comboTipoUser.setValue("Selecione um item");
		txtNome.requestFocus();
	}

	@FXML
	public void preencherTipoPesquisa(){

		List<String> tipo= new ArrayList<String>();
		tipo.add("Nome");
		tipo.add("CPF");
		tipo.add("Matr�cula");
		comboTipoBuscaUser.getItems().addAll(tipo);
	}
	@FXML
	public void preencherDepartamento(){

		List<String> dpto= new ArrayList<String>();
		dpto.add("Administrativo");
		dpto.add("Financeiro");
		dpto.add("Recursos humanos");
		dpto.add("Marketing");
		dpto.add("Estudandil");
		dpto.add("T.I");
		comboDepart.getItems().addAll(dpto);
	}
	@FXML
	public void preencherTipoUser(){

		List<String> tipo= new ArrayList<String>();
		tipo.add("Administrador");
		tipo.add("Atendente");
		tipo.add("Respons�vel");
		comboTipoUser.getItems().addAll(tipo);
	}
	@FXML
	public void preencherTipoCargo(){
		List<String> listaCargos= new ArrayList<String>();
		listaCargos.add("Coordenador");
		listaCargos.add("Secret�rio");
		listaCargos.add("Analista");
		listaCargos.add("Diretor");
		listaCargos.add("Contador");
		comboCargo.getItems().addAll(listaCargos);
	}

	//	private List<String> listarCargos() {
	//		List<String> listaCargos= new ArrayList<String>();
	//		this.listAdm = admNegocio.listarAdms();
	//		listAdm.forEach( cargo -> {
	//			listaCargos.add(cargo.getCargo());
	//		}
	//
	//				);
	//		return listaCargos;
	//	}



	//--------------------------------------DOCUMENTOS-------------------------------------------//


	@FXML
	public void addDocumentoLista(ActionEvent event) {
		boolean validar;
		validar= validarDocs(txtNomeDoc.getText());
		if(validar){
			Documentos doc = new Documentos();
			//		String nomeDoc=txtNomeDoc.getText();
			doc.setNome(txtNomeDoc.getText());
			docList.add(doc);
			colunDocs.setCellValueFactory(new PropertyValueFactory<Documentos, String>("nome"));
			docView = FXCollections.observableArrayList(docList);
			tblDocs.getItems().removeAll();
			tblDocs.setItems(docView);
			String msg = "Documento inserido!";
			exibeMensagem(msg);
			limparCamposDoc();
		}else{
			String msg = "O documento ja existe na lista!";
			exibeMensagem(msg);
			limparCamposDoc();
		}
	}
	@FXML
	public void salvarDocumentoList() {

		docList.forEach(doc ->{
			String validar="";

			if(doc.getId()==null){
				try {
					validar=docNegocio.salvar(doc);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(validar.equals("salvo")){
					populaViewDocs();
					if(cont<1){
						String msg = "Documentos salvos com sucesso!";
						exibeMensagem(msg);
						cont++;
					}
				}else{
					exibeMensagem("Falha!!!");
				}
			}

		});
		cont=0;
	}
	@FXML
	public void excluirDocumentosList(ActionEvent event) throws SQLException {
		doc = new Documentos();
		doc =  tblDocs.getSelectionModel().getSelectedItem();
		String validar="";
		if(doc.getId().equals(null)){
			tblCadDoc.getItems().remove(doc);
			exibeMensagem("Documento excluido da lista!");
		}else{
			validar=docNegocio.excluir(doc);
			if(validar.equals("excluido")){
				String msg = "Documentos excluido com sucesso!";
				exibeMensagem(msg);
				populaViewDocs();
			}else{
				exibeMensagem("Falha!!!");
			}
		}
	}
	@FXML
	public void limparCamposDoc(){
		txtNomeDoc.setText("");
	}
	public boolean validarDocs(String nome){
		boolean resp = false;

		for(int i=0;i<docList.size();i++){
			if(docList.get(i).getNome().equals(nome)){
				return resp;
			}
		}
		return resp = true;
	}
	public void populaViewDocs(){
		this.docList = docNegocio.listarDocs();
		colunDocs.setCellValueFactory(new PropertyValueFactory<Documentos, String>("nome"));
		docView = FXCollections.observableArrayList(docList);
		tblDocs.getItems().removeAll();
		tblDocs.setItems(docView);
	}

	//------------------------------Cadastrar Tipo de processo--------------------------------//

	@FXML
	public void populaComboDoc(){
		docList.forEach( doc1 -> {
			comboDocumentos.getItems().add(doc1.getNome());
		}

				);
	}

	@FXML
	public void addDocumentoTabela(ActionEvent event) {
		String nomeDoc= (String) comboDocumentos.getSelectionModel().getSelectedItem();
		listarDoc(nomeDoc);

		if(!doc.getNome().equals("")||doc.getNome().equals(null)){
			if(validarDocs(nomeDoc)){
				docList2.add(doc);
				colunCadDoc.setCellValueFactory(new PropertyValueFactory<Documentos, String>("nome"));
				docView = FXCollections.observableArrayList(docList2);
				tblCadDoc.getItems().removeAll();
				tblCadDoc.setItems(docView);
				comboDocumentos.setValue("Selecione um item");
				doc=null;
			}
		}
	}
	public void listarDoc(String nome){
		docList = docNegocio.listarDocs();
		docList.forEach( doc1 -> {
			if(doc1.getNome().equals(nome)){
				doc = doc1;
			}
		}

				);

	}








	public void exibeMensagem(String msg){

		Notifications.create()
		.text(String.valueOf(msg))
		.owner(main )
		.hideAfter(Duration.seconds(3))
		.darkStyle()
		.position(Pos.TOP_RIGHT)
		.showInformation();


	}
	public void animaCamposValidados(List<Control> controls) {
		controls.forEach(control -> {
			RotateTransition rotateTransition = new RotateTransition(Duration.millis(60), control);
			rotateTransition.setFromAngle(-4);
			rotateTransition.setToAngle(4);
			rotateTransition.setCycleCount(8);
			rotateTransition.setAutoReverse(true);
			rotateTransition.setOnFinished((ActionEvent event1) ->{
				control.setRotate(0);
			});
			rotateTransition.play();
		});
		if(!controls.isEmpty()){
			controls.get(0).requestFocus();

		}
	}



	// Event Listener on Button[#btnExcluirProc].onAction
	@FXML
	public void excluirProcesso(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnAlterarProc].onAction
	@FXML
	public void editarProcesso(ActionEvent event) {
		// TODO Autogenerated
	}


	// Event Listener on ToggleButton[#btnAddResp].onAction
	@FXML
	public void addResponsavelFluxo(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ToggleButton[#btnBuscaUser].onAction

	public void excluirUsuario(ActionEvent event){

	}
	// Event Listener on Button[#btnExcluirDoc].onAction

	// Event Listener on Button[#btnAlterarDoc].onAction





}
