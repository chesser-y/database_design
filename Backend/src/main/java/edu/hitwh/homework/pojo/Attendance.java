package edu.hitwh.homework.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 考勤实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    private Integer id;          // 主键ID
    private Integer studentId;   // 学生ID
    private Integer courseId;    // 课程ID
    private Integer absenceCount; // 缺勤次数
    private LocalDateTime createTime;  // 创建时间
    private LocalDateTime updateTime;  // 修改时间
}
