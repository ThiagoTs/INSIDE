package Negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.DocumentosDao;
import Dao.TipoProcDao;
import Modelo.Administrador;
import Modelo.Documentos;
import Modelo.Processo;
import Modelo.TipoProcesso;

public class ProcessosNegocio {
	TipoProcDao  proDao = new TipoProcDao();
	TipoProcesso TiProc = new TipoProcesso();
	DocumentosDao docDao = new DocumentosDao();
	String salvo = "falha";
	String salvo2 = "falha";

	public String salvar(TipoProcesso proc,List<Administrador> admList2,List<Documentos>docList2) throws SQLException{

		salvo = "falha!";
		StringBuilder sb = new StringBuilder();
		if(proc.getIdProperty().getValue()==0){
			if(checarNome(proc)){
				salvo = proDao.salvarTipoProc(proc);
			}else{
				sb.append(salvo);
				return sb.toString();
			}
			if(salvo.equals("salvo")){
				salvo = fazerRelaci(proc,admList2,docList2);
			}
		}
		return salvo;
	}


	public String fazerRelaci(TipoProcesso proc,List<Administrador> admList2,List<Documentos>docList2) throws SQLException{
		List<TipoProcesso> procList = new ArrayList();
		salvo="falha";
		StringBuilder sb = new StringBuilder();

		procList=proDao.listarTipoProc();
		procList.forEach(pro2 ->{
			if(pro2.getNome().equals(proc.getNome())){
				this.TiProc = pro2;
			}
		});
		admList2.forEach(adm ->{
			try {
				salvo = proDao.salvarRelaciUser(TiProc, adm);
			} catch (SQLException e) {
				sb.append(salvo);
				e.printStackTrace();
			}		
		});
		docList2.forEach(doc1 ->{
			try {
				salvo2 = docDao.salvarRelaciDoc(doc1,TiProc);
			} catch (SQLException e) {
				sb.append(salvo);
				e.printStackTrace();
			}
		});
		if(salvo.equals("salvo")&&salvo2.equals("salvo")){
			proDao.darComite();
			docDao.darComiteDoc();
		}
		return salvo;
	}
	public boolean checarNome(TipoProcesso pro){
		List<TipoProcesso> proList = new ArrayList<TipoProcesso>();

		proList=proDao.listarTipoProc();
		for(TipoProcesso pro2:proList){
			if(pro.getNome().equalsIgnoreCase(pro2.getNome()));
			salvo="Esse nome ja existe na lista!";
			return false;
		}
		return true;
	}
	public TipoProcesso listarTipoProc(String nomeTipo){
		List<TipoProcesso> proList = new ArrayList<TipoProcesso>();
		TipoProcesso tiProc = new TipoProcesso();
		proList=proDao.listarTipoProc();
		for(TipoProcesso tp: proList){
			if(tp.getNome().equalsIgnoreCase(nomeTipo)){
				return tiProc=tp;
			}
		}
		return tiProc;
		
	}
	
}
