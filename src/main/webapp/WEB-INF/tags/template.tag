<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="head" required="false" fragment="true" %>
<%@ attribute name="pageTitle" required="false" fragment="false" %>
<%@ attribute name="ngApp" required="false" fragment="false" %>

<c:set var="_baseUrl" value="${empty pageContext.request.contextPath || pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath}"/>

<html lang="en" data-ng-app="${ngApp}">
<head>
	<title>
		<c:if test="${not empty pageTitle}"><c:out value="${pageTitle}"/> - </c:if><c:out value="${siteName}"/>
	</title>
	<jsp:invoke fragment="head"/>
</head>

<body>
	<jsp:doBody />
</body>
</html>