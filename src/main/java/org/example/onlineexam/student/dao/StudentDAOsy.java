package org.example.onlineexam.student.dao;

import lombok.Cleanup;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.ConnectionUtil;
import org.example.onlineexam.student.vo.StudentVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Log4j2
@ToString
public enum StudentDAOsy {

    INSTANCE;

    StudentDAOsy() {

    }

    public Optional<StudentVO> get(String id, String pw) throws Exception {

        String query = """
                SELECT * FROM tbl_student
                WHERE s_id = ?
                AND s_pw = ?
                AND del_flag = FALSE
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.setString(2, pw);
        @Cleanup ResultSet rs = ps.executeQuery();

        // 쿼리는 문제 없는데 값이 틀린다던지 문제가 생기는 경우
        if( !rs.next() ) {
            return Optional.empty();
        }

        StudentVO studentVO = StudentVO.builder()
                .s_no(rs.getInt("s_no"))
                .s_id(rs.getString("s_id"))
                .s_pw(rs.getString("s_pw"))
                .create_date(rs.getTimestamp("create_date"))
                .mod_date(rs.getTimestamp("mod_date"))
                .del_flag(rs.getBoolean("del_flag"))
                .build();


        return Optional.of(studentVO);
    }
}
