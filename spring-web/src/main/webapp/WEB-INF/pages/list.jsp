<%--<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<c:catch var="hasPage">
	<c:import url="notpage.jsp" />
</c:catch>

<c:if test="${hasPage != null}">
	this page is missing
</c:if>