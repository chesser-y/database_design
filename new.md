```mysql
CREATE TABLE course (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '课程ID',
    name VARCHAR(30) NOT NULL UNIQUE COMMENT '课程名称',
    teacher_id INT UNSIGNED UNIQUE COMMENT '授课教师ID',
    begin_time DATE NOT NULL COMMENT '开课时间',
    end_time DATE NOT NULL COMMENT '结课时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

CONSTRAINT fk_course_teacher FOREIGN KEY (teacher_id) REFERENCES emp(id)

) COMMENT='课程表';
```

老师 课程一一对应；

```mysql
CREATE TABLE score (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    student_id INT UNSIGNED NOT NULL,
    course_id INT UNSIGNED NOT NULL,
    score DECIMAL(5,2) NOT NULL CHECK (score >= 0 AND score <= 100),
    exam_time DATE NOT NULL,

create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

CONSTRAINT fk_score_student FOREIGN KEY (student_id) REFERENCES stu(id),
CONSTRAINT fk_score_course FOREIGN KEY (course_id) REFERENCES course(id),
UNIQUE (student_id, course_id) -- 一个学生每门课程只记录一次成绩

) COMMENT='学生成绩表';
```



```mysql
CREATE TABLE attendance
(
    id                INT UNSIGNED PRIMARY KEY AUTO_INCREMENT 	COMMENT '主键ID',
    student_id        INT UNSIGNED NOT NULL COMMENT '学生ID，外键',
    course_id         INT UNSIGNED NOT NULL COMMENT '课程ID，外键',
    absence_count     INT DEFAULT 0 COMMENT '该学生在该课程的缺勤次数',
    create_time       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    

CONSTRAINT fk_attendance_student FOREIGN KEY (student_id) REFERENCES stu (id),
CONSTRAINT fk_attendance_course FOREIGN KEY (course_id) REFERENCES course (id),

UNIQUE KEY unique_student_course (student_id, course_id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='考勤表，记录学生在课程中的缺勤次数';
```





数据统计管理：

员工信息统计改为班级信息统计； 班级 | 班级总人数 | 班级平均学分绩 | 班级男女比

​	分页查询条件和班级管理一样；

​	班级总人数：使用分组查询； group by class_name having class_name = ''

​	班级平均学分绩：使用连接查询；班级 学院 成绩 表连接然后求平均；

​	班级男女比：使用嵌套查询；查询班级里的男生人数 在查询班级的女性人数，然后求比例；



课程成绩查询：学生 | 课程 | 成绩 | 考勤

​	创建视图，



**课程成绩查询视图**

```sql
CREATE VIEW course_score_view AS
SELECT 
    stu.student_name AS 'student_name',
    c.name AS 'class_name',
    s.score AS 'score',
    a.absence_count AS 'absence_count'
FROM 
    stu
JOIN 
    score s ON stu.id = s.student_id
JOIN 
    course c ON s.course_id = c.id
LEFT JOIN 
    attendance a ON stu.id = a.student_id AND s.course_id = a.course_id
ORDER BY 
    c.name, stu.student_name;

```

**班级信息统计视图**

```sql
CREATE VIEW class_statistics_view AS
SELECT 
    c.class_name AS 'class_name',
    COUNT(s.id) AS 'total_num',
    AVG(sc.score) AS 'avg_score',
    CONCAT(
        ROUND((SELECT COUNT(*) FROM stu s2 WHERE s2.class_name = c.class_name AND s2.gender = 1) * 100 / COUNT(s.id), 2), 
        ':', 
        ROUND((SELECT COUNT(*) FROM stu s2 WHERE s2.class_name = c.class_name AND s2.gender = 0) * 100 / COUNT(s.id), 2)
    ) AS 'male / female'
FROM 
    cls c
JOIN 
    stu s ON c.class_name = s.class_name
LEFT JOIN 
    score sc ON s.id = sc.student_id
GROUP BY 
    c.class_name
ORDER BY 
    c.class_name;

```

