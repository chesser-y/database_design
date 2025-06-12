package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.CourseMapper;
import edu.hitwh.homework.pojo.Course;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> listAll() {
        return courseMapper.listAll();
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Integer teacherId, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<Course> courseList = courseMapper.list(name, teacherId, begin, end);
        Page p = (Page) courseList;
        long total = p.getTotal();
        List result = p.getResult();
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }

    @Override
    public void save(Course course) {
        course.setUpdateTime(LocalDateTime.now());
        course.setCreateTime(LocalDateTime.now());
        courseMapper.insert(course);
        return;
    }

    @Override
    public void delete(List<Integer> ids) {
        courseMapper.delete(ids);
        return;
    }

    @Override
    public Course getById(Integer id) {
        return courseMapper.getById(id);
    }

    @Override
    public void update(Course course) {
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.update(course);
        return;
    }
}
