<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Testing JSP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<h1>Hello World !</h1>

<%--@elvariable id="searchResult" type=""--%>
<form:form action="citizenPlanList" modelAttribute="searchResult" method="post" cssStyle="margin: 20px">
    <table style="padding: 20px" >
        <tr>
            <td>plan Name</td>
            <td><form:select path="planName">
                <form:option value="">--select--</form:option>
                <form:options items="${planName}" />
            </form:select></td>
        </tr>
        <tr>
            <td>plan Status</td>
            <td><form:select path="planStatus">
                <form:option value="">--select--</form:option>
                <form:options items="${planStatus}" />
            </form:select></td>
        </tr>

        <tr>
            <td>gender</td>
           <td><form:radiobutton path="gender" value="Male"/>Male
           <form:radiobutton path="gender" value="Female"/>Female</td>
        </tr>

        <tr>
            <td>Plan Start date</td>
            <td><input name="planStartDate" type="text" placeholder="yyyy-mm-dd"></td>
        </tr>
        <tr>
            <td>Plan End date</td>
            <td><input name="planEndDate" type="text" placeholder="yyyy-mm-dd"></td>
        </tr>
<tr  >
   <td> <a href="/form" >reset</a></td>
   <td> <input class="btn btn-primary" type="submit" value="search result"></td>
</tr>
    </table>

</form:form>


<hr>

<h1>${citizenPlan}</h1>


<table class="table table-striped">
    <tr>
        <th>Citizen Name</th>
        <th>Gender</th>
        <th>Plan Name</th>
        <th>Plan Status</th>
        <th>Plan Start Date</th>
        <th>Plan End Date</th>
    </tr>
    <tbody>
    <c:forEach items="${citizenPlan}" var="c">
        <tr>
            <td>${c.citizenName}</td>
            <td>${c.gender}</td>
            <td>${c.planName}</td>
            <td>${c.planStatus}</td>
            <td>${c.planStartDate}</td>
            <td>${c.planEndDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<hr>

Export : <a href="exportPdf">pdf</a>   <a href="exportExcel">excel</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>