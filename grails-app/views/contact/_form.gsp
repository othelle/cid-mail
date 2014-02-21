<%@ page import="com.othelle.cig.email.Contact" %>



<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="contact.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="50" required="" value="${contactInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="contact.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" maxlength="50" required="" value="${contactInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="contact.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="email" cols="40" rows="5" maxlength="1024" required="" value="${contactInstance?.email}"/>
	
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'collections', 'error')} ">
	<label for="collections">
		<g:message code="contact.collections.label" default="Collections" />

	</label>
	<g:select name="collections" from="${com.othelle.cig.email.Collection.list()}" multiple="multiple" optionKey="id" size="5" value="${contactInstance?.collections*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'lastName', 'error')} required">
    <label for="lastName">
        Организация
    </label>
    <g:textField name="organization" maxlength="25" required="" value="${contactInstance?.organization}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: contactInstance, field: 'localMail', 'error')} ">
	<label for="localMail">
		<g:message code="contact.localMail.label" default="Local Mail" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${contactInstance?.localMail?}" var="l">
    <li><g:link controller="localMail" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="localMail" action="create" params="['contact.id': contactInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'localMail.label', default: 'LocalMail')])}</g:link>
</li>
</ul>

</div>

