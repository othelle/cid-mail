<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 25.07.12
  Time: 10:08
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
<div id="list-contact" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label.import"/></h1>

    <h3><g:message code="default.list.label.import.step3"/></h3>
</br>

    <g:form controller="exchangeContacts" method="post" action="import" enctype="multipart/form-data">
        <div class="even">${error}</div>
        <g:submitButton name="ok" value='Ok'/>

    </g:form>
</div>

</body>
</html>