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

@WebServlet(name = "Index", urlPatterns = {"/index*"})
public class IndexController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String URL = "/index.jsp";
      RequestDispatcher rd = request.getRequestDispatcher(URL);
      rd.forward(request, response);
    }
}