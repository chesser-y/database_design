package edu.hitwh.homework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.hitwh.homework.mapper.ClsStatMapper;
import edu.hitwh.homework.pojo.ClsStat;
import edu.hitwh.homework.pojo.PageBean;
import edu.hitwh.homework.service.ClsStatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClsStatServiceImpl implements ClsStatService {
    @Autowired
    private ClsStatMapper clsStatMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String className) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 执行查询
        List<ClsStat> list = clsStatMapper.listByCondition(className);

        // 获取分页信息
        Page<ClsStat> p = (Page<ClsStat>) list;

        // 封装结果
        long total = p.getTotal();
        List result = p.getResult();
        PageBean pageBean = new PageBean(total, result);
        return pageBean;
    }
}
