package br.com.ufg.si.pw.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import br.com.ufg.si.pw.bd.AlunoDao;
import br.com.ufg.si.pw.model.Aluno;

/**
 * {@link ServletContextListener} utilizado para iniciar a aplicacao.
 *  
 * @author Ana Leticia
 *
 */
public class StartupServletListener implements ServletContextListener {
	
	private static final Logger LOG = Logger.getLogger(StartupServletListener.class);
	

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			AlunoDao dao = new AlunoDao();
			
			dao.incluirAluno(new Aluno(1, "Ana Leticia"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
