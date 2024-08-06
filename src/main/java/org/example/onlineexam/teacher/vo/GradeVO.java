package org.example.onlineexam.teacher.vo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GradeVO {
    private Integer s_no;
    private String s_name;
    private int totalScore;
}//end GradeVO