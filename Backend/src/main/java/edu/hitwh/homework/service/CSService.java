package edu.hitwh.homework.service;

import edu.hitwh.homework.pojo.PageBean;

public interface CSService {
    PageBean page(Integer page, Integer pageSize, String studentName, String className);
}
