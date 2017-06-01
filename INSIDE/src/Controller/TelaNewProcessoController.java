package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import Dao.DocumentosDao;
import Dao.ProcessosDao;
import Dao.TipoProcDao;
import Modelo.Administrador;
import Modelo.Aluno;
import Modelo.Documentos;
import Modelo.Processo;
import Modelo.TipoProcesso;
import Negocio.ProcessosNegocio;
import application.Main;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class TelaNewProcessoController implements Initializable {
	@FXML
	private AnchorPane aPanePrincipal;
	@FXML
	private ComboBox<String> comboTipoProc;
	@FXML
	private TextArea txtComent;
	@FXML
	private ListView<String> listaDocs;
	@FXML
	private ListView listaAnexos;
	@FXML
	private Button btnAbrir;
	@FXML
	private TextField txtNomeAluno;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextArea txtDescri;
	
	private CheckBox btnAnexo;


	static Aluno alu = new Aluno();
	TipoProcDao tiProcDao = new TipoProcDao();
	DocumentosDao docDao = new DocumentosDao();
	TipoProcesso tiProc = new TipoProcesso();
	ProcessosNegocio procNeg = new ProcessosNegocio();
	Processo proc = new Processo();
	ProcessosDao proDao = new ProcessosDao();
	List<Administrador> listAdm = new ArrayList<>();
	Main main = null;
	PainelAdmController padm = new PainelAdmController();
	public int cont=1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populaDocs();
		populaTipoProc();
		txtNomeAluno.setText(alu.getNome());
		txtDescri.setText("");

	}

	@FXML
	public void abrirNewProc(ActionEvent event) throws Exception {
		String salvo = "falha";
		listAdm=tiProcDao.listarAdmsTipo(tiProc);
		String nomeProc = comboTipoProc.getSelectionModel().getSelectedItem();
		String comentario = txtComent.getText();
		if(!nomeProc.equals("")||nomeProc==null){
			proc.setNome(nomeProc);
			if(!comentario.equals("")){
				proc.setComentarios(comentario);
			}
			proc.setDataProc(LocalDate.now());
			proc.setIdAluno(alu.getId());
			proc.setAlunoNome(alu.getNome());
			proc.setStatus("Aguardando resposta de "+ listAdm.get(0).getNome()+ " - "+ listAdm.get(0).getDepartamento());
			salvo = proDao.salvarProc(proc);
		}else{
			exibeMensagem("Selecione um tipo de processo!");
		}
		if(salvo.equals("salvo")){
			Processo pro = buscarProc();
			salvo = fazerRealacao(pro);
			if(salvo.equals("salvo")){
			exibeMensagem("Processo aberto com sucesso!");
			finalizar();
			}else{
				//padm.populaStatusProc();
				exibeMensagem(salvo);
				finalizar();
			}
		}

	}
	public String fazerRealacao(Processo pro) throws SQLException{
		String salvo = "falha";
		List<Administrador> listAdm2 = new ArrayList<>();
		listAdm2=tiProcDao.listarAdmsTipo(tiProc);
		String status ="";
		if(pro.getId()!=0){
			for(Administrador adm: listAdm2){
				if(cont==1){
					status = "on"; 
				}else{
					if(cont==2){
						status = "next";
					}else{
						status = "off";
					}
				}
				salvo = proDao.salvarRelacaoAdm(pro, adm, cont, status);
				cont++;
			}
			cont=0;
		}else{
			exibeMensagem("falha ao buscar o processo");
		}
		return salvo;

	}
	public Processo buscarProc(){
		Processo pro =  new Processo();
		List<Processo>listProc2 = new ArrayList<>();
		listProc2 = proDao.listarProc();
		for(Processo pro2 : listProc2){
			if(pro2.getNome().equals(proc.getNome())&&pro2.getIdAluno()==alu.getId()){
				return pro=pro2;
			}
		}
		return pro;

	}
	public void recuperarAluno(Aluno alu){
		this.alu=alu;
	}
	public void populaDocs(){
		List<Documentos> docList = new ArrayList<Documentos>();
		listaDocs.getItems().clear();
		listaAnexos.getItems().clear();
		docList=docDao.listarAdmsTipo(tiProc);
		docList.forEach(doc1 ->{
			listaDocs.getItems().add(doc1.getNome());
			listaAnexos.getItems().add(btnAnexo = new CheckBox());
		});
	}

	public void populaTipoProc(){
		List<TipoProcesso> proList = new ArrayList<TipoProcesso>();
		proList=tiProcDao.listarTipoProc();

		proList.forEach(pro1 ->{
			comboTipoProc.getItems().add(pro1.getNome());
		});
	}
	@FXML
	public void pegaTipoProc(ActionEvent event) {
		String nomeTipoProc = comboTipoProc.getSelectionModel().getSelectedItem();
		tiProc=procNeg.listarTipoProc(nomeTipoProc);
		txtDescri.setText(tiProc.getDescricao());
		populaDocs();
	}
	public void finalizar() throws Exception{

		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/Visao/tabAbrirNovoProc.fxml");
		Parent fxmlParent = null;
		fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
		aPanePrincipal.getChildren().clear();
		aPanePrincipal.getChildren().add(fxmlParent);
	}
	@FXML
	public void cancelar(ActionEvent event) throws Exception {
		finalizar();
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
