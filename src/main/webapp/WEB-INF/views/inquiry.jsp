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

<form:form method="post" action="add" modelAttribute="inquiry">

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
            <td><form:input path="topic" /></td>
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

</body>
</html>