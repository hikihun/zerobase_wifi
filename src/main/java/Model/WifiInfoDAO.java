package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WifiInfoDAO {

    Connection conn = null;
    PreparedStatement pstmt = null;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + "C:/Users/skh11/Desktop/wifi.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void disconnect() {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int addWifiInfo(WifiInfoVO wifiInfo) {
        connect();
        String sql = "insert into wifi_info (X_SWIFI_MGR_NO," +
                "X_SWIFI_WRDOFC," +
                "X_SWIFI_MAIN_NM," +
                "X_SWIFI_ADRES1," +
                "X_SWIFI_ADRES2," +
                "X_SWIFI_INSTL_FLOOR," +
                "X_SWIFI_INSTL_TY," +
                "X_SWIFI_INSTL_MBY," +
                "X_SWIFI_SVC_SE," +
                "X_SWIFI_CMCWR," +
                "X_SWIFI_CNSTC_YEAR," +
                "X_SWIFI_INOUT_DOOR," +
                "X_SWIFI_REMARS3," +
                "LAT," +
                "LNT," +
                "WORK_DTTM) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, wifiInfo.getX_SWIFI_MGR_NO());
            pstmt.setString(2, wifiInfo.getX_SWIFI_WRDOFC());
            pstmt.setString(3, wifiInfo.getX_SWIFI_MAIN_NM());
            pstmt.setString(4, wifiInfo.getX_SWIFI_ADRES1());
            pstmt.setString(5, wifiInfo.getX_SWIFI_ADRES2());
            pstmt.setString(6, wifiInfo.getX_SWIFI_INSTL_FLOOR());
            pstmt.setString(7, wifiInfo.getX_SWIFI_INSTL_TY());
            pstmt.setString(8, wifiInfo.getX_SWIFI_INSTL_MBY());
            pstmt.setString(9, wifiInfo.getX_SWIFI_SVC_SE());
            pstmt.setString(10, wifiInfo.getX_SWIFI_CMCWR());
            pstmt.setString(11, wifiInfo.getX_SWIFI_CNSTC_YEAR());
            pstmt.setString(12, wifiInfo.getX_SWIFI_INOUT_DOOR());
            pstmt.setString(13, wifiInfo.getX_SWIFI_REMARS3());
            pstmt.setString(14, wifiInfo.getLNT());
            pstmt.setString(15, wifiInfo.getLAT());
            pstmt.setString(16, wifiInfo.getWORK_DTTM());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        } finally {
            disconnect();
        }
        return 1;
    }

    public List<WifiInfoVO> getWifiInfoList(String lat, String lnt) {
        connect();
//        System.out.println(lat);
//        System.out.println(lnt);
        List<WifiInfoVO> list = new ArrayList<>();
        String sql = "SELECT *,round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)-radians(?))+sin(radians(?))*sin(radians(LAT))),4) AS distance "
            + "FROM wifi_info "
            + "order by distance "
            + " limit 0, 20";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lat);
            pstmt.setString(2, lnt);
            pstmt.setString(3, lat);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                WifiInfoVO wifiInfo = new WifiInfoVO();
                wifiInfo.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifiInfo.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifiInfo.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifiInfo.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifiInfo.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifiInfo.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifiInfo.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifiInfo.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifiInfo.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifiInfo.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifiInfo.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                wifiInfo.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifiInfo.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifiInfo.setLAT(rs.getString("LAT"));
                wifiInfo.setLNT(rs.getString("LNT"));
                wifiInfo.setWORK_DTTM(rs.getString("WORK_DTTM"));
                wifiInfo.setDistance(rs.getString("distance"));
                list.add(wifiInfo);
//                System.out.println(wifiInfo);
            }
            rs.close();
        }catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            disconnect();
        }
        return list;
    }

    public WifiInfoVO getWifiInfoDetail(String id, String lat, String lnt) {
        connect();
        String sql = "select *, round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)-radians(?))+sin(radians(?))*sin(radians(LAT))),4) AS distance " +
                "from wifi_info where X_SWIFI_MGR_NO = ?";
        WifiInfoVO detail = new WifiInfoVO();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lat);
            pstmt.setString(2, lnt);
            pstmt.setString(3, lat);
            pstmt.setString(4, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                detail.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                detail.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                detail.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                detail.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                detail.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                detail.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                detail.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                detail.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                detail.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                detail.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                detail.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                detail.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                detail.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                detail.setLAT(rs.getString("LAT"));
                detail.setLNT(rs.getString("LNT"));
                detail.setWORK_DTTM(rs.getString("WORK_DTTM"));
                detail.setDistance(rs.getString("distance"));

            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            disconnect();
        }
            return detail;
    }

}
