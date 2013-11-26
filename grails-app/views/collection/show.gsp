<%@ page import="com.othelle.cig.email.Collection" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'collection.label', default: 'Collection')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-collection" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="collection.list.label"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="collection.new.label"/></g:link></li>
        <li><g:link class="create" controller="contact" action="create"><g:message code="contact.new.label" /></g:link></li>
    </ul>
</div>

<div id="show-collection" class="content scaffold-show" role="main">
    <h1><g:message code="collection.show.label"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list collection">

        <g:if test="${collectionInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="collection.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${collectionInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${collectionInstance?.email}">
            <li class="fieldcontain">
                <span id="email-label" class="property-label"><g:message code="collection.email.label"
                                                                         default="Email"/></span>

                <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${collectionInstance}"
                                                                                         field="email"/></span>

            </li>
        </g:if>

        <g:if test="${collectionInstance?.password}">
            <li class="fieldcontain">
                <span id="password-label" class="property-label"><g:message code="collection.password.label"
                                                                            default="Password"/></span>

                <span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${collectionInstance}"
                                                                                            field="password"/></span>

            </li>
        </g:if>

        <g:if test="${collectionInstance?.contacts}">
            <li class="fieldcontain">
                <span id="contacts-label" class="property-label"><g:message code="collection.contacts.label"
                                                                            default="Contacts"/></span>

                <g:each in="${collectionInstance.contacts}" var="c">
                    <span class="property-value" aria-labelledby="contacts-label"><g:link controller="contact"
                                                                                          action="show"
                                                                                          id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${collectionInstance?.checkMail}">
            <li class="fieldcontain">
                <span id="checkMail-label" class="property-label"><g:message code="checkMail.label"
                                                                             default="Check Mail"/></span>
                <ul>
                    <g:each in="${collectionInstance.checkMail}" var="l">
                        <li><span class="property-value" aria-labelledby="checkMail-label">
                            <g:message code="checkMail.subject.label" default="Тема"/>:&nbsp;
                            <g:link controller="checkMail" action="show" id="${l.id}">${l?.subject}</g:link>
                        </br>
                            <g:message code="checkMail.emailFrom.label"
                                       default="Отправитель"/>:&nbsp;${l?.emailFrom}</br>
                            <g:message code="checkMail.dateSend.label"
                                       default="Дата отправки"/>:&nbsp;${l?.dateSend}</br>
                        </br></span></li>

                    </g:each>
                </ul>
            </li>
        </g:if>

        <g:if test="${collectionInstance?.code}">
            <li class="fieldcontain">
                <span id="code-label" class="property-label"><g:message code="collection.code.label"
                                                                        default="Code"/></span>

                <span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${collectionInstance}"
                                                                                        field="code"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${collectionInstance?.id}"/>
            <g:link class="edit" action="edit" id="${collectionInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
