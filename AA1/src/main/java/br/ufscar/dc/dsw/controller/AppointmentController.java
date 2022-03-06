package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.AppointmentDAO;
import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.dao.ClientDAO;
import br.ufscar.dc.dsw.domain.Professional;
import br.ufscar.dc.dsw.dao.ProfessionalDAO;
import br.ufscar.dc.dsw.util.Error;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

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
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | MessagingException |ServletException e) {
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

    private void insere(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException, MessagingException {
    	Error erros = new Error();
    	Client clienteLogado = (Client) request.getSession().getAttribute("clienteLogado");
        Professional professionalLogged = (Professional) request.getSession().getAttribute("professionalLogged");
       
        if (clienteLogado == null) {
            erros.add("Necessita estar logado para acessar essa página.");

            request.setAttribute("mensagens", erros);
            String URL = "/login.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(URL);
		    rd.forward(request, response);
            return;
        }
        if (clienteLogado != null) {
            request.setCharacterEncoding("UTF-8");
            String hora = request.getParameter("horario");
        	hora = hora.substring(0, 2);
        	Integer horaConsulta = Integer.parseInt(hora);
        	String videoConf = request.getParameter("videoConf");
        	String cpfCliente = request.getParameter("cpfCliente");
        	String cpfProfessional = request.getParameter("cpfProfissional");
        	LocalDate dataConsulta = LocalDate.now();
            try {
                dataConsulta = LocalDate.parse(request.getParameter("data"));
            } catch (Exception e) {
                dataConsulta = LocalDate.now();
            }
            
            if (request.getParameter("data") != null && request.getParameter("horario") != null && request.getParameter("appointmentOK") != null) {
            	AppointmentDAO appointmentDao = new AppointmentDAO();
            	
            	Appointment appointment = new Appointment(cpfCliente, cpfProfessional, dataConsulta, horaConsulta);
            	
            	boolean existe = false;
            	
            	List<Appointment> clientAppointments = appointmentDao.getAllByClient(cpfCliente);
            	
            	for (Appointment i : clientAppointments) {
            		if (i.getCpfCliente().equals(cpfCliente) && i.getDataConsulta().equals(dataConsulta) && i.getHoraConsulta().equals(horaConsulta)) {
            			existe = true;
            		}
            		else {
            			List<Appointment> professionalAppointments = appointmentDao.getAllByProfessional(cpfProfessional);
            			for (Appointment j : professionalAppointments) {
            				if (j.getDataConsulta().equals(dataConsulta) && j.getHoraConsulta().equals(horaConsulta)) {
            					existe = true;
            				}
            			}
            		}
            		
            	}
            	
            	
            	if (!existe) {
                    dao.insert(appointment);
                    String URL = "/appointment"; 
                    sendEmailClient(request, response, appointment);
                    sendEmailProf(request, response, appointment);
                    RequestDispatcher rd = request.getRequestDispatcher(URL);
                    rd.forward(request, response);
            	} else {
                    erros.add("O horário escolhido já está ocupado por você ou pelo profissional.");
                    request.setAttribute("mensagens", erros);
                    String URL ="/cliente/formConsulta.jsp?cpfProf=" + cpfProfessional;
                    RequestDispatcher rd = request.getRequestDispatcher(URL);
                    rd.forward(request, response);
                    return;
            	}
            }
        }
        
        
        response.sendRedirect("/AA1/cliente/listProfessionals.jsp");
        return;
    }

    private void sendEmailClient(HttpServletRequest request, HttpServletResponse response, Appointment appointment) throws ServletException, IOException, MessagingException {
    	ProfessionalDAO daoProfessional = new ProfessionalDAO();
        Professional professional = daoProfessional.get(appointment.getCpfProfissional());
        ClientDAO daoClient = new ClientDAO();
        Client client = daoClient.get(appointment.getCpfCliente());

        Properties props = new Properties();
        
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
        

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("consultaprofissionais1@gmail.com", "dbcae15342");
            }
        });
        
        MimeMessage message = new MimeMessage(session);
        String emailCliente = client.getEmail();
        String htmlMessage = "<h2>Consulta</h2><p>Olá "+ client.getName()+", sua consulta com "+ professional.getName()+" foi marcada para o dia "+appointment.getDataConsulta()+" às "+appointment.getHoraConsulta()+"h. O link da vídeo conferência é: " + request.getParameter("videoConf")+"</p>";
        message.setContent( htmlMessage, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress("consultaprofissionais1@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailCliente));
        message.setSubject("Consulta Marcada");

        Transport.send(message);

    }

    private void sendEmailProf(HttpServletRequest request, HttpServletResponse response, Appointment appointment) throws ServletException, IOException, MessagingException {
    	ProfessionalDAO daoProfessional = new ProfessionalDAO();
        Professional professional = daoProfessional.get(appointment.getCpfProfissional());
        ClientDAO daoClient = new ClientDAO();
        Client client = daoClient.get(appointment.getCpfCliente());

        Properties props = new Properties();
        
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
        

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("consultaprofissionais1@gmail.com", "dbcae15342");
            }
        });

        MimeMessage message = new MimeMessage(session);
        String emailProfessional = professional.getEmail();
        String htmlMessage = "<h2>Consulta</h2><p>Olá "+professional.getName()+", uma consulta com o paciente "+client.getName()+ " foi marcada para o dia "+appointment.getDataConsulta()+" às "+appointment.getHoraConsulta()+"h. O link da vídeo conferência é + "+request.getParameter("videoConf")+"</p>";
        message.setContent(htmlMessage, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress("consultaprofissionais1@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailProfessional));
        message.setSubject("Consulta Marcada");

        Transport.send(message);

    }
   
}


