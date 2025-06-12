package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.ScoreMapper;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Score;
import edu.hitwh.homework.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, Integer studentId, Integer courseId, LocalDate examTime) {
        // 1. 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 2. 执行分页查询
        List<Score> scoreList = scoreMapper.list(studentId, courseId, examTime);

        // 3. 获取分页信息
        Page p = (Page) scoreList;
        long total = p.getTotal();
        List result = p.getResult();

        // 4. 封装分页结果
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }

    @Override
    public void save(Score score) {
        // 设置创建时间和更新时间
        score.setCreateTime(LocalDateTime.now());
        score.setUpdateTime(LocalDateTime.now());

        // 调用mapper保存数据
        scoreMapper.insert(score);
    }

    @Override
    public void delete(List<Integer> ids) {
        // 批量删除成绩
        scoreMapper.delete(ids);
    }

    @Override
    public Score getById(Integer id) {
        // 根据ID查询成绩
        return scoreMapper.getById(id);
    }

    @Override
    public void update(Score score) {
        // 更新修改时间
        score.setUpdateTime(LocalDateTime.now());

        // 调用mapper更新数据
        scoreMapper.update(score);
    }

    @Override
    public void batchImport(List<Score> scores) {
        // 批量导入成绩
        for (Score score : scores) {
            // 设置创建时间和更新时间
            score.setCreateTime(LocalDateTime.now());
            score.setUpdateTime(LocalDateTime.now());
        }

        // 调用mapper批量插入
        scoreMapper.batchImport(scores);
    }
}
