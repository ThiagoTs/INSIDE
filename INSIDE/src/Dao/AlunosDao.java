package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Modelo.Administrador;
import Modelo.Aluno;
import daoUtil.ConnectionFactory;

public class AlunosDao {

	ConnectionFactory connection = null;
	private Connection con;
	private Statement stm;
	private PreparedStatement stmt;

	public AlunosDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}



	String sqlListar = "select * from alunos;";
	
	

	public String  Editar(Aluno alu) {
		String salvo = "falha";
		try {
			String sqlEditar = "UPDATE alunos SET nome = ?,cpf = ?,telefone = ?,matricula = ?,"
					+"email = ?,sexo = ?,curso = ?,ender = ?,respons = ?,city = ?,dataNasci =?";
					sqlEditar+= " WHERE id = ?";


			stmt = con.prepareStatement(sqlEditar);

			stmt.setString(1, alu.getNome());
			stmt.setString(2, alu.getCpf());
			stmt.setString(3, alu.getTelefone());
			stmt.setString(4, alu.getMatricula());
			stmt.setString(5, alu.getEmail());
			stmt.setString(6, alu.getSexo());
			stmt.setString(7, alu.getCurso());
			stmt.setString(8, alu.getEnder());
			stmt.setString(9, alu.getResponsavel());
			stmt.setString(10, alu.getCity());
			stmt.setDate(11, Date.valueOf(alu.getDataNasci()));
			stmt.setInt(12, alu.getId());


			stmt.executeUpdate();
			salvo = "salvo";

		} catch (SQLException e) {
			System.out.println("Erro na alteracao:" + e.getMessage());
		}

		return salvo;
	}

	public List<Aluno> listarAlunos() {
		List<Aluno> list = new ArrayList<>();
		ResultSet res = null;

		try {
			stm = con.createStatement();
			res = stm.executeQuery(sqlListar);

			while (res.next()){

				Aluno alu = new Aluno();
				
				
				alu.setId(res.getInt("id"));
				alu.setNome(res.getString("nome"));
				alu.setCpf(res.getString("cpf"));
				alu.setTelefone(res.getString("telefone"));
				alu.setMatricula(res.getString("matricula"));
				alu.setEmail(res.getString("email"));
				alu.setSexo(res.getString("sexo"));
				alu.setCurso(res.getString("curso"));
				alu.setEnder(res.getString("ender"));
				alu.setResponsavel(res.getString("respons"));
				alu.setCity(res.getString("city"));
				Date dataNascimento = res.getDate("dataNasci");
				alu.setDataNasci(dataNascimento.toLocalDate());
				
			

				list.add(alu);
			}
		}
		catch (SQLException e){
			System.out.println("Erro na consulta1:" + e.getMessage());
		}
		return list;
	}
}

