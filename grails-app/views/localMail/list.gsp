
<%@ page import="com.othelle.cig.email.LocalMail" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'localMail.label', default: 'LocalMail')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-localMail" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-localMail" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'localMail.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'localMail.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="flagSend" title="${message(code: 'localMail.flagSend.label', default: 'Flag Send')}" />
					
						<th><g:message code="localMail.contact.label" default="Contact" /></th>
					
						<g:sortableColumn property="dateSent" title="${message(code: 'localMail.dateSent.label', default: 'Date Sent')}" />
					
						<g:sortableColumn property="subject" title="${message(code: 'localMail.subject.label', default: 'Subject')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${localMailInstanceList}" status="i" var="localMailInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${localMailInstance.id}">${fieldValue(bean: localMailInstance, field: "description")}</g:link></td>
					
						<td><g:formatDate date="${localMailInstance.dateCreated}" /></td>
					
						<td><g:formatBoolean boolean="${localMailInstance.flagSend}" /></td>
					
						<td>${fieldValue(bean: localMailInstance, field: "contact")}</td>
					
						<td><g:formatDate date="${localMailInstance.dateSent}" /></td>
					
						<td>${fieldValue(bean: localMailInstance, field: "subject")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${localMailInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
