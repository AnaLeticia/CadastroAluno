package br.com.ufg.si.pw.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ufg.si.pw.bd.AlunoDao;
import br.com.ufg.si.pw.model.Aluno;

/**
 * Servlet responsável pelas operacoes de crud com aluno.
 * 
 * @author Ana Leticia
 *
 */
@WebServlet(value = "/AlunoCtrl", name = "AlunoCtrl")
public class AlunoController extends HttpServlet {

	private static final long serialVersionUID = -8378017949145441001L;

	private static String INSERT_OR_EDIT = "/aluno.jsp";
	private static String LIST_USER = "/listagem.jsp";

	private AlunoDao dao;

	public AlunoController() {
		try {
			dao = new AlunoDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String forward = "";
		String action = "listar";
		action = req.getParameter("action");

		if (action != null) {
			switch (action) {
			case "listar":
				try {
					forward = LIST_USER;
					req.setAttribute("alunos", dao.listarAlunos());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "editar":
				forward = INSERT_OR_EDIT;
				Integer matriculaEdit = Integer.parseInt(req.getParameter("matricula"));
				Aluno aluno = null;
				try {
					aluno = dao.listarAluno(matriculaEdit);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				req.setAttribute("aluno", aluno);
				break;
			case "deletar":
				int matricula = Integer.parseInt(req.getParameter("matricula"));
				try {
					dao.apagarAluno(matricula);
					forward = LIST_USER;
					req.setAttribute("alunos", dao.listarAlunos());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "incluir":
				forward = INSERT_OR_EDIT;
				break;
			}
		}

		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Aluno aluno = new Aluno();
		aluno.setMatricula(request.getParameter("matricula"));
		aluno.setNome(request.getParameter("nome"));

		try {
			dao.incluirAluno(aluno);

			RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
			request.setAttribute("alunos", dao.listarAlunos());
			view.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
