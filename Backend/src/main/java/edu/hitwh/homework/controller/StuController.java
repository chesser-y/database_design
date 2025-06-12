package edu.hitwh.homework.controller;

import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Result;
import edu.hitwh.homework.pojo.Stu;
import edu.hitwh.homework.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/stu")
@RestController
public class StuController {
    @Autowired
    private StuService stuService;

    //分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String studentName,
                       String highestEducation,
                       String className,
                       String studentNo) {
        log.info("分页查询：(page, pageSize, studentName, highestEducation, className, studentNo) = ({},{},{},{},{},{})",
                page, pageSize, studentName, highestEducation, className, studentNo);
        PageBean pageBean = stuService.page(page, pageSize, studentName, studentNo, highestEducation, className);
        return Result.success(pageBean);
    }

    @GetMapping("/all")
    public Result listAll() {
        log.info("查询全部学生");
        List<Stu> stuList = stuService.listAll();
        return Result.success(stuList);
    }



    //新增学员
    @PostMapping
    public Result save(@RequestBody Stu stu) {
        log.info("新增学员并保存: {}", stu.toString());
        stuService.save(stu);
        return Result.success();
    }

    //删除学员
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除学员");
        stuService.delete(ids);
        return Result.success();
    }

    //回显学员信息
    @GetMapping("/{id}")
    public Result getStuById(@PathVariable Integer id) {
        Stu stu = stuService.getById(id);
        return Result.success(stu);
    }

    //更新学员信息
    @PutMapping
    public Result update(@RequestBody Stu stu) {
        log.info("更新学员信息: {}", stu.toString());
        stuService.update(stu);
        return Result.success();
    }

    //处理学员违纪
//    @PutMapping("/{id}/discipline")
//    public Result handleDiscipline(@PathVariable Integer id, @RequestParam int penaltyScore) {
//        log.info("处理学员违纪：学员ID = {}，增加违纪分数 = {}", id, penaltyScore);
//        stuService.handleDiscipline(id, penaltyScore);
//        return Result.success();
//    }
    @PutMapping("/{id}/discipline")
    public Result handleDiscipline(@PathVariable Integer id,@RequestParam Integer penaltyScore) {
        log.info("处理学员违纪：学员ID = {}，增加违纪分数 = {}", id, penaltyScore);
        stuService.handleDiscipline(id, penaltyScore);
        return Result.success();
    }
}

