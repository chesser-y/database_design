package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.Semester;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SemesterMapper {
    /**
     * 分页条件查询
     * @param name 学期名称
     * @return 学期列表
     */
    List<Semester> list(String name);

    /**
     * 新增学期
     * @param semester 学期对象
     */
    void insert(Semester semester);

    /**
     * 批量删除学期
     * @param ids 学期ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询学期
     * @param id 学期ID
     * @return 学期对象
     */
    Semester getById(Integer id);

    /**
     * 更新学期信息
     * @param semester 学期对象
     */
    void update(Semester semester);

    /**
     * 根据学期ID删除所有关联关系
     * @param semesterId 学期ID
     */
    void deleteAllCourseMappings(Integer semesterId);

    /**
     * 添加学期与课程的关联关系
     * @param semesterId 学期ID
     * @param courseId 课程ID
     */
    void insertCourseMapping(@Param("semesterId") Integer semesterId, @Param("courseId") Integer courseId);
}