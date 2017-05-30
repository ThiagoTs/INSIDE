package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.text.Text;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Dao.ProcessosDao;
import Modelo.Administrador;
import Modelo.Aluno;
import Modelo.Processo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.scene.control.TableColumn;

public class TabProcPendController implements Initializable {
	@FXML
	private AnchorPane aPaneProcPend;
	@FXML
	private Pane panePendentes;
	@FXML
	private TableView<Processo> tblProcPendente;
	@FXML
	private TableColumn<Processo,Integer> colunPendProtoc;
	@FXML
	private TableColumn<Processo,String> colunPendNomeProc;
	@FXML
	private TableColumn<Processo,String> colunPendNomeAlu;
	@FXML
	private TextField txtBuscarProc;
	@FXML
	private Button btnAbrirProcView;
	@FXML
	private ToggleButton btnBuscarProcPendente;
	@FXML
	private Text textProcAberto;
	
	
	PainelAdmController padm = new PainelAdmController();
	ProcessosDao proDao = new ProcessosDao();
	ObservableList<Processo> proView = null;
	List<Processo> listProcPend = new ArrayList<>();
	static Administrador adm = new Administrador(); ;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populaProcPend();
		
	}
	public static void pegaAdm(Administrador adm2){
		adm = adm2;
	}
	
	
	@FXML
	public void abrirProcView(ActionEvent event) throws Exception {
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/Visao/telaProcesso.fxml");
		Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
		panePendentes.getChildren().clear();
		panePendentes.getChildren().add(fxmlParent);
	}
	// Event Listener on ToggleButton[#btnBuscarProcPendente].onAction
	@FXML
	public void buscarProcessoPendente(ActionEvent event) {
		// TODO Autogenerated
	}
	public void populaProcPend(){
		List<Processo> listPro = new ArrayList<>();
		listProcPend = proDao.listarProcProAdm(adm);	
		for(Processo pro : listProcPend){
			if(pro.getStatusPend().equals("on")){
			listPro.add(pro);
			}
		}
		colunPendProtoc.setCellValueFactory(new PropertyValueFactory<Processo, Integer>("id"));
		colunPendNomeProc.setCellValueFactory(new PropertyValueFactory<Processo, String>("Nome"));
		colunPendNomeAlu.setCellValueFactory(new PropertyValueFactory<Processo, String>("alunoNome"));
		proView = FXCollections.observableArrayList(listPro);
		tblProcPendente.getItems().removeAll();
		tblProcPendente.setItems(proView);
	}
	
}
