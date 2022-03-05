package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClientDAO;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.util.Error;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/clientes/*")
public class ClientController  extends HttpServlet{
        private static final long serialVersionUID = 1L;
	
	private ClientDAO dao;
	
	@Override
        public void init() {
                dao = new ClientDAO();
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
        
        if (clienteLogado == null) {
            erros.add("Necessita estar logado para acessar essa página.");

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
        
        List<Client> listaClientes = dao.getAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/list.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formCadastro.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender= request.getParameter("gender");
        String telephone = request.getParameter("telephone");
        String role = request.getParameter("role");
        if (role == null || role.equals("")) {
            role = "CLIENT";
        }
        LocalDate birth_date = LocalDate.now();
        try {
            birth_date = LocalDate.parse(request.getParameter("birth_date"));
        }
        catch (Exception e) {
            birth_date = LocalDate.now();
        }

        try {
            Client client = new Client(cpf, name, email, password, gender, telephone, birth_date, role);
            dao.insert(client);
        } catch (Exception e) {
            Error erros = new Error();
            erros.add("Erro nos dados preenchidos.");

            request.setAttribute("mensagens", erros);

            RequestDispatcher rd = request.getRequestDispatcher("/cliente/formCadastro.jsp");
            rd.forward(request, response);
        }
        Client clienteLogado = (Client) request.getSession().getAttribute("clienteLogado");

        if( clienteLogado != null ){
            if(clienteLogado.getRole().equals("ADMIN")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes");
                dispatcher.forward(request, response);
            }
        }else{
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            //dispatcher.forward(request, response);
            //String path = request.getRequestURI();
            //System.out.print("\n"+ path);
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
        Client client = dao.get(cpf);
        dao.delete(client);
        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        Client client= dao.get(cpf);
        request.setAttribute("cliente", client);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/formEdicao.jsp");
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
        Client client = dao.get(cpf);

        String name = request.getParameter("name");
        if (name == "") {
            name = client.getName();
        }
        String email = request.getParameter("email");
        if (email == "") {
            email = client.getEmail();
        }
        String password = request.getParameter("password");
        if (password == "") {
            password = client.getPassword();
        }
        String gender = request.getParameter("gender");
        if (gender == "") {
            gender = client.getGender();
        }

        String telephone= request.getParameter("telephone");
        if (telephone== "") {
            telephone = client.getTelephone();
        }

        String role = request.getParameter("role");
        if (role == "") {
            role = client.getRole();
        }

        LocalDate birthDate = client.getBirthDate();
        try {
            birthDate = LocalDate.parse(request.getParameter("birthDate"));
        }
        catch (Exception e) {
            birthDate = client.getBirthDate();
        }

        Client clienteAtualizado = new Client(cpf, name, email, password, gender, telephone, birthDate, role);
        try {
            dao.update(clienteAtualizado);
        } catch (Exception e) {
            erros.add("Erro nos dados preenchidos.");

            request.setAttribute("mensagens", erros);

            RequestDispatcher rd = request.getRequestDispatcher("/cliente/formEdicao.jsp");
            rd.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes");
        dispatcher.forward(request, response);
    }
}


