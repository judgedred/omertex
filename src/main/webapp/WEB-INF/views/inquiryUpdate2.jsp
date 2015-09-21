<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>InquiryUpdate</title>
</head>
<body>


<h2>Update an inquiry</h2>

<form:form method="post" action="update" modelAttribute="inquiry">

    <table>
        <tr>
            <td><form:label path="customerName">
                Имя клиента
            </form:label></td>
            <td><form:input path="customerName" /></td>
        </tr>
        <tr>
            <td><form:label path="topic">
                Тема
            </form:label></td>
            <td><form:select path="topic.topicId" items="${topicList}"  /> </td>
        </tr>
        <tr>
            <td><form:label path="createDate">
                Дата создания
            </form:label></td>
            <td><form:input path="createDate" /></td>
        </tr>
        <tr>
            <td><form:label path="description">
                Описание
            </form:label></td>
            <td><form:input path="description" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="Добавить" /></td>
        </tr>
    </table>
</form:form>

<form:form method="post" action="addAttribute" modelAttribute="form">

    <table>
        <c:forEach items="${form.attributeOfInquiryMap}" var="attributeOfInquiryMap" varStatus="status">
        <tr>
            <td><form:label path="attributeOfInquiryMap['${attributeOfInquiryMap.key}']">
                ${attributeOfInquiryMap.key}
            </form:label></td>
            <td><form:input path="attributeOfInquiryMap['${attributeOfInquiryMap.key}']" /></td>
        </tr>
            <%--<tr>
                <td><form:label path="attributeOfInquiryMap['${attributeOfInquiryMap.value}']">
                    ${attributeOfInquiryMap.value}
                </form:label></td>
                <td><form:input path="attributeOfInquiryMap['${attributeOfInquiryMap.value}']" /></td>
            </tr>--%>
        </c:forEach>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="Добавить" /></td>
        </tr>
    </table>
</form:form>