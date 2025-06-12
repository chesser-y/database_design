package edu.hitwh.homework.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 课程实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer id;           // 课程ID
    private String name;          // 课程名称
    private Integer teacherId;    // 授课教师ID
    private LocalDate beginTime;  // 开课时间
    private LocalDate endTime;    // 结课时间
    private LocalDateTime createTime;  // 创建时间
    private LocalDateTime updateTime;  // 修改时间
}
