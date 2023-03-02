package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class HistoryDAO {
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


    public ArrayList<HistoryVO> listHistory() {
        connect();
        ArrayList<HistoryVO> list = new ArrayList<>();
        String sql = "select * from history";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                HistoryVO history = new HistoryVO();
                history.setId(rs.getInt("id"));
                history.setLat(rs.getString("lat"));
                history.setLnt(rs.getString("lnt"));
                history.setDate(rs.getString("date"));
                list.add(history);
                System.out.println(history);
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

    public int addHistory(String lat, String lnt) {
            connect();
            String sql = "insert into history (LAT, LNT) values(?,?)";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, lat);
                pstmt.setString(2, lnt);
                pstmt.executeUpdate();
            } catch (Exception e) {
            System.out.println(e);
            return 0;
        } finally {
            disconnect();
        }
        return 1;
    }

    public int deleteHistory(int id) {
        connect();
        String sql = "delete from history where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        } finally {
            disconnect();
        }
        return 1;
    }
}
