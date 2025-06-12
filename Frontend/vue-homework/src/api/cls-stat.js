import request from "@/utils/request";

// 分页查询班级信息统计
export function page(className, page, pageSize) {
  return request({
    url: `/cls-stat?className=${className}&page=${page}&pageSize=${pageSize}`,
    method: "get"
  });
}