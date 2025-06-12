package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.Cls;
import edu.hitwh.homework.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface ClsService {

    /**
     * 查询全部班级数据
     * @return
     */
    List<Cls> listAll();
    /**
     * 分页查询
     * @param page 起始页码
     * @param pageSize 每页获取的数据
     * @param className 班级名称 可模糊查询
     * @param begin 课程 起始时间
     * @param end 结束时间
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String className, LocalDate begin, LocalDate end);

    /**
     * 新增班级 POST
     * @param cls 班级对象
     */
    void save(Cls cls);

    /**
     * 根据班级 id 删除
     * @param ids 班级 id 列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询 ，实现回显
     * @param id 要查询的班级 id
     * @return
     */
    Cls getById(Integer id);

    /**
     * 更新数据
     * @param cls 班级封装对象
     */
    void update(Cls cls);
}
