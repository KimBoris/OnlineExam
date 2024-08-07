package org.example.onlineexam.student.dao;

import jakarta.servlet.http.HttpSession;
import lombok.Cleanup;
import org.example.onlineexam.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public enum ResultDAO {
    INSTANCE;

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
