<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<fmt:bundle basename="msgs">
	    <head>
	        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	        <title>
	        	<fmt:message key="title"/>
	        </title>
	        <link href="styles.css" rel="stylesheet" type="text/css"/>
	    </head>
	    <body>
			<div class="menu">
				<c:if test="${sessionScope.clienteLogado != null }">
					<fmt:message key="index_hello_logged"/> ${sessionScope.clienteLogado.name}.
				</c:if>
				<c:if test="${sessionScope.professionalLogged != null }">
					<fmt:message key="index_hello_logged"/> ${sessionScope.professionalLogged.name}.
				</c:if>
				<c:if test="${sessionScope.professionalLogged.role == 'ADMIN' || sessionScope.clienteLogado.role == 'ADMIN'}">
					<a href="clientes">
						<fmt:message key="client_list"/> 
					</a>
					<br>
				</c:if>
				<br>
				
				<c:choose>
                    <c:when test="${sessionScope.clienteLogado == null && sessionScope.professionalLogged == null}">
	                    <a class="btn-login" href="login.jsp">Login</a><br>
						<a class="btn-login" href="clientes/cadastro">
                    		<fmt:message key="client_registration"/> 
                    	</a>
						<a class="btn-login" href="profissionais/cadastro">
                    		<fmt:message key="professional_registration"/> 
                    	</a>
	                    <a class="btn-login" href="profissionais/lista">
	                    	<fmt:message key="professional_list" />
	                    </a>
                  	</c:when>
					<c:when test="${sessionScope.clienteLogado != null && sessionScope.professionalLogged == null}">
                    	<div>
	                    	<br>
	                    	<a href="profissionais/lista">
	                    		<fmt:message key="professional_list" />
	                    	</a>
							<br>
							<a href="appointment/lista">
	                    		<fmt:message key="appointment_list" />
	                    	</a>
							<br>
							<a href="${pageContext.request.contextPath}/logout.jsp">
	                        	<fmt:message key="log_out"/> 
	                        </a>
                    	</div>
                  	</c:when>
					<c:when test="${sessionScope.clienteLogado == null && sessionScope.professionalLogged != null}">
                    	<div>
                    	<br>
						<a href="appointment/lista">
                    		<fmt:message key="appointment_list" />
                    	</a>
						<br>
						<a href="${pageContext.request.contextPath}/logout.jsp">
                        	<fmt:message key="log_out"/> 
                        </a>
                    	</div>
                  	</c:when>
				</c:choose>
			</div>
			<p><fmt:message key="schedule_manage" /><br><fmt:message key="appointments" /> </p>
			<img src="./assets/img.svg" />			
	    </body>
    </fmt:bundle>
</html>