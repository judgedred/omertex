<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>InquiryUpdate</title>
</head>
<body>


<h2>Update an inquiry</h2>


<form method="post" action="/support/update/${inquiry.inquiryId}" id="inquiryUpdateForm">
    <table>
        <tr>
            <td><label for id="customerName">Имя клиента</label></td>
            <td><input type="text" id="customerName" name="customerName" value="${inquiry.customerName}"></td>
        </tr>
        <tr>
            <td><label for id="topic">Тема</label></td>
            <td><select name="topic" id="topic"/>
                <c:forEach items="${topicList}" var="topic">
                    <option value="${topic.topicId}" ${topic.topicId == inquiry.topic.topicId ? 'selected' : ''}>${topic.topicName}</option>

                </c:forEach>
            </td>
        </tr>
        <tr>
            <td><label for id="createDate">Дата создания</label></td>
            <td><input type="text" id="createDate" name="createDate" value="${inquiry.createDate}"></td>
        </tr>
        <tr>
            <td><label for id="description">Описание</label></td>
            <td><input type="text" id="description" name="description" value="${inquiry.description}"></td>
        </tr>
            <c:forEach items="${attributeMap}" var="attributeMap" varStatus="status">
                <tr>
                    <td>${attributeMap.key}</td>
                    <td><input name="attributeMap['${attributeMap.key}']" value="${attributeMap.value}"/></td>
                </tr>
            </c:forEach>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="Обновить" /></td>
        </tr>
    </table>
</form>


</body>
</html>