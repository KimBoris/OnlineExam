package org.example.onlineexam.teacher.vo;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DetailVO {

    private Integer q_no;
    private int q_num;
    private String q_view;
    private int r_input;
    private int q_right;

}
