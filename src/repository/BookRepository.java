package repository;

import domain.BookVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookRepository {
    private ArrayList<BookVO> bookVOList;

    public ArrayList<BookVO> select(String target, int colIdx) {
        // 변수 선언
        Connection con = JDBCConnector.getConnection();
        ResultSet rs;
        PreparedStatement ps;

        bookVOList = new ArrayList<>();

        String[] colNames = {"name", "publish", "author"};


        // Query
        String sql = "SELECT * from book, category WHERE book.category = category.category_id and " + colNames[colIdx] + " like ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, '%'+target+'%');
            rs = ps.executeQuery();

            while (rs.next()) {
                BookVO book = new BookVO(
                        rs.getInt("isbn"),
                        rs.getString("name"),
                        rs.getString("publish"),
                        rs.getString("author"),
                        rs.getInt("price"),
                        rs.getString("category_name")
                );
                bookVOList.add(book);
            }
        } catch (SQLException e) {
            System.out.println("====== ERROR ======\n" + e.getMessage());
            throw new RuntimeException(e);
        }


        // 메모리 정리
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        // ArrayList 반환
        return bookVOList;
    }

    public void insert(BookVO book) {
        Connection con = JDBCConnector.getConnection();
        PreparedStatement ps;

        int category = switch(book.getCategoryName()) {
            case "SF" -> 10;
            case "IT" -> 20;
            case "경제" -> 30;
            case "만화" -> 40;
            case "추리" -> 50;
            default -> 0;
        };

        String sql = "INSERT INTO book VALUES(?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, book.getIsbn());
            ps.setString(2, book.getName());
            ps.setString(3, book.getPublish());
            ps.setString(4, book.getAuthor());
            ps.setInt(5, book.getPrice());
            ps.setInt(6, category);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("====== ERROR ======\n" + e.getMessage());
            throw new RuntimeException(e);
        }

        // 메모리 정리
        try {
            if (con != null) con.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            System.out.println("INSERT Close 문제 발생");
            e.printStackTrace();
        }
    }
}
