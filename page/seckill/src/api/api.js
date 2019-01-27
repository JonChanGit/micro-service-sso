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
