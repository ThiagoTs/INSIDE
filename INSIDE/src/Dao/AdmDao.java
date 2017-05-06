package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modelo.Administrador;
import Modelo.Processo;
import daoUtil.ConnectionFactory;

public class AdmDao {
	ConnectionFactory connection = null;
	private Connection con;
	private Statement stm;
	private PreparedStatement stmt;

	public AdmDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}



	String sqlSalvar = "INSERT INTO processos_internos.administradores" +
			"(nome,CPF,telefone,matricula,email,sexo,cargo,departamentos,login,senha,id_Processos,tipoUser)" +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)" ;


	public String salvar(Administrador adm,Processo processo) throws SQLException {


		String salvo = "falha";


		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlSalvar);
			stmt.setString(1, adm.getNome());
			stmt.setString(2, adm.getCpf());
			stmt.setString(3, adm.getTelefone());
			stmt.setString(4, adm.getMatricula());
			stmt.setString(5, adm.getEmail());
			stmt.setString(6, adm.getSexo());
			stmt.setString(7, adm.getCargo());
			stmt.setString(8, adm.getDepartamento());
			stmt.setString(9, adm.getLogin());
			stmt.setString(10, adm.getSenha());
			stmt.setInt(11,processo.getId() );
			stmt.setString(12, adm.getTipoUser());
			

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

	public String  Editar(Administrador adm) {
		String salvo = "falha";
		try {
			String sql;
			sql  = "UPDATE administradores SET nome = ?, cpf = ?, telefone = ?, matricula = ? ,"
					+ " email = ?,sexo = ?, cargo = ?, departamentos = ?,login = ?, senha = ?,tipoUser = ?";
			sql += "WHERE id = ?";

			stmt = con.prepareStatement(sql);

			stmt.setString(1, adm.getNome());
			stmt.setString(2, adm.getCpf());
			stmt.setString(3, adm.getTelefone());
			stmt.setString(4, adm.getMatricula());
			stmt.setString(5, adm.getEmail());
			stmt.setString(6, adm.getSexo());
			stmt.setString(7, adm.getCargo());
			stmt.setString(8, adm.getDepartamento());
			stmt.setString(9, adm.getLogin());
			stmt.setString(10, adm.getSenha());
			stmt.setString(10, adm.getTipoUser());
			stmt.setInt(10, adm.getId());


			stmt.executeUpdate();
			salvo = "salvo";

		} catch (SQLException e) {
			System.out.println("Erro na alteracao:" + e.getMessage());
		}

		return salvo;
	}

	public List<Administrador> listarAdms() {
		List<Administrador> list = new ArrayList<Administrador>();
		ResultSet res = null;

		try {
			stm = con.createStatement();
			res = stm.executeQuery("SELECT * FROM administradores");

			while (res.next()){

				Administrador adm = new Administrador();
				
				
				adm.setId(res.getInt("id"));
				adm.setNome(res.getString("nome"));
				adm.setCpf(res.getString("cpf"));
				adm.setTelefone(res.getString("telefone"));
				adm.setMatricula(res.getString("matricula"));
				adm.setEmail(res.getString("email"));
				adm.setSexo(res.getString("sexo"));
				adm.setCargo(res.getString("cargo"));
				adm.setDepartamento(res.getString("departamentos"));
				adm.setLogin(res.getString("login"));
				adm.setSenha(res.getString("senha"));
				adm.setId_Processos(res.getInt("id_Processos"));
				adm.setTipoUser(res.getString("tipoUser"));
				
			

				list.add(adm);
			}
		}
		catch (SQLException e){
			System.out.println("Erro na consulta1:" + e.getMessage());
		}
		return list;
	}
}
