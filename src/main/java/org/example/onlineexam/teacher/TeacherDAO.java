package org.example.onlineexam.teacher;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.util.Optional;

@Log4j2
public enum TeacherDAO {
    INSTANCE;

    public Optional<TeacherVO> get(String id, String pw) throws Exception {
        String query ="""
                select * from tbl_teacher
                                where t_id = 'aa'
                                and t_pw = '11'
                                and
                                    del_flag = false;
                """;


        return null;




    }
}
