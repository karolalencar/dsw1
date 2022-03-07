<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <fmt:bundle basename="msgs">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title_login"/></title>
		<link href="styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    	<div class="login-container">
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
        
	        <div class="form-container">     
	            <h2> <fmt:message key="sign_in" /> </h2>
	            <form class="login-form" method="post" action="login">
	                <div class="login-campos">
	                    <label for="cliente_login">Email</label>
	                    <input class="input" type="text" name="cliente_login" value="${param.login}" placeholder="Email"/>
	                    <br>
	                    <label for="cliente_password">Password</label>
	                    <input class="input" type="password" name="cliente_senha" class="password" placeholder=<fmt:message key="str_pw"/>>
	                </div>
	                
	                <input class="btn" type="submit" class="submit" name="clienteOK" value=<fmt:message key="str_login"/> />
	            </form>
	        </div>
        </div>
    </body>
    </fmt:bundle>
</html>
