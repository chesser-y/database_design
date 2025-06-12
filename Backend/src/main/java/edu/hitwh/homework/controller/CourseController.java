package edu.hitwh.homework.controller;

import edu.hitwh.homework.pojo.Course;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Result;
import edu.hitwh.homework.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/course")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public Result listAll() {
        log.info("查询全部课程");
        List<Course> courseList = courseService.listAll();
        return Result.success(courseList);
    }

    // 分页查询，支持按课程名称、教师ID和结课时间范围查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Integer teacherId,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("分页查询课程：(page, pageSize, name, teacherId, begin, end) = ({},{},{},{},{},{})",
                page, pageSize, name, teacherId, begin, end);
        PageBean pageBean = courseService.page(page, pageSize, name, teacherId, begin, end);
        return Result.success(pageBean);
    }

    // 新增课程
    @PostMapping
    public Result save(@RequestBody Course course) {
        log.info("新增课程并保存: {}", course.toString());
        courseService.save(course);
        return Result.success();
    }

    // 删除课程
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除课程");
        courseService.delete(ids);
        return Result.success();
    }

    // 根据ID查询课程
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Course course = courseService.getById(id);
        return Result.success(course);
    }

    // 更新课程信息
    @PutMapping
    public Result update(@RequestBody Course course) {
        courseService.update(course);
        return Result.success();
    }
}
