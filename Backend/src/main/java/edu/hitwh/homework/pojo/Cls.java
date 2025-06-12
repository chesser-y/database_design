package edu.hitwh.homework.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cls {
    private Integer id; // 主键ID
    private String className; // 班级名称
    private String classRoom; // 班级教室
    private LocalDate begin; // 开课时间
    private LocalDate end; // 结课时间
    private Integer classTeacherId; // 班主任ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}
