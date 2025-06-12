package edu.hitwh.homework.controller;

import edu.hitwh.homework.pojo.Semester;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Result;
import edu.hitwh.homework.service.SemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/semester")
@RestController
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @GetMapping("/all")
    public Result listAll() {
        log.info("查询全部学期");
        List<Semester> semesterList = semesterService.listAll();
        return Result.success(semesterList);
    }

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name) {

        log.info("分页查询学期：(page, pageSize, name) = ({},{},{})",
                page, pageSize, name);
        PageBean pageBean = semesterService.page(page, pageSize, name);
        return Result.success(pageBean);
    }

    @PostMapping
    public Result save(@RequestBody Semester semester) {
        log.info("新增学期并保存: {}", semester.toString());
        semesterService.saveWithCourses(semester);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除学期");
        semesterService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Semester semester = semesterService.getById(id);
        return Result.success(semester);
    }

    @PutMapping
    public Result update(@RequestBody Semester semester) {
        log.info("更新学期信息: {}", semester.toString());
        semesterService.updateWithCourses(semester);
        return Result.success();
    }
}