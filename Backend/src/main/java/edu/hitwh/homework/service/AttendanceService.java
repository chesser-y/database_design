package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.Attendance;
import edu.hitwh.homework.pojo.PageBean;

import java.util.List;

public interface AttendanceService {

    /**
     * 查询全部考勤记录
     * @return 考勤列表
     */
    List<Attendance> listAll();

    /**
     * 分页查询考勤记录
     * @param page 起始页码
     * @param pageSize 每页获取的数据量
     * @param studentId 学生ID 精确查询
     * @param courseId 课程ID 精确查询
     * @return 分页结果
     */
    PageBean page(Integer page, Integer pageSize, Integer studentId, Integer courseId);

    /**
     * 新增考勤记录
     * @param attendance 考勤对象
     */
    void save(Attendance attendance);

    /**
     * 根据考勤ID删除
     * @param ids 考勤ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询考勤，实现回显
     * @param id 要查询的考勤ID
     * @return 考勤对象
     */
    Attendance getById(Integer id);

    /**
     * 更新考勤数据
     * @param attendance 考勤封装对象
     */
    void update(Attendance attendance);

    /**
     * 批量导入考勤记录
     * @param attendances 考勤列表
     */
    void batchImport(List<Attendance> attendances);
}
