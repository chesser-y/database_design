import request from "@/utils/request";

// 分页查询课程成绩统计
export function page(studentName, className, page, pageSize) {
  return request({
    url: `/cs?studentName=${studentName}&className=${className}&page=${page}&pageSize=${pageSize}`,
    method: "get"
  });
}