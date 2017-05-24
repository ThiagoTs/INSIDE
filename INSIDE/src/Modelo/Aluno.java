package Modelo;

import java.time.LocalDate;
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
	private LocalDate dataNasci;
	
	private List<TipoProcesso> processos = new ArrayList<TipoProcesso>();
	private List<Documentos> documentos = new ArrayList<Documentos>();
	
	
	public Aluno(SimpleIntegerProperty id, SimpleStringProperty nome, SimpleStringProperty cpf,
			SimpleStringProperty telefone, SimpleStringProperty matricula, SimpleStringProperty email,
			SimpleStringProperty sexo, SimpleStringProperty curso, SimpleStringProperty ender,
			SimpleStringProperty responsavel, SimpleStringProperty city, LocalDate dataNasci) {
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
	public void setId(int id) {
		this.id.set(id);
	}

	public SimpleStringProperty getNomeProperty() {
		return nome;
	}
	
	public String getNome() {
		return nome.get();
	}
	public void setNome(SimpleStringProperty nome) {
		this.nome = nome;
	}
	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public SimpleStringProperty getCpfProperty() {
		return cpf;
	}
	public String getCpf() {
		return cpf.get();
	}
	public void setCpf(SimpleStringProperty cpf) {
		this.cpf = cpf;
	}
	public void setCpf(String cpf) {
		this.cpf.set(cpf);
	}
	public SimpleStringProperty getTelefoneProperty() {
		return telefone;
	}
	public String getTelefone() {
		return telefone.get();
	}
	public void setTelefone(SimpleStringProperty telefone) {
		this.telefone = telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone.set(telefone);
	}

	public SimpleStringProperty getMatriculaProperty() {
		return matricula;
	}
	public String getMatricula() {
		return matricula.get();
	}

	public void setMatricula(SimpleStringProperty matricula) {
		this.matricula = matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula.set(matricula);
	}

	public SimpleStringProperty getEmailProperty() {
		return email;
	}
	public String getEmail() {
		return email.get();
	}

	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	

	public SimpleStringProperty getSexoProperty() {
		return sexo;
	}
	public String getSexo() {
		return sexo.get();
	}

	public void setSexo(SimpleStringProperty sexo) {
		this.sexo = sexo;
	}
	public void setSexo(String sexo) {
		this.sexo.set(sexo);
	}

	public SimpleStringProperty getCursoProperty() {
		return curso;
	}
	public String getCurso() {
		return curso.get();
	}

	public void setCurso(SimpleStringProperty curso) {
		this.curso = curso;
	}
	public void setCurso(String curso) {
		this.curso.set(curso);
	}

	public SimpleStringProperty getEnderProperty() {
		return ender;
	}
	public String getEnder() {
		return ender.get();
	}
	public void setEnder(SimpleStringProperty ender) {
		this.ender = ender;
	}
	public void setEnder(String ender) {
		this.ender.set(ender);
	}

	public SimpleStringProperty getResponsavelProperty() {
		return responsavel;
	}
	public String getResponsavel() {
		return responsavel.get();
	}

	public void setResponsavel(SimpleStringProperty responsavel) {
		this.responsavel = responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel.set(responsavel);
	}

	public SimpleStringProperty getCityProperty() {
		return city;
	}
	public String getCity() {
		return city.get();
	}

	public void setCity(SimpleStringProperty city) {
		this.city = city;
	}
	public void setCity(String city) {
		this.city.set(city);
	}

	public LocalDate getDataNasci() {
		return dataNasci;
	}

	public void setDataNasci(LocalDate dataNasci) {
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
