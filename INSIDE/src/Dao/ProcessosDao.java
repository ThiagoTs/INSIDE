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
import Modelo.Documentos;
import Modelo.Processo;
import Modelo.TipoProcesso;
import daoUtil.ConnectionFactory;

public class ProcessosDao {

	
	ConnectionFactory connection = null;
	private Connection con;
	private Statement stm;
	private PreparedStatement stmt;

	public ProcessosDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}

	String sqlListarProc = "select * from processos;";

    String sqlSalvar = "INSERT INTO processos(nome,status,comentarios,dataIni,id_Aluno)"+
    "VALUES(?,?,?,?,?);";

    String sqlRelacao = "INSERT INTO procopen(id_Proc,id_Resp,cont,status)"+
    "VALUES(?,?,?,?);";
    
    String sqlListarPorAluno = "select processos.* from processos,alunos where id_Aluno = alunos.id and alunos.id = ?;";
    
    String sqlListarPorAdm = "select processos.*,administradores.nome as nomeAdm,administradores.departamentos,procopen.status as statusPend,alunos.nome as alunoNome "
    		+ "from procopen,processos,administradores,alunos where id_Proc = processos.id and id_Resp = administradores.id and id_Aluno=alunos.id and administradores.id = ?;";
    		
    		
    public String salvarProc(Processo pro,Aluno alu) throws SQLException{
    	String salvo = "falha";
    	

        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setString(1, pro.getNome());
            stmt.setString(2, pro.getStatus());
            stmt.setString(3, pro.getComentarios());
            stmt.setDate(4, Date.valueOf(pro.getDataProc()));
            stmt.setInt(5, alu.getId());
            
       

            stmt.executeUpdate();

            //Grava as informaÃ§Ãµes se caso de problema os dados nÃ£o sÃ£o gravados
            con.commit();
            salvo = "salvo";

        } catch (SQLException e ) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação! ");
                    con.rollback();
                } catch(SQLException e2) {
                    System.err.print("Erro na transação 1!"+e2);
                    salvo = "\"Erro na transação!\"+e2";
                }
            }
            System.out.println("erro na execução: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }

        return salvo;
    }
    public String salvarRelacaoAdm(Processo pro, Administrador adm,int cont,String status) throws SQLException{
    	String salvo = "falha";
    	

        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlRelacao);
            stmt.setInt(1, pro.getId());
            stmt.setInt(2, adm.getId());
            stmt.setInt(3, cont);
            stmt.setString(4, status);
            
            

            stmt.executeUpdate();

            //Grava as informaÃ§Ãµes se caso de problema os dados nÃ£o sÃ£o gravados
            con.commit();
            salvo = "salvo";

        } catch (SQLException e ) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação! ");
                    con.rollback();
                } catch(SQLException e2) {
                    System.err.print("Erro na transação 2!"+e2);
                    salvo = "Erro na transação!\n"+e2;
                }
            }
            System.out.println("erro na execução: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }

        return salvo;
    }
    

    public List<Processo> listarProc() {
    	List<Processo> processos = new ArrayList<Processo>();
        ResultSet res = null;

		try {
			stm = con.createStatement();
			res = stm.executeQuery(sqlListarProc);

			while (res.next()){

				Processo proc = new Processo();
				
				
				proc.setId(res.getInt("id"));
				proc.setNome(res.getString("nome"));
				proc.setComentarios(res.getString("comentarios"));
				LocalDate data = res.getDate("dataIni").toLocalDate();
				proc.setDataProc(data);
				proc.setIdAluno(res.getInt("id_Aluno"));

                processos.add(proc);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta 1:" + e.getMessage());
        }
        return processos;
    }
    public List<Processo> listarProcProAluno(Aluno alu) {
    	List<Processo> processos = new ArrayList<Processo>();
        ResultSet res = null;

		try {
			
		    stmt = con.prepareStatement(sqlListarPorAluno);
            stmt.setInt(1, alu.getId());
            res = stmt.executeQuery();
            
			while (res.next()){

				Processo proc = new Processo();
				
				
				proc.setId(res.getInt("id"));
				proc.setNome(res.getString("nome"));
				proc.setComentarios(res.getString("comentarios"));
				LocalDate data = res.getDate("dataIni").toLocalDate();
				proc.setDataProc(data);
				proc.setStatus(res.getString("status"));
				proc.setIdAluno(res.getInt("id_Aluno"));

                processos.add(proc);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta 1:" + e.getMessage());
        }
        return processos;
    }
    public List<Processo> listarProcProAdm(Administrador adm) {
    	List<Processo> processos = new ArrayList<Processo>();
        ResultSet res = null;

		try {
			
		    stmt = con.prepareStatement(sqlListarPorAdm);
            stmt.setInt(1, adm.getId());
            res = stmt.executeQuery();
            
			while (res.next()){

				Processo proc = new Processo();
				
				
				proc.setId(res.getInt("id"));
				proc.setNome(res.getString("nome"));
				proc.setComentarios(res.getString("comentarios"));
				LocalDate data = res.getDate("dataIni").toLocalDate();
				proc.setDataProc(data);
				proc.setStatus(res.getString("status"));
				proc.setIdAluno(res.getInt("id_Aluno"));
				proc.setNomeAdm(res.getString("nomeAdm"));
				proc.setAdmDepart(res.getString("departamentos"));
				proc.setAlunoNome(res.getString("alunoNome"));
				proc.setStatusPend(res.getString("statusPend"));
				

                processos.add(proc);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta 1:" + e.getMessage());
        }
        return processos;
    }

}
