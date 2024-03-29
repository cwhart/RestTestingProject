<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0148464
  Date: 6/8/2018
  Time: 9:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <c:forEach items="${viewModel.players}" var="player">
        <tr>
            <td><a href="/player/show?id=${player.playerId}">${player.first}
                    ${player.last}
            </a></td>
            <td><a href="/player/edit?id=${player.playerId}">edit</a></td>
        </tr>
    </c:forEach>
</table>

<c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
    <a href="/player/list?offset=${(pageNumber -1) *5}">${pageNumber}</a>
</c:forEach>

<br />
<a href="/player/create">Create New Player</a>

</body>
</html>
