<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="files" type="java.util.ArrayList" scope="request"/>
<tags:master pageTitle="Files">
    <head></head>

    <p class="center-text"><a href="${pageContext.request.contextPath}/upload">Загрузить Excel документ</a></p>

    <table>
        <thead>
        <tr>
            <th>Название файла</th>
            <th>Название документа</th>
            <th>Дата начала отчета</th>
            <th>Дата конца отчета</th>
            <th>Дата создания отчета</th>
        </tr>
        </thead>
        <c:forEach var="periodEntity" items="${files}">
            <tr>
                <td>
                   <a href="${pageContext.request.contextPath}/files/${periodEntity.id}">${periodEntity.fileName}</a>
                </td>
                <td>${periodEntity.docName}</td>
                <td>${periodEntity.startDate}</td>
                <td>${periodEntity.finishDate}</td>
                <td>${periodEntity.createDocDate}</td>
            </tr>
        </c:forEach>
    </table>
</tags:master>