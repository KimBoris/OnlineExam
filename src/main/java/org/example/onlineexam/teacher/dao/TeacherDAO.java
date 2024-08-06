package org.example.onlineexam.teacher.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.ConnectionUtil;
import org.example.onlineexam.teacher.vo.ExamVO;
import org.example.onlineexam.teacher.vo.GradeVO;
import org.example.onlineexam.teacher.vo.TeacherVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
        log.info("");

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
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

    public List<ExamVO> getExam() throws Exception {

        String query = """
                select e_no, e_name, t_name from tbl_exam e
                inner join tbl_teacher t
                on e.t_no = t.t_no
               """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<ExamVO> examList = new ArrayList<>();

        while (rs.next()) {
            ExamVO exam = ExamVO.builder()
                    .e_no(rs.getInt("e_no"))
                    .e_name(rs.getString("e_name"))
                    .t_name(rs.getString("t_name"))
                    .build();
            examList.add(exam);
        }//end while

        return examList;
    }

    public List<GradeVO> getGrade(Integer eno) throws Exception {
        String query = """
                select
                    s.s_no,
                    s.s_name,
                    SUM(CASE WHEN q.q_right = r.r_input THEN 1 ELSE 0 END) AS total_score
                from tbl_student s
                inner join tbl_result r on s.s_no = r.s_no
                inner join tbl_question q on r.q_no = q.q_no
                where q.e_no = ?
                group by s.s_no, s.s_name
            """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, eno);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<GradeVO> gradeList = new ArrayList<>();

        while (rs.next()) {
            GradeVO grade = GradeVO.builder()
                    .s_no(rs.getInt("s_no"))
                    .s_name(rs.getString("s_name"))
                    .totalScore(rs.getInt("total_score"))
                    .build();
            gradeList.add(grade);
        }

        return gradeList;
    }
}
