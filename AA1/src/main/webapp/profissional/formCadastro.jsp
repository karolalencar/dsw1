<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
	<fmt:bundle basename="msgs">
		<head>
			<title>
				<fmt:message key="professional_registration"/>
			</title>
			<style>
				
			</style>
		</head>
		<body>
			<%
				String contextPath = request.getContextPath().replace("/", "");
			%>
			<c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        	</c:if>
			<div align="center">
				<h1>
					<fmt:message key="professional_registration"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%= contextPath%>/profissionais/insercao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="professional_registration"/>
						</legend>
						<fmt:message key="cpf"/> </br>
						<input type="text" name="cpf"/> <br/>
						<fmt:message key="name"/> </br>
						<input type="text" name="name"/> <br/>
						<fmt:message key="email"/> </br>
						<input type="email" name="email"/> <br/>
						<fmt:message key="password"/> </br>
						<input type="password" name="password"/> <br/>
						<fmt:message key="expertise"/> </br>
						<input type="text" name="expertise"/> <br/>
						<fmt:message key="knowledge_area"/> </br>
						<input type="text" name="knowledge_area"/> <br/>
						<c:if test="${sessionScope.professionalLogged != null  && sessionScope.professionalLogged.role == 'ADMIN'}">
							<fmt:message key="role"/> </br>
							<select name="role" id="role">
								<option value="USER">PROF</option>
								<option value="ADMIN">ADMIN</option>
							</select> <br/>
						</c:if>
						<fmt:message key="qualification"/> </br>
						<input type="text" name="qualifications"/> <br/>
						<input type="submit" name="enviar" value="<fmt:message key="register"/>" />
					</fieldset>
				</form>
			</div>
			<div>
			<form action="pdf/insercao" enctype="multipart/form-data" method="post">
						<legend>
							Registrando PDF no banco de dados
						</legend>
						<br/>
						Digite o nome do PDF:
						<input type="text" name="name"/> <br/><br/>
						Coloque seu PDF:
						<input name="upload" type="file" accept=".pdf"> <br/>
						<input type="submit" name="enviar" value="Enviar" />
					
				</form>	
		</div>
		</body>
	</fmt:bundle>
</html>