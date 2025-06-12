import request from "@/utils/request";

// 分页查询成绩
export function page(studentId, courseId, examTime, page, pageSize) {
  return request({
    url: `/score?studentId=${studentId}&courseId=${courseId}&examTime=${examTime}&page=${page}&pageSize=${pageSize}`,
    method: "get"
  });
}

// 新增成绩
export function add(score) {
  return request({
    url: "/score",
    method: "post",
    data: score
  });
}

// 更新成绩
export function update(score) {
  return request({
    url: "/score",
    method: "put",
    data: score
  });
}

// 删除成绩
export function deleteById(ids) {
  return request({
    url: `/score/${ids}`,
    method: "delete"
  });
}

// 根据ID查询成绩
export function selectById(id) {
  return request({
    url: `/score/${id}`,
    method: "get"
  });
}