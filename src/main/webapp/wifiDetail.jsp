<%@ page import="Model.WifiInfoVO" %>
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
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>와이파이 정보 구하기</h1>
    <a href="/">홈</a> |
    <a href="/history/list">위치 히스토리 목록</a> |
    <a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="/bookmark/list">북마크 보기</a> |
    <a href="/bookmark/group">북마크 그룹 관리</a><br>

    <table id="customers">
        <%
            WifiInfoVO wifiInfoDetail = (WifiInfoVO) request.getAttribute("wifiInfoDetail");
        %>
        <tr>
            <th>거리(Km)</th>
            <td><%=wifiInfoDetail.getDistance() %></td>
        </tr>
        <tr>
            <th>관리번호</th>
            <td><%=wifiInfoDetail.getX_SWIFI_MGR_NO() %></td>
        </tr>
        <tr>
            <th>자치구</th>
            <td><%=wifiInfoDetail.getX_SWIFI_WRDOFC() %> </td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><a href="/wifiDetail?id=<%=wifiInfoDetail.getX_SWIFI_MGR_NO()%>&lat=<%=request.getAttribute("lat")%>&lnt=<%=request.getAttribute("lnt")%>"><%=wifiInfoDetail.getX_SWIFI_MAIN_NM() %></a>  </td>
        </tr>
        <tr>
            <th>도로명 주소</th>
            <td><%=wifiInfoDetail.getX_SWIFI_ADRES1() %> </td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td><%=wifiInfoDetail.getX_SWIFI_ADRES2() %></td>
        </tr>
        <tr>
            <th>설치위치(층)</th>
            <td><%=wifiInfoDetail.getX_SWIFI_INSTL_FLOOR() %></td>
        </tr>
        <tr>
            <th>설치유형</th>
            <td><%=wifiInfoDetail.getX_SWIFI_INSTL_TY() %></td>
        </tr>
        <tr>
            <th>설치기관</th>
            <td><%=wifiInfoDetail.getX_SWIFI_INSTL_MBY() %></td>
        </tr>
        <tr>
            <th>서비스구분</th>
            <td><%=wifiInfoDetail.getX_SWIFI_SVC_SE() %></td>
        </tr>
        <tr>
            <th>망종류</th>
            <td><%=wifiInfoDetail.getX_SWIFI_CMCWR() %></td>
        </tr>
        <tr>
            <th>설치년도</th>
            <td><%=wifiInfoDetail.getX_SWIFI_CNSTC_YEAR() %></td>
        </tr>
        <tr>
            <th>실내외구분</th>
            <td><%=wifiInfoDetail.getX_SWIFI_INOUT_DOOR() %></td>
        </tr>
        <tr>
            <th>WIFI접속환경</th>
            <td><%=wifiInfoDetail.getX_SWIFI_REMARS3() %></td>
        </tr>
        <tr>
            <th>X좌표</th>
            <td><%=wifiInfoDetail.getLAT() %></td>
        </tr>
        <tr>
            <th>Y좌표</th>
            <td><%=wifiInfoDetail.getLNT() %></td>
        </tr>
        <tr>
            <th>작업일자</th>
            <td><%=wifiInfoDetail.getWORK_DTTM() %></td>
        </tr>

    </table>
</body>
</html>
<%--tr { display: block; float: left; }--%>
<%--th, td { display: block; }--%>
<%--#customers tr { display: block; float: left; }--%>
<%--#customers th, td { display: block; }--%>