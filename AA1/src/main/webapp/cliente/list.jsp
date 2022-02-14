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
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
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
			<div align="center">
				<table border="1">
					<caption>
						<fmt:message key="client_list"/>
					</caption>
					<tr>
						<th><fmt:message key="cpf"/></th>
						<th><fmt:message key="name"/></th>
						<th><fmt:message key="email"/></th>
						<th><fmt:message key="password"/></th>
						<th><fmt:message key="gender"/></th>
						<th><fmt:message key="phone"/></th>
						<th><fmt:message key="birthdate"/></th>
						<th><fmt:message key="role"/></th>
						<th><fmt:message key="actions"/></th>
					</tr>
					<c:forEach var="cliente" items="${requestScope.listaClientes}">
						<tr>
							<td>${cliente.cpf}</td>
							<td>${cliente.name}</td>
							<td>${cliente.email}</td>
							<td>${cliente.password}</td>
							<td>${cliente.gender}</td>
							
							<td>${cliente.telephone}</td>
							<td>${cliente.birthDate}</td>
							
							<td>${cliente.role}</td>
							<td>
								<a href="/<%= contextPath%>/clientes/edicao?cpf=${cliente.cpf}">
									<fmt:message key="edition"/>
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<fmt:message var="confirmation_text" key="confirmation_text"/>
								<a
									href="/<%= contextPath%>/clientes/remocao?cpf=${cliente.cpf}"
									onclick="return confirm(${confirmation_text});">
									<fmt:message key="removal"/>
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>