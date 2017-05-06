package Modelo;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Processo {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty nome = new SimpleStringProperty();
	private SimpleStringProperty descri��o = new SimpleStringProperty();
	private SimpleStringProperty status = new SimpleStringProperty();
	private SimpleStringProperty comentarios = new SimpleStringProperty();
	
	private List<Documentos>documentos = new ArrayList<Documentos>();
	private List<Responsavel> fluxo = new ArrayList<Responsavel>();
	
	
	
	public Processo(SimpleIntegerProperty id, SimpleStringProperty nome, SimpleStringProperty descri��o,
			SimpleStringProperty status, SimpleStringProperty comentarios) {
		
		this.id = id;
		this.nome = nome;
		this.descri��o = descri��o;
		this.status = status;
		this.comentarios = comentarios;
	}
	
	public Processo(){}
	
	public SimpleIntegerProperty getIdProperty() {
		return id;
	}
	public int getId(){
		return id.get();
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
	public SimpleStringProperty getDescri��o() {
		return descri��o;
	}
	public void setDescri��o(SimpleStringProperty descri��o) {
		this.descri��o = descri��o;
	}
	public SimpleStringProperty getStatus() {
		return status;
	}
	public void setStatus(SimpleStringProperty status) {
		this.status = status;
	}
	public SimpleStringProperty getComentarios() {
		return comentarios;
	}
	public void setComentarios(SimpleStringProperty comentarios) {
		this.comentarios = comentarios;
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
