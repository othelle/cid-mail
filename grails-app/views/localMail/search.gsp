<%@ page import="com.othelle.cig.email.LocalMail"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'localMail.label', default: 'localMail')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
	<a href="#list-localMail" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>

	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link controller="localMail">
					<g:message code="localMail.label" />
				</g:link></li>
			<li><g:link class="mail_delete" action="deleteByFlagSend">
					<g:message code="localMail.deleteByFlagSend.label" />
				</g:link></li>
			<div class="search">
				<g:form class="search" controller="localMail" action='search'>
					<g:textField name="q" value="${params.q}" />
					<g:select name="max" from="${[1, 5, 10, 50]}"
						value="${params.max ?: 10}" />

					<g:submitButton name="searchButton" class="searchButton"
						value="${message(code: 'search.label', default: 'Search')}" />
				</g:form>
			</div>
		</ul>
	</div>

	<div id="list-localMail" class="content scaffold-list" role="main">
		<h1>
			<g:if test="${searchResult?.results}">
				<g:message code="search.result.label" args="[searchResult.total]" />
			</g:if>
			<g:if test="${!searchResult?.total || searchResult.total==0}">
				<g:message code="search.not.found.message" />
			</g:if>
		</h1>

		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<hr />
		<g:if test="${searchResult?.results}">
			<g:if test="${searchResult?.total && searchResult.total>0}">
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="subject"
								title="${message(code: 'localMail.subject.label', default: 'Subject')}" />
							<g:sortableColumn property="contact"
								title="${message(code: 'localMail.contact.label', default: 'Contact')}" />
							<g:sortableColumn property="dateCreated"
								title="${message(code: 'localMail.dateCreated.label', default: 'Date Created')}" />
							<g:sortableColumn property="dateSent"
								title="${message(code: 'localMail.dateSent.label', default: 'Date Sent')}" />
							<g:sortableColumn property="flagSend"
								title="${message(code: 'localMail.flagSend.label', default: 'Flag Send')}" />
						</tr>
					</thead>
					<tbody>
						<g:each in="${searchResult.results}" status="i"
							var="localMailInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td><g:link action="show" id="${localMailInstance.id}">
										${fieldValue(bean: localMailInstance, field: "subject")}
									</g:link> %{-- <g:if test="${localMailInstance?.attachment}">
										<li class="fieldcontain"><span id="attachment-label"
											class="property-label"><g:message
													code="checkMail.attachment.label" default="Attachment" /></span>

											<g:each in="${localMailInstance.attachment}" var="a">
												<span class="property-value"
													aria-labelledby="attachment-label"> ${a?.encodeAsHTML()}
												</span>
											</g:each></li>
									</g:if>--}%</td>
								<td>
									${fieldValue(bean: localMailInstance, field: "contact")}
								</td>
								<td><g:formatDate date="${localMailInstance.dateCreated}" /></td>
								<td><g:formatDate date="${localMailInstance.dateSent}" /></td>
								<td>
									${localMailInstance.flagSend ? "В очереди" : "Отправлено"}
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate params="[q:params.q]" total="${searchResult.total}" />
				</div>
			</g:if>
		</g:if>



	</div>
</body>
</html>
