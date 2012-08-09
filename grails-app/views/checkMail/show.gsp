
<%@ page import="com.othelle.cig.email.CheckMail" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'checkMail.label', default: 'CheckMail')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-checkMail" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-checkMail" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list checkMail">
			
				<g:if test="${checkMailInstance?.uid}">
				<li class="fieldcontain">
					<span id="uid-label" class="property-label"><g:message code="checkMail.uid.label" default="Uid" /></span>
					
						<span class="property-value" aria-labelledby="uid-label"><g:fieldValue bean="${checkMailInstance}" field="uid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${checkMailInstance?.from}">
				<li class="fieldcontain">
					<span id="from-label" class="property-label"><g:message code="checkMail.from.label" default="From" /></span>
					
						<span class="property-value" aria-labelledby="from-label"><g:fieldValue bean="${checkMailInstance}" field="from"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${checkMailInstance?.subject}">
				<li class="fieldcontain">
					<span id="subject-label" class="property-label"><g:message code="checkMail.subject.label" default="Subject" /></span>
					
						<span class="property-value" aria-labelledby="subject-label"><g:fieldValue bean="${checkMailInstance}" field="subject"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${checkMailInstance?.body}">
				<li class="fieldcontain">
					<span id="body-label" class="property-label"><g:message code="checkMail.body.label" default="Body" /></span>
					
						<span class="property-value" aria-labelledby="body-label"><g:fieldValue bean="${checkMailInstance}" field="body"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${checkMailInstance?.dateSend}">
				<li class="fieldcontain">
					<span id="dateSend-label" class="property-label"><g:message code="checkMail.dateSend.label" default="Date Send" /></span>
					
						<span class="property-value" aria-labelledby="dateSend-label"><g:formatDate date="${checkMailInstance?.dateSend}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${checkMailInstance?.flagNew}">
				<li class="fieldcontain">
					<span id="flagNew-label" class="property-label"><g:message code="checkMail.flagNew.label" default="Flag New" /></span>
					
						<span class="property-value" aria-labelledby="flagNew-label"><g:formatBoolean boolean="${checkMailInstance?.flagNew}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${checkMailInstance?.collection}">
				<li class="fieldcontain">
					<span id="collection-label" class="property-label"><g:message code="checkMail.collection.label" default="Collection" /></span>
					
						<span class="property-value" aria-labelledby="collection-label"><g:link controller="collection" action="show" id="${checkMailInstance?.collection?.id}">${checkMailInstance?.collection?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${checkMailInstance?.id}" />
					<g:link class="edit" action="edit" id="${checkMailInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
