<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form :inline="true" :model="searchCSS" class="demo-form-inline">
      <el-form-item label="学生姓名">
        <el-input
          v-model="searchCSS.studentName"
          placeholder="请输入学生姓名"
        ></el-input>
      </el-form-item>
      <el-form-item label="课程名称">
        <el-input
          v-model="searchCSS.className"
          placeholder="请输入课程名称"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格 -->
    <template>
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="studentName" label="学生姓名" align="center"></el-table-column>
        <el-table-column prop="className" label="课程名称" align="center"></el-table-column>
        <el-table-column prop="score" label="成绩" align="center"></el-table-column>
        <el-table-column prop="absenceCount" label="缺勤次数" align="center"></el-table-column>
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
import { page } from "@/api/course-score-stat.js";
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
      // 课程成绩统计模型数据
      searchCSS: {
        studentName: "",
        className: ""
      },
      // 表格数据
      tableData: [],
      token: { token: getToken() }
    };
  },

  mounted() {
    this.page(); // 当页面加载完成后，发送异步请求，获取数据
  },

  methods: {
    // 查询分页数据
    page() {
      page(
        this.searchCSS.studentName,
        this.searchCSS.className,
        this.currentPage,
        this.pageSize
      ).then((res) => {
        this.totalCount = res.data.data.total;
        this.tableData = res.data.data.rows;
      });
    },

    // 查询方法
    onSubmit() {
      this.currentPage = 1;
      this.page();
    },

    clear() {
      this.searchCSS = { studentName: "", className: "" };
      this.page();
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
    }
  }
};
</script>

<style>
/* 样式 */
</style>