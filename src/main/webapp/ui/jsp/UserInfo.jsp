<html>
<head>
    <title> UserInfo </title>
</head>
<body>
<div>
    <div class="container" style="margin:50px">
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-4 text-center"><strong>ID</strong> ${user.id}</div>
            <div class="col-md-4 text-center"><strong>Name</strong> ${user.name}</div>
            <div class="col-md-4 text-center"><strong>Surname</strong> ${user.surname}</div>
            <div class="col-md-4 text-center"><strong>Email</strong> ${user.email}</div>
            <div class="col-md-4 text-center"><strong>Password</strong> ${user.password}</div>
            <div class="col-md-4 text-center"><strong>Type</strong> ${user.type     }</div>
            <div class="col-md-4 text-center"><strong>Chosen plan</strong> ${user.plan.desc}</div>
        </div>
    </div>
</div>
</body>
</html>