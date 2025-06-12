import request from "@/utils/request";

// 查询全部课程
export function findAllCourses() {
  return request({
    url: "/course/all",
    method: "get"
  });
}

// 分页查询课程
export function page(name, teacherId, begin, end, page, pageSize) {
  return request({
    url: `/course?name=${name}&teacherId=${teacherId}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`,
    method: "get"
  });
}

// 新增课程
export function add(course) {
  return request({
    url: "/course",
    method: "post",
    data: course
  });
}

// 更新课程
export function update(course) {
  return request({
    url: "/course",
    method: "put",
    data: course
  });
}



// 删除课程
export function deleteById(ids) {
  return request({
    url: `/course/${ids}`,
    method: "delete"
  });
}

// 根据ID查询课程
export function selectById(id) {
  return request({
    url: `/course/${id}`,
    method: "get"
  });
  
}

export function findAll() {
  return request({
    url: '/course/all',
    method: 'get',
  })
}