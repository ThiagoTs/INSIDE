package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modelo.Administrador;
import Modelo.Documentos;
import Modelo.TipoProcesso;
import daoUtil.ConnectionFactory;

public class DocumentosDao {
	
	ConnectionFactory connection = null;
	private Connection con;
	private Statement stm;
	private PreparedStatement stmt;

	public DocumentosDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}
	
	String sqlSalvar = "INSERT INTO processos_internos.documentos" +
			"(nome)" +
			"VALUES(?)" ;

	String sqlSalvarRelacDoc = "insert into procDoc" +
            "(id_Documento, id_Processo)" +
            "values(?,?);";

	
	public String salvar(Documentos doc) throws SQLException {


		String salvo = "falha";


		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlSalvar);
			stmt.setString(1, doc.getNome());
			

			stmt.executeUpdate();

			//Grava as informaÃ§Ãµes se caso de problema os dados nÃ£o sÃ£o gravados
			con.commit();
			salvo = "salvo";

		} catch (SQLException e ) {
			if (con != null) {
				try {
					System.err.print("Rollback efetuado na transação");
					con.rollback();
				} catch(SQLException e2) {
					System.err.print("Erro na transação!"+e2);
					salvo = "\"Erro na transação!\"+e2";
				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			con.setAutoCommit(true);
		}

		return salvo;
	}
	public String salvarRelaciDoc(Documentos doc,TipoProcesso tiProc) throws SQLException {


		String salvo = "falha";


		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlSalvarRelacDoc);
			stmt.setInt(1, doc.getId());
			stmt.setInt(2, tiProc.getId());
			

			stmt.executeUpdate();

			//Grava as informaÃ§Ãµes se caso de problema os dados nÃ£o sÃ£o gravados
			salvo = "salvo";

		} catch (SQLException e ) {
			if (con != null) {
				try {
					System.err.print("Rollback efetuado na transação DOC");
					con.rollback();
				} catch(SQLException e2) {
					System.err.print("Erro na transação DOC!"+e2);
					salvo = "\"Erro na transação!\"+e2";
				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			
		}

		return salvo;
	}
	public void darComiteDoc() throws SQLException{
		this.con.commit();
		con.setAutoCommit(true);
	}
	public String  Editar(Documentos doc) {
		String salvo = "falha";
		try {
			String sql;
			sql  = "UPDATE documentos SET nome = ?";
			sql += "WHERE id = ?";

			stmt = con.prepareStatement(sql);

			stmt.setString(1, doc.getNome());
			stmt.setInt(3, doc.getId());


			stmt.executeUpdate();
			salvo = "salvo";

		} catch (SQLException e) {
			System.out.println("Erro na alteracao:" + e.getMessage());
		}

		return salvo;
	}
	public String excluir(Documentos doc) {
		String excluido = "falha";
		try {
			String sql;
			sql  = " DELETE FROM documentos where id=?";

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, doc.getId());


			stmt.executeUpdate();
			excluido = "excluido";

		} catch (SQLException e) {
			System.out.println("Erro na alteracao:" + e.getMessage());
		}

		return excluido;
	}
	public List<Documentos> listarDocs() {
		List<Documentos> list = new ArrayList<Documentos>();
		ResultSet res = null;

		try {
			stm = con.createStatement();
			res = stm.executeQuery("SELECT * FROM documentos");

			while (res.next()){

				Documentos doc = new Documentos();
				
				
				doc.setId(res.getInt("id"));
				doc.setNome(res.getString("nome"));
				

				list.add(doc);
			}
		}
		catch (SQLException e){
			System.out.println("Erro na consulta1:" + e.getMessage());
		}
		return list;
	}

}
