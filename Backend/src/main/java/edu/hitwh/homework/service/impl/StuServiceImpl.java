package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.StuMapper;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Stu;
import edu.hitwh.homework.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;
    private int PUNISHMENT = 5;
    @Override
    public PageBean page(Integer page, Integer pageSize, String studentName, String studentNo,
                         String highestEducation, String className) {
        PageHelper.startPage(page, pageSize);
        List<Stu> stuList = stuMapper.list(studentName, studentNo, highestEducation, className);
        Page p = (Page) stuList;
        long total = p.getTotal();
        List result = p.getResult();
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }

    @Override
    public void save(Stu stu) {
        stu.setCreateTime(LocalDateTime.now());
        stu.setUpdateTime(LocalDateTime.now());
        stuMapper.insert(stu);
        log.info("新增学员成功: {}", stu);
    }

    @Override
    public void delete(List<Integer> ids) {
        stuMapper.delete(ids);
        log.info("删除学员成功, 学员ID: {}", ids);
    }

    @Override
    public Stu getById(Integer id) {
        return stuMapper.getById(id);
    }

    @Override
    public void update(Stu stu) {
        stu.setUpdateTime(LocalDateTime.now());
        stuMapper.update(stu);
        log.info("更新学员信息成功: {}", stu);
    }

    @Override
//    public void handleDiscipline(Integer id, int penaltyScore) {
//        Stu stu = stuMapper.getById(id);
//        if (stu != null) {
//            // 处理违纪，增加违纪分数
//            stu.setPenaltyScore(stu.getPenaltyScore() + penaltyScore);
//            stuMapper.update(stu);
//            log.info("学员ID: {} 处理违纪，增加违纪分数: {}", id, penaltyScore);
//        } else {
//            log.warn("学员ID: {} 不存在，无法处理违纪", id);
//        }
//    }


    public void handleDiscipline(Integer id, Integer penaltyScore) {
        Stu stu = stuMapper.getById(id);
        if (stu != null) {
            // 处理违纪，增加违纪分数
            stu.setDisciplineCount(stu.getDisciplineCount() + 1);
            stu.setPenaltyScore(stu.getPenaltyScore() + penaltyScore);
            stuMapper.update(stu);
            log.info("学员ID: {} 处理违纪, 增加违纪分数:{}", id, penaltyScore);
        } else {
            log.warn("学员ID: {} 不存在，无法处理违纪", id);
        }
    }

    @Override
    public List<Stu> listAll() {
        return stuMapper.listAll();
    }
}
