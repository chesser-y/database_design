import request from "@/utils/request";

// 分页查询教材
export function page(name, page, pageSize) {
  return request({
    url: `/material?name=${name}&page=${page}&pageSize=${pageSize}`,
    method: "get"
  });
}

// 新增教材
export function add(material) {
  return request({
    url: "/material",
    method: "post",
    data: material
  });
}

// 更新教材
export function update(material) {
  return request({
    url: "/material",
    method: "put",
    data: material
  });
}

// 删除教材
export function deleteById(ids) {
  return request({
    url: `/material/${ids}`,
    method: "delete"
  });
}

// 根据ID查询教材
export function selectById(id) {
  return request({
    url: `/material/${id}`,
    method: "get"
  });
}

// 根据课程名称查询教材
export function listByCourseName(courseName) {
  return request({
    url: `/material/course/${courseName}`,
    method: "get"
  });
}