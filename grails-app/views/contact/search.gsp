<%@ page import="com.othelle.cig.email.Contact"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'contact.label', default: 'Contact')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
	<a href="#list-contact" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>

	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link controller="contact">
					<g:message code="contact.default.list.label" />
				</g:link></li>
		</ul>
	</div>

	<div id="list-contact" class="content scaffold-list" role="main">
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
							<g:sortableColumn property="organization"
								title="${message(code: 'contact.organization.label', default: 'Organization')}" />
							<g:sortableColumn property="firstName"
								title="${message(code: 'contact.firstName.label', default: 'First Name')}" />
							<g:sortableColumn property="lastName"
								title="${message(code: 'contact.lastName.label', default: 'Last Name')}" />

							<g:sortableColumn property="email"
								title="${message(code: 'contact.email.label', default: 'Email')}" />
							<g:sortableColumn property="email"
								title="${message(code: 'contact.collections.label', default: 'Collection')}" />
						</tr>
					</thead>
					<tbody>
						<g:each in="${searchResult.results}" status="i" var="result">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td><g:if test="${result?.organization}">
										<g:link action="show" id="${result.id}">
											${fieldValue(bean: result, field: "organization")}
										</g:link>
									</g:if></td>
								<td><g:if test="${result?.firstName}">
										${fieldValue(bean: result, field: "firstName")}
									</g:if></td>
								<td><g:if test="${result?.lastName}">
										${fieldValue(bean: result, field: "lastName")}
									</g:if></td>
								<td><g:if test="${result?.email}">
										<g:link action="show" id="${result.id}">
											${fieldValue(bean: result, field: "email")}
										</g:link>
									</g:if></td>
								<td><g:if test="${result?.collections}">
										<g:each in="${result.collections}" var="c">
											<g:link controller="collection" action="show" id="${c.id}">
												${c?.encodeAsHTML()}
											</g:link>
											<br />
										</g:each>
									</g:if></td>

							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${searchResult.total}" />
				</div>
			</g:if>
		</g:if>



	</div>
</body>
</html>
