<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="files" type="java.util.ArrayList" scope="request"/>
<tags:master pageTitle="Files">
    <head></head>

    <table>
        <thead>
        <tr>
            <td>File Name</td>
            <td>Document Name</td>
            <td>Start Date</td>
            <td>Finish Date</td>
            <td>Document Created Date</td>
        </tr>
        </thead>
        <c:forEach var="accountingEntity" items="${files}">
            <tr>
                <td>
                   <a href="${pageContext.request.contextPath}/files/${accountingEntity.id}">${accountingEntity.fileName}</a>
                </td>
                <td>${accountingEntity.docName}</td>
                <td>${accountingEntity.startDate}</td>
                <td>${accountingEntity.finishDate}</td>
                <td>${accountingEntity.createDocDate}</td>
            </tr>
        </c:forEach>
    </table>
</tags:master>