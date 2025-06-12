import request from '@/utils/request'

export function page(className,begin,end,page,pageSize) {
  return request({
    url: '/cls?className='+className+'&begin='+begin+'&end='+end+'&page='+page+'&pageSize='+pageSize,
    method: 'get'
  })
}

export function add(cls) {
  return request({
    url: '/cls',
    method: 'post',
    data: cls
  })
}

export function update(cls) {
  return request({
    url: '/cls',
    method: 'put',
    data: cls
  })
}


export function deleteById(id) {
  return request({
    url: '/cls/'+id,
    method: 'delete',
  })
}


export function selectById(id) {
  return request({
    url: '/cls/'+id,
    method: 'get',
  })
}

export function findAll() {
  return request({
    url: '/cls/all',
    method: 'get'
  })
}

