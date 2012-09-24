
<%@ page import="com.othelle.cig.email.Attachment" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'attachment.label', default: 'Attachment')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-attachment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-attachment" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						%{--<g:sortableColumn property="fileByte" title="${message(code: 'attachment.fileByte.label', default: 'File Byte')}" />--}%
					
						<g:sortableColumn property="name" title="${message(code: 'attachment.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${attachmentInstanceList}" status="i" var="attachmentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						%{--<td><g:link action="show" id="${attachmentInstance.id}">${fieldValue(bean: attachmentInstance, field: "fileByte")}</g:link></td>--}%
					
						<td>${fieldValue(bean: attachmentInstance, field: "name")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${attachmentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
