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
					<fmt:message key="professional_managing"/>
				</h1>
				<h2>
					<a href="/<%=contextPath%>">
						<fmt:message key="main_menu"/>
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="/<%=contextPath%>/profissionais/cadastro">
						<fmt:message key="professional_new"/>
					</a>
				</h2>
			</div>
			<div>
				<form action="/<%= contextPath%>/profissionais/atualizacao" method="post">
					<fieldset>
						<legend>
							<fmt:message key="professional_edition"/>
						</legend>
						<fmt:message key="cpf.readonly"/></br>
						<input type="text" name="cpf" value="${profissional.cpf}" readonly/> <br/>

						<fmt:message key="name"/> </br>
						<input type="text" name="name" value="${profissional.name}"/> <br/>

						<fmt:message key="email"/> </br>
						<input type="email" name="email" value="${profissional.email}"/> <br/>

						<fmt:message key="password"/> </br>
						<input type="password" name="password"/> <br/>
                
						<fmt:message key="knowledge_area"/> </br>
                        <select name="knowledge_area" id="knowledge_area" >
							<option value="Ciências Exatas e da Terra">Ciências Exatas e da Terra</option>
							<option value="Ciências Biológicas">Ciências Biológicas</option>
							<option value="Engenharias">Engenharias</option>
							<option value="Ciências da Saúde">Ciências da Saúde</option>
							<option value="Ciências Agrárias">Ciências Agrárias</option>
							<option value="Linguística, Letras e Artes">Linguística, Letras e Artes</option>
							<option value="Ciências Sociais Aplicadas">Ciências Sociais Aplicadas</option>
							<option value="Ciências Humanas">Ciências Humanas</option>
						</select> <br/>
						<fmt:message key="expertise"/> </br>
						<input type="text" name="expertise" value="${profissional.expertise}"/> <br/>

						<fmt:message key="qualification"/> </br>
						<input type="text" name="qualifications" value="${profissional.qualifications}"/> <br/>

						<fmt:message key="role"/> </br>
						<input type="text" name="role" value="${profissional.role}" readonly/> <br/>

						<input type="submit" name="enviar" value="<fmt:message key="update"/>" />
					</fieldset>
				</form>
			</div>
		</body>
	</fmt:bundle>
</html>