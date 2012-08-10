
<%@ page import="com.othelle.cig.email.LocalMail" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'localMail.label', default: 'LocalMail')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-localMail" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-localMail" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list localMail">
			
				<g:if test="${localMailInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="localMail.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${localMailInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${localMailInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="localMail.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${localMailInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${localMailInstance?.flagSend}">
				<li class="fieldcontain">
					<span id="flagSend-label" class="property-label"><g:message code="localMail.flagSend.label" default="Flag Send" /></span>
					
						<span class="property-value" aria-labelledby="flagSend-label"><g:formatBoolean boolean="${localMailInstance?.flagSend}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${localMailInstance?.contact}">
				<li class="fieldcontain">
					<span id="contact-label" class="property-label"><g:message code="localMail.contact.label" default="Contact" /></span>
					
						<span class="property-value" aria-labelledby="contact-label"><g:link controller="contact" action="show" id="${localMailInstance?.contact?.id}">${localMailInstance?.contact?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${localMailInstance?.dateSent}">
				<li class="fieldcontain">
					<span id="dateSent-label" class="property-label"><g:message code="localMail.dateSent.label" default="Date Sent" /></span>
					
						<span class="property-value" aria-labelledby="dateSent-label"><g:formatDate date="${localMailInstance?.dateSent}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${localMailInstance?.subject}">
				<li class="fieldcontain">
					<span id="subject-label" class="property-label"><g:message code="localMail.subject.label" default="Subject" /></span>
					
						<span class="property-value" aria-labelledby="subject-label"><g:fieldValue bean="${localMailInstance}" field="subject"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${localMailInstance?.id}" />
					<g:link class="edit" action="edit" id="${localMailInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
