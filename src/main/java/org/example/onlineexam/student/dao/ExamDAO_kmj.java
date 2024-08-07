package org.example.onlineexam.student.dao;

import lombok.Cleanup;
import org.example.onlineexam.common.ConnectionUtil;
import org.example.onlineexam.teacher.vo.ExamVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum ExamDAO_kmj {
    INSTANCE;

    public List<ExamVO> getExam() throws SQLException {

        String query = "SELECT e_no,e_name FROM tbl_exam";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<ExamVO> list = new ArrayList<>();

        while (rs.next()){
            ExamVO vo = ExamVO.builder()
                    .e_no(rs.getInt("e_no"))
                    .e_name(rs.getString("e_name"))
                    .build();

            list.add(vo);
        }

        return list;
    }
}
