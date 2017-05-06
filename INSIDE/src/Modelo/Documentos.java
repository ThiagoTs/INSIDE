package Modelo;



public class Documentos {
	
	private Integer id;
	private String nome;
	private String arquivo;
	
	public Documentos(Integer id,String nome, String arquivo) {
		this.id = id;
		this.nome = nome;
		this.arquivo = arquivo;
	}
	public Documentos(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
