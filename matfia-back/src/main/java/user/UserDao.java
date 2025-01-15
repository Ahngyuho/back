package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }


    public void signup(UserReqDto userReqDto) {

        String sql = "INSERT INTO users (id, password, birth_date, name, email, address, phone, user_type) VALUE (?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userReqDto.getId());
            pstmt.setString(2, userReqDto.getPassword());
            pstmt.setString(3, userReqDto.getBirthDate());
            pstmt.setString(4, userReqDto.getName());
            pstmt.setString(5, userReqDto.getEmail());
            pstmt.setString(6, userReqDto.getAddress());
            pstmt.setString(7, userReqDto.getPhone());
            pstmt.setString(8, userReqDto.getUserType());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectUser(UserReqDto userReqDto) {
        String sql = "SELECT idx FROM users WHERE id = ? AND password = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userReqDto.getId());
            pstmt.setString(2, userReqDto.getPassword());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return rs.getInt("idx");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
