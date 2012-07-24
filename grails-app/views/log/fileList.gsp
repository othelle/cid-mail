<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 23.07.12
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title><g:message code="default.page.title.label" default="File List"/></title>
</head>

<body >
<h2 style="padding: 10px;">
    <g:message code="default.page.title.label" default="Please click on the links below to view detailed reports:"/>
</h2>
<g:if test="${showBackLink}">
    <div class="nav" role="navigation">
        <a class="showReportLink" href="${createLink(action: 'index', params: [filePath: prevLocation])}">
            <g:message code="default.link.back.label" default="Back"/>
        </a>
    </div>
</g:if>
<div id="list-checkMail" class="content scaffold-list" role="main">
    <div id="left-container" style="overflow: auto;">
        <g:render template="/log/fileList" model="[locations: locations]" plugin='fileViewer'/>
    </div>



    <div id="right-container"  style="padding:10px;">
      <div><h4>${filePath}</h4> </div>
        <div>   <g:render template="/log/fileDetails" model="[fileContents: fileContents, filePath: filePath]"
                  plugin='fileViewer'/>
        </div>

    </div>
</div>
</body>
</html>