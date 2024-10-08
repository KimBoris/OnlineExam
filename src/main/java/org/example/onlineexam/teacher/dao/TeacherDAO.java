package org.example.onlineexam.teacher.dao;

import lombok.Cleanup;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.ConnectionUtil;
import org.example.onlineexam.teacher.vo.DetailVO;
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
@ToString
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

    // 시험 데이터 리스트
    public List<ExamVO> getExam() throws Exception {

        String query = """
            select e_no, e_name, t_name from tbl_exam e
            inner join tbl_teacher t
            on e.t_no = t.t_no
            order by e_no desc
        """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<ExamVO> examList = new ArrayList<>();

        while (rs.next()) {
            ExamVO exam = ExamVO.builder()
                    .e_no(rs.getInt("e_no"))
                    .e_name(rs.getString("e_name"))
                    .build();
            examList.add(exam);
        }//end while

        return examList;
    }

    // 학생별 성적 정보
    public List<GradeVO> getGrade(Integer e_no) throws Exception {
        String query = """
SELECT
s.s_no,s.s_name
FROM
tbl_question q
INNER JOIN tbl_result r ON q.q_no = r.q_no
INNER JOIN tbl_student s ON r.s_no = s.s_no
WHERE
e_no = ?
GROUP BY r.s_no
""";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, e_no);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<GradeVO> gradeList = new ArrayList<>();

        while (rs.next()) {
            GradeVO grade = GradeVO.builder()
                    .s_no(rs.getInt("s_no"))
                    .s_name(rs.getString("s_name"))
                    .build();
            gradeList.add(grade);
        }

        return gradeList;
    }

    public List<DetailVO> getDetail(int eno, int sno) throws Exception {

        String query = """
            SELECT
            q.q_no, q.q_num, q.q_view, r.r_input, q.q_right
            FROM
            tbl_question q
            INNER JOIN tbl_result r ON q.q_no = r.q_no
            WHERE
            e_no = ? AND s_no = ?
        """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, eno);
        ps.setInt(2, sno);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<DetailVO> detailList = new ArrayList<>();

        while (rs.next()) {
            DetailVO detailVO = DetailVO.builder()
                    .q_no(rs.getInt("q_no"))
                    .q_num(rs.getInt("q_num"))
                    .q_view(rs.getString("q_view"))
                    .r_input(rs.getInt("r_input"))
                    .q_right(rs.getInt("q_right"))
                    .build();
            detailList.add(detailVO);
        }

        return detailList;
    }
}