<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="client_managing"/>
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
					<fmt:message key="client_managing"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="/<%=contextPath%>/clientes/cadastro">
						<fmt:message key="client_new"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%= contextPath%>/clientes/atualizacao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="client_edition"/>
						</legend>
						<fmt:message key="cpf.readonly"/></br>
						<input type="text" name="cpf" value="${cliente.cpf}" readonly/> <br/>
						<fmt:message key="name"/> </br>
						<input type="text" name="name" value="${cliente.name}"/> <br/>
						<fmt:message key="email"/> </br>
						<input type="email" name="email" value="${cliente.email}"/> <br/>
						<fmt:message key="password"/> </br>
						<input type="password" name="password"/> <br/>
						<fmt:message key="gender"/> </br>
						<input type="text" name="gender" value="${cliente.gender}"/> <br/>
						<fmt:message key="phone"/> </br>
						<input type="text" name="telephone" value="${cliente.telephone}"/> <br/>
						<fmt:message key="birthdate"/> </br>
						<input type="text" name="birthDate" value="${cliente.birthDate}"/> <br/>
						<fmt:message key="role"/> </br>
							<select name="role" id="role">
								<option value="CLIENT">CLIENT</option>
								<option value="ADMIN">ADMIN</option>
							</select> <br/>
						<input type="submit" name="enviar" value="<fmt:message key="update"/>" />
					</fieldset>
				</form>
			</div>
		</body>
	</fmt:bundle>
</html>