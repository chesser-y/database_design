package edu.hitwh.homework.controller;

import edu.hitwh.homework.pojo.Attendance;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Result;
import edu.hitwh.homework.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/attendance")
@RestController
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    // 查询全部考勤记录
    @GetMapping("/all")
    public Result listAll() {
        log.info("查询全部考勤记录");
        List<Attendance> attendanceList = attendanceService.listAll();
        return Result.success(attendanceList);
    }

    // 分页查询，支持按学生ID和课程ID查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       Integer studentId,
                       Integer courseId) {

        log.info("分页查询考勤：(page, pageSize, studentId, courseId) = ({},{},{},{})",
                page, pageSize, studentId, courseId);
        PageBean pageBean = attendanceService.page(page, pageSize, studentId, courseId);
        return Result.success(pageBean);
    }

    // 新增考勤记录
    @PostMapping
    public Result save(@RequestBody Attendance attendance) {
        log.info("新增考勤记录并保存: {}", attendance.toString());
        attendanceService.save(attendance);
        return Result.success();
    }

    // 删除考勤记录
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除考勤记录");
        attendanceService.delete(ids);
        return Result.success();
    }

    // 根据ID查询考勤记录
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Attendance attendance = attendanceService.getById(id);
        return Result.success(attendance);
    }

    // 更新考勤记录
    @PutMapping
    public Result update(@RequestBody Attendance attendance) {
        log.info("更新考勤记录: {}", attendance.toString());
        attendanceService.update(attendance);
        return Result.success();
    }

    // 批量导入考勤记录
    @PostMapping("/batch")
    public Result batchImport(@RequestBody List<Attendance> attendances) {
        log.info("批量导入考勤记录，共{}条", attendances.size());
        attendanceService.batchImport(attendances);
        return Result.success();
    }
}
