<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<jsp:useBean id="periodEntity" type="model.PeriodEntity" scope="request"/>
<tags:master pageTitle="${periodEntity.docName}">
    <head></head>
    <a href="${pageContext.request.contextPath}">All files</a>

    <div class="center-text">
        <h3 class="left-text">Название банка: ${periodEntity.bankEntity.name}</h3>
        <h2>Оборотная ведомость по балансовым счетам</h2>
        <p>за период с ${periodEntity.startDate.toLocaleString()} по ${periodEntity.finishDate.toLocaleString()}</p>
        <p>по банку</p>
        <br>
        <span class="left-text">
            <p>${periodEntity.createDocDate.toLocaleString()}</p>
            <p>${periodEntity.currency}</p>
        </span>
        <table class="center-text">
            <thead>
            <tr>
                <th rowspan="2">Б/сч</th>
                <th colspan="2">ВХОДЯЩЕЕ САЛЬДО</th>
                <th colspan="2">ОБОРОТЫ</th>
                <th colspan="2">ИСХОДЯЩЕЕ САЛЬДО</th>
            </tr>
            <tr>
                <th>Актив</th>
                <th>Пассив</th>
                <th>Дебет</th>
                <th>Кредит</th>
                <th>Актив</th>
                <th>Пассив</th>
            </tr>
            </thead>
            <c:forEach var="accountEntity" items="${periodEntity.accountingEntityList}">
            <tr>
                <td>${accountEntity.accountNum}</td>
                <td>${accountEntity.htmlInputBalanceAsset}</td>
                <td>${accountEntity.htmlInputBalancePassive}</td>
                <td>${accountEntity.htmlTurnoverDebit}</td>
                <td>${accountEntity.htmlTurnoverCredit}</td>
                <td>${accountEntity.htmlOutputBalanceAsset}</td>
                <td>${accountEntity.htmlOutputBalancePassive}</td>
            </tr>
            </c:forEach>
        </table>
    </div>
</tags:master>
