<%@ page import="Model.BookmarkGroupVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

      #customers tr:nth-child(even){background-color: #f2f2f2;}

      #customers tr:hover {background-color: #ddd;}

      #customers th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: center;
        background-color: #04AA6D;
        color: white;
      }
      #customers td {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: center;
      }
    </style>
</head>
<body>
  <h1>북마크 그룹</h1>
  <a href="/">홈</a> |
  <a href="/history/list">위치 히스토리 목록</a> |
  <a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
  <a href="/bookmark/list">북마크 보기</a> |
  <a href="/bookmark/group">북마크 그룹 관리</a><br>

  <button type="button" onclick="location.href='/bookmark/add'">북마크 그룹 이름 추가</button>

  <table id="customers">
    <tr>
      <th>ID</th>
      <th>북마크 이름</th>
      <th>순서</th>
      <th>등록일자</th>
      <th>수정일자</th>
      <th>비고</th>
    </tr>
      <%
      List<BookmarkGroupVO> bookmarkGroupList = (List<BookmarkGroupVO>) request.getAttribute("bookmarkGroupList");
      for (BookmarkGroupVO bookmark : bookmarkGroupList) {
    %>
    <tr>
      <td><%=bookmark.getId() %></td>
      <td><%=bookmark.getName() %> </td>
      <td><%=bookmark.getOrd() %> </td>
      <td><%=bookmark.getRegDate() %> </td>
      <td><%=bookmark.getModDate()%></td>
      <td><a href="/bookmark/edit?id=<%=bookmark.getId()%>">수정</a> <a href="/bookmark/delete?id=<%=bookmark.getId()%>">삭제</a></td>
    </tr>

    <% } %>


</body>
</html>
