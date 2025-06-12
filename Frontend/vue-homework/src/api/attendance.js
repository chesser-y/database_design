import request from "@/utils/request";

// 分页查询考勤记录
export function page(studentId, courseId, page, pageSize) {
  return request({
    url: `/attendance?studentId=${studentId}&courseId=${courseId}&page=${page}&pageSize=${pageSize}`,
    method: "get"
  });
}

// 新增考勤记录
export function add(attendance) {
  return request({
    url: "/attendance",
    method: "post",
    data: attendance
  });
}

// 更新考勤记录
export function update(attendance) {
  return request({
    url: "/attendance",
    method: "put",
    data: attendance
  });
}

// 删除考勤记录
export function deleteById(ids) {
  return request({
    url: `/attendance/${ids}`,
    method: "delete"
  });
}

// 根据ID查询考勤记录
export function selectById(id) {
  return request({
    url: `/attendance/${id}`,
    method: "get"
  });
}