/*eslint no-param-reassign: 0*/
/*eslint no-nested-ternary: 0*/
import { createService, clientAuthInfo } from './request-service'
import NProgress from 'nprogress' // Progress 进度条
import { MessageBox } from 'mint-ui'
import store from '../store'
import { isEmptyString } from '@/utils/validate'

/**
 * 默认错误提示定义
 * @type {{}}
 */
const defaultErrorDefined = {
  500: '访问异常，请重试',
  401: '请登录后操作'
}

/**
 * 创建axios实例
 * 调用时如果需要隐藏统一的错误提示，需要手动抛出错误并且设置hiddenDefaultAlert=true
 * @type {AxiosInstance}
 */
const service = createService()

// request拦截器
service.interceptors.request.use(
  (config) => {
    const { token } = store.getters
    if (token) {
      if (!isEmptyString(token)) {
        config.headers.Authorization = `Bearer ${token}` // 让每个请求携带自定义token 请根据实际情况自行修改
      } else {
        config.auth = clientAuthInfo
      }
    } else {
      config.auth = clientAuthInfo
    }
    NProgress.start()
    return config
  },
  (error) => {
    // Do something with request error
    console.error(error) // for debug
    Promise.reject(error)
    NProgress.done()
  }
)

// response 拦截器
service.interceptors.response.use(
  (response) => {
    NProgress.done()
    /**
     * code为非0是抛错 可结合自己业务进行修改
     */
    const res = response.data
    if (res.code == null) {
      return response.data.responseBody == null ? response.data : response.data.responseBody
    }
    if (res.code !== 0) {
      MessageBox.alert(res.message)
      // 以下Token判断未实现
      // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录')
      }
      return Promise.reject(new Error('error'))
    }
    return response.data.responseBody == null ? response.data : response.data.responseBody
  },
  (error) => {
    if (error.hiddenDefaultAlert !== true) {
      let msg = null
      if (error.response && error.response.status) {
        if (error.config.errorDefined && error.config.errorDefined[error.response.status]) {
          msg = error.config.errorDefined[error.response.status]
        } else {
          msg = defaultErrorDefined[error.response.status]
        }
      }
      MessageBox.alert(`${msg}`)
    }
    NProgress.done()
    return Promise.reject(error)
  }
)

export default service
