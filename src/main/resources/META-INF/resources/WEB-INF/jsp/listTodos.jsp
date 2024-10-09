<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
    <head>
        <title>TODO Page</title>
    </head>
    <body>
        <div>Welcome ${username}</div>
        <hr>
        <h2>Your TODOs</h2>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Done</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.id}</td>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>