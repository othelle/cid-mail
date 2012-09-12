<%@ page import="com.othelle.cig.email.CheckMail" %>



<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'uid', 'error')} required">
	<label for="uid">
		<g:message code="checkMail.uid.label" default="Uid" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="uid" required="" value="${checkMailInstance?.uid}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'emailFrom', 'error')} ">
	<label for="emailFrom">
		<g:message code="checkMail.emailFrom.label" default="Email From" />
		
	</label>
	<g:textField name="emailFrom" value="${checkMailInstance?.emailFrom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'subject', 'error')} required">
	<label for="subject">
		<g:message code="checkMail.subject.label" default="Subject" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="subject" maxlength="100" required="" value="${checkMailInstance?.subject}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'body', 'error')} ">
	<label for="body">
		<g:message code="checkMail.body.label" default="Body" />
		
	</label>
	<g:textArea name="body" cols="40" rows="5" maxlength="10000" value="${checkMailInstance?.body}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'dateSend', 'error')} ">
	<label for="dateSend">
		<g:message code="checkMail.dateSend.label" default="Date Send" />
		
	</label>
	<g:datePicker name="dateSend" precision="day"  value="${checkMailInstance?.dateSend}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'flagNew', 'error')} ">
	<label for="flagNew">
		<g:message code="checkMail.flagNew.label" default="Flag New" />
		
	</label>
	<g:checkBox name="flagNew" value="${checkMailInstance?.flagNew}" />
</div>

<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'attachment', 'error')} ">
	<label for="attachment">
		<g:message code="checkMail.attachment.label" default="Attachment" />
		
	</label>
	<g:select name="attachment" from="${com.othelle.cig.email.Attachment.list()}" multiple="multiple" optionKey="id" size="5" value="${checkMailInstance?.attachment*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'collection', 'error')} required">
	<label for="collection">
		<g:message code="checkMail.collection.label" default="Collection" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="collection" name="collection.id" from="${com.othelle.cig.email.Collection.list()}" optionKey="id" required="" value="${checkMailInstance?.collection?.id}" class="many-to-one"/>
</div>

