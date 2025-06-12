package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.SemesterMapper;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.pojo.Semester;
import edu.hitwh.homework.service.SemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterMapper semesterMapper;

    @Override
    public List<Semester> listAll() {
        return semesterMapper.list(null);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        List<Semester> semesterList = semesterMapper.list(name);
        Page p = (Page) semesterList;
        long total = p.getTotal();
        List result = p.getResult();
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }

    @Override
    @Transactional
    public void save(Semester semester) {
        semesterMapper.insert(semester);
    }

    @Override
    @Transactional
    public void delete(List<Integer> ids) {
        // 先删除关联关系
        for (Integer id : ids) {
            semesterMapper.deleteAllCourseMappings(id);
        }

        // 再删除学期
        semesterMapper.delete(ids);
    }

    @Override
    public Semester getById(Integer id) {
        return semesterMapper.getById(id);
    }

    @Override
    @Transactional
    public void update(Semester semester) {
        semesterMapper.update(semester);
    }

    @Override
    @Transactional
    public void saveWithCourses(Semester semester) {
        // 保存学期基本信息
        semesterMapper.insert(semester);

        // 保存关联的课程关系
        if (semester.getCourseIds() != null && !semester.getCourseIds().isEmpty()) {
            for (Integer courseId : semester.getCourseIds()) {
                semesterMapper.insertCourseMapping(semester.getId(), courseId);
            }
        }
    }

    @Override
    @Transactional
    public void updateWithCourses(Semester semester) {
        // 更新学期基本信息
        semesterMapper.update(semester);

        // 先删除所有关联关系
        semesterMapper.deleteAllCourseMappings(semester.getId());

        // 再添加新的关联关系
        if (semester.getCourseIds() != null && !semester.getCourseIds().isEmpty()) {
            for (Integer courseId : semester.getCourseIds()) {
                semesterMapper.insertCourseMapping(semester.getId(), courseId);
            }
        }
    }
}