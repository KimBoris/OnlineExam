package org.example.onlineexam.student.dao;

import jakarta.servlet.http.HttpSession;
import lombok.Cleanup;
import org.example.onlineexam.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public enum ResultDAO {
    INSTANCE;

    public boolean resultCheck(Integer s_no, Integer q_no) throws Exception{

        String query = """
                SELECT *
                FROM tbl_result
                WHERE s_no = ? AND q_no = ?
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, s_no);
        ps.setInt(2, q_no);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return true;
        }
        return false;
    }

    public void insertResult(Integer s_no, Integer q_no, Integer r_input) throws Exception {
        String sql = """
                INSERT INTO tbl_result
                    (s_no,q_no,r_input)
                VALUES
                    (?,?,?)
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, s_no);
        ps.setInt(2, q_no);
        ps.setInt(3, r_input);

        ps.executeUpdate();
    }
}
