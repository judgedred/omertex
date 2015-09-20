<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Inquiry</title>
</head>
<body>


<h2>Add an inquiry</h2>

<form:form method="post" action="addInquiry" modelAttribute="form">

    <table>
        <tr>
            <td><form:label path="inquiry.customerName">
                Имя клиента
            </form:label></td>
            <td><form:input path="inquiry.customerName" /></td>
        </tr>
        <tr>
            <td><form:label path="inquiry.topic">
                Тема
            </form:label></td>
            <td><form:select path="inquiry.topic.topicId" items="${topicList}"  /> </td>
        </tr>
        <tr>
            <td><form:label path="inquiry.createDate">
                Дата создания
            </form:label></td>
            <td><form:input path="inquiry.createDate" /></td>
        </tr>
        <tr>
            <td><form:label path="inquiry.description">
                Описание
            </form:label></td>
            <td><form:input path="inquiry.description" /></td>
        </tr>
            <tr>
                <td><form:label path="attributeOfInquiryMap['${attributeOfInquiryMap.key}']">
                    Имя
                </form:label></td>
                <td><input path="attributeOfInquiryMap['${form.attributeOfInquiryMap.key}']" value="${form.attributeOfInquiryMap.value}"/></td>

            </tr>
        <tr>
            <td><form:label path="attributeOfInquiryMap['${attributeOfInquiryMap.key}']">
                Значение
            </form:label></td>
            <td><input path="attributeOfInquiryMap['${form.attributeOfInquiryMap.value}']" value="${form.attributeOfInquiryMap.value}"/></td>
        </tr>


        <tr>
            <td colspan="2"><input type="submit"
                                   value="Добавить" /></td>
        </tr>
    </table>
</form:form>




</body>
</html>