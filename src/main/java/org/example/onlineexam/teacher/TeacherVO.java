package org.example.onlineexam.teacher;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherVO {
    private Integer t_no;
    private String t_name;
    private String t_id;
    private String t_pw;
    private Timestamp create_date;
    private Timestamp mod_date;
    private boolean del_flag;
}
