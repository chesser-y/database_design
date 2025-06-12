package edu.hitwh.homework.controller;

import edu.hitwh.homework.pojo.Material;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Result;
import edu.hitwh.homework.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/material")
@RestController
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping("/all")
    public Result listAll() {
        log.info("查询全部教材");
        List<Material> materialList = materialService.listAll();
        return Result.success(materialList);
    }

    // 分页查询，支持按教材名称查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name) {

        log.info("分页查询教材：(page, pageSize, name) = ({},{},{})",
                page, pageSize, name);
        PageBean pageBean = materialService.page(page, pageSize, name);
        return Result.success(pageBean);
    }

    /**
     * 根据课程名称查询所需教材
     * @param courseName 课程名称
     * @return 教材列表
     */
    @GetMapping("/course/{courseName}")
    public Result listByCourseName(@PathVariable String courseName) {
        log.info("根据课程名称查询所需教材: {}", courseName);
        List<Material> materialList = materialService.listByCourseName(courseName);
        return Result.success(materialList);
    }


    // 删除教材
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除教材");
        materialService.delete(ids);
        return Result.success();
    }

    // 根据ID查询教材
    @GetMapping("/id/{id}")
    public Result getById(@PathVariable Integer id) {
        Material material = materialService.getById(id);
        return Result.success(material);
    }


    /**
     * 新增教材并关联课程
     * @param material 教材对象
     */
    @PostMapping
    public Result save(@RequestBody Material material) {
        log.info("新增教材并关联课程: {}", material.toString());
        materialService.saveWithCourses(material);
        return Result.success();
    }

    /**
     * 更新教材并更新关联课程
     * @param material 教材对象
     */
    @PutMapping
    public Result update(@RequestBody Material material) {
        log.info("更新教材并更新关联课程: {}", material.toString());
        materialService.updateWithCourses(material);
        return Result.success();
    }

}