import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example',
    name: 'Example',
    meta: { title: '班级学员管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'classes',
        name: 'Classes',
        component: () => import('@/views/classes'),
        meta: { title: '班级管理', icon: 'el-icon-menu' }
      },
      {
        path: 'student',
        name: 'Student',
        component: () => import('@/views/student'),
        meta: { title: '学员管理', icon: 'el-icon-user-solid' }
      }
    ]
  }, 
  {
    path: '/system',
    component: Layout,
    redirect: '/system',
    name: 'System',
    meta: { title: '系统信息管理', icon: 'el-icon-s-tools' },
    children: [
      {
        path: 'dept',
        name: 'Dept',
        component: () => import('@/views/dept'),
        meta: { title: '部门管理', icon: 'el-icon-menu' }
      },
      {
        path: 'emp',
        name: 'Emp',
        component: () => import('@/views/emp'),
        meta: { title: '员工管理', icon: 'el-icon-user-solid' }
      }
    ]
  },

  {
    path: '/course-info',
    component: Layout,
    redirect: '/course-info',
    name: 'CourseInfo',
    meta: { title: '课程信息管理', icon: 'el-icon-s-tools' },
    children: [
      {
        path: 'course',
        name: 'Course',
        component: () => import('@/views/course'),
        meta: { title: '教师任课管理', icon: 'el-icon-menu' }
      },
      {
        path: 'attendance',
        name: 'Attendance',
        component: () => import('@/views/attendance'),
        meta: { title: '学生考勤管理', icon: 'el-icon-menu' }
      },
      {
        path: 'score',
        name: 'Score',
        component: () => import('@/views/score'),
        meta: { title: '学生成绩管理', icon: 'el-icon-menu' }
      },
      {
        path: 'material',
        name: 'Material',
        component: () => import('@/views/material'),
        meta: { title: '课程教材管理', icon: 'el-icon-menu' }
      },
      {
        path: 'semester',
        name: 'Semester',
        component: () => import('@/views/semester'),
        meta: { title: '学期规划管理', icon: 'el-icon-s-data' }
      }
    ]
  },

  {
    path: '/stat-info',
    component: Layout,
    redirect: '/stat-info',
    name: 'StatInfo',
    meta: { title: '统计信息管理', icon: 'el-icon-s-tools' },
    children: [
      {
        path: 'cls-stat',
        name: 'ClassStat',
        component: () => import('@/views/cls-stat'),
        meta: { title: '班级信息统计', icon: 'el-icon-s-data' }
      },
      {
        path: 'course-score-stat',
        name: 'CourseScoreStat',
        component: () => import('@/views/course-score-stat'),
        meta: { title: '课程成绩统计', icon: 'el-icon-s-data' }
      }
    ]
  },
 
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router