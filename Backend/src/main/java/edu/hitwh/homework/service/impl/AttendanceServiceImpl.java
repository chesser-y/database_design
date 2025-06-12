package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.AttendanceMapper;
import edu.hitwh.homework.pojo.Attendance;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public List<Attendance> listAll() {
        return attendanceMapper.listAll();
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, Integer studentId, Integer courseId) {
        // 1. 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 2. 执行分页查询
        List<Attendance> attendanceList = attendanceMapper.list(studentId, courseId);

        // 3. 获取分页信息
        Page p = (Page) attendanceList;
        long total = p.getTotal();
        List result = p.getResult();

        // 4. 封装分页结果
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }

    @Override
    public void save(Attendance attendance) {
        // 设置创建时间和更新时间
        attendance.setCreateTime(LocalDateTime.now());
        attendance.setUpdateTime(LocalDateTime.now());

        // 调用mapper保存数据
        attendanceMapper.insert(attendance);
    }

    @Override
    public void delete(List<Integer> ids) {
        // 批量删除考勤记录
        attendanceMapper.delete(ids);
    }

    @Override
    public Attendance getById(Integer id) {
        // 根据ID查询考勤记录
        return attendanceMapper.getById(id);
    }

    @Override
    public void update(Attendance attendance) {
        // 更新修改时间
        attendance.setUpdateTime(LocalDateTime.now());

        // 调用mapper更新数据
        attendanceMapper.update(attendance);
    }

    @Override
    public void batchImport(List<Attendance> attendances) {
        // 批量导入考勤记录
        for (Attendance attendance : attendances) {
            // 设置创建时间和更新时间
            attendance.setCreateTime(LocalDateTime.now());
            attendance.setUpdateTime(LocalDateTime.now());
        }

        // 调用mapper批量插入
        attendanceMapper.batchImport(attendances);
    }
}
