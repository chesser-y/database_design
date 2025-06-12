package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    /**
     * 分页条件查询
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 考勤列表
     */
    List<Attendance> list(Integer studentId, Integer courseId);

    /**
     * 新增考勤记录
     * @param attendance 考勤对象
     */
    void insert(Attendance attendance);

    /**
     * 批量删除考勤记录
     * @param ids 考勤ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询考勤记录
     * @param id 考勤ID
     * @return 考勤对象
     */
    Attendance getById(Integer id);

    /**
     * 更新考勤信息
     * @param attendance 考勤对象
     */
    void update(Attendance attendance);

    /**
     * 批量导入考勤记录
     * @param attendances 考勤列表
     */
    void batchImport(List<Attendance> attendances);

    /**
     * 查询所有考勤记录
     * @return 所有考勤列表
     */
    @Select("select * from attendance")
    List<Attendance> listAll();
}
