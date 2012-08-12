<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 24.07.12
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="default.page.title.label" default="Exchange data"/></title>
</head>

<body>

<g:hasErrors bean="${fc}">
    <div class="errors">

        <g:renderErrors bean="${fc}"/>
    </div>
</g:hasErrors>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>

    </ul>
</div>

<div id="list-contact" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label.export"/></h1>

    <h3><g:message code="default.list.label.export.step1"/></h3>

    <g:form controller="exchangeContacts" method="post" action="export" enctype="multipart/form-data">

        <g:submitButton name="next" value='Next'/>

    </g:form>
</div>
</body>
</html>