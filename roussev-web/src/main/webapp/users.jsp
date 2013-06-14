<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>

	<h2>Users</h2>
	<table padding="4" border="1" >
		<tr>
			<th>First</th>
			<th>Last</th>
			<th>Birthdate</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.firstName}"/></td>
				<td><c:out value="${user.lastName}"/></td>
				<td><fmt:formatDate  dateStyle="short" value="${user.birthday}"/></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>