package edu.hitwh.homework.controller;

import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Result;
import edu.hitwh.homework.service.CSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/cs")
@RestController
public class CSController {
    @Autowired
    private CSService csService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String studentName,
                       String className) {
        log.info("课程成绩查询: page={}, pageSize={}, studentName={}, className={}",
                page, pageSize, studentName, className);
        PageBean pageBean = csService.page(page, pageSize, studentName, className);
        return Result.success(pageBean);
    }
}
