<%@ page import="com.othelle.cig.email.Collection" %>



<div class="fieldcontain ${hasErrors(bean: collectionInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="collection.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="25" required="" value="${collectionInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: collectionInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="collection.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${collectionInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: collectionInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="collection.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${collectionInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: collectionInstance, field: 'contacts', 'error')} ">
	<label for="contacts">
		<g:message code="collection.contacts.label" default="Contacts" />
		
	</label>
	<g:select name="contacts" from="${com.othelle.cig.email.Contact.list()}" multiple="multiple" optionKey="id" size="5" value="${collectionInstance?.contacts*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: collectionInstance, field: 'checkMail', 'error')} ">
	<label for="checkMail">
		<g:message code="checkMail.label" default="Check Mail" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${collectionInstance?.checkMail?}" var="c">
    <li><g:link controller="checkMail" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="checkMail" action="create" params="['collection.id': collectionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'checkMail.label', default: 'CheckMail')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: collectionInstance, field: 'code', 'error')} ">
	<label for="code">
		<g:message code="collection.code.label" default="Code" />
		
	</label>
	<g:textField name="code" value="${collectionInstance?.code}"/>
</div>

