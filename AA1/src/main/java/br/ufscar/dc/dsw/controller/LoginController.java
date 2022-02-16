package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.dao.ClientDAO;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.util.Error;

@WebServlet(name = "Login", urlPatterns = {"/login/*", "/logout.jsp"})
public class LoginController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Error erros = new Error();
        if (request.getParameter("clienteOK") != null && request.getParameter("clienteOK") != "cliente_login=&cliente_senha="){			
		String login = request.getParameter("cliente_login");
		String password= request.getParameter("cliente_senha");
		
		if (login == null || login.isEmpty()) {
			erros.add("Login não informado!");
		}
		if (password == null || password.isEmpty()) {
			erros.add("Senha não informada!");
		}
		if (!erros.isExisteErros()) {
			
			ClientDAO dao = new ClientDAO();
			
			Client client = dao.getbyLogin(login);
			
			
			if (client != null) {
				if (client.getPassword().equals(password)) {
					request.getSession().setAttribute("clienteLogado", client);
					String URL = "/index.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(URL);
					rd.forward(request, response);
					return;
				} else {
					erros.add("Senha inválida!");
				}
			} else {
				erros.add("Cliente não encontrado!");
			}
		}
	}
		
        request.getSession().invalidate();
		request.setAttribute("mensagens", erros);
		String URL = "/index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
    }
}