<%@ page import="com.othelle.cig.email.Contact" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'contact.label', default: 'Contact')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-contact" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="contact.new.label"/></g:link></li>
    </ul>
</div>

<div id="list-contact" class="content scaffold-list" role="main">
    <h1><g:message code="contact.list.label"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="firstName"
                              title="${message(code: 'contact.firstName.label', default: 'First Name')}"/>

            <g:sortableColumn property="lastName"
                              title="${message(code: 'contact.lastName.label', default: 'Last Name')}"/>

            <g:sortableColumn property="email" title="${message(code: 'contact.email.label', default: 'Email')}"/>
            <g:sortableColumn property="email"
                              title="${message(code: 'contact.collections.label', default: 'Collection')}"/>
            <g:sortableColumn property="organization"
                              title="Организация"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${contactInstanceList}" status="i" var="contactInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:if test="${contactInstance?.firstName}">
                    <g:link action="show"
                            id="${contactInstance.id}">${fieldValue(bean: contactInstance, field: "firstName")}</g:link></g:if></td>
                <td><g:if
                        test="${contactInstance?.lastName}">${fieldValue(bean: contactInstance, field: "lastName")}</g:if></td>
                <td><g:if
                        test="${contactInstance?.email}">${fieldValue(bean: contactInstance, field: "email")}</g:if></td>
                <td><g:if test="${contactInstance?.collections}">
                    <g:each in="${contactInstance.collections}" var="c">
                        <g:link controller="collection" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link>
                        <br/>
                    </g:each>
                </g:if>
                </td>
                <td><g:if
                        test="${contactInstance?.organization}">${fieldValue(bean: contactInstance, field: "organization")}</g:if></td>


            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${contactInstanceTotal}"/>
    </div>
</div>
</body>
</html>
