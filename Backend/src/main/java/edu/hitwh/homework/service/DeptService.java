package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {

    /**
     * 查询所有部门数据
     * @return 存储Dept对象的集合
     */
    List<Dept> list();

    /**
     * 删除部门信息
     * @param id 根据id删除部门
     */
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept 新增部门的信息
     */
    void add(Dept dept);

    /**
     * 根据id查询部门信息
     * @param id 部门id
     * @return 单个部门对象
     */
    Dept get(Integer id);

    /**
     * 根据id更新部门
     * @param dept
     */
    void update(Dept dept);
}
