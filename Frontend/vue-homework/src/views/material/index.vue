<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form :inline="true" :model="searchMaterial" class="demo-form-inline">
      <el-form-item label="教材名称">
        <el-input
          v-model="searchMaterial.name"
          placeholder="请输入教材名称"
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
      <el-button type="primary" size="medium" @click="dialogVisible = true; material = {};">+ 新增教材</el-button>
    </el-row>

    <!-- 表格 -->
    <template v-if="!showCourseMaterials">
      <el-table :data="tableData" style="width: 100%" border @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="name" label="教材名称" align="center"></el-table-column>
        <el-table-column prop="author" label="作者" align="center"></el-table-column>
        <el-table-column prop="publisher" label="出版社" align="center"></el-table-column>
        <el-table-column prop="edition" label="版本" align="center"></el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="update(scope.row.id)">编辑</el-button>
            <el-button type="danger" size="small" @click="deleteById(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

    <!-- 新的表单显示课程需要的教材 -->
    <template v-if="showCourseMaterials">
      <el-table :data="courseMaterials" style="width: 100%" border>
        <el-table-column prop="name" label="教材名称" align="center"></el-table-column>
        <el-table-column prop="author" label="作者" align="center"></el-table-column>
        <el-table-column prop="publisher" label="出版社" align="center"></el-table-column>
        <el-table-column prop="edition" label="版本" align="center"></el-table-column>
      </el-table>
    </template>

    <!-- 分页工具条 -->
    <el-pagination
      v-if="!showCourseMaterials"
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

    <!-- 编辑对话框 -->
    <el-dialog ref="form" title="编辑教材" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="material" label-width="80px" size="mini">
        <el-form-item label="* 教材名称">
          <el-input v-model="material.name" placeholder="请输入教材名称"></el-input>
        </el-form-item>
        <el-form-item label="* 作者">
          <el-input v-model="material.author" placeholder="请输入作者"></el-input>
        </el-form-item>
        <el-form-item label="* 出版社">
          <el-input v-model="material.publisher" placeholder="请输入出版社"></el-input>
        </el-form-item>
        <el-form-item label="* 版本">
          <el-input v-model="material.edition" placeholder="请输入版本"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="add">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { page, add, update, deleteById, selectById, deleteByIds, getMaterialsByCourse } from "@/api/material.js";
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
      // 教材模型数据
      searchMaterial: {
        name: "",
        courseName: ""
      },
      // 表格数据
      tableData: [],
      material: {},
      // 被选中的id数组
      selectedIds: [],
      // 复选框选中数据集合
      multipleSelection: [],
      token: { token: getToken() },
      // 是否显示课程需要的教材表单
      showCourseMaterials: false,
      // 课程需要的教材数据
      courseMaterials: []
    };
  },

  mounted() {
    this.page();
  },

  methods: {
    // 查询分页数据
    page() {
      if (this.searchMaterial.courseName) {
        getMaterialsByCourse(
          this.searchMaterial.courseName,
          this.currentPage,
          this.pageSize
        ).then((res) => {
          this.totalCount = res.data.data.total;
          this.tableData = res.data.data.rows;
          this.showCourseMaterials = false;
        });
      } else {
        page(
          this.searchMaterial.name,
          this.currentPage,
          this.pageSize
        ).then((res) => {
          this.totalCount = res.data.data.total;
          this.tableData = res.data.data.rows;
          this.showCourseMaterials = false;
        });
      }
    },

    // 复选框选中后执行的方法
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.selectedIds = val.map(item => item.id);
    },

    // 查询方法
    onSubmit() {
      if (this.searchMaterial.courseName) {
        getMaterialsByCourse(this.searchMaterial.courseName).then((res) => {
          this.courseMaterials = res.data.data.rows;
          this.showCourseMaterials = true;
        });
      } else {
        this.currentPage = 1;
        this.page();
      }
    },

    clear() {
      this.searchMaterial = { name: "", courseName: "" };
      this.showCourseMaterials = false;
      this.page();
    },

    // 添加数据
    add() {
      let operator;

      if (this.material.id) {
        // 修改
        operator = update(this.material);
      } else {
        operator = add(this.material);
      }

      operator.then((resp) => {
        if (resp.data.code == "1") {
          this.dialogVisible = false;
          this.page();
          this.$message({ message: "恭喜你，保存成功", type: "success" });
          this.material = {};
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
          this.material = result.data.data;
        }
      });
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

    // 删除教材
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

    // 批量删除
    deleteByIds() {
      if (this.selectedIds.length === 0) {
        this.$message.warning('请选择要删除的数据');
        return;
      }
      this.$confirm('此操作将删除选中的数据，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteByIds(this.selectedIds).then((resp) => {
          if (resp.data.code === 1) {
            this.$message.success('恭喜你，删除成功');
            this.page();
            this.selectedIds = [];
            this.multipleSelection = [];
          } else {
            this.$message.error(resp.data.msg);
          }
        });
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    }
  }
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
      font-family: '楷体';
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>