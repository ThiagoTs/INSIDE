package Modelo;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Usuarios {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty tipoUser = new SimpleStringProperty();
	private SimpleStringProperty nome = new SimpleStringProperty();
	private SimpleStringProperty cpf = new SimpleStringProperty();
	private SimpleStringProperty telefone = new SimpleStringProperty();
	private SimpleStringProperty matricula = new SimpleStringProperty();
	private SimpleStringProperty email = new SimpleStringProperty();
	private SimpleStringProperty sexo = new SimpleStringProperty();
	private SimpleStringProperty cargo = new SimpleStringProperty();
	private SimpleStringProperty departamento = new SimpleStringProperty();

	
	private String senha;
	private String login;
	
	
	private List<Processo> procPendentes = new ArrayList<Processo>();
	
	
	public Usuarios(SimpleIntegerProperty id, SimpleStringProperty tipoUser, SimpleStringProperty nome,
			SimpleStringProperty cpf, SimpleStringProperty telefone, SimpleStringProperty matricula,
			SimpleStringProperty email, SimpleStringProperty sexo, SimpleStringProperty cargo,
			SimpleStringProperty departamento,String senha,String login) {
		this.id = id;
		this.tipoUser = tipoUser;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.matricula = matricula;
		this.email = email;
		this.sexo = sexo;
		this.cargo = cargo;
		this.departamento = departamento;
		this.senha = senha;
		this.login = login;
	}
	
	public List<Processo> getProcPendentes() {
		return procPendentes;
	}
	public void setProcPendentes(List<Processo> procPendentes) {
		this.procPendentes = procPendentes;
	}
	public Usuarios(){}
	public SimpleIntegerProperty getIdProperty() {
		return id;
	}
	public void setIdProperty(SimpleIntegerProperty id) {
		this.id = id;
	}
	public void setId(int id){
		this.id.set(id);
	}
	public int getId(){
		return id.get();
		
	}


	public SimpleStringProperty getTipoUserProperty() {
		return tipoUser;
	}
	public String getTipoUser(){
		return tipoUser.get();
	}
	public void setTipoUser(SimpleStringProperty tipoUser) {
		this.tipoUser = tipoUser;
	}
	public void setTipoUser(String tipoUser) {
		this.tipoUser.set(tipoUser);
	}
	public SimpleStringProperty getNomeProperty() {
		return nome;
	}
	public String getNome(){
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
	public String getCpf(){
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
	public String getTelefone(){
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
	public String getMatricula(){
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
	public String getEmail(){
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
	public String getSexo(){
		return sexo.get();
	}
	public void setSexo(SimpleStringProperty sexo) {
		this.sexo = sexo;
	}
	public void setSexo(String sexo) {
		this.sexo.set(sexo);
	}
	public SimpleStringProperty getCargoProperty() {
		return cargo;
	}
	public String getCargo(){
		return cargo.get();
	}
	public void setCargo(SimpleStringProperty cargo) {
		this.cargo = cargo;
	}
	public void setCargo(String cargo) {
		this.cargo.set(cargo);
	}
	public SimpleStringProperty getDepartamentoProperty() {
		return departamento;
	}
	public String getDepartamento(){
		return departamento.get();
	}
	public void setDepartamento(SimpleStringProperty departamento) {
		this.departamento = departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento.set(departamento);
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	
	
	
	
	
}
