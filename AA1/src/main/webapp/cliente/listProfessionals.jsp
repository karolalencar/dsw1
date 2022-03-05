<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="professional_managing"/>
			</title>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<div align="center">
				<h1>
					<fmt:message key="professional_managing"/>
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
						<fmt:message key="professional_list"/>
					</caption>
					<tr>
						<th><fmt:message key="cpf"/></th>
						<th><fmt:message key="name"/></th>
						<th><fmt:message key="email"/></th>
						<th><fmt:message key="qualification"/></th>
						<th><fmt:message key="knowledge_area"/></th>
						<th><fmt:message key="expertise"/></th>
						<th><fmt:message key="schedulling" /></th>
					</tr>
					<c:forEach var="profissional" items="${requestScope.professionalsList}">
						<tr>
							<td>${profissional.cpf}</td>
							<td>${profissional.name}</td>
							<td>${profissional.email}</td>							
							<td>${profissional.qualifications}</td>
							<td>${profissional.knowledgeArea}</td>
                            <td>${profissional.expertise}</td>
                            <td>
                            	<a href="${pageContext.request.contextPath}/cliente/formConsulta.jsp?cpfProf=${profissional.cpf}"><fmt:message key="btn_schedulling" /> </a>
                            </td>                           
						</tr>
					</c:forEach>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>