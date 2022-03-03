<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <fmt:bundle basename="msgs">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title_login"/>"</title>

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
        
        <div class="box cliente">     
            <h2>Login</h2>
            <form method="post" action="login">
                
                <div class="login-form">
                    <label for="cliente_login">Email</label>
                    <input type="text" name="cliente_login" value="${param.login}" placeholder="Email"/>
                    
                    <label for="cliente_password">Password</label>
                    <input type="password" name="cliente_senha" class="password" placeholder=<fmt:message key="str_pw"/>>
                </div>
                
                <input type="submit" class="submit" name="clienteOK" value=<fmt:message key="str_login"/>>
            </form>
        </div>

        

    </body>
    </fmt:bundle>
</html>
