package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Stu;

import java.util.List;

public interface StuService {

    /**
     * 分页查询学员信息
     * @param page 起始页码
     * @param pageSize 每页获取的数据
     * @param studentName 学生姓名 可模糊查询
     * @param studentNo 学号 可模糊查询
     * @param highestEducation 最高学历 精确查询
     * @param className 所属班级 精确查询
     * @return 返回分页结果
     */
    PageBean page(Integer page, Integer pageSize, String studentName, String studentNo,
                  String highestEducation, String className);

    /**
     * 新增学员信息
     * @param stu 学员对象
     */
    void save(Stu stu);

    /**
     * 根据学员 id 删除学员信息
     * @param ids 学员 id 列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据学员 id 查询学员信息，实现回显
     * @param id 要查询的学员 id
     * @return 返回学员对象
     */
    Stu getById(Integer id);

    /**
     * 更新学员信息
     * @param stu 学员封装对象
     */
    void update(Stu stu);

    /**
     * 处理学员违纪次数及违纪分数
     *
     * @param id           学员 id
     *                     //     * @param penaltyScore 违纪分数
     * @param penaltyScore
     */
//    void handleDiscipline(Integer id, int penaltyScore);
    void handleDiscipline(Integer id, Integer penaltyScore);

    /**
     * 查询全部学生
     * @return
     */
    List<Stu> listAll();
}
