<%@ page import="com.othelle.cig.email.CheckMail" %>



<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'subject', 'error')} required">
	<label for="subject">
		<g:message code="checkMail.subject.label" default="Subject" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="subject" maxlength="100" required="" value="${checkMailInstance?.subject}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'body', 'error')} required">
	<label for="body">
		<g:message code="checkMail.body.label" default="Body" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="body" cols="40" rows="5" maxlength="1000" required="" value="${checkMailInstance?.body}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: checkMailInstance, field: 'dateSend', 'error')} ">
	<label for="dateSend">
		<g:message code="checkMail.dateSend.label" default="Date Send" />
		
	</label>
	<g:datePicker name="dateSend" precision="day"  value="${checkMailInstance?.dateSend}" default="none" noSelection="['': '']" />
</div>

