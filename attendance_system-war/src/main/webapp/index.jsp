<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Jason Huang (yetianhuang.cs@gmail.com)
  Date: 4/25/14
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Attendance History</title>
    <link rel="icon" href="attendance_icon.ico">
    <link rel="stylesheet" href="stylesheets/checkbox.css"/>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
<form:form method="POST" action="${pageContext.request.contextPath}/search" commandName="classAndDateForm"
           id="classAndDateForm">
    <form:errors path="*" cssClass="errorblock"/>
    <table>
        <tr>
            <td> Class :</td>
            <td>
                <form:select path="classId">
                    <form:option value="NONE" label="--Select--"/>
                    <form:options items="${selectionList.classIds}"/>
                </form:select>
            </td>
            <td>
                <form:errors path="classId" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td> Date :</td>
            <td>
                <form:select path="date">
                    <form:option value="NONE" label="--Select--"/>
                    <form:options items="${selectionList.dates}"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit"/>
            </td>
        </tr>
    </table>
</form:form>
<div id="attendance_table">
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#classAndDateForm').submit(function (event) {
            var classId = $('#classId').val();
            var date = $('#date').val();
            var json = {"classId": classId, "date": date };

            $.ajax({
                url: $("#classAndDateForm").attr("action"),
                dataType: "json",
                data: JSON.stringify(json),
                type: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                statusCode: {
                    404: function () {
                        alert("page not found!");
                    }
                }
            }).done(function (data) {
                $("#attendance_table").html(function () {
                    var respContent = "<h3>Day attendance record:</h3>";
                    respContent += "<table>";
                    $.each(data.allStudents, function (index, value) {
                        respContent += "<tr>";
                        respContent += "<td>";
                        respContent += value;
                        respContent += "</td>";
                        respContent += "<td>";
                        respContent += "<input type=\"checkbox\" class=\"css-checkbox\" id=\"cb" + index + "\"";
                        <!-- not found -->
                        if (-1 != $.inArray(value, data.missingStudents)) {
                            respContent += "checked=\"checked\"";
                            respContent += "/>";
                        } else {
                            respContent += "/>";
                        }
                        respContent += "<label for=\"cb" + index + "\" name=\"checkbox" + index
                                + "\" class=\"css-label lite-x-gray\"/>";
                        respContent += "</td>";
                        respContent += "</tr>";
                    });
                    respContent += "</table>";
                    return respContent;
                });
            }).error(function () {
                alert("error");
            });
            event.preventDefault();
        });
    });
</script>

</body>
</html>
