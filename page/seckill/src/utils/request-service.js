import axios from 'axios'

export function createService() {
  const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API // api 的 base_url
    // timeout: 5000 // 请求超时时间
  })
  return service
}

export const clientAuthInfo = {
  username: 'clientapp',
  password: '12345678'
}
