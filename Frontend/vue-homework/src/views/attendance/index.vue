<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form :inline="true" :model="searchAttendance" class="demo-form-inline">
      <el-form-item label="学生ID">
        <el-input
          v-model="searchAttendance.studentId"
          placeholder="请输入学生ID"
        ></el-input>
      </el-form-item>
      <el-form-item label="课程ID">
        <el-input
          v-model="searchAttendance.courseId"
          placeholder="请输入课程ID"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>

    <!-- 按钮 -->
    <el-row>
      <el-button type="danger" size="medium" @click="deleteByIds">- 批量删除</el-button>
      <el-button type="primary" size="medium" @click="dialogVisible = true; attendance = {};">+ 新增考勤记录</el-button>
    </el-row>

    <!-- 添加数据对话框表单 -->
    <el-dialog ref="form" title="编辑考勤记录" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="attendance" label-width="80px" size="mini">
        <el-form-item label="* 学生ID">
          <el-input v-model="attendance.studentId" placeholder="请输入学生ID"></el-input>
        </el-form-item>
        <el-form-item label="* 课程ID">
          <el-input v-model="attendance.courseId" placeholder="请输入课程ID"></el-input>
        </el-form-item>
        <el-form-item label="* 缺勤次数">
          <el-input v-model="attendance.absenceCount" placeholder="请输入缺勤次数"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="add">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <br>
    <!-- 表格 -->
    <template>
      <el-table :data="tableData" style="width: 100%" border @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="studentId" label="学生ID" align="center"></el-table-column>
        
        <el-table-column prop="studentId" label="学生姓名" align="center" :formatter="formatStudentName"></el-table-column>
        <el-table-column prop="courseId" label="课程ID" align="center"></el-table-column>
        <el-table-column prop="courseId"  label="课程"  align="center" :formatter="formatCourseName"></el-table-column>
        <el-table-column prop="absenceCount" label="缺勤次数" align="center"></el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="update(scope.row.id)">编辑</el-button>
            <el-button type="danger" size="small" @click="deleteById(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

    <!-- 分页工具条 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :background="background"
      :current-page="currentPage"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="5"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCount"
    >
    </el-pagination>
  </div>
</template>

<script>
import { page, add, update, deleteById, selectById } from "@/api/attendance.js";
import { findAllStudents } from "@/api/stu.js"; // 新增：获取学生列表
import { findAllCourses } from "@/api/course.js"; // 新增：获取课程列表
import { getToken } from "@/utils/auth";

export default {
  data() {
    return {
      background: true,
      // 每页显示的条数
      pageSize: 5,
      // 总记录数
      totalCount: 100,
      // 当前页码
      currentPage: 1,
      // 添加数据对话框是否展示的标记
      dialogVisible: false,
      // 考勤模型数据
      searchAttendance: {
        studentId: "",
        courseId: ""
      },
      attendanceList: [],
      attendance: {},
      // 被选中的id数组
      selectedIds: [],
      studentList: [],
      courseList: [],
      // 复选框选中数据集合
      multipleSelection: [],
      // 表格数据
      tableData: [],
      token: { token: getToken() }
    };
  },

  mounted() {
    this.page();
    // 加载学生和课程列表
    findAllStudents()
      .then((res) => {
        this.studentList = res.data.data;
        // console.log('学生列表数据:', this.studentList);
      });
    findAllCourses().then((res) => {
      this.courseList = res.data.data;
    });
  },

  methods: {
    // 查询分页数据
    page() {
      page(
        this.searchAttendance.studentId,
        this.searchAttendance.courseId,
        this.currentPage,
        this.pageSize
      ).then((res) => {
        this.totalCount = res.data.data.total;
        this.tableData = res.data.data.rows;
      });
    },

    // 复选框选中后执行的方法
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    // 查询方法
    onSubmit() {
      this.currentPage = 1;
      this.page();
    },

    clear() {
      this.searchAttendance = { studentId: "", courseId: "" };
      this.page();
    },
    // 添加数据
    add() {
      let operator;

      if (this.attendance.id) {
        // 修改
        operator = update(this.attendance);
      } else {
        operator = add(this.attendance);
      }

      operator.then((resp) => {
        if (resp.data.code == "1") {
          this.dialogVisible = false;
          this.page();
          this.$message({ message: "恭喜你，保存成功", type: "success" });
          this.attendance = {};
        } else {
          this.$message.error(resp.data.msg);
        }
      });
    },
    update(id) {
      // 1. 打开窗口
      this.dialogVisible = true;
      // 2. 发送请求

      selectById(id).then((result) => {
        if (result.data.code == 1) {
          this.attendance = result.data.data;
          this.attendance;
        }
      });
    },
    formatStudentName(row) {
      const student = this.studentList.find((c) => c.id === row.studentId);
      return student ? student.studentName : "未知";
    },

    // 新增：格式化课程ID为课程名称
    formatCourseName(row) {
      const course = this.courseList.find((c) => c.id === row.courseId);
      return course ? course.name : "未知";
    },
    // 分页
    handleSizeChange(val) {
      // 重新设置每页显示的条数
      this.pageSize = val;
      this.page();
    },

    handleCurrentChange(val) {
      // 重新设置当前页码
      this.currentPage = val;
      this.page();
    },

    // 删除考勤记录
    deleteById(id) {
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        // 2. 发送AJAX请求
        deleteById(id).then((resp) => {
          if (resp.data.code == 1) {
            // 删除成功
            this.$message.success("恭喜你，删除成功");
            this.page();
          } else {
            this.$message.error(resp.data.msg);
          }
        });
      }).catch(() => {
        // 用户点击取消按钮
        this.$message.info("已取消删除");
      });
    },

    // 批量删除考勤记录
    deleteByIds() {
      // 弹出确认提示框
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        // 用户点击确认按钮
        // 1. 创建id数组, 从 this.multipleSelection 获取即可
        for (let i = 0; i < this.multipleSelection.length; i++) {
          this.selectedIds[i] = this.multipleSelection[i].id;
        }

        // 2. 发送AJAX请求
        deleteById(this.selectedIds).then((resp) => {
          if (resp.data.code == "1") {
            // 删除成功
            this.$message.success("恭喜你，删除成功");
            this.page();
          } else {
            this.$message.error(resp.data.msg);
          }
        });
      }).catch(() => {
        // 用户点击取消按钮
        this.$message.info("已取消删除");
      });
    }
  }
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>