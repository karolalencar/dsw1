# Atividade AA-1: Sistema para agendamento de consultas *online* com profissionais
Corresponde ao requisito C1.

## Arquitetura: Modelo-Visão-Controlador

## Tecnologias Utilizadas
* Servlet, JSP, JSTL e JDBC (Lado Servidor)
* Javascript e CSS (Lado Cliente)

## Criação do Banco de Dados
```
mysql -uroot -p
source db/MySQL/create.sql;
```

## Compilação e Deployment
```
mvn compile
mvn tomcat7:deploy
```
