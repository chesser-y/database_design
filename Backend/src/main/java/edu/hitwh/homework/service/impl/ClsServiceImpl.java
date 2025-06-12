package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.ClsMapper;
import edu.hitwh.homework.pojo.Cls;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.service.ClsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClsServiceImpl implements ClsService {
    @Autowired
    private ClsMapper clsMapper;

    @Override
    public List<Cls> listAll() {
        return clsMapper.listAll();
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String className, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<Cls> clsList = clsMapper.list(className, begin, end);
        Page p = (Page) clsList;
        long total = p.getTotal();
        List result = p.getResult();
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }

    @Override
    public void save(Cls cls) {
        cls.setUpdateTime(LocalDateTime.now());
        cls.setCreateTime(LocalDateTime.now());
        clsMapper.insert(cls);
        return;
    }

    @Override
    public void delete(List<Integer> ids) {
        clsMapper.delete(ids);
        return;
    }

    @Override
    public Cls getById(Integer id) {
        return clsMapper.getById(id);
    }

    @Override
    public void update(Cls cls) {
        clsMapper.update(cls);
        return;
    }
}
