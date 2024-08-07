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

    //문제번호
    private Integer q_num;

    //문제 지문
    private String q_view;

    //보기
    private String q_answer1;
    private String q_answer2;
    private String q_answer3;
    private String q_answer4;
    private String q_answer5;


    //q_right = 문제의 정답
    private Integer q_right;

}
