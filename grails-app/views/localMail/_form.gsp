<%@ page import="com.othelle.cig.email.LocalMail" %>



<div class="fieldcontain ${hasErrors(bean: localMailInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="localMail.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="1000" required="" value="${localMailInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: localMailInstance, field: 'flagSend', 'error')} ">
	<label for="flagSend">
		<g:message code="localMail.flagSend.label" default="Flag Send" />
		
	</label>
	<g:checkBox name="flagSend" value="${localMailInstance?.flagSend}" />
</div>

<div class="fieldcontain ${hasErrors(bean: localMailInstance, field: 'contact', 'error')} ">
	<label for="contact">
		<g:message code="localMail.contact.label" default="Contact" />
		
	</label>
	<g:select id="contact" name="contact.id" from="${com.othelle.cig.email.Contact.list()}" optionKey="id" value="${localMailInstance?.contact?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: localMailInstance, field: 'dateSent', 'error')} ">
	<label for="dateSent">
		<g:message code="localMail.dateSent.label" default="Date Sent" />
		
	</label>
	<g:datePicker name="dateSent" precision="day"  value="${localMailInstance?.dateSent}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: localMailInstance, field: 'subject', 'error')} ">
	<label for="subject">
		<g:message code="localMail.subject.label" default="Subject" />
		
	</label>
	<g:textField name="subject" value="${localMailInstance?.subject}"/>
</div>

