package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.fabric.xmlrpc.base.Value;

import Dao.AlunosDao;
import Dao.ProcessosDao;
import Modelo.Administrador;
import Modelo.Aluno;
import Modelo.Processo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TabPane;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;

public class PainelAdmController implements Initializable{
	@FXML
	private Pane paneInicialAdm;
	@FXML
	private BorderPane bPanePainel;
	@FXML
	private GridPane gridStatusProc;
	@FXML
	private TableView<Processo> tblStatusProc;
	@FXML
	private TableColumn<Processo,String> colunStatusProc;
	@FXML
	private TabPane tblviewPrincipal;
	@FXML
	private Tab tabProcessos;
	@FXML
	private AnchorPane aPaneProcessos;
	@FXML
	private Tab tabCadastros;
	@FXML
	private AnchorPane aPaneCadastros;
	@FXML
	private GridPane gridUser;
	@FXML
	private HBox hbConfigSair;
	@FXML
	private ToggleButton btnConfig;
	@FXML
	private ToggleButton btnSair;
	@FXML
	private TextArea textUser;

	static Administrador adm = new Administrador();
	InicialController iniCon = new InicialController();
	ProcessosDao proDao = new ProcessosDao();
	ObservableList<Processo> proPendView = null;
	List<Processo> listProcPend = new ArrayList<Processo>();
	AlunosDao aluDao = new AlunosDao();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textUser.setText(adm.getNome());
		preencherMenus();
		populaStatusProc();


	}


	public static void pegaAdm(Administrador adm1){
		adm = adm1;
	}


	@FXML
	public void abrirConfig(ActionEvent event) {

	}
	public void populaStatusProc(){
		String status="";
		List<Processo> listPro = new ArrayList<>();
		listProcPend = proDao.listarProcProAdm(adm);
		for(Processo pro : listProcPend){
			if(pro.getStatusPend().equals("on")){
				status = pro.getNome()+"\nAluno: "+pro.getAlunoNome()+"\n"+pro.getDataProc().toString();
				pro.setStatusAlu(status);
				listPro.add(pro);
			}
		}

		colunStatusProc.setCellValueFactory(new PropertyValueFactory<Processo,String>("statusAlu"));
		//colunStatusProc.setCellValueFactory(new PropertyValueFactory<Processo, String>("Status"));
		proPendView = FXCollections.observableArrayList(listPro);
		tblStatusProc.getItems().removeAll();
		tblStatusProc.setItems(proPendView);

	}

	@FXML
	public void deslogar(ActionEvent event) throws Exception {
		adm = new Administrador();

		paneInicialAdm.getChildren().clear();
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/Visao/telaLogon.fxml");
		Parent fxmlParent = null;
		try {
			fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paneInicialAdm.getChildren().clear();
		paneInicialAdm.getChildren().add(fxmlParent);
	}
	public void preencherMenus(){
		try {

			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/Visao/tabProcessos.fxml");
			Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
			//aPaneProcessos.getChildren().clear();
			aPaneProcessos.getChildren().add(fxmlParent);


		} catch (Exception e) {
			e.getMessage();
		}
		try {

			URL arquivoFXML2;
			arquivoFXML2 = getClass().getResource("/Visao/tabCadastros.fxml");
			Parent fxmlParent2 =(Parent) FXMLLoader.load(arquivoFXML2);
			//aPaneCadastros.getChildren().clear();
			aPaneCadastros.getChildren().add(fxmlParent2);

		} catch (Exception e) {
			e.getMessage();
		}

	}




}


