<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 06.08.12
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Отчет</title>

</head>

<body>

<div class="nav" role="navigation">
    <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></div>

<formset>
    <h1 ><g:message code="Отчет"/></h1>
<div id="fon">
    <table>
        <g:form action="results">
            <tr>
                <td><g:message code="collection.sender.label" default="Отправитель"/></td>
                <td><g:textField name="emailFrom"/></td>
            </tr>
            <tr>
                <td><g:message code="collection.from.label" default="Получатель"/></td>
                <td>
                    <div><g:message code="collection.group.label" default="Группа (название)"/></div>

                    <div><g:textField name="groupTo"/></div>

                    <div><g:message code="collection.Contact.label" default="Контакт (название)"/></div>

                    <div><g:textField name="contactTo"/></div>
                </td>
            </tr>
            <tr>
                <br/>
                <td><g:message code="collection.Date.label" default="Дата"/></td>
                <td>
                    <g:message code="collection.Date.from.label" default="с"/> <calendar:datePicker name="dateOne"
                                                                                                  defaultValue="${new Date()}"
                                                                                                  dateFormat="%d/%m/%Y"/>
                    <br/><br/>
                    <g:message code="collection.Date.to.label" default="по"/> <calendar:datePicker name="dateSecond"
                                                                                              defaultValue="${new Date()}"
                                                                                              dateFormat="%d/%m/%Y"/>
                </td>
            </tr>
            <tr>
                <td><g:message code="collection.QueryType.label" default="Тип запроса"/></td>
                <td><g:radioGroup name="queryType" labels="['И', 'ИЛИ', 'НЕТ']" values="['and', 'or', 'not']"
                                  value="and">
                    ${it.radio} ${it.label}
                </g:radioGroup>
                </td>
            </tr>


            <tr>
                <td/>
                <td>
                    <g:submitButton name="search" value="Search"/></td>
            </tr>
        </g:form>
    </table>
</div>
</formset>


</body>
</html>
