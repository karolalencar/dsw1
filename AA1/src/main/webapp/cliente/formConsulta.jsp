<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE>
<html>
	<fmt:bundle basename="msgs">
		<head>
			<title><fmt:message key="schedule_title" /> </title>
			<link href="${pageContext.request.contextPath}/styles.css" rel="stylesheet" type="text/css"/>
		</head>
		
		<body>
			<div class="cadastro-consulta-container">
				<div class="form-container consulta">
					<h1><fmt:message key="schedule_title" /> </h1>
 					<c:if test="${professional != null}">
   						<input class="input" type="hidden" name="cpfCliente" value="${sessionScope.clienteLogado.cpf}" />
   					</c:if>
   					<div>
   						<label for="data"><fmt:message key="data_chosen"/></label><br>
   						<input class="input" type="date" id="data" name="data" min="2022-03-06" placeholder="<fmt:message key="birthdateph"/>" 
   								required value="" />
   					</div>
   					<div>
   						<label for="horario"><fmt:message key="schedule_time"/></label><br>
						<select class="input" name="horario">
							<option value="09:00:00" selected>09:00</option>
							<option value="10:00:00">10:00</option>
							<option value="11:00:00">11:00</option>
							<option value="12:00:00">12:00</option>
							<option value="13:00:00">13:00</option>
							<option value="14:00:00">14:00</option>
							<option value="15:00:00">15:00</option>
							<option value="16:00:00">16:00</option>
							<option value="17:00:00">17:00</option>
							<option value="18:00:00">18:00</option>
						</select>
   					</div>
   					<div>
   						<label for="data"><fmt:message key="link_call"/></label><br>
   						<input class="input" type="text" id="videoconferencia" name="videoConf" required value="" />
   					</div>
   						<input style="margin-top: 10px;" class="btn" name="appointmentOK" type="submit" value="<fmt:message key="confirm" />" />
				</div>
			</div>
		</body>
	</fmt:bundle>
</html>