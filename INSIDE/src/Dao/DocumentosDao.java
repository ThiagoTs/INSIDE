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
			"(nome,arquivo)" +
			"VALUES(?,?)" ;


	public String salvar(Documentos doc) throws SQLException {


		String salvo = "falha";


		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlSalvar);
			stmt.setString(1, doc.getNome());
			stmt.setString(2, doc.getArquivo());
			

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
	public String  Editar(Documentos doc) {
		String salvo = "falha";
		try {
			String sql;
			sql  = "UPDATE documentos SET nome = ?, arquivo= ?";
			sql += "WHERE id = ?";

			stmt = con.prepareStatement(sql);

			stmt.setString(1, doc.getNome());
			stmt.setString(2, doc.getArquivo());
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
				doc.setArquivo(res.getString("arquivo"));
				

				list.add(doc);
			}
		}
		catch (SQLException e){
			System.out.println("Erro na consulta1:" + e.getMessage());
		}
		return list;
	}

}
