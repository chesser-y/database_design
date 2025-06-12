package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.MaterialMapper;
import edu.hitwh.homework.pojo.Material;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Material> listAll() {
        return materialMapper.listAll();
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        List<Material> materialList = materialMapper.list(name);
        Page p = (Page) materialList;
        long total = p.getTotal();
        List result = p.getResult();
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }

    @Override
    public void save(Material material) {
        materialMapper.insert(material);
    }


    @Override
    public Material getById(Integer id) {
        return materialMapper.getById(id);
    }

    @Override
    public void update(Material material) {
        materialMapper.update(material);
    }

    @Override
    public List<Material> listByCourseName(String courseName) {
        return materialMapper.listByCourseName(courseName);
    }

    @Override
    @Transactional
    public void saveWithCourses(Material material) {
        // 保存教材基本信息
        materialMapper.insert(material);

        // 保存关联的课程关系
        if (material.getCourseIds() != null && !material.getCourseIds().isEmpty()) {
            for (Integer courseId : material.getCourseIds()) {
                materialMapper.insertCourseMapping(material.getId(), courseId);
            }
        }
    }

    @Override
    @Transactional
    public void updateWithCourses(Material material) {
        // 更新教材基本信息
        materialMapper.update(material);

        // 先删除所有关联关系
        materialMapper.deleteAllCourseMappings(material.getId());

        // 再添加新的关联关系
        if (material.getCourseIds() != null && !material.getCourseIds().isEmpty()) {
            for (Integer courseId : material.getCourseIds()) {
                materialMapper.insertCourseMapping(material.getId(), courseId);
            }
        }
    }

    @Override
    @Transactional
    public void delete(List<Integer> ids) {
        // 先删除关联关系
        for (Integer id : ids) {
            materialMapper.deleteAllCourseMappings(id);
        }

        // 再删除教材
        materialMapper.delete(ids);
    }
}