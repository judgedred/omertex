<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>InquiryAdd</title>
</head>
<body>


<h2>Add an inquiry</h2>


<form method="post" action="/support/customers/${inquiry.customerName}/inquiries" id="inquiryAddForm">
    <table>
    <tr>
        <td><label for id="customerName">Имя клиента</label></td>
        <td><input type="text" id="customerName" name="customerName" value="${inquiry.customerName}"> </td>
    </tr>
    <tr>
        <td><label for id="topic">Тема</label></td>
        <td><select id="topic" name="topic"/>
            <c:forEach items="${topicList}" var="topic">
                <option value="${topic.topicId}">${topic.topicName}</option>

            </c:forEach>
        </td>
    </tr>
    <tr>
        <td><label for id="createDate">Дата создания</label></td>
        <td><input type="text" id="createDate" name="createDate"></td>
    </tr>
    <tr>
        <td><label for id="description">Описание</label></td>
        <td><input type="text" id="description" name="description"></td>
    </tr>
    <tr>
        <td><label for id="attributeName">Имя атрибута</label></td>
        <td><input type="text" id="attributeName" name="attributeName"></td>
    </tr>
    <tr>
        <td><label for id="attributeValue">Значение</label></td>
        <td><input type="text" id="attributeValue" name="attributeValue"></td>
    </tr>
    <tr>
        <td><label for id="attributeName2">Имя атрибута</label></td>
        <td><input type="text" id="attributeName2" name="attributeName"></td>
    </tr>
    <tr>
        <td><label for id="attributeValue2">Значение</label></td>
        <td><input type="text" id="attributeValue2" name="attributeValue"></td>
    </tr>
    <tr>
        <td colspan="2"><input type="submit"
                               value="Добавить" /></td>
    </tr>
    </table>
</form>


</body>
</html>