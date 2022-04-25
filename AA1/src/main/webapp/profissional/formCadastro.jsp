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
					<fmt:message key="professional_registration"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%=contextPath%>/profissionais/insercao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="professional_registration"/>
						</legend>
						<fmt:message key="cpf"/> 
						<input type="text" name="cpf"/> <br/>
						<fmt:message key="name"/> 
						<input type="text" name="name"/> <br/>
						<fmt:message key="email"/> 
						<input type="email" name="email"/> <br/>
						<fmt:message key="password"/> 
						<input type="password" name="password"/> <br/>
						<fmt:message key="expertise"/> 
						<input type="text" name="expertise"/> <br/>
						<fmt:message key="knowledge_area"/> 
						<select name="knowledge_area" id="knowledge_area">
							<option value="Ciências Exatas e da Terra">Ciências Exatas e da Terra</option>
							<option value="Ciências Biológicas">Ciências Biológicas</option>
							<option value="Engenharias">Engenharias</option>
							<option value="Ciências da Saúde">Ciências da Saúde</option>
							<option value="Ciências Agrárias">Ciências Agrárias</option>
							<option value="Linguística, Letras e Artes">Linguística, Letras e Artes</option>
							<option value="Ciências Sociais Aplicadas">Ciências Sociais Aplicadas</option>
							<option value="Ciências Humanas">Ciências Humanas</option>
						</select> <br/>
						<c:if test="${sessionScope.professionalLogged != null  && sessionScope.professionalLogged.role == 'ADMIN'}">
							<fmt:message key="role"/> </br>
							<select name="role" id="role">
								<option value="PROF">PROF</option>
								<option value="ADMIN">ADMIN</option>
							</select> <br/>
						</c:if>
						<label><fmt:message key="qualification" /> </label>
						<input type="text" name="qualification" /> <br>
						
			
						<input type="submit" name="enviar" value="<fmt:message key="register"/>" />
					</fieldset>
				</form>
			</div>
			
		</body>
	</fmt:bundle>
</html>