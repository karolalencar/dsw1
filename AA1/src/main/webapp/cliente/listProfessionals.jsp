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
			<script src="js/ajaxExpertise.js"></script> <!-- <- Não está funcionando, não sei pq-->
			<script>
				var xmlHttp;

				function areaSelecionada(str) {
					if (typeof XMLHttpRequest !== "undefined") {
						xmlHttp = new XMLHttpRequest();
					} else if (window.ActiveXObject) {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					}

					if (xmlHttp === null) {
						alert("Browser does not support XMLHTTP Request");
						return;
					}

					var url = "profissionais/buscaPorAreaJS";
					url += "?area=" + str;
					xmlHttp.onreadystatechange = atualizaExpertise;
					xmlHttp.open("GET", url, true);
					xmlHttp.send(null);
				}

				function atualizaExpertise() {
					if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {
						document.getElementById("expertise").innerHTML = xmlHttp.responseText;
					}
				}
			</script>
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
				<form name='form' action="/<%= contextPath%>/profissionais/profissionaisArea" method="get">
					<table>
						<tr>
							<td>Área de Conhecimento</td>
							<td>
								<select id = 'knowledge_area' name='area_conhecimento' onchange='areaSelecionada(this.value)'>
									<option value='--'>--</option>
									<option value="Ciências Exatas e da Terra">Ciências Exatas e da Terra</option>
									<option value="Ciências Biológicas">Ciências Biológicas</option>
									<option value="Engenharias">Engenharias</option>
									<option value="Ciências da Saúde">Ciências da Saúde</option>
									<option value="Ciências Agrárias">Ciências Agrárias</option>
									<option value="Linguística, Letras e Artes">Linguística, Letras e Artes</option>
									<option value="Ciências Sociais Aplicadas">Ciências Sociais Aplicadas</option>
									<option value="Ciências Humanas">Ciências Humanas</option>
								</select>   
							</td>
						</tr>
						<tr id='expertise'>    
							<td>Expertise</td>
							<td>
								<select id='expertise_id' name='expertise' >
								</select>
							</td>   
						</tr>
					</table>
					<input type="submit" value="<fmt:message key="filter"/>">
				</form>
				<form name='form-button-clear' action="/<%= contextPath%>/profissionais/lista" >
					<input type="submit" value="<fmt:message key="clean_filter"/>">
				</form>
			</div>
			<div align="center">
				<table border="1">
					<caption>
						<fmt:message key="professional_list"/>
					</caption>
					<tr>
						<th><fmt:message key="name"/></th>
						<th><fmt:message key="email"/></th>
						<th><fmt:message key="qualification"/></th>
						<th><fmt:message key="knowledge_area"/></th>
						<th><fmt:message key="expertise"/></th>
						<th><fmt:message key="schedulling" /></th>
					</tr>
					<c:forEach var="profissional" items="${requestScope.professionalsList}">
						<tr>
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