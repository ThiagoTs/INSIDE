package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Aluno {
	
	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty nome = new SimpleStringProperty();
	private SimpleStringProperty cpf = new SimpleStringProperty();
	private SimpleStringProperty telefone = new SimpleStringProperty();
	private SimpleStringProperty matricula = new SimpleStringProperty();
	private SimpleStringProperty email = new SimpleStringProperty();
	private SimpleStringProperty sexo = new SimpleStringProperty();
	private SimpleStringProperty curso = new SimpleStringProperty();
	private SimpleStringProperty ender = new SimpleStringProperty();
	private SimpleStringProperty responsavel = new SimpleStringProperty();
	private SimpleStringProperty city = new SimpleStringProperty();
	private Date dataNasci;
	
	private List<TipoProcesso> processos = new ArrayList<TipoProcesso>();
	private List<Documentos> documentos = new ArrayList<Documentos>();
	
	
	public Aluno(SimpleIntegerProperty id, SimpleStringProperty nome, SimpleStringProperty cpf,
			SimpleStringProperty telefone, SimpleStringProperty matricula, SimpleStringProperty email,
			SimpleStringProperty sexo, SimpleStringProperty curso, SimpleStringProperty ender,
			SimpleStringProperty responsavel, SimpleStringProperty city, Date dataNasci) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.matricula = matricula;
		this.email = email;
		this.sexo = sexo;
		this.curso = curso;
		this.ender = ender;
		this.responsavel = responsavel;
		this.city = city;
		this.dataNasci = dataNasci;
	}
	
	public Aluno(){}

	public SimpleIntegerProperty getIdProperty() {
		return id;
	}
	public int getId(){
		return this.id.get();
	}

	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}

	public SimpleStringProperty getNome() {
		return nome;
	}

	public void setNome(SimpleStringProperty nome) {
		this.nome = nome;
	}

	public SimpleStringProperty getCpf() {
		return cpf;
	}

	public void setCpf(SimpleStringProperty cpf) {
		this.cpf = cpf;
	}

	public SimpleStringProperty getTelefone() {
		return telefone;
	}

	public void setTelefone(SimpleStringProperty telefone) {
		this.telefone = telefone;
	}

	public SimpleStringProperty getMatricula() {
		return matricula;
	}

	public void setMatricula(SimpleStringProperty matricula) {
		this.matricula = matricula;
	}

	public SimpleStringProperty getEmail() {
		return email;
	}

	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}

	public SimpleStringProperty getSexo() {
		return sexo;
	}

	public void setSexo(SimpleStringProperty sexo) {
		this.sexo = sexo;
	}

	public SimpleStringProperty getCurso() {
		return curso;
	}

	public void setCurso(SimpleStringProperty curso) {
		this.curso = curso;
	}

	public SimpleStringProperty getEnder() {
		return ender;
	}

	public void setEnder(SimpleStringProperty ender) {
		this.ender = ender;
	}

	public SimpleStringProperty getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(SimpleStringProperty responsavel) {
		this.responsavel = responsavel;
	}

	public SimpleStringProperty getCity() {
		return city;
	}

	public void setCity(SimpleStringProperty city) {
		this.city = city;
	}

	public Date getDataNasci() {
		return dataNasci;
	}

	public void setDataNasci(Date dataNasci) {
		this.dataNasci = dataNasci;
	}

	public List<TipoProcesso> getProcessos() {
		return processos;
	}

	public void setProcessos(List<TipoProcesso> processos) {
		this.processos = processos;
	}

	public List<Documentos> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documentos> documentos) {
		this.documentos = documentos;
	}
	
	
	

}
