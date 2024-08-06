package org.example.onlineexam.teacher.dao;

import lombok.Cleanup;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.ConncetionUtil;
import org.example.onlineexam.teacher.vo.QuestionVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@ToString
public enum QuestionDAO {
    INSTANCE;

    QuestionDAO(){}

    public int insertExam(String e_name, List<QuestionVO> voList) throws SQLException {

        // Exam 테이블 INSERT SQL문
        String sql0 = """
                INSERT INTO
                    tbl_exam
                    (t_no, e_name)
                VALUES (?, ?)
                """;

        @Cleanup Connection con = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql0);

        Integer t_no = 2;
        ps.setInt(1, t_no);
        ps.setString(2, e_name);

        int exam_check = ps.executeUpdate();

        // 예외처리 - 정상적으로 들어가지 않았을경우
        if(exam_check != 1){}

        ps.close();

        String query0 = "SELECT e_no FROM tbl_exam WHERE e_name = ? ORDER BY e_no DESC";
        ps = con.prepareStatement(query0);
        ps.setString(1, e_name);

        @Cleanup ResultSet rs = ps.executeQuery();
        rs.next();

        Integer e_no = rs.getInt("e_no");

        String sql1 = """
                INSERT INTO
                    tbl_question(
                    e_no,
                    q_num,
                    q_view,
                    q_answer1,
                    q_answer2,
                    q_answer3,
                    q_answer4,
                    q_answer5,
                    q_right
                VALUES
                    (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        ps = con.prepareStatement(sql1);

        for (QuestionVO vo : voList){
            ps.setInt(1, e_no);
            ps.setInt(2, vo.getQ_num());
            ps.setString(3, vo.getQ_view());
            ps.setString(4, vo.getQ_answer1());
            ps.setString(5, vo.getQ_answer2());
            ps.setString(6, vo.getQ_answer3());
            ps.setString(7, vo.getQ_answer4());
            ps.setString(8, vo.getQ_answer5());
            ps.setInt(9, vo.getQ_right());
            ps.executeUpdate();
        }

        return 0;
    }


}
