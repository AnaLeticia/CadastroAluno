package br.com.ufg.si.pw.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.ufg.si.pw.model.Aluno;

public class AlunoDao {
	
	private static final Logger LOG = Logger.getLogger(AlunoDao.class);
	private static final String USER = "postgres";
	private static final String PASSWORD = "oobj.postgres";
	
	private Connection conn;

	public AlunoDao() throws SQLException {
		
		carregarClasseDriverPostgres();
		
		obterConnectionPostgres();
		
		try {
			dropTabelaAluno();
			criarTabelaAluno();
		} catch (SQLException e) {
			// nao tem problema
			LOG.error(e);
		}
	}
	
	private void carregarClasseDriverPostgres() {
		try {
			LOG.info("Carregando classe do driver do Postgres...");
			Class.forName("org.postgresql.Driver");
			LOG.info("Classe do driver do Postgres carregada com sucesso...");
		} catch (ClassNotFoundException e1) {
			LOG.error("Falha ao carregar a clase do driver do Postgres:", e1);
		}
	}

	private void obterConnectionPostgres() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/crud-aluno";
		conn = DriverManager.getConnection(url, USER, PASSWORD);
	}
	
	public Aluno listarAluno(Integer matricula) throws SQLException {
		Aluno aluno = null;
		String sql = "select * from aluno order by matricula";
		// Obtém referência para uma sentença SQL.
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		// Executa a instrução SQL.
		ResultSet rs = prepareStatement.executeQuery();
		while (rs.next()) {
			int matriculaBd = rs.getInt("matricula");
			String nomeBd = rs.getString("nome");

			aluno = new Aluno(matriculaBd, nomeBd);
		}
		rs.close();
		prepareStatement.close();
		return aluno;
	}

	public List<Aluno> listarAlunos() throws SQLException {
		List<Aluno> alunos = new ArrayList<Aluno>();
		String sql = "select * from aluno order by matricula";
		// Obtém referência para uma sentença SQL.
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		// Executa a instrução SQL.
		ResultSet rs = prepareStatement.executeQuery();
		while (rs.next()) {
			int matricula = rs.getInt("matricula");
			String nome = rs.getString("nome");

			Aluno aluno = new Aluno(matricula, nome);
			alunos.add(aluno);
		}
		rs.close();
		prepareStatement.close();
		return alunos;
	}
	
	public boolean apagarAluno(Integer matricula) throws SQLException {
		Aluno aluno = null;
		if (matricula != null) {
			aluno = new Aluno(matricula, null);
		}
		return apagarAluno(aluno);
	}

	public boolean apagarAluno(Aluno aluno) throws SQLException {
		String sql = "delete from aluno where matricula=?";
		// Obtém referência para uma sentença SQL.
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, aluno.getMatricula());
		// Executa a instrução SQL.
		prepareStatement.executeUpdate();
		prepareStatement.close();
		return false;
	}

	public void alterarAluno(Aluno aluno) throws SQLException {
		String sql = "update aluno set nome=? where matricula=?";
		// Obtém referência para uma sentença SQL.
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, aluno.getNome());
		prepareStatement.setInt(2, aluno.getMatricula());
		// Executa a instrução SQL.
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}

	public void incluirAluno(Aluno aluno) throws SQLException {
		String sql = "insert into aluno (matricula, nome) values (?, ?)";
		// Obtém referência para uma sentença SQL.
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, aluno.getMatricula());
		prepareStatement.setString(2, aluno.getNome());
		// Executa a instrução SQL.
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}

	private void criarTabelaAluno() throws SQLException {
		String sql = "create table aluno (";
		sql += "matricula int, ";
		sql += "nome varchar(255) ";
		sql += ")";

		// Obtém referência para uma sentença SQL.
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		// Executa a instrução SQL.
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
	
	private void dropTabelaAluno() throws SQLException {
		String sql = "drop table aluno";

		// Obtém referência para uma sentença SQL.
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		// Executa a instrução SQL.
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
}