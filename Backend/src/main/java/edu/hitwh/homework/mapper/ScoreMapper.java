package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ScoreMapper {
    /**
     * 分页条件查询
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @param examTime 考试时间
     * @return 成绩列表
     */
    List<Score> list(Integer studentId, Integer courseId, LocalDate examTime);

    /**
     * 新增成绩
     * @param score 成绩对象
     */
    void insert(Score score);

    /**
     * 批量删除成绩
     * @param ids 成绩ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询成绩
     * @param id 成绩ID
     * @return 成绩对象
     */
    Score getById(Integer id);

    /**
     * 更新成绩信息
     * @param score 成绩对象
     */
    void update(Score score);

    /**
     * 批量导入成绩
     * @param scores 成绩列表
     */
    void batchImport(List<Score> scores);

    /**
     * 查询所有成绩
     * @return 所有成绩列表
     */
    @Select("select * from score")
    List<Score> listAll();
}
