<%@ page import="com.othelle.cig.email.Attachment" %>



<div class="fieldcontain ${hasErrors(bean: attachmentInstance, field: 'fileByte', 'error')} required">
	<label for="fileByte">
		<g:message code="attachment.fileByte.label" default="File Byte" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="fileByte" name="fileByte" />
</div>

<div class="fieldcontain ${hasErrors(bean: attachmentInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="attachment.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="30" required="" value="${attachmentInstance?.name}"/>
</div>

