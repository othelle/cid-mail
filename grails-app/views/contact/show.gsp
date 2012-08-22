
<%@ page import="com.othelle.cig.email.Contact" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contact.label', default: 'Contact')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-contact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="contact.default.list.label" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="contact.new.label"/></g:link></li>
			</ul>
		</div>
		<div id="show-contact" class="content scaffold-show" role="main">
			<h1><g:message code="contact.show.label" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list contact">

				<g:if test="${contactInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="contact.firstName.label" default="First Name" /></span>

						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${contactInstance}" field="firstName"/></span>

				</li>
				</g:if>

				<g:if test="${contactInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="contact.lastName.label" default="Last Name" /></span>

						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${contactInstance}" field="lastName"/></span>

				</li>
				</g:if>

				<g:if test="${contactInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="contact.email.label" default="Email" /></span>

						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${contactInstance}" field="email"/></span>

				</li>
				</g:if>

                <g:if test="${contactInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label">Организация</span>

						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${contactInstance}" field="organization"/></span>

				</li>
				</g:if>

				<g:if test="${contactInstance?.collections}">
				<li class="fieldcontain">
					<span id="collections-label" class="property-label"><g:message code="contact.collections.label" default="Collections" /></span>

						<g:each in="${contactInstance.collections}" var="c">
						<span class="property-value" aria-labelledby="collections-label"><g:link controller="collection" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${contactInstance?.localMail}">
				<li class="fieldcontain">
					<span id="localMail-label" class="property-label"><g:message code="contact.localMail.label" default="Local Mail" /></span>

						<g:each in="${contactInstance.localMail}" var="l">
						<span class="property-value" aria-labelledby="localMail-label"><g:link controller="localMail" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${contactInstance?.id}" />
					<g:link class="edit" action="edit" id="${contactInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
