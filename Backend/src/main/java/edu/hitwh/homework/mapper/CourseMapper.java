package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CourseMapper {

    /**
     * 分页条件查询
     * @param name 课程名称
     * @param teacherId 教师ID
     * @param begin 结课时间范围开始
     * @param end 结课时间范围结束
     * @return 课程列表
     */
    List<Course> list(String name, Integer teacherId, LocalDate begin, LocalDate end);

    /**
     * 新增课程
     * @param course 课程对象
     */
    void insert(Course course);

    /**
     * 批量删除课程
     * @param ids 课程ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询课程
     * @param id 课程ID
     * @return 课程对象
     */
    Course getById(Integer id);

    /**
     * 更新课程信息
     * @param course 课程对象
     */
    void update(Course course);

    /**
     * 查询所有课程
     * @return 所有课程列表
     */
    @Select("select * from course")
    List<Course> listAll();
}
