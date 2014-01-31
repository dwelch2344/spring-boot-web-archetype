<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tags:template pageTitle="Demo">
	<jsp:attribute name="head">  
		<!-- In the header, from the page... -->
    </jsp:attribute>
	<jsp:body>
		<h1>Hello Boot!</h1>
		<ul>
			<c:forEach items="${peope}" var="person">
				<li><c:out value="${person.id} ${person.name}"></c:out>
			</c:forEach>
		</ul>
	</jsp:body>
</tags:template>