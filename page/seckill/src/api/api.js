import Qs from 'qs'
import request from '@/utils/request'
import { AUTHENTICATION_SERVICE } from './pre-uri'

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
