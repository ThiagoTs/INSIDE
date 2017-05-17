package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TipoProcesso {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty nome = new SimpleStringProperty();
	private SimpleStringProperty descricao = new SimpleStringProperty();

	
	private List<Documentos>documentos = new ArrayList<Documentos>();
	private List<Responsavel> fluxo = new ArrayList<Responsavel>();
	
	
	public TipoProcesso(SimpleIntegerProperty id, SimpleStringProperty nome, SimpleStringProperty descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public TipoProcesso(){}
	
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
	public SimpleStringProperty getDescricaoProperty() {
		return descricao;
	}
	public String getDescricao(){
		return descricao.get();
	}
	public void setDescricaoProperty(SimpleStringProperty descrição) {
		this.descricao = descrição;
	}
	public void setDescricao(String descr){
		this.descricao.set(descr);
	}
	
	public List<Documentos> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<Documentos> documentos) {
		this.documentos = documentos;
	}

	public List<Responsavel> getFluxo() {
		return fluxo;
	}

	public void setFluxo(List<Responsavel> fluxo) {
		this.fluxo = fluxo;
	}

	
}
