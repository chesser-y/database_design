package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.EmpMapper;
import edu.hitwh.homework.pojo.Emp;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
//public class EmpServiceImpl implements EmpService {
//    @Autowired
//    private EmpMapper empMapper;
//
//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        //1、获取总记录数
//        Long count = empMapper.count();
//
//        //2、获取分页查询结果列表
//        Integer start = (page - 1) * pageSize; //计算起始索引 , 公式: (页码-1)*页大小
//        List<Emp> empList = empMapper.list(start, pageSize);
//
//        //3、封装PageBean对象
//        PageBean pageBean = new PageBean(count , empList);
//        return pageBean;
//    }

public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

        /**
         * 1 设置分页查询的参数
         *  startPage方法
         *      参数 1 page: 页码
         *      参数 2 pageSize: 每页显示数量
         */
        PageHelper.startPage(page, pageSize);

        // 2 查询所有数据
        List<Emp> empList = empMapper.list(name, gender, begin, end);

        // 3 把记录所有数据的集合转成Page类型, 获取pageBean所需要的参数
        Page p = (Page) empList;
        // 根据page对象获取总记录数
        long total = p.getTotal();
        // 根据page对象获取列表
        List result = p.getResult();

        // 4 封装PageBean对象
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        return;
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        //调用dao登录函数
        Emp loginEmp = empMapper.getByUsernameAndPassword(emp);
        return loginEmp;
    }

    @Override
    public List<Emp> listAll() {
        return empMapper.listAll();
    }
}

