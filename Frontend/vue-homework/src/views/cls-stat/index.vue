<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form :inline="true" :model="searchClsStat" class="demo-form-inline">
      <el-form-item label="班级名称">
        <el-input
          v-model="searchClsStat.className"
          placeholder="请输入班级名称"
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
        <el-table-column prop="className" label="班级名称" align="center"></el-table-column>
        <el-table-column prop="totalNum" label="学生数量" align="center"></el-table-column>
        <el-table-column prop="avgScore" label="平均成绩" align="center"></el-table-column>
        <el-table-column prop="maleFemale" label="班级男女比" align="center"></el-table-column>
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
import { page } from "@/api/cls-stat.js";
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
      // 班级统计模型数据
      searchClsStat: {
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
        this.searchClsStat.className,
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
      this.searchClsStat = { className: "" };
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