package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.CSMapper;
import edu.hitwh.homework.pojo.CS;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.service.CSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CSServiceImpl implements CSService {
    @Autowired
    private CSMapper csMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String studentName, String className) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 执行查询
        List<CS> list = csMapper.listByCondition(studentName, className);

        // 获取分页信息
        Page<CS> p = (Page<CS>) list;

        // 封装结果
        long total = p.getTotal();
        List result = p.getResult();
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }
}
