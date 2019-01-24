/*eslint no-param-reassign: 0*/
/*eslint no-nested-ternary: 0*/
import _ from 'lodash'
import { createService, clientAuthInfo } from './request-service'
import NProgress from 'nprogress' // Progress 进度条
import { MessageBox } from 'mint-ui'
import JWT from 'jwt-simple'
import store from '../store'
import { getToken } from '@/utils/auth'
import { isEmptyString } from '@/utils/validate'

/**
 * 创建axios实例
 * 调用时如果需要隐藏统一的错误提示，需要手动抛出错误并且设置hiddenDefaultAlert=true
 * @type {AxiosInstance}
 */
const service = createService()

let reloginMessageBoxIsShow = false

// request拦截器
service.interceptors.request.use(
  (config) => {
    if (store.getters.token) {
      const payload = JWT.decode(store.getters.token, '', true)
      const now = _.now() // + store.getters.synchronousMete.difference
      if (now >= payload.exp * 1000) {
        /* store.dispatch('LogOut').then(() => {
          location.reload() // 为了重新实例化vue-router对象 避免bug
        })
        store.dispatch('LogOut')*/
        const error = new Error('登陆已过期，请重新登陆')
        error.hiddenDefaultAlert = true
        if (reloginMessageBoxIsShow === false) {
          reloginMessageBoxIsShow = true
          MessageBox.alert('会话过期')
        }
        /* store.dispatch('LogOut').then(() => {
          window.setTimeout(() => { location.reload() })
        })*/
        throw error
      } else if (now >= (payload.exp - (60 * 10)) * 1000) {
        // 在Token即将过期的前10分钟进行刷新Token操作
        store.dispatch('RefreshToken').then(() => {})
      }
      const token = getToken()
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
    console.error('error =>', error)
    if (error.hiddenDefaultAlert !== true) {
      let msg = error.response == null ? error.message : ((error.response.data != null && error.response.data.message != null && error.response.data.message.trim() !== '') ? error.response.data.message : (error.response.data.error_description != null ? error.response.data.error_description : error.response.status))
      if (error.response) {
        if (error.response.status === 401) {
          msg = `登陆信息过期，请重新登陆`
        } else if (error.response.status === 400 && (msg.trim() === 'Bad credentials' || msg.trim() === 'User account is locked')) {
          msg = '身份认证失败,请确认你的账号密码'
        } else if (error.response.status === 400 && msg.trim() === 'User account has expired') {
          msg = '身份过期，请联系管理员处理'
        } else if (error.response.status >= 500 || error.response.data.error === 'Internal Server Error') {
          msg = `网络连接异常，请重试。刷新页面或许可以解决这个问题。`
        } else if (error.response.status === 404) {
          msg = `网络连接异常，请重试.刷新页面或许可以解决这个问题。`
        } else {
          msg = `操作失败，请重试(${msg})`
        }
      }
      MessageBox.alert(msg)
    }
    NProgress.done()
    return Promise.reject(error)
  }
)

export default service
