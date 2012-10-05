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

<div class="nav" role="navigation">
    <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
</div>

<h1><g:message code="default.result.label"/></h1>
<div id="fon">
<div>
    <p>Searched ${com.othelle.cig.email.CheckMail.count()} records
    for items matching <em>${term}</em>.
    Found <strong>${checkMails.size()}</strong> hits.
    </p>
</div>

<div id="list-checkMail" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="uid" title="${message(code: 'checkMail.uid.label', default: 'Uid')}"/>

            <g:sortableColumn property="emailFrom"
                              title="${message(code: 'checkMail.emailFrom.label', default: 'Email From')}"/>

            <g:sortableColumn property="subject"
                              title="${message(code: 'checkMail.subject.label', default: 'Subject')}"/>

            <g:sortableColumn property="body" title="${message(code: 'checkMail.body.label', default: 'Body')}"/>

            <g:sortableColumn property="dateSend"
                              title="${message(code: 'checkMail.dateSend.label', default: 'Date Send')}"/>

            <g:sortableColumn property="flagNew"
                              title="${message(code: 'checkMail.flagNew.label', default: 'Flag New')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${checkMails}" status="i" var="checkMail">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link controller="checkMail" action="show"
                            id="${checkMail.id}">${fieldValue(bean: checkMail, field: "subject")}</g:link></td>

                <td>${fieldValue(bean: checkMail, field: "emailFrom")}</td>

                <td>${fieldValue(bean: checkMail, field: "subject")}</td>

                <td>${fieldValue(bean: checkMail, field: "body")}</td>

                <td><g:formatDate date="${checkMail.dateSend}"/></td>

                <td><g:formatBoolean boolean="${checkMail.flagNew}"/></td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <g:link action='search'><g:message code="default.SearchAgain.label" default="Search Again"/></g:link>
</div>
</div>

</body>
</html>
