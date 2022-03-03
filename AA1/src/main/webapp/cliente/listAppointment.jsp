<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="appointment_managing"/>
			</title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<div align="center">
				<h1>
					<fmt:message key="appointment_managing"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
				</h2>
			</div>
			<div align="center">
				<table border="1">
					<caption>
						<fmt:message key="appointment_list"/>
					</caption>
					<tr>
						<th><fmt:message key="cpf_client"/></th>
						<th><fmt:message key="cpf_professional"/></th>
						<th><fmt:message key="appointment_date"/></th>
						<th><fmt:message key="appointment_hour"/></th>
					</tr>
					<c:forEach var="consulta" items="${requestScope.listaConsultasCliente}">
						<tr>
							<td>${consulta.cpfCliente}</td>
							<td>${consulta.cpfProfissional}</td>
							<td>${consulta.dataConsulta}</td>
							<td>${consulta.horaConsulta}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>