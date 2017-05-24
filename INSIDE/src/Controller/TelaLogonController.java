package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
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
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
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
	List<Administrador> listAdm ;

	Main main = null;

	@FXML
	public void esqueciSenha(ActionEvent event) {

	}

	@FXML
	public void limparCampos(ActionEvent event) {
		txtUsuario.setText("");
		txtSenha.setText("");
		txtUsuario.requestFocus();
	}


	@FXML
	public void entrar(ActionEvent event) throws Exception{
		adm = new Administrador();
		String msg = txtUsuario.getText();
		String msg2 = txtSenha.getText();
		if(validaCampos()){
			buscaUser(msg);
			if(adm.getId()!=0){
				if(adm.getSenha().equals(msg2)){	
					painelAdm.pegaAdm(adm);	
					URL arquivoFXML;
					arquivoFXML = getClass().getResource("/Visao/painelAdm.fxml");
					Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
					panePrincipal.getChildren().clear();
					panePrincipal.getChildren().add(fxmlParent);

				}else{
					msg = "Usuário ou senha incorretos!";
					exibeMensagem(msg);
				}
			}else{
				msg = "Usuário ou senha incorretos!";
				exibeMensagem(msg);
			}
		}

	}

	private void buscaUser(String msg) {
		listAdm = admNegocio.listarAdms();
		for(Administrador adm2:listAdm){
			if(adm2.getLogin().equalsIgnoreCase(msg)){
				this.adm = adm2;	
			}
		}
	}
	public boolean validaCampos(){
		String msg = txtUsuario.getText();
		String msg2 = txtSenha.getText();
		List<Control>  controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(msg.equals("")||msg==null){
			sb.append("Digite o Usuário!\n");
			controls.add(txtUsuario);
		}
		if(msg2.equals("")||msg2==null){
			sb.append("Digite a senha!\n");
			controls.add(txtSenha);

		}
		if(!sb.equals("")||sb!=null){
			exibeMensagem(sb.toString());
			animaCamposValidados(controls);
		}
		return sb.toString().isEmpty();
	}
	
	
	@FXML
	public void enter(KeyEvent event) throws Exception {
		adm = new Administrador();
		String msg = txtUsuario.getText();
		String msg2 = txtSenha.getText();
		if(validaCampos()){
			buscaUser(msg);
			if(adm.getId()!=0){
				if(adm.getSenha().equals(msg2)){	
					painelAdm.pegaAdm(adm);	
					URL arquivoFXML;
					arquivoFXML = getClass().getResource("/Visao/painelAdm.fxml");
					Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
					panePrincipal.getChildren().clear();
					panePrincipal.getChildren().add(fxmlParent);

				}else{
					msg = "Usuário ou senha incorretos!";
					exibeMensagem(msg);
				}
			}else{
				msg = "Usuário ou senha incorretos!";
				exibeMensagem(msg);
			}
		}
		
			
	}

	@FXML
	public void help(ActionEvent event) {

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {


		txtSenha.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                	try {
						enter(event);
					} catch (Exception e) {
						
						e.printStackTrace();
					} 
                }
            }
        });
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

	public void exibeMensagem(String msg){
		Notifications.create()
		.text(String.valueOf(msg))
		.owner(main)
		.hideAfter(Duration.seconds(3))
		.darkStyle()
		.position(Pos.TOP_RIGHT)
		.showInformation();


	}
}
