<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="client_registration"/>
			</title>
			<link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet" type="text/css"/>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<c:if test="${mensagens.existeErros}">
            <div class="erro" id="erro">
                <ul class="erro-container">
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li class="erro-item"> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        	</c:if>
			<div align="center">
				<h1>
					<fmt:message key="client_registration"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%= contextPath%>/clientes/insercao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="client_registration"/>
						</legend>
						<fmt:message key="cpf"/> </br>
						<input type="text" name="cpf"/> <br/>
						<fmt:message key="name"/> </br>
						<input type="text" name="name"/> <br/>
						<fmt:message key="email"/> </br>
						<input type="email" name="email"/> <br/>
						<fmt:message key="password"/> </br>
						<input type="password" name="password"/> <br/>
						<fmt:message key="gender"/> </br>
						<input type="text" name="gender"/> <br/>
						<fmt:message key="phone"/> </br>
						<input type="text" name="telephone" placeholder="X XXXX-XXXX"/> <br/>
						<c:if test="${sessionScope.clienteLogado != null  && sessionScope.clienteLogado.role == 'ADMIN'}">
							<fmt:message key="role"/> </br>
							<select name="role" id="role">
								<option value="CLIENT">CLIENT</option>
								<option value="ADMIN">ADMIN</option>
							</select> <br/>
						</c:if>
						<fmt:message key="birthdate"/> </br>
						<input type="text" name="birth_date" placeholder="<fmt:message key="birthdateph"/>"/> <br/>
						<input type="submit" name="enviar" value="<fmt:message key="register"/>" />
					</fieldset>
				</form>
			</div>
		</body>
	</fmt:bundle>
</html>