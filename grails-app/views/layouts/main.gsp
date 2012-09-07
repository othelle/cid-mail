<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <r:require modules="jquery-ui,scaffolding"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <g:layoutHead/>
    <r:layoutResources/>
    <calendar:resources/>
</head>

<body>
<div id="grailsLogo" role="banner">
    <div id="header">

        <div id="logo">
            <g:link>
                <img src="http://asg-ts.ru/wp-content/themes/ASG/images/logo.png" style="margin-top:1px;" alt="строительная компания" name="АСГ Техно-Строй"/>
            </g:link>
        </div>

        <div class="ab-ds">
            <div id="about"><a href="http://localhost:8080/creative-email/" style="text-decoration:none;">АСГ Техно-Строй</a></div>

            <div class="description">строительная компания</div>
        </div>

        <div id="tel">
            <a href="mailto:info@asg-ts.ru">info@asg-ts.ru</a>
            <br/>+7(495) 722-20-14</div>

        <div class="topname">
            <div class="ugol"></div>

            <div class="softname">Управление закупками 1.2</div>
        </div>
    </div>
</div>
<g:layoutBody/>
<div class="footer" role="contentinfo">
    <div id="copywrite_l">Разработка: <a href="http://www.CreativeIdeaGroup.ru">"Creative Idea Group" - студия коммерческого креатива.</a></div>

    <div id="copywrite_r">Поддержка: <a href="mailto:cig.branding@gmail.com">cig.branding@gmail.com</a></div>
</div>

<div id="spinner" class="spinner" style="display:none;"></div>
<g:javascript library="application"/>
<r:layoutResources/>
</body>
</html>
