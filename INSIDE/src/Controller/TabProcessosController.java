package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import Dao.AlunosDao;
import Modelo.Administrador;
import Modelo.Aluno;
import Modelo.Documentos;
import application.Main;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TabPane;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Tab;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

import javafx.scene.layout.Pane;

public class TabProcessosController implements Initializable {

	@FXML
	private AnchorPane aPaneProcesso;
	@FXML
	private TabPane tabPaneProcessos;
	@FXML
	private Tab tabAbrirNovoProc;
	@FXML
	private AnchorPane aPaneAbrirNovo;
	@FXML
	private Tab tabProcPendente;
	@FXML
	private AnchorPane aPaneProcPend;
	@FXML
	private Tab tabBuscarProc;
	@FXML
	private AnchorPane aPaneConsulProc;

	TabProcPendController proPend = new TabProcPendController();

	@Override
	public void initialize(URL location, ResourceBundle resources) {


		try {
			preencherTabs();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	@FXML
	public void preencherTabs() throws Exception {

		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/Visao/tabAbrirNovoProc.fxml");
		Parent fxmlParent = null;
		fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
		//aPaneProcessos.getChildren().clear();
		aPaneAbrirNovo.getChildren().add(fxmlParent);


		URL arquivoFXML2;
		arquivoFXML2 = getClass().getResource("/Visao/tabProcPend.fxml");
		Parent fxmlParent2 =(Parent) FXMLLoader.load(arquivoFXML2);
		//aPaneProcessos.getChildren().clear();
		aPaneProcPend.getChildren().add(fxmlParent2);



		URL arquivoFXML3;
		arquivoFXML3 = getClass().getResource("/Visao/tabConsultaProc.fxml");
		Parent fxmlParent3 =(Parent) FXMLLoader.load(arquivoFXML3);
		//aPaneProcessos.getChildren().clear();
		aPaneConsulProc.getChildren().add(fxmlParent3);
	}



}
