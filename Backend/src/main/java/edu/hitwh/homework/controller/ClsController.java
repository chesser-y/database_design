package edu.hitwh.homework.controller;

import edu.hitwh.homework.pojo.Cls;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Result;
import edu.hitwh.homework.service.ClsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/cls")
@RestController
public class ClsController {
    @Autowired
    private ClsService clsService;

    @GetMapping("/all")
    public Result listAll() {
        log.info("查询全部员工");
        List<Cls> clsList = clsService.listAll();
        return Result.success(clsList);
    }

    //分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String className,
                       @DateTimeFormat(pattern = "yy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yy-MM-dd") LocalDate end) {

        log.info("分页查询：(page, pageSize, className, begin, end) = ({},{},{},{},{})",
                page, pageSize, className, begin, end);
        PageBean pageBean = clsService.page(page, pageSize, className, begin, end);
        return Result.success(pageBean);
    }
    //新增
    @PostMapping
    public Result save(@RequestBody Cls cls) {
        log.info("新增班级并保存: {}", cls.toString());
        clsService.save(cls);
        return Result.success();
    }
    //删除
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除班级");
        clsService.delete(ids);
        return Result.success();
    }
    //回显
    @GetMapping("/{id}")
    public Result getClsById(@PathVariable Integer id) {
        Cls cls= clsService.getById(id);
        return Result.success(cls);
    }
    @PutMapping
    public Result update(@RequestBody Cls cls) {
        clsService.update(cls);
        return Result.success();
    }


}
