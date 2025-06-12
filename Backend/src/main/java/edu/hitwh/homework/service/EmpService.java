package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.Emp;
import edu.hitwh.homework.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    /**
     * 分页查询
     *
     * @param page     起始页 : 起始序码是 (page - 1) * pageSize
     * @param pageSize 分页数据条数
     * @param name 查询姓名 -模糊查询
     * @param gender 性别 - 精切查询
     * @param begin 起始日期 - 精确查询 范围
     * @param end 结束日期 - 范围
     * @return 页面 {总页码数, emp序列}
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 根据ids删除员工
     * @param ids 员工的id集合,可以批量删除
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp 员工信息，需要补全其他字段信息
     */
    void save(Emp emp);

    /**
     * 通过id获取员工信息
     * @param id 员工id
     * @return 放回员工对象 emp
     */
    Emp getById(Integer id);

    /**
     * 更新员工信息
     * @param emp 新员工信息对象
     */
    void update(Emp emp);

    /**
     * 登录
     * @param emp 登录json 封装的用户信息
     * @return
     */
    Emp login(Emp emp);

    /**
     * 查询全部员工数据
     * @return
     */
    List<Emp> listAll();
}
