import request from "@/utils/request";

// 分页查询学期
export function page(name, page = 1, pageSize = 10) {
  return request({
    url: `/semester?name=${name}&page=${page}&pageSize=${pageSize}`,
    method: "get"
  });
}

// 查询全部学期
export function listAll() {
  return request({
    url: "/semester/all",
    method: "get"
  });
}

// 新增学期
export function save(semester) {
  return request({
    url: "/semester",
    method: "post",
    data: semester
  });
}

// 删除学期
export function deleteById(ids) {
  return request({
    url: `/semester/${ids}`,
    method: "delete"
  });
}

// 根据 ID 查询学期
export function getById(id) {
  return request({
    url: `/semester/${id}`,
    method: "get"
  });
}

// 更新学期信息
export function update(semester) {
  return request({
    url: "/semester",
    method: "put",
    data: semester
  });
}