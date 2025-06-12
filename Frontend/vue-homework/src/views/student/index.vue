<template>
  <div class="app-container">
    <!--搜索表单-->
    <!--搜索表单-->
    <el-form :inline="true" :model="searchStu" class="demo-form-inline">
      <el-form-item label="学员姓名">
        <el-input
          v-model="searchStu.studentName"
          placeholder="请输入学员名称"
        ></el-input>
      </el-form-item>

      <el-form-item label="学号">
        <el-input v-model="searchStu.studentNo" placeholder="请输入学号">
        </el-input>
      </el-form-item>

      <el-form-item label="最高学历">
        <el-select
          v-model="searchStu.highestEducation"
          placeholder="请选择"
          style="width: 100%"
        >
          <el-option
            v-for="item in highestEducationList"
            :key="item.value"
            :label="item.name"
            :value="item.name"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="所属班级">
        <el-select
          v-model="searchStu.className"
          placeholder="请选择"
          style="width: 100%"
        >
          <!-- 
            <el-option label="学工部" value="1"></el-option>
            <el-option label="教研部" value="2"></el-option>
             -->
          <el-option
            v-for="item in clsList"
            :key="item.value"
            :label="item.className"
            :value="item.className"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>

    <!--按钮-->
    <el-row>
      <el-button type="danger" size="medium" @click="deleteByIds"
        >- 批量删除</el-button
      >
      <el-button
        type="primary"
        size="medium"
        @click="
          dialogVisible = true;
          stu = {
            /** */
          };
        "
        >+ 添加学员</el-button
      >
    </el-row>

    <!--添加数据对话框表单-->
    <el-dialog
      ref="form"
      title="编辑信息"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-form ref="form" :model="stu" label-width="80px" size="mini">
        <el-form-item label="* 姓名">
          <el-input
            v-model="stu.studentName"
            placeholder="请输入姓名"
          ></el-input>
        </el-form-item>

        <el-form-item label="* 学号">
          <el-input v-model="stu.studentNo" placeholder="请输入学号"></el-input>
        </el-form-item>

        <el-form-item label="* 性别">
          <el-select
            v-model="stu.gender"
            placeholder="请选择"
            style="width: 100%"
          >
            <el-option
              v-for="item in genderList"
              :key="item.value"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="* 手机号">
          <el-input v-model="stu.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>

        <el-form-item label="最高学历">
          <el-select
            v-model="stu.highestEducation"
            placeholder="请选择"
            style="width: 100%"
          >
            <!-- <el-option label="金庸" value="1"></el-option>
            <el-option label="东方不败" value="2"></el-option> -->

            <el-option
              v-for="item in highestEducationList"
              :key="item.value"
              :label="item.name"
              :value="item.name"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="* 所属班级">
          <el-select
            v-model="stu.className"
            placeholder="请选择"
            style="width: 100%"
          >
            <!-- <el-option label="金庸" value="1"></el-option>
            <el-option label="东方不败" value="2"></el-option> -->

            <el-option
              v-for="item in clsList"
              :key="item.value"
              :label="item.className"
              :value="item.className"
            />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="add">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <br />
    <!--表格-->
    <template>
      <el-table
        :data="tableData"
        style="width: 100%"
        border
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          width="55"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="studentName"
          label="姓名"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="studentNo"
          label="学号"
          align="center"
        ></el-table-column>
        <el-table-column prop="gender" label="性别" align="center">
          <template slot-scope="scope">
            <span style="margin-right: 10px">
              {{ scope.row.gender == "1" ? "男" : "女" }}</span
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="className"
          label="班级"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="phone"
          label="手机号"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="highestEducation"
          label="最高学历"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="disciplineCount"
          label="违纪次数"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="penaltyScore"
          label="违纪扣分"
          align="center"
        ></el-table-column>

        <el-table-column align="center" label="操作" width="275">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="update(scope.row.id)"
              >编辑</el-button
            >
            <el-button
              type="primary"
              size="small"
              @click="handleDiscipline(scope.row.id)"
              >违纪</el-button
            >
            <el-button
              type="danger"
              size="small"
              @click="deleteById(scope.row.id)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </template>

    <el-dialog
      ref="form"
      title="扣分详情"
      :visible.sync="disciplineDialogVisible"
      width="30%"
    >
      <el-form
        ref="form"
        :model="disciplineForm"
        label-width="80px"
        size="mini"
      >
        <el-form-item label="扣分">
          <el-input
            v-model="disciplineForm.penaltyScore"
            placeholder="请输入扣分分数"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitDiscipline">提交</el-button>
          <el-button @click="disciplineDialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!--分页工具条-->
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
import {
  page,
  add,
  update,
  deleteById,
  selectById,
  submitDiscipline,
} from "@/api/stu.js";
import { findAll } from "@/api/cls.js";
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
      // 学生模型数据
      searchStu: {
        studentName: "",
        studentNo: "",
        highestEducation: "",
        className: "",
      },
      disciplineDialogVisible: false,
      disciplineForm: {
        id: null,
        penaltyScore: 0, // 初始值
      },
      stuList: [],
      clsList: [],
      stu: {},
      genderList: [
        { id: 1, name: "男" },
        { id: 0, name: "女" },
      ],
      highestEducationList: [
        {
          id: 1,
          name: "初中",
        },
        {
          id: 2,
          name: "高中",
        },
        {
          id: 3,
          name: "大专",
        },
        {
          id: 4,
          name: "本科",
        },
        {
          id: 5,
          name: "硕士",
        },
        {
          id: 6,
          name: "博士",
        },
      ],
      // 被选中的id数组
      selectedIds: [],
      // 复选框选中数据集合
      multipleSelection: [],
      // 表格数据
      tableData: [],
      token: { token: getToken() },
    };
  },

  mounted() {
    this.page(); //当页面加载完成后，发送异步请求，获取数据
    findAll().then((result) => {
      this.clsList = result.data.data;
    });
  },

  methods: {
    // 查询分页数据
    page() {
      page(
        this.searchStu.studentName,
        this.searchStu.studentNo,
        this.searchStu.highestEducation,
        this.searchStu.className,
        this.currentPage,
        this.pageSize
      ).then((res) => {
        this.totalCount = res.data.data.total;
        this.tableData = res.data.data.rows;
      });
    },

    handleDiscipline(id) {
      this.disciplineForm.id = id;
      this.disciplineForm.penaltyScore = 1;
      this.disciplineDialogVisible = true;
    },

    submitDiscipline() {
      submitDiscipline(this.disciplineForm.id, this.disciplineForm.penaltyScore)
        .then(() => {
          this.$message.success("违纪信息提交成功");
          this.disciplineDialogVisible = false;
          this.page();
        })
        .catch((error) => {
          console.error(error);
          this.$message.error("提交失败，请重试");
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
      this.searchStu = {
        studentName: "",
        studentNo: "",
        highestEducation: "",
        className: "",
      };
      this.page();
    },
    // 添加数据
    add() {
      let operator;

      if (this.stu.id) {
        //修改
        operator = update(this.stu);
      } else {
        operator = add(this.stu);
      }

      operator.then((resp) => {
        if (resp.data.code == "1") {
          this.dialogVisible = false;
          this.page();
          this.$message({ message: "恭喜你，保存成功", type: "success" });
          this.stu = {};
        } else {
          this.$message.error(resp.data.msg);
        }
      });
    },
    update(id) {
      //1. 打开窗口
      this.dialogVisible = true;
      //2. 发送请求

      selectById(id).then((result) => {
        if (result.data.code == 1) {
          this.stu = result.data.data;
          this.stu;
        }
      });
    },

    //分页
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

    //删除学生信息
    deleteById(id) {
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //2. 发送AJAX请求
          deleteById(id).then((resp) => {
            if (resp.data.code == 1) {
              //删除成功
              this.$message.success("恭喜你，删除成功");
              this.page();
            } else {
              this.$message.error(resp.data.msg);
            }
          });
        })
        .catch(() => {
          //用户点击取消按钮
          this.$message.info("已取消删除");
        });
    },

    // 批量删除学生信息
    deleteByIds() {
      // 弹出确认提示框
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //用户点击确认按钮
          //1. 创建id数组, 从 this.multipleSelection 获取即可
          for (let i = 0; i < this.multipleSelection.length; i++) {
            this.selectedIds[i] = this.multipleSelection[i].id;
          }

          //2. 发送AJAX请求
          deleteById(this.selectedIds).then((resp) => {
            if (resp.data.code == "1") {
              //删除成功
              this.$message.success("恭喜你，删除成功");
              this.page();
            } else {
              this.$message.error(resp.data.msg);
            }
          });
        })
        .catch(() => {
          //用户点击取消按钮
          this.$message.info("已取消删除");
        });
    },
  },
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


