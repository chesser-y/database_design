<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form :inline="true" :model="searchCourse" class="demo-form-inline">
      <el-form-item label="课程名称">
        <el-input
          v-model="searchCourse.name"
          placeholder="请输入课程名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="教师ID">
        <el-input
          v-model="searchCourse.teacherId"
          placeholder="请输入教师ID"
        ></el-input>
      </el-form-item>
      <el-form-item label="结课时间">
        <el-date-picker
          v-model="endDate"
          clearable
          value-format="yyyy-MM-dd"
          type="daterange"
          placeholder="选择日期范围"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          style="width: 400px; margin-left: 20px"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>

    <!-- 按钮 -->
    <el-row>
      <el-button type="danger" size="medium" @click="deleteByIds">- 批量删除</el-button>
      <el-button type="primary" size="medium" @click="dialogVisible = true; course = {};">+ 新增课程</el-button>
    </el-row>

    <!-- 添加数据对话框表单 -->
    <el-dialog ref="form" title="编辑课程" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="course" label-width="80px" size="mini">
        <el-form-item label="* 课程名称">
          <el-input v-model="course.name" placeholder="请输入课程名称"></el-input>
        </el-form-item>
        <el-form-item label="* 开始日期">
          <el-date-picker
            v-model="course.begin"
            clearable
            type="date"
            placeholder="请选择开始日期"
            size="small"
            style="width:100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="* 结束日期">
          <el-date-picker
            v-model="course.end"
            clearable
            type="date"
            placeholder="请选择结束日期"
            size="small"
            style="width:100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="* 教师ID">
          <el-input v-model="course.teacherId" placeholder="请输入教师ID"></el-input>
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
        <el-table-column prop="name" label="课程名称" align="center"></el-table-column>
        <!-- <el-table-column prop="teacherId" label="教师ID" align="center"></el-table-column> -->
        <el-table-column prop="teacherId" label="教师姓名" align="center" :formatter="formatTeacherName"></el-table-column>
        <el-table-column prop="beginTime" label="开始日期" align="center"></el-table-column>
        <el-table-column prop="endTime" label="结束日期" align="center"></el-table-column>
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
import { page, add, update, deleteById, selectById, listAll } from "@/api/course.js";
import { getToken } from "@/utils/auth";
import {findAll} from "@/api/emp.js";

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
      // 课程模型数据
      searchCourse: {
        name: "",
        teacherId: ""
      },
      courseList: [],
      empList: [],
      course: {},
      beginTime: "",
      endTime: "",
      endDate: "",
      // 被选中的id数组
      selectedIds: [],
      // 复选框选中数据集合
      multipleSelection: [],
      // 表格数据
      tableData: [],
      token: { token: getToken() }
    };
  },

  mounted() {
    this.page(); // 当页面加载完成后，发送异步请求，获取数据
    findAll().then((result) => {
      this.empList = result.data.data;
    });
  },

  methods: {
    // 查询分页数据
    page() {
      page(
        this.searchCourse.name,
        this.searchCourse.teacherId,
        this.beginTime,
        this.endTime,
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
      this.searchCourse = { name: "", teacherId: "" };
      this.beginTime = "";
      this.endTime = "";
      this.endDate = "";
      this.page();
    },

    // 添加数据
    add() {
      let operator;

      if (this.course.id) {
        // 修改
        operator = update(this.course);
      } else {
        operator = add(this.course);
      }

      operator.then((resp) => {
        if (resp.data.code == "1") {
          this.dialogVisible = false;
          this.page();
          this.$message({ message: "恭喜你，保存成功", type: "success" });
          this.course = {};
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
          this.course = result.data.data;
          this.course;
        }
      });
    },

    formatTeacherName(row) {
      const teacher = this.empList.find(emp => emp.id === row.teacherId);
      return teacher ? teacher.name : '未知';
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

    // 删除课程
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

    // 批量删除课程
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
  },

  watch: {
    endDate(val) {
      if (val && val.length >= 2) {
        this.beginTime = val[0];
        this.endTime = val[1];
      } else {
        this.beginTime = "";
        this.endTime = "";
      }
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