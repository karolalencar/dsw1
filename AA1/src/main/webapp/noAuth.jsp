<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autorização de Usuário</title>
    </head>
    <body>
              
        <%
                String contextPath = request.getContextPath().replace("/", "");
	%>
        <div align="center">
        
                <h2>
                        <a href="/<%=contextPath%>">
                                Página Inicial
                        </a>
                        
                </h2>
        </div>
        <h1>Autorização de Usuário</h1>
        
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
    </body>
