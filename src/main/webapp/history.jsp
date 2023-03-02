<%@ page import="Model.HistoryVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #customers tr:hover {
            background-color: #ddd;
        }

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }

        /*#customers td {*/
        /*    text-align: center;*/
        /*}*/
    </style>
</head>
<body>
    <h1>위치 히스토리 목록</h1>
    <a href="/">홈</a> |
    <a href="/history/list">위치 히스토리 목록</a> |
    <a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="/bookmark/list">북마크 보기</a> |
    <a href="/bookmark/group">북마크 그룹 관리</a><br>

<table id="customers">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    <%
        List<HistoryVO> historyList = (List<HistoryVO>) request.getAttribute("historyList");
        for (HistoryVO history : historyList) {
    %>
        <tr>
            <td><%=history.getId() %></td>
            <td><%=history.getLat() %> </td>
            <td><%=history.getLnt() %> </td>
            <td><%=history.getDate() %> </td>
            <td align="center">
                <button type="button" onclick="location.href='/history/delete?id=<%=history.getId()%>'">삭제</button>
            </td>
        </tr>
    <% } %>

</table>
</body>
</html>