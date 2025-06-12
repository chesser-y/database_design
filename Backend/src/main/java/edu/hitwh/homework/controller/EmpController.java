package edu.hitwh.homework.controller;

import edu.hitwh.homework.pojo.Emp;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Result;
import edu.hitwh.homework.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    //查询全部员工
    @GetMapping("/all")
    public Result listAll() {
        log.info("查询全部员工");
        List<Emp> empList = (List<Emp>) empService.listAll();
        return Result.success(empList);
    }

    //条件 分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yy-MM-dd") LocalDate end) {
        log.info("分页查询：(page, pageSize, name, gender, begin, end) = ({},{},{},{},{},{})",
                page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }
    // 删除
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除员工");
        empService.delete(ids);
        return Result.success();
    }
    //新增
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工并保存: {}", emp.toString());
        empService.save(emp);
        return Result.success();
    }
    //回显
    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable Integer id) {
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }
    //更新
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }
}


