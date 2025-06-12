package edu.hitwh.homework.controller;

import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Result;
import edu.hitwh.homework.pojo.Score;
import edu.hitwh.homework.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/score")
@RestController
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    // 分页查询，支持按学生ID、课程ID和考试时间查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       Integer studentId,
                       Integer courseId,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate examTime) {

        log.info("分页查询成绩：(page, pageSize, studentId, courseId, examTime) = ({},{},{},{},{})",
                page, pageSize, studentId, courseId, examTime);
        PageBean pageBean = scoreService.page(page, pageSize, studentId, courseId, examTime);
        return Result.success(pageBean);
    }

    // 新增成绩
    @PostMapping
    public Result save(@RequestBody Score score) {
        log.info("新增成绩并保存: {}", score.toString());
        scoreService.save(score);
        return Result.success();
    }

    // 删除成绩
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除成绩");
        scoreService.delete(ids);
        return Result.success();
    }

    // 根据ID查询成绩
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Score score = scoreService.getById(id);
        return Result.success(score);
    }

    // 更新成绩信息
    @PutMapping
    public Result update(@RequestBody Score score) {
        log.info("更新成绩信息: {}", score.toString());
        scoreService.update(score);
        return Result.success();
    }

    // 批量导入成绩
    @PostMapping("/batch")
    public Result batchImport(@RequestBody List<Score> scores) {
        log.info("批量导入成绩，共{}条", scores.size());
        scoreService.batchImport(scores);
        return Result.success();
    }
}
