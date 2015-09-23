<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Inquiries</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script>
        $(".delete").click(function(){
            $.ajax({
                url: this.getAttribute('href'),
                type: 'DELETE'
            })
            return false
        })
    </script>
</head>
<body>


<h2>Inquiries</h2>



<h3>Inquiries</h3>
<c:if test="${!empty inquiryList}">
    <table class="data">
        <tr>
            <th>Имя клиента</th>
            <th>Описание</th>
            <th>Дата создания</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${inquiryList}" var="inquiry">
            <tr>
                <td>${inquiry.customerName}</td>
                <td>${inquiry.description}</td>
                <td>${inquiry.createDate}</td>
                <td><a href="inquiryAddForm/${inquiry.customerName}">Добавить</a></td>
                <td><a href="update/${inquiry.inquiryId}">Изменить</a></td>
                <td><a href="customers/${inquiry.customerName}/inquiries/${inquiry.inquiryId}" class="delete" >Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>