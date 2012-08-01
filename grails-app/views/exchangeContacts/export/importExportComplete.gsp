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
    <script type="text/javascript">
        function toggle_show(id) {
            document.getElementById(id).style.display = document.getElementById(id).style.display == 'none' ? 'block' : 'none';
        }

    </script>
</head>

<body>

<div id="list-contact" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label.export"/></h1>

    <h3><g:message code="default.list.label.export.step2"/></h3>
    <br/>

    <g:form controller="exchangeContacts" method="post" action="import" enctype="multipart/form-data">
        <g:if test="${error}">
            <div class="message">${error}</div>--}
        </g:if>
        <div><h4>${filePath}</h4></div>
        <div><g:render template="/exchangeContacts/export/fileDetails"
                       model="[fileContents: fileContents, filePath: file]"/>
        </div>

        <g:submitButton name="ok" value='Ok'/>

    </g:form>
</div>
</body>
</html>