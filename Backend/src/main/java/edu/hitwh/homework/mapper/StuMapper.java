package edu.hitwh.homework.mapper;

import edu.hitwh.homework.pojo.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StuMapper {

    /**
     * 查询学员列表
     * @param studentName 学生姓名，支持模糊查询
     * @param studentNo 学号，支持模糊查询
     * @param highestEducation 最高学历，精确查询
     * @param className 所属班级，精确查询
     * @return 学员列表
     */
    List<Stu> list(String studentName, String studentNo, String highestEducation, String className);

    /**
     * 插入学员数据
     * @param stu 学员对象
     */
    void insert(Stu stu);

    /**
     * 删除学员数据
     * @param ids 学员 id 列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据学员 id 查询
     * @param id 学员 id
     * @return 学员对象
     */
    Stu getById(Integer id);

    /**
     * 更新学员信息
     * @param stu 学员对象
     */
    void update(Stu stu);

    /**
     * 处理学员违纪信息
     * @param id 学员 id
//     * @param penaltyScore 违纪扣分
     */
    void handleDiscipline(Integer id);

    @Select("select * from stu")
    List<Stu> listAll();
}
