<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Order</title>
</head>
<body>
<form:form id="regForm" modelAttribute="order" action="orderProcess" method="post">
    <table align="center">
        <tr>
            <td>
                <form:label path="userID">User ID</form:label>
            </td>
            <td>
                <form:input path="userID" name="userID" id="userID" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="privelege">Privelege</form:label>
            </td>
            <td>
                <form:input path="privelege" name="privelege" id="privelege" />
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:button id="add" name="add">Add</form:button>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>