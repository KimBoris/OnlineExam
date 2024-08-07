package org.example.onlineexam;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizVO {

    private Integer qno;

    private String question;

    private int answer;

    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String op5;


}
