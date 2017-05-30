package Negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.DocumentosDao;
import Modelo.Administrador;
import Modelo.Documentos;

public class DocumentosNegocio {
	DocumentosDao docDao = new DocumentosDao();

	public String salvar(Documentos doc) throws SQLException {
		List<Documentos>listDoc = new ArrayList<Documentos>();

		String salvo = "falha";
		StringBuilder sb = new StringBuilder();
		if(doc.getId()==0){
			salvo = docDao.salvar(doc);
		}else{
			sb.append(salvo);
			return sb.toString();
		}
		return salvo;

	}
	public String excluir(Documentos doc) throws SQLException {
		List<Documentos>listDoc = new ArrayList<Documentos>();

		String excluido = "falha";
		StringBuilder sb = new StringBuilder();
		try {
			excluido = docDao.excluir(doc);
		} catch (Exception e) {
			sb.append(excluido);
			return sb.toString();
		}


		return excluido;

	}

	public List<Documentos> listarDocs(){
		List<Documentos> docs = new ArrayList<Documentos>();
		docs = docDao.listarDocs();
		return docs;
	}
	



}
