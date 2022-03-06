package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfessionalDAO;
import br.ufscar.dc.dsw.domain.Professional;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.util.Error;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profissionais/*")
public class ProfessionalController  extends HttpServlet{
        private static final long serialVersionUID = 1L;
	
	private ProfessionalDAO dao;
	
	@Override
        public void init() {
                dao = new ProfessionalDAO();
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
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                case "/profissionaisArea":
                    professionalsByArea(request, response);
                    break;   
                case "/profissionais/buscaPorAreaJS":
                    buscaPorAreaJS(request, response);
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
        Professional professionalLogged = (Professional) request.getSession().getAttribute("professionalLogged");
        Client clienteLogado = (Client) request.getSession().getAttribute("clienteLogado");

        List<Professional> professionalsList = dao.getAll();
        request.setAttribute("professionalsList", professionalsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/list.jsp");

        if(clienteLogado == null && professionalLogged == null){
            dispatcher = request.getRequestDispatcher("/listProfessionals.jsp");
        }
        else if(clienteLogado != null){
            if(clienteLogado.getRole().equals("CLIENT")){
                dispatcher = request.getRequestDispatcher("/cliente/listProfessionals.jsp");
            }else if(clienteLogado.getRole().equals("ADMIN")){
                dispatcher = request.getRequestDispatcher("/profissional/list.jsp");
            }   
        }
        else if(professionalLogged != null){
            if(professionalLogged.getRole().equals("ADMIN") ){
                dispatcher = request.getRequestDispatcher("/profissional/list.jsp");
            }
        }
        dispatcher.forward(request, response);
    }

    private void professionalsByArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Error erros = new Error();
        Professional professionalLogged = (Professional) request.getSession().getAttribute("professionalLogged");
        Client clienteLogado = (Client) request.getSession().getAttribute("clienteLogado");
        String area = request.getParameter("area_conhecimento");
        String expertise = request.getParameter("expertise");
        
        if(expertise == ""){
            List<Professional> professionalsList = dao.getAllByArea(area);
            request.setAttribute("professionalsList", professionalsList);
            request.setAttribute("areaConhecimento", area);
        }else{
            List<Professional> professionalsList = dao.getAllByAreaExpertise(area, expertise);
            request.setAttribute("professionalsList", professionalsList);
            request.setAttribute("areaConhecimento", area);
        }
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/list.jsp");
        
        if(clienteLogado == null && professionalLogged == null){
            dispatcher = request.getRequestDispatcher("/listProfessionals.jsp");
        }
        else if(clienteLogado != null){
            if(clienteLogado.getRole().equals("CLIENT")){
                dispatcher = request.getRequestDispatcher("/cliente/listProfessionals.jsp");
            }else if(clienteLogado.getRole().equals("ADMIN")){
                dispatcher = request.getRequestDispatcher("/profissional/list.jsp");
            }   
        }
        else if(professionalLogged != null){
            if(professionalLogged.getRole().equals("ADMIN") ){
                dispatcher = request.getRequestDispatcher("/profissional/list.jsp");
            }
        }
        dispatcher.forward(request, response);

    }

    private void buscaPorAreaJS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String area = request.getParameter("area");

        String buffer = "<tr><td>Expertise</td><td><select id='expertise' name='expertise' "
                + "onchange='apresenta()'>";
        buffer = buffer + "<option value=''>Selecione</option>";

        List<Professional> professionalsList = dao.getAllByArea(area);

        for (Professional professional : professionalsList) {
            buffer = buffer + "<option value='" + professional.getExpertise() + "'>" + professional.getExpertise()  + "</option>";
        }
        buffer = buffer + "</select></td>";
        
        
        //System.out.println(buffer);
        response.getWriter().println(buffer);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/formCadastro.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getParameter("enviar") != null) {
    		try {
    			request.setCharacterEncoding("UTF-8");
    	        String cpf = request.getParameter("cpf");
    	        String name = request.getParameter("name");
    	        String email = request.getParameter("email");
    	        String password = request.getParameter("password");
    	        String role = request.getParameter("role");
    	        String qualifications = request.getParameter("qualification");
    	        String knowledge_area = request.getParameter("knowledge_area");
    	        String expertise = request.getParameter("expertise");
    	        if (role == null || role.equals("")) {
    	            role = "PROF";
    	        }
            	
                Professional professional = new Professional(cpf, name, email, password, role, qualifications, knowledge_area, expertise);
                dao.insert(professional);
            } catch (Exception e) {
                Error erros = new Error();
                erros.add("Erro nos dados preenchidos.");

                request.setAttribute("mensagens", erros);

                RequestDispatcher rd = request.getRequestDispatcher("/profissional/formCadastro.jsp");
                rd.forward(request, response);
            }
    	}

        
        Client clienteLogado = (Client) request.getSession().getAttribute("clienteLogado");

        if(clienteLogado != null){
            if(clienteLogado.getRole().equals("ADMIN")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/profissionais");
                dispatcher.forward(request, response);
            }
        }else{
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            //dispatcher.forward(request, response);
            response.sendRedirect("/AA1/login.jsp");
        }
        
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Error erros = new Error();
        Client clienteLogado = (Client) request.getSession().getAttribute("clienteLogado");
        
        if (clienteLogado == null) {
            erros.add("Precisa estar logado para acessar essa página.");

            request.setAttribute("mensagens", erros);
            String URL = "/login.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
        } else if (!clienteLogado.getRole().equals("ADMIN")) {
            erros.add("Não possui permissão de acesso.");
            erros.add("Apenas [ADMIN] pode acessar essa página.");

            request.setAttribute("mensagens", erros);
            String URL = "/noAuth.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
        }
        
        String cpf = request.getParameter("cpf");
        Professional professional = dao.get(cpf);
        dao.delete(professional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissionais/lista");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Error erros = new Error();
        Client clienteLogado = (Client) request.getSession().getAttribute("clienteLogado");
        System.out.print("cheguei aqui 1");
        if (clienteLogado == null) {
            System.out.print("cheguei aqui 1");
            erros.add("Precisa estar logado para acessar essa página.");

            request.setAttribute("mensagens", erros);
            String URL = "/login.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
        } else if (!clienteLogado.getRole().equals("ADMIN")) {
            erros.add("Não possui permissão de acesso.");
            erros.add("Apenas [ADMIN] pode acessar essa página.");

            request.setAttribute("mensagens", erros);
            String URL = "/noAuth.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
        }
        
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        Professional professional = dao.get(cpf);
        request.setAttribute("profissional", professional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/formEdicao.jsp");
        dispatcher.forward(request, response);
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Error erros = new Error();
        Client clienteLogado = (Client) request.getSession().getAttribute("clienteLogado");
        
        if (clienteLogado == null) {
            erros.add("Precisa estar logado para acessar essa página.");

            request.setAttribute("mensagens", erros);
            String URL = "/login.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
        } else if (!clienteLogado.getRole().equals("ADMIN")) {
            erros.add("Não possui permissão de acesso.");
            erros.add("Apenas [ADMIN] pode acessar essa página.");

            request.setAttribute("mensagens", erros);
            String URL = "/noAuth.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
        }
        
        request.setCharacterEncoding("UTF-8");

        String cpf = request.getParameter("cpf");
        Professional professional = dao.get(cpf);

        String name = request.getParameter("name");
        if (name == "") {
            name = professional.getName();
        }
        String email = request.getParameter("email");
        if (email == "") {
            email = professional.getEmail();
        }
        String password = request.getParameter("password");
        if (password == "") {
            password = professional.getPassword();
        }
        String qualifications = request.getParameter("qualifications");
        if (qualifications == "") {
            qualifications = professional.getQualifications();
        }

        String knowledge_area = request.getParameter("knowledge_area");
        if (knowledge_area == "") {
            knowledge_area = professional.getKnowledgeArea();
        }

        String role = request.getParameter("role");
        if (role == "") {
            role = professional.getRole();
        }

        String expertise = request.getParameter("expertise");
        if (expertise == "") {
        	expertise = professional.getExpertise();
        }

        Professional updateProfessional = new Professional(cpf, name, email, password, role, qualifications, knowledge_area, expertise);
        try {
            dao.update(updateProfessional);
        } catch (Exception e) {
            erros.add("Erro nos dados preenchidos.");

            request.setAttribute("mensagens", erros);

            RequestDispatcher rd = request.getRequestDispatcher("/profissional/formEdicao.jsp");
            rd.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("profissionais");
        dispatcher.forward(request, response);
    }
}

