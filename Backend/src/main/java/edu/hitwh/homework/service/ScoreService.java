package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Score;

import java.time.LocalDate;
import java.util.List;

public interface ScoreService {
    /**
     * 分页查询成绩
     * @param page 起始页码
     * @param pageSize 每页获取的数据量
     * @param studentId 学生ID 精确查询
     * @param courseId 课程ID 精确查询
     * @param examTime 考试时间 精确查询
     * @return 分页结果
     */
    PageBean page(Integer page, Integer pageSize, Integer studentId, Integer courseId, LocalDate examTime);

    /**
     * 新增成绩
     * @param score 成绩对象
     */
    void save(Score score);

    /**
     * 根据成绩ID删除
     * @param ids 成绩ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询成绩，实现回显
     * @param id 要查询的成绩ID
     * @return 成绩对象
     */
    Score getById(Integer id);

    /**
     * 更新成绩数据
     * @param score 成绩封装对象
     */
    void update(Score score);

    /**
     * 批量导入成绩
     * @param scores 成绩列表
     */
    void batchImport(List<Score> scores);
}
