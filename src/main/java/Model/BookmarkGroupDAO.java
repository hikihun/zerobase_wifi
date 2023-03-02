package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDAO {
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

    public List<BookmarkGroupVO> getBookmarkGroupList() {
        connect();
        List<BookmarkGroupVO> bookmarkGroupList = new ArrayList<>();
        String sql = "select * from bookmark_group";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookmarkGroupVO bookmark = new BookmarkGroupVO();
                bookmark.setId(rs.getInt("id"));
                bookmark.setName(rs.getString("name"));
                bookmark.setOrd(rs.getInt("ord"));
                bookmark.setRegDate(rs.getString("reg_date"));
                bookmark.setModDate(rs.getString("mod_date"));
                bookmarkGroupList.add(bookmark);
            }
            rs.close();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            disconnect();
        }
        return bookmarkGroupList;
    }

    public int addBookmark(String name, int order) {
        connect();
        String sql = "insert into bookmark_group ('name', 'ord') values (?, ?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, order);
            pstmt.executeUpdate();
        }catch (Exception e) {
            System.out.println(e);
            return 0;
        } finally {
            disconnect();
        }
        return 1;
    }

    public int updateBookmark(String name, int order, int id) {
        connect();
        String sql = "update bookmark_group set name = ?, ord = ? where id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, order);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        }catch (Exception e) {
            System.out.println(e);
            return 0;
        } finally {
            disconnect();
        }
        return 1;
    }

    public int deleteBookmark(int id) {
        connect();
        String sql = "delete from bookmark_group where id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch (Exception e) {
            System.out.println(e);
            return 0;
        } finally {
            disconnect();
        }
        return 1;
    }

}
