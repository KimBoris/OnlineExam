package org.example.onlineexam.student;


import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class StudentVO {

    private Integer s_no;
    private String s_name;
    private String s_id;
    private String s_pw;
    private Timestamp create_date;
    private Timestamp mod_date;
    private boolean del_flag;
}
