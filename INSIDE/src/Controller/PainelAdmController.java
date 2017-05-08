package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Modelo.Administrador;
import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.scene.control.TabPane;

import javafx.scene.control.ToggleButton;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;

import javafx.scene.control.TableColumn;

public class PainelAdmController implements Initializable{
	@FXML
	private Pane paneInicialAdm;
	@FXML
	private BorderPane bPanePainel;
	@FXML
	private GridPane gridStatusProc;
	@FXML
	private TableView tblStatusProc;
	@FXML
	private TableColumn colunStatusProc;
	@FXML
	private TabPane tblviewPrincipal;
	@FXML
	private Tab tabProcessos;
	@FXML
	private Tab tabCadastros;
	@FXML
	private AnchorPane aPaneProcessos;
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
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textUser.setText(adm.getNome());
		preencherMenus();
		
	}

	
	public static void pegaAdm(Administrador adm1){
		adm = adm1;
	}

	@FXML
	public void abrirConfig(ActionEvent event) {

	}
	
	@FXML
	public void deslogar(ActionEvent event) throws IOException {

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
			System.out.println("To aqui");
		} catch (Exception e) {
			e.getMessage();
		}

	}


}


