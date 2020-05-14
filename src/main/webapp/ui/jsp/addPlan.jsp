<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration</title>
</head>
<body>
<form:form id="regForm" modelAttribute="plan" action="addPlanProcess" method="post">
    <table align="center">
        <tr>
            <td>
                <form:label path="desc">Desc</form:label>
            </td>
            <td>
                <form:input path="desc" name="desc" id="desc" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="cost">Cost</form:label>
            </td>
            <td>
                <form:input path="cost" name="cost" id="cost" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="priveleges">Priveleges</form:label>
            </td>
            <td>
                <form:input path="priveleges" name="priveleges" id="priveleges" />
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