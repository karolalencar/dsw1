package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.util.Error;

@WebServlet(name="Index", urlPatterns = {"index.jsp"})
public class IndexController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
}
