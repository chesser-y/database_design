package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.Material;
import edu.hitwh.homework.pojo.PageBean;

import java.util.List;

public interface MaterialService {
    /**
     * 查询全部教材数据
     * @return 教材列表
     */
    List<Material> listAll();

    /**
     * 分页查询教材
     * @param page 起始页码
     * @param pageSize 每页获取的数据量
     * @param name 教材名称 可模糊查询
     * @return 分页结果
     */
    PageBean page(Integer page, Integer pageSize, String name);

    /**
     * 新增教材
     * @param material 教材对象
     */
    void save(Material material);

    /**
     * 根据教材ID删除
     * @param ids 教材ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询教材，实现回显
     * @param id 要查询的教材ID
     * @return 教材对象
     */
    Material getById(Integer id);

    /**
     * 更新教材数据
     * @param material 教材封装对象
     */
    void update(Material material);

    /**
     * 根据课程名称查询所需教材
     * @param courseName 课程名称
     * @return 教材列表
     */
    List<Material> listByCourseName(String courseName);

    /**
     * 保存教材并关联课程
     * @param material 教材对象
     */
    void saveWithCourses(Material material);

    /**
     * 更新教材并更新关联课程
     * @param material 教材对象
     */
    void updateWithCourses(Material material);
}

