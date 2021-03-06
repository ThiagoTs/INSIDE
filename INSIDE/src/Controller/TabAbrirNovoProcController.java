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
import Modelo.Aluno;
import application.Main;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class TabAbrirNovoProcController implements Initializable {
	@FXML
	private AnchorPane aPaneAbrirPro;
	@FXML
	private Button btnAbrirAlunoView;
	@FXML
	private TextField txtPesqAluno;
	@FXML
	private ComboBox<String> comboTipoPesquiAluno;
	@FXML
	private Text textProcAberto1;
	@FXML
	private TableView<Aluno> tabAlunosPorbusca;
	@FXML
	private TableColumn<Aluno,String> colunMatric;
	@FXML
	private TableColumn<Aluno,String> colunNome;
	@FXML
	private TableColumn<Aluno,String> colunCpf;
	@FXML
	private ToggleButton btnBuscar;
	
	Aluno alu = new Aluno();
	AlunosDao aluDao = new AlunosDao();
	List<Aluno> listAlu = new ArrayList<>() ;
	ObservableList<Aluno> aluView = null;
	Main main = null;
	TelaDadosAlunoController aluC = new TelaDadosAlunoController();
	PainelAdmController padm = new PainelAdmController();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherTipoPesq();
        //padm.populaStatusProc();

	}

	@FXML
	public void populaAlunosView(){
		List<String> list = new ArrayList<>();
		listAlu=aluDao.listarAlunos();
		listAlu.forEach(alu ->{
			list.add(alu.getNome());
		});
		comboTipoPesquiAluno.getItems().addAll(list);
	}
	@FXML
	public void preencherTipoPesq(){
		List<String> proc= new ArrayList<String>();
		proc.add("Matr�cula");
		proc.add("Nome");
		proc.add("CPF");
		comboTipoPesquiAluno.getItems().addAll(proc);
		
	}
	@FXML
	public void buscarAluno(ActionEvent event) {
		String pesq="";
		alu = new Aluno();
		pesq = comboTipoPesquiAluno.getSelectionModel().getSelectedItem();
		tabAlunosPorbusca.getItems().removeAll();
		listAlu = aluDao.listarAlunos();
		if(pesq==null){
			exibeMensagem("Selecione o tipo de pesquisa!");
		}else{
			if(pesq.equals("Nome")){
				for( Aluno alu2:listAlu){
					if(alu2.getNome().equalsIgnoreCase(txtPesqAluno.getText())){
						alu=alu2;
						populaViewAluno();
					}
				}
			}else{
				if(pesq.trim().equals("Matr�cula")){
					for(Aluno alu2:listAlu){
						if(alu2.getMatricula().equals(txtPesqAluno.getText())){
							alu=alu2;	
							populaViewAluno();
						}	
					}
				}else{
					if(pesq.trim().equals("CPF")){
						for(Aluno alu2:listAlu){
							if(alu2.getCpf().equals(txtPesqAluno.getText())){
								alu=alu2;
								populaViewAluno();
							}
						}
					}
				}
			}
			if(alu.getId()==0){
				exibeMensagem("Aluno n�o encontrado!");
			}
		}
	}

	public void populaViewAluno(){

		colunMatric.setCellValueFactory(new PropertyValueFactory<Aluno, String>("Matricula"));
		colunNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("Nome"));
		colunCpf.setCellValueFactory(new PropertyValueFactory<Aluno, String>("cpf"));
		aluView = FXCollections.observableArrayList(alu);
		tabAlunosPorbusca.getItems().removeAll();
		tabAlunosPorbusca.setItems(aluView);
		comboTipoPesquiAluno.setValue("Selecione um item");

	}
	@FXML
	public void abrirViewAluno(ActionEvent event) throws Exception {
		aluC.recuperarAluno(alu);
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/Visao/telaDadosAluno.fxml");
		Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
		aPaneAbrirPro.getChildren().clear();
		aPaneAbrirPro.getChildren().add(fxmlParent);
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

	

}
