<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Testing JSP</title>
</head>
<body>
<h1>Hello World !</h1>

<%--@elvariable id="searchResult" type=""--%>
<form:form action="" modelAttribute="searchResult" method="post">
    <table>
        <tr>
            <td>plan Name</td>
            <td><form:select path="planName">
                <form:option value="">--select--</form:option>
            </form:select></td>
        </tr>
        <tr>
            <td>plan Status</td>
            <td><form:select path="planStatus">
                <form:option value="">--select--</form:option>
            </form:select></td>
        </tr>

        <tr>
            <td>gender</td>
            <td><form:select path="gender">
                <form:option value="">--select--</form:option>
                <form:option value="Male">Male</form:option>
                <form:option value="Female">Female</form:option>
            </form:select></td>
        </tr>

        <tr>
            <td>Plan Start date</td>
            <td><input name="planStartDate" type="text" placeholder="yyyy-mm-dd"></td>
        </tr>
        <tr>
            <td>Plan End date</td>
            <td><input name="planEndDate" type="text" placeholder="yyyy-mm-dd"></td>
        </tr>
        <tr>
          <input type="submit" value="search result">
        </tr>
    </table>
</form:form>


<hr>
<hr>

Export : <a href="">pdf</a>   <a href="">excel</a>


</body>
</html>