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

    String sqlSalvar = "INSERT INTO processos(nome,status,comentarios,dataIni,id_Aluno,nomeAluno)"+
    "VALUES(?,?,?,?,?,?);";

    String sqlRelacao = "INSERT INTO procopen(id_Proc,id_Resp,cont,status)"+
    "VALUES(?,?,?,?);";
    
    String sqlListarPorAluno = "select processos.* from processos,alunos where id_Aluno = alunos.id and alunos.id = ?;";
    
    String sqlListarPorAdm = "select processos.*,administradores.nome as nomeAdm,administradores.departamentos,procopen.status" 
    		+" as statusPend from procopen,processos,administradores where procopen.id_Proc = processos.id"
    		+ " and procopen.id_Resp = administradores.id and administradores.id = ?;";
    String sqlListarPorProcesso = "select procopen.id as idOpen,procopen.cont,processos.*,administradores.nome as nomeAdm,administradores.departamentos,procopen.status"
+" as statusPend,alunos.nome as alunoNome from procopen,processos,administradores,alunos"
 +" where procopen.id_Proc = processos.id and procopen.id_Resp = administradores.id and processos.id_Aluno=alunos.id and processos.id=?;";
    		
    public String salvarProc(Processo pro) throws SQLException{
    	String salvo = "falha";
    	

        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setString(1, pro.getNome());
            stmt.setString(2, pro.getStatus());
            stmt.setString(3, pro.getComentarios());
            stmt.setDate(4, Date.valueOf(pro.getDataProc()));
            stmt.setInt(5, pro.getIdAluno());
            stmt.setString(6, pro.getAlunoNome());
            
       

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
				proc.setAlunoNome(res.getString("nomeAluno"));

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
            System.out.println("Erro na consulta proAlu:" + e.getMessage());
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
				proc.setAlunoNome(res.getString("nomeAluno"));
				proc.setStatusPend(res.getString("statusPend"));
				

                processos.add(proc);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta proAdm:" + e.getMessage());
        }
        return processos;
    }
    public List<Processo> listarProcessos(Processo pro) {
    	List<Processo> processos = new ArrayList<Processo>();
        ResultSet res = null;

		try {
			
		    stmt = con.prepareStatement(sqlListarPorProcesso);
            stmt.setInt(1, pro.getId());
            res = stmt.executeQuery();
            
			while (res.next()){

				Processo proc = new Processo();
				
				proc.setIdOpen(res.getInt("idopen"));
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
				proc.setCont(res.getInt("cont"));
				

                processos.add(proc);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta pro:" + e.getMessage());
        }
        return processos;
    }
    public String  alterarStatus(Processo pro) {
		String salvo = "falha";
		try {
			String sql;
			sql  = "update processos set status = ?,comentarios = ? where id=?;";

			stmt = con.prepareStatement(sql);

			stmt.setString(1, pro.getStatus());
			stmt.setString(2, pro.getComentarios());
			stmt.setInt(2, pro.getId());
		

			stmt.executeUpdate();
			salvo = "salvo";

		} catch (SQLException e) {
			System.out.println("Erro na alteracao status:" + e.getMessage());
		}

		return salvo;
	}
    public String  alterarStatusOn(Processo pro) {
 		String salvo = "falha";
 		try {
 			String sql;
 			sql  = "update procopen set status = ? where id=?;";

 			stmt = con.prepareStatement(sql);

 			stmt.setString(1, pro.getStatusPend());
 			stmt.setInt(2, pro.getIdOpen());
 		

 			stmt.executeUpdate();
 			salvo = "salvo";

 		} catch (SQLException e) {
 			System.out.println("Erro na alteracao on:" + e.getMessage());
 		}

 		return salvo;
 	}
    public String  alterarStatusNext(Processo pro) {
 		String salvo = "falha";
 		try {
 			String sql;
 			sql  = "update procopen set status = ? where id=?;";

 			stmt = con.prepareStatement(sql);

 			stmt.setString(1, pro.getStatusPend());
 			stmt.setInt(2, pro.getIdOpen());
 		

 			stmt.executeUpdate();
 			salvo = "salvo";

 		} catch (SQLException e) {
 			System.out.println("Erro na alteracao next:" + e.getMessage());
 		}

 		return salvo;
 	}
    public String  alterarStatusOff(Processo pro) {
 		String salvo = "falha";
 		try {
 			String sql;
 			sql  = "update procopen set status = ? where id=?;";

 			stmt = con.prepareStatement(sql);

 			stmt.setString(1, pro.getStatusPend());
 			stmt.setInt(2, pro.getIdOpen());
 		

 			stmt.executeUpdate();
 			salvo = "salvo";

 		} catch (SQLException e) {
 			System.out.println("Erro na alteracao next:" + e.getMessage());
 		}

 		return salvo;
 	}

    

}
