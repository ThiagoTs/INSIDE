package Modelo;



public class Documentos {
	
	private int id;
	private String nome;
	private String arquivo;
	
	public Documentos(int id,String nome, String arquivo) {
		this.id = id;
		this.nome = nome;
		this.arquivo = arquivo;
	}
	public Documentos(){}
	
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
	

}
