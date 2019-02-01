// 注册方法到this
import _ from 'lodash' // https://www.lodashjs.com/docs/4.17.5.html
import { Toast } from 'mint-ui'
import { isEmptyString, isEmpty } from './validate'
import {
  deepCloneObject, fractional, pushAllToSet, arrayRemoveDuplicates, setToArray, bubbleSortPlus
} from './index'

const rexNumber = /^[0-9]+$/

export default {
  install(Vue, options) {
    Vue.prototype.$_ = _
    Vue.prototype.$toast = Toast
    Vue.prototype.$fractional = fractional
    Vue.prototype.$isEmpty = isEmpty
    Vue.prototype.$pushAllToSet = pushAllToSet
    Vue.prototype.$bubbleSortPlus = bubbleSortPlus
    Vue.prototype.$arrayRemoveDuplicates = arrayRemoveDuplicates
    Vue.prototype.$setToArray = setToArray
    Vue.prototype.$isEmptyOrZero = function(val) {
      if (isEmpty(val)) {
        return true
      }
      if (val === 0) {
        return true
      }
      return false
    }
    Vue.prototype.$isEmptyString = isEmptyString
    Vue.prototype.$success = function(msg) {
      this.$message({
        message: msg,
        type: 'success'
      })
    }
    Vue.prototype.$error = function(msg) {
      this.$message({
        message: msg,
        type: 'error'
      })
    }
    /**
     * 判断字符串是否是数字
     * @param val
     * @returns {boolean}
     */
    Vue.prototype.$isNumberByString = function(val) {
      val += ''
      return rexNumber.test(val)
    }
    Vue.prototype.$getLoginUser = function() {
      const loginUser = this.$store.state.user.loginUser
      if (isEmpty(loginUser)) {
        return null
      }
      if (isEmptyString(loginUser.username)) {
        return null
      }
      return loginUser
    }
    /**
     * 根据传入的数据返回该数据对应类型的默认值
     * 只处理基础数据类型
     */
    Vue.prototype.$getDefaultValue = function(obj) {
      const _type = typeof obj
      switch (_type) {
        case 'string':
          return ''
        case 'number':
          return 0
      }
      return obj
    }
    /**
     * 深度赋值对象，target为模板目标
     * 若source中不存在的property从target中删除
     * @param target
     * @param objB
     */
    Vue.prototype.$deepCloneObject = function(target, source, deleteTargetProperty = true) {
      deepCloneObject(target, source, deleteTargetProperty)
    }
  }
}
