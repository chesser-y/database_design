package edu.hitwh.homework.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stu {
    private Integer id; // 学生自增主键
    private String studentName; // 学生姓名
    private String studentNo; // 学号
    private Short gender; // 性别
    private String phone; // 手机号
    private String highestEducation; // 最高学历
    private String className; // 所属班级
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
    private Integer disciplineCount; // 违纪次数
    private Integer penaltyScore; // 违纪扣分
}
