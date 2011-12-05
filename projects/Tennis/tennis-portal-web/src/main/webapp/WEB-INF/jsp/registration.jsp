<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simple jsp page</title>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
<script type='text/javascript' src="<c:url value='resources/js/jquery.tipsy.js'/>"></script>
<link rel="stylesheet" href="<c:url value='resources/css/cashback-site.css'/>" type="text/css" media="screen, projection"/>
</head>
<body>
<form id="register" class="register" action="" method="POST" title="">
    <ul>
        <li>
            <label><spring:message code="cashback.portal.registration.email" text="???portal-2010092610-01???"/></label>
            <input type="text" name="email" value=""
                   title="<spring:message code="cashback.portal.registration.email.title" text="???portal-2010092610-02???"/>">
        </li>
        <li>
            <label><spring:message code="cashback.portal.registration.password" text="???portal-2010092610-03???"/></label>
            <input class="" type="text" name="password" value=""
                   title="<spring:message code="cashback.portal.registration.password.title" text="???portal-2010092610-04???"/>">
        </li>
        <li>
            <label><spring:message code="cashback.portal.registration.password.confirmed" text="???portal-2010092610-05???"/></label>
            <input class="" type="text" name="confirmedPassword" value=""
                   title="<spring:message code="cashback.portal.registration.password.confirmed.title" text="???portal-2010092610-06???"/>">
        </li>
        <li>
            <label><spring:message code="cashback.portal.registration.username" text="???portal-2010092610-07???"/></label>
            <input class="" type="text" name="username" value=""
                   title="<spring:message code="cashback.portal.registration.username.title" text="???portal-2010092610-08???"/>">
        </li>
        <li>
            <input type="button" name="submit"
                   value="<spring:message code="cashback.portal.registration.button.createaccount" text="???portal-2010092610-09???"/>">
        </li>
    </ul>
</form>
<script type="text/javascript" charset="utf-8">
    $(function() {
        $('form [title]').tipsy({trigger: 'focus', gravity: 'w'});
    });
</script>
</body>
</html>