package org.example.onlineexam.teacher.vo;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionVO {

    private Integer q_no;

    private Integer e_no;

    private int q_num;

    private String q_view;

    private String q_answer1;
    private String q_answer2;
    private String q_answer3;
    private String q_answer4;
    private String q_answer5;

    private int q_right;

}
