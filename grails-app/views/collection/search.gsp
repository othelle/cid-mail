<%@ page import="com.othelle.cig.email.Collection"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'collection.label', default: 'Collection')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
	<a href="#list-collection" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>

	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link controller="collection">
					<g:message code="collection.list.label" />
				</g:link></li>
			<div class="search">
				<g:form class="search" controller="collection" action='search'>
					<g:textField name="q" value="${params.q}" />
					<g:select name="max" from="${[1, 5, 10, 50]}"
						value="${params.max ?: 10}" />

					<g:submitButton name="searchButton" class="searchButton"
						value="${message(code: 'search.label', default: 'Search')}" />
				</g:form>
			</div>
		</ul>
	</div>

	<div id="list-collection" class="content scaffold-list" role="main">
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

							<g:sortableColumn property="name"
								title="${message(code: 'collection.name.label', default: 'Name')}" />

							<g:sortableColumn property="email"
								title="${message(code: 'collection.email.label', default: 'Email')}" />

							<g:sortableColumn property="password"
								title="${message(code: 'collection.password.label', default: 'Password')}" />

							<g:sortableColumn property="code"
								title="${message(code: 'collection.code.label', default: 'Code')}" />

						</tr>
					</thead>
					<tbody>
						<g:each in="${searchResult.results}" status="i" var="result">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

								<td><g:link action="show" id="${result.id}">
										${fieldValue(bean: result, field: "name")}
									</g:link></td>

								<td>
									${fieldValue(bean: result, field: "email")}
								</td>

								<td>
									${fieldValue(bean: result, field: "password")}
								</td>

								<td>
									${fieldValue(bean: result, field: "code")}
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
