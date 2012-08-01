<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 25.07.12
  Time: 7:53
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
%{--
<g:hasErrors bean="${pc}">
    <div class="errors">
        <g:renderErrors bean="${pc}"/>
    </div>
</g:hasErrors>--}%
</br>
<div class="nav" role="navigation">
    <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
</div>

<div id="list-contact" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label.import"/></h1>

    <h3><g:message code="default.list.label.import.step2"/></h3>
</br>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form controller="exchangeContacts" method="post" action="import" enctype="multipart/form-data">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="firstName"
                                  title="${message(code: 'contact.firstName.label', default: 'First Name')}"/>
                <g:sortableColumn property="lastName"
                                  title="${message(code: 'contact.lastName.label', default: 'Last Name')}"/>
                <g:sortableColumn property="email" title="${message(code: 'contact.email.label', default: 'Email')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${previewDetailsList}" var="previewDetails">
                <tr class="even">
                    <td>${fieldValue(bean: previewDetails, field: "firstName")}</td>
                    <td>${fieldValue(bean: previewDetails, field: "lastName")}</td>
                    <td>${fieldValue(bean: previewDetails, field: "email")}</td>

                </tr>

            </g:each>
            </tbody>

        </table>
        </br>
        <g:submitButton name="back" value='Back'/>
        <g:submitButton name="next" value='Next'/>
        <g:submitButton name="cancel" value='Cancel'/>
    </g:form>
</div>
</body>
</html>