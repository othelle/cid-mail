<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 06.08.12
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><head>
    <meta name="layout" content="main">
    <title><g:message code="default.result.label"/></title>
</head>

<body>
<br/>

<div class="nav" role="navigation">
    <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
</div>
</br>


<h1><g:message code="default.result.label"/></h1>

<div>
    <p>Searched ${com.othelle.cig.email.CheckMail.count()} records
    for items matching <em>${term}</em>.
    Found <strong>${checkMails.size()}</strong> hits.
    </p>
    <br/>
    <ul>
        <g:each var="chekMail" in="${checkMails}">
            <li>${chekMail.subject} : ${chekMail.body} : ${chekMail.dateSend} : ${chekMail.collection}</li>
        </g:each>
    </ul>

    <g:link action='search'><g:message code="default.SearchAgain.label" default="Search Again"/></g:link>
</div>
</body>
</html>