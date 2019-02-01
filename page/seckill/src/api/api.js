import Qs from 'qs'
import request from '@/utils/request'
import { AUTHENTICATION_SERVICE, SALES_SERVICE } from './pre-uri'

export function login(username, password) {
  return request({
    url: `${AUTHENTICATION_SERVICE}/oauth/token`,
    method: 'post',
    data: Qs.stringify({
      username,
      password,
      grant_type: 'password'
    })
  })
}

/**
 * 获取账户信息
 * @param userId
 */
export function getAccount(userId) {
  return request({
    url: `${SALES_SERVICE}/account/${userId}`,
    method: 'get',
    errorDefined: {
      404: `用户未找到`
    }
  })
}

/**
 * 获取商品列表
 */
export function getCommodity() {
  return request({
    url: `${SALES_SERVICE}/commodity`,
    method: 'get',
    errorDefined: {
      404: `数据未找到`
    }
  })
}

/**
 * 获取单个商品
 */
export function getCommodityById(id) {
  return request({
    url: `${SALES_SERVICE}/commodity/${id}`,
    method: 'get',
    errorDefined: {
      404: `数据未找到`
    }
  })
}

/**
 * 购买商品
 * @param id
 */
export function buyCommodity() {
  return request({
    url: `${SALES_SERVICE}/order`,
    method: 'post',
    data: {
      commodity: 1,
      address: 'address',
      amount: 99
    }
  })
}

