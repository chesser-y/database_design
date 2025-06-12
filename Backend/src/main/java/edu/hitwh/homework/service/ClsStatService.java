package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.PageBean;

public interface ClsStatService {
    PageBean page(Integer page, Integer pageSize, String className);
}
