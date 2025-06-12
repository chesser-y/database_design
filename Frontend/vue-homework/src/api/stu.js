import request from '@/utils/request'

export function page(studentName, studentNo, highestEducation, className, page, pageSize) {
  return request({
    url: '/stu?studentName=' + studentName + '&highestEducation=' + highestEducation +
      '&className=' + className + '&studentNo=' + studentNo + '&page=' + page + '&pageSize=' + pageSize,
    method: 'get'
  })
}

export function add(stu) {
  return request({
    url: '/stu',
    method: 'post',
    data: stu
  })
}

export function findAllStudents() {
  return request({
    url: '/stu/all',
    method: 'get',
  })
}

export function update(stu) {
  return request({
    url: '/stu',
    method: 'put',
    data: stu
  })
}


export function deleteById(id) {
  return request({
    url: '/stu/' + id,
    method: 'delete',
  })
}


export function selectById(id) {
  return request({
    url: '/stu/' + id,
    method: 'get',
  })
}

export function submitDiscipline(id, penaltyScore) {
  return request({
    url: `/stu/${id}/discipline?penaltyScore=` + penaltyScore,
    method: 'put',
  });
}

