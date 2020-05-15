<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> All Users </title>
</head>
<body>
<c:forEach var="order" items="${orders}">
    <div>
        <div class="container" style="margin:50px">
            <div class="row" style="border:1px solid green;padding:10px">
                <div class="col-md-4 text-center"><strong>ID</strong> ${order.id}</div>
                <div class="col-md-4 text-center"><strong>User ID</strong> ${order.userID}</div>
                <div class="col-md-4 text-center"><strong>Privelege</strong> ${order.privelege}</div>
            </div>
        </div>
    </div>
</c:forEach>
</div>
</body>
</html>