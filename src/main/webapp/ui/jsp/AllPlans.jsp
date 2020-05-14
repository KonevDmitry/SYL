<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> All Users </title>
</head>
<body>
<c:forEach var="plan" items="${plans}">
    <div>
        <div class="container" style="margin:50px">
            <div class="row" style="border:1px solid green;padding:10px">
                <div class="col-md-4 text-center"><strong>ID</strong> ${plan.id}</div>
                <div class="col-md-4 text-center"><strong>Description</strong> ${plan.desc}</div>
                <div class="col-md-4 text-center"><strong>Cost</strong> ${plan.cost}</div>
                <div class="col-md-4 text-center"><strong>Priveleges</strong> ${plan.priveleges}</div>
            </div>
        </div>
    </div>
</c:forEach>
</div>
</body>
</html>