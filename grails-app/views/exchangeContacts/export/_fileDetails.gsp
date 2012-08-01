<g:if test="${file}">
    <g:link onclick="toggle_show('code')"><g:message code="default.page.title.label.toggle"
                                                                      default="Toggle"/></g:link>

    <div id=code style="display: none;">
        <code>
            ${fileContents}
        </code>
    </div>
    <br/>
    <br/>

    <div class="downloadLink">
        <a href="${createLink(controller: 'file', action: 'downloadFile', params: [filePath: file])}">
            <g:message code="default.link.download.label" default="Download complete file"/>
        </a>
    </div>
</g:if>
