package Dao;

import java.sql.Connection;
import java.sql.Date;
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

public class TipoProcDao {

	
	ConnectionFactory connection = null;
	private Connection con;
	private Statement stm;
	private PreparedStatement stmt;

	public TipoProcDao() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}
	String sqlSalvarTipoProc = "INSERT INTO tipoproc"
+"(nome,descricao) VALUES(?,?);";
	
	String sqlSalvarRelac = "insert into admProc" +
            "(id_Processo, id_Adm)" +
            "values(?,?);";
	String sqlListarProc = "select * from tipoproc;";
    
    String sqlListarAdmsTipo = "select administradores.nome,tipoproc.nome from admProc,tipoproc,administradores\n "+
 "where admProc.id_Processo = tipoproc.id and administradores.id = admProc.id_Adm and  tipoproc.id=?;";

    public String salvarTipoProc(TipoProcesso pro) throws SQLException{
    	String salvo = "falha";
    	
    	
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvarTipoProc);
            stmt.setString(1, pro.getNome());
            stmt.setString(2, pro.getDescricao());
       

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
                    System.err.print("Erro na transação!"+e2);
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
    public String salvarRelaciUser(TipoProcesso proc, Administrador adm) throws SQLException {

        String salvo = "falha";
        
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvarRelac);
            stmt.setInt(1, proc.getId());
            stmt.setInt(2, adm.getId());
       

            stmt.executeUpdate();

            //Grava as informaÃ§Ãµes se caso de problema os dados nÃ£o sÃ£o gravados
            
            salvo = "salvo";

        } catch (SQLException e ) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação! ");
                    con.rollback();
                } catch(SQLException e2) {
                    System.err.print("Erro na transação!"+e2);
                    salvo = "\"Erro na transação!\"+e2";
                }
            }
            System.out.println("erro na execuÃ§Ã£o: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            
        }

        return salvo;
    }
    public void darComite() throws SQLException{
    	this.con.commit();
    	con.setAutoCommit(true);
    }

    public List<TipoProcesso> listarTipoProc() {
    	List<TipoProcesso> processos = new ArrayList<TipoProcesso>();
        ResultSet res = null;

		try {
			stm = con.createStatement();
			res = stm.executeQuery(sqlListarProc);

			while (res.next()){

				TipoProcesso proc = new TipoProcesso();
				
				
				proc.setId(res.getInt("id"));
				proc.setNome(res.getString("nome"));
				proc.setDescricao(res.getString("descricao"));

                processos.add(proc);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta 1:" + e.getMessage());
        }
        return processos;
    }
    public List<Administrador> listarAdmsTipo(TipoProcesso tiProc) {
    	List<Administrador> admList = new ArrayList<>();
    	 ResultSet res = null;

         try {

             stmt = con.prepareStatement(sqlListarAdmsTipo);
             stmt.setInt(1, tiProc.getId());
             res = stmt.executeQuery();

			while (res.next()){

				Administrador adm = new Administrador();
				
				adm.setNome(res.getString("nome"));

                admList.add(adm);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta 1:" + e.getMessage());
        }
        return admList;
    }

}
