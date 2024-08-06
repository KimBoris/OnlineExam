package org.example.onlineexam.teacher.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.ConncetionUtil;
import org.example.onlineexam.teacher.vo.TeacherVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Log4j2
public enum TeacherDAO {
    INSTANCE;

    public Optional<TeacherVO> get(String id, String pw) throws Exception {
        String query = """
                select * from tbl_teacher
                                where t_id = ?
                                and t_pw = ?
                                and
                                    del_flag = false;
                """;
        log.info(query);

        @Cleanup Connection con = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, id );
        ps.setString(2, pw );
        @Cleanup ResultSet rs = ps.executeQuery();


        if (!rs.next()) {
            return Optional.empty();
        }

        TeacherVO vo = TeacherVO.builder()
                .t_no(rs.getInt("t_no"))
                .t_name(rs.getString("t_name"))
                .t_id(rs.getString("t_id"))
                .t_pw(rs.getString("t_pw"))
                .create_date(rs.getTimestamp("create_date"))
                .mod_date(rs.getTimestamp("mod_date"))
                .del_flag(rs.getBoolean("del_flag"))
                .build();

        return Optional.of(vo);
    }
}
