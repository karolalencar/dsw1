package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.AppointmentDAO;
import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Professional;
import br.ufscar.dc.dsw.util.Error;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/appointment/*")
public class AppointmentController  extends HttpServlet{
        private static final long serialVersionUID = 1L;
	
	private AppointmentDAO dao;
	
	@Override
        public void init() {
                dao = new AppointmentDAO();
        }
	
	@Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
                doGet(request, response);
        }
	
	@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String action = request.getPathInfo();
                if (action == null) {
                action = "";
        }
        
        try {
            switch (action) {
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }
	
	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Error erros = new Error();
        Client clienteLogado = (Client) request.getSession().getAttribute("clienteLogado");
        Professional professionalLogged = (Professional) request.getSession().getAttribute("professionalLogged");
       
        if (clienteLogado == null && professionalLogged == null) {
            erros.add("Necessita estar logado para acessar essa página.");

            request.setAttribute("mensagens", erros);
            String URL = "/login.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
        }
        if (clienteLogado != null){
            List <Appointment> listaConsultasCliente = dao.getAllByClient(clienteLogado.getCpf());
            request.setAttribute("listaConsultasCliente", listaConsultasCliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/listAppointment.jsp");
            dispatcher.forward(request, response);
        }
        if (professionalLogged != null){
            List <Appointment> listaConsultasProfissional = dao.getAllByProfessional(professionalLogged.getCpf());
            request.setAttribute("listaConsultasProfissional", listaConsultasProfissional);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/listAppointment.jsp");
            dispatcher.forward(request, response);
        }
        
        
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    
}

