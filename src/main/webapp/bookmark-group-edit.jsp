
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
    </style>
</head>
<body>
    <h1>북마크 목록</h1>
    <a href="/">홈</a> |
    <a href="/history/list">위치 히스토리 목록</a> |
    <a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="/bookmark/list">북마크 보기</a> |
    <a href="/bookmark/group">북마크 그룹 관리</a><br>

    <form method="post" action="/bookmark/editBookmark">
        <input type="hidden" value="<%=request.getAttribute("id")%>" name="id">
        <table id="customers">
            <tr>
                <th>북마크 이름</th>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <th>순서</th>
                <td><input type="text" name="order"/> </td>
            </tr>
            <tr align="center">
                <td colspan="2"><a href="/bookmark/group">돌아가기</a> <input type="submit" value="수정"/> </td>
            </tr>
        </table>
    </form>

</body>
</html>
