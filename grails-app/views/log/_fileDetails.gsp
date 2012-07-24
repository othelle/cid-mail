<g:if test="${filePath}">
    <code>
        ${fileContents}
    </code>

    <div class="downloadLink">
        <a href="${createLink(controller: 'file', action: 'downloadFile', params: [filePath: filePath])}">
            <g:message code="default.link.download.label" default="Download complete file"/>
        </a>
    </div>
</g:if>