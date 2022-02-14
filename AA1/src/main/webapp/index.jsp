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
	    </head>
	    <body>
			<div class="menu">
				<c:if test="${sessionScope.clienteLogado == null}">
					<fmt:message key="index_hello"/>
				</c:if>
				<c:if test="${sessionScope.clienteLogado != null }">
					<fmt:message key="index_hello_logged"/> ${sessionScope.clienteLogado.name}.
				</c:if>
				<br>
				<c:if test="${sessionScope.clienteLogado != null && sessionScope.clienteLogado.role == 'ADMIN'}">
					<a href="clientes">
						<fmt:message key="client_list"/> 
					</a>
					<br>
				</c:if>
				
				<c:choose>
                                        <c:when test="${sessionScope.clienteLogado == null }">
                                                <a href="login.jsp">
                                                        Login
                                                </a>
                                                <br>
                                                <a href="clientes/cadastro">
                                                        <fmt:message key="client_registration"/> 
                                                </a>
                                                <br>
                                                <a href="profissionais/cadastro">
                                                        <fmt:message key="professional_registration"/> 
                                                </a>
                                                <br>
                                        </c:when>
                                        <c:otherwise>
                                                <a href="${pageContext.request.contextPath}/logout.jsp">
                                                        <fmt:message key="log_out"/> 
                                                </a>
                                                <br>
                                        </c:otherwise>
				</c:choose>
			</div>
	    </body>
    </fmt:bundle>
</html>