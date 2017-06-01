package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Processo {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty nome = new SimpleStringProperty();
	private SimpleStringProperty status = new SimpleStringProperty();
	private SimpleStringProperty comentarios = new SimpleStringProperty();
	private LocalDate dataIni;
	
// não instanciado;
	private int idAluno;
	private String nomeAluno;
	
	private String nomeAdm;
	

	private String alunoNome;
	private String admDepart;
	private String statusPend;
	private int idOpen;
	private int cont;
	
	

	private SimpleStringProperty statusAlu = new SimpleStringProperty();
	

	public Processo(SimpleIntegerProperty id, SimpleStringProperty nome, SimpleStringProperty status,
			SimpleStringProperty comentarios, LocalDate dataIni) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.comentarios = comentarios;
		this.dataIni = dataIni;

	}
	
	public Processo(){}
	
	public SimpleIntegerProperty getIdProperty() {
		return id; 
	}
	public int getId(){
		return id.get();
	}
	public void setIdProperty(SimpleIntegerProperty id) {
		this.id = id;
	}
	public void setId(int id){
		this.id.set(id);
	}
	public SimpleStringProperty getNomeProperty() {
		return nome;
	}
	public String getNome(){
		return nome.get();
	}
	public void setNomeProperty(SimpleStringProperty nome) {
		this.nome = nome;
	}
	public void setNome(String nome){
		this.nome.set(nome);
	}
	
	public SimpleStringProperty getStatusProperty() {
		return status;
	}
	public String getStatus(){
		return this.status.get();
	}
	public void setStatus(SimpleStringProperty status) {
		this.status = status;
	}
	public void setStatus(String status){
		this.status.set(status);
	}
	public SimpleStringProperty getComentariosProperty() {
		return comentarios;
	}
	public String getComentarios(){
		return this.comentarios.get();
	}
	public void setComentariosProperty(SimpleStringProperty comentarios) {
		this.comentarios = comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios.set(comentarios);;
	}
	public LocalDate getDataProc() {
		return dataIni;
	}

	public void setDataProc(LocalDate dataProc) {
		this.dataIni = dataProc;
	}
	public int getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	public String getNomeAdm() {
		return nomeAdm;
	}

	public void setNomeAdm(String nomeAdm) {
		this.nomeAdm = nomeAdm;
	}

	public String getAdmDepart() {
		return admDepart;
	}

	public void setAdmDepart(String admDepart) {
		this.admDepart = admDepart;
	}
	public String getStatusPend() {
		return statusPend;
	}

	public void setStatusPend(String statusPend) {
		this.statusPend = statusPend;
	}
	public String getAlunoNome() {
		return alunoNome;
	}

	public void setAlunoNome(String alunoNome) {
		this.alunoNome = alunoNome;
	}
	public SimpleStringProperty getStatusAluProperty() {
		return statusAlu;
	}
	public String getStatusAlu() {
		return statusAlu.get();
	}

	public void setStatusAlu(String statusAlu) {
		this.statusAlu.set(statusAlu);
	}
	public void setStatusAluProperty(SimpleStringProperty statusAlu) {
		this.statusAlu = statusAlu;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public int getIdOpen() {
		return idOpen;
	}

	public void setIdOpen(int idOpen) {
		this.idOpen = idOpen;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

}
