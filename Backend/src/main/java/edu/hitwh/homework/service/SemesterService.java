package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.Semester;
import edu.hitwh.homework.pojo.PageBean;

import java.util.List;

public interface SemesterService {
    /**
     * 查询全部学期数据
     * @return 学期列表
     */
    List<Semester> listAll();

    /**
     * 分页查询学期
     * @param page 起始页码
     * @param pageSize 每页获取的数据量
     * @param name 学期名称 可模糊查询
     * @return 分页结果
     */
    PageBean page(Integer page, Integer pageSize, String name);

    /**
     * 新增学期
     * @param semester 学期对象
     */
    void save(Semester semester);

    /**
     * 根据学期ID删除
     * @param ids 学期ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询学期，实现回显
     * @param id 要查询的学期ID
     * @return 学期对象
     */
    Semester getById(Integer id);

    /**
     * 更新学期数据
     * @param semester 学期封装对象
     */
    void update(Semester semester);

    /**
     * 保存学期并关联课程
     * @param semester 学期对象
     */
    void saveWithCourses(Semester semester);

    /**
     * 更新学期并更新关联课程
     * @param semester 学期对象
     */
    void updateWithCourses(Semester semester);
}