package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    //获取总记录数
//    @Select("select count(*) from emp")
//    public Long count();
//
//    //获取当前页的结果列表
//    @Select("select * from emp limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);


    //查询所有数据
//    @Select("select * from emp")
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    //根据id删除员工
    void delete(List<Integer> ids);

    //新增员工数据插入数据库
    void insert(Emp emp);

    //根据id获取员工信息
    Emp getById(Integer id);

    //更新员工信息
    void update(Emp emp);

    //登录功能：根据用户名&密码查询信息
    Emp getByUsernameAndPassword(Emp emp);

    //查询全部员工数据
    @Select("select * from emp")
    List<Emp> listAll();
}

