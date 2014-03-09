<!doctype html>
<html>
<head>
<meta name="layout" content="main" />
<title>АСГ Техно-строй</title>
<style type="text/css" media="screen">
#status {
	background-color: #eee;
	border: .2em solid #fff;
	margin: 2em 2em 1em;
	padding: 1em;
	width: 12em;
	float: left;
	-moz-box-shadow: 0px 0px 1.25em #ccc;
	-webkit-box-shadow: 0px 0px 1.25em #ccc;
	box-shadow: 0px 0px 1.25em #ccc;
	-moz-border-radius: 0.6em;
	-webkit-border-radius: 0.6em;
	border-radius: 0.6em;
}

.ie6 #status {
	display: inline;
	/* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
}

#status ul {
	font-size: 0.9em;
	list-style-type: none;
	margin-bottom: 0.6em;
	padding: 0;
}

#status li {
	line-height: 1.3;
}

#status h1 {
	text-transform: uppercase;
	font-size: 1.1em;
	margin: 0 0 0.3em;
}

h2 {
	margin-top: 1em;
	margin-bottom: 0.3em;
	font-size: 1em;
}

p {
	line-height: 1.5;
	margin: 0.25em 0;
}

#controller-list ul {
	list-style-position: inside;
}

#controller-list li {
	line-height: 1.3;
	list-style-position: inside;
	margin: 0.25em 0;
}

@media screen and (max-width: 480px) {
	#status {
		display: none;
	}
	#page-body {
		margin: 0 1em 1em;
	}
	#page-body h1 {
		margin-top: 0;
	}
}
</style>
</head>

<body>
<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
	<div id="status" role="complementary" style="display: none;">
		<h1>Отладочная информация</h1>
		<h5>Эта панель будет убрана в релизе</h5>
		<br />
		<ul>
			<li>App version: <g:meta name="app.version" /></li>
			<li>Grails version: <g:meta name="app.grails.version" /></li>
			<li>Groovy version: ${org.codehaus.groovy.runtime.InvokerHelper.getVersion()}</li>
			<li>JVM version: ${System.getProperty('java.version')}</li>
			<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
			<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
			<li>Domains: ${grailsApplication.domainClasses.size()}</li>
			<li>Services: ${grailsApplication.serviceClasses.size()}</li>
			<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
		</ul>
		<br />

		<h1>Установленные плагины</h1>
		<ul>
			<g:each var="plugin"
				in="${applicationContext.getBean('pluginManager').allPlugins}">
				<li>
					${plugin.name} - ${plugin.version}
				</li>
			</g:each>
		</ul>
	</div>

	<div id="page-body" role="main">
		<h1>Контрольная панель</h1>
		<div id="controller-list" role="navigation">
			<ul style="width: 582px;">
				<h4>Списки рассылки</h4>

				<li class="controller"><g:link controller="collection">Группы</g:link></li>
				<li class="controller"><g:link controller="contact">Контакты</g:link></li>
				<br />

				<h4>История запросов</h4>
				<li class="controller"><g:link controller="checkMail">Письма полученные</g:link></li>
				<li class="controller"><g:link controller="localMail">Письма для отправки</g:link></li>

				<br />
				<h4>Логгирование и отчетность</h4>
				<li class="controller"><g:link controller="log">Логи</g:link></li>
				<li class="controller"><g:link controller="report">Отчеты</g:link></li>

				<br />
				<h4>Обмен контактами</h4>
				<li class="controller"><g:link controller="exchangeContacts"
						action="import">Импорт</g:link></li>
				<li class="controller"><g:link controller="exchangeContacts"
						action="export">Экспорт</g:link></li>

				<br />
				<h4>Сервис</h4>
				<li class="controller"><g:link controller="localMail"
						action="deleteByFlagSend">Удаление истории запросов</g:link></li>
				<li><g:link controller="contact"
						action="reindex"><g:message code="reindex" /></g:link>
				</ul>


		</div>
		<div class="homemessage">
			<b>Уважаемый пользователь!</b>

			<p>Данное програмное обеспечение разработано в целях
				автоматизации производства, в частности документооборота связанного
				с приемом и отправкой e-mail корреспонденции.</p>

			<b>Основные функциональные возможности ПО:</b>
			<ul>
				<li>Получение корреспонденции с почтового сервиса e-mail
					указанного Заказчиком;</li>
				<li>Пересылка корреспонденции по группам в соответствии с
					указаниями администратора ПО;</li>
				<li>Автоматическая пересылка корреспонденции в соответствии с
					привязкой группы к определенному почтовому адресу;</li>
				<li>Администрирование базы получателей корреспонденции с
					возможностью добавлять и удалять данные контактов, объединять
					контакты в группы;</li>
				<li>Импорт и Экспорт контактов из и в формат .csv (Microsoft
					Outlook®)</li>
				<li>Ведение логов (довольно подробно: когда, с какого адреса
					пришло, на какие перенаправляли, и т.п.).</li>
				<li>Возможность сделать отчет: по интервалу дат, по адресу
					(откуда пришло письмо), по группе (какие письма ушли на эту
					группу), по контакту аналогично;</li>
			</ul>

			<b>Привила пересылки:</b>
			<ul>
				<li>Каждый контакт из группы получает отдельное письмо.</li>
				<li>Каждое письмо не содержит информацию, на какой оно было
					отправлено изначально.</li>
				<li>Оправлять всю корреспонденцию с одного адреса;</li>
				<li>Каждый контакт может входить в произвольное количество
					групп и иметь произвольное количество e-mail адресов.</li>
				<li>Если в карточке контакта присутствуют несколько e-mail
					адресов, то на них отправляется одно общее письмо с перечислением
					всех адресатов в поле кому.</li>
			</ul>

		</div>
	</div>
</body>
</html>

