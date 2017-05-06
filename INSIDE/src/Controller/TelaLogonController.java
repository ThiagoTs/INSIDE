package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import Modelo.Administrador;
import Negocio.AdmNegocio;
import application.Main;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import javafx.scene.control.PasswordField;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.control.Hyperlink;

public class TelaLogonController implements Initializable{
	
	@FXML
	private Pane panePrincipal;
	@FXML
	private BorderPane bPanePrincipal;
	@FXML
	private GridPane gridPane;
	@FXML
	private ImageView imgInside;
	@FXML
	private Rectangle imgRetangulo;
	@FXML
	private GridPane gridLogin;
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblSenha;
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private Hyperlink linkEsqueciSenha;
	@FXML
	private HBox hboxBtn;
	@FXML
	private Button btnLimpar;
	@FXML
	private Button btnEntrar;
	@FXML
	private Text txtLogin;
	@FXML
	private Hyperlink linkAjuda;
	
	Administrador adm = new Administrador();
	AdmNegocio admNegocio = new AdmNegocio();
	PainelAdmController painelAdm = new PainelAdmController();
	List<Administrador> listAdm = new ArrayList();
	Main main = null;
	
	@FXML
	public void esqueciSenha(ActionEvent event) {
	
	}

	@FXML
	public void limparCampos(ActionEvent event) {
	
	}
	
	@FXML
	public void entrar(ActionEvent event) throws Exception{
		adm = new Administrador();
		String msg = txtUsuario.getText();
		String msg2 = txtSenha.getText();
		adm.setLogin(msg);
		adm = buscaUser();
		if((adm.getLogin().equals(msg))&&(adm.getSenha().equals(msg2))){
			
		URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/painelAdm.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
        panePrincipal.getChildren().clear();
        panePrincipal.getChildren().add(fxmlParent);
        painelAdm.pegaAdm(adm);
		}else{
			msg = "Login ou senha incorretos!";
			exibeMensagem(msg);
		}
		
	}

	private Administrador buscaUser() {
		
		listAdm = admNegocio.listarCliente();
        listAdm.forEach( adm2 -> {
            if(adm2.getLogin().equals(adm.getLogin())){
            	System.out.println("Entrei aqui");
            	this.adm = adm2;
            }
                }
        );
       return adm;
	}

	@FXML
	public void help(ActionEvent event) {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
	}
	 public void exibeMensagem(String msg){
	        Notifications.create()
	                .title("Aten��o")
	                .text(String.valueOf(msg))
	                .owner(main)
	                .hideAfter(Duration.seconds(3))
	                .darkStyle()
	                .position(Pos.CENTER)
	                .showInformation();


	    }
}
