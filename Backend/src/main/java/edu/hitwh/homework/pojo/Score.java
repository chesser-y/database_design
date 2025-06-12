package edu.hitwh.homework.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 学生成绩实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private Integer id;          // 主键ID
    private Integer studentId;   // 学生ID
    private Integer courseId;    // 课程ID
    private BigDecimal score;    // 分数(5,2)
    private LocalDate examTime;  // 考试时间
    private LocalDateTime createTime;  // 创建时间
    private LocalDateTime updateTime;  // 修改时间
}
