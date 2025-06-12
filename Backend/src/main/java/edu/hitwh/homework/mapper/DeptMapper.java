package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    //查询所有部门数据
    @Select("select id, name, create_time, update_time from dept")
    List<Dept> list();

    //根据id删除部门
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    //插入dept对象
    @Insert("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    //根据id查询部门信息
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept get(Integer id);

    //更新部门信息
    @Update("update dept set name = #{name}, update_time=now() where id = #{id}")
    void update(Dept dept);
}