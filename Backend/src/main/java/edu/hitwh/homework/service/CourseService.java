package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.Course;
import edu.hitwh.homework.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {

    /**
     * 查询全部课程数据
     * @return 课程列表
     */
    List<Course> listAll();

    /**
     * 分页查询课程
     * @param page 起始页码
     * @param pageSize 每页获取的数据量
     * @param name 课程名称 可模糊查询
     * @param teacherId 教师ID
     * @param begin 结课时间范围开始
     * @param end 结课时间范围结束
     * @return 分页结果
     */
    PageBean page(Integer page, Integer pageSize, String name, Integer teacherId, LocalDate begin, LocalDate end);

    /**
     * 新增课程
     * @param course 课程对象
     */
    void save(Course course);

    /**
     * 根据课程ID删除
     * @param ids 课程ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询课程，实现回显
     * @param id 要查询的课程ID
     * @return 课程对象
     */
    Course getById(Integer id);

    /**
     * 更新课程数据
     * @param course 课程封装对象
     */
    void update(Course course);
}
