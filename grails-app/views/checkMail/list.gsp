
<%@ page import="com.othelle.cig.email.CheckMail" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'checkMail.label', default: 'CheckMail')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-checkMail" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-checkMail" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="uid" title="${message(code: 'checkMail.uid.label', default: 'Uid')}" />
					
						<g:sortableColumn property="from" title="${message(code: 'checkMail.from.label', default: 'From')}" />
					
						<g:sortableColumn property="subject" title="${message(code: 'checkMail.subject.label', default: 'Subject')}" />
					
						<g:sortableColumn property="body" title="${message(code: 'checkMail.body.label', default: 'Body')}" />
					
						<g:sortableColumn property="dateSend" title="${message(code: 'checkMail.dateSend.label', default: 'Date Send')}" />
					
						<g:sortableColumn property="flagNew" title="${message(code: 'checkMail.flagNew.label', default: 'Flag New')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${checkMailInstanceList}" status="i" var="checkMailInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${checkMailInstance.id}">${fieldValue(bean: checkMailInstance, field: "uid")}</g:link></td>
					
						<td>${fieldValue(bean: checkMailInstance, field: "from")}</td>
					
						<td>${fieldValue(bean: checkMailInstance, field: "subject")}</td>
					
						<td>${fieldValue(bean: checkMailInstance, field: "body")}</td>
					
						<td><g:formatDate date="${checkMailInstance.dateSend}" /></td>
					
						<td><g:formatBoolean boolean="${checkMailInstance.flagNew}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${checkMailInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
