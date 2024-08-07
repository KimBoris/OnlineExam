package org.example.onlineexam.teacher.vo;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExamVO {

    private Integer e_no;
    private String e_name;

    private Integer t_no;

    private Timestamp create_date;
    private Timestamp mod_date;

    private boolean del_flag;
}//end ExamVO
