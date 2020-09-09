<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<tags:master pageTitle="Загрузка Excel файла">
    <head></head>

    <p class="success">${param.message}</p>
    <p class="error">${param.error}</p>

    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        <br>
        <label for="fileName">Выберите файл для загрузки: </label>
        <input type="file" name="fileName" id="fileName">
        <br>
        <br>
        <input type="submit" value="Загрузить">
    </form>
</tags:master>
