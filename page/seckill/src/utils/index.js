/**
 * Created by jiachenpan on 16/11/18.
 */

export function parseTime(time, cFormat) {
  if (arguments.length === 0) {
    return null
  }
  const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if (('' + time).length === 10) time = parseInt(time) * 1000
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
    let value = formatObj[key]
    // Note: getDay() returns 0 on Sunday
    if (key === 'a') { return ['日', '一', '二', '三', '四', '五', '六'][value ] }
    if (result.length > 0 && value < 10) {
      value = '0' + value
    }
    return value || 0
  })
  return time_str
}

export function formatTime(time, option) {
  time = +time * 1000
  const d = new Date(time)
  const now = Date.now()

  const diff = (now - d) / 1000

  if (diff < 30) {
    return '刚刚'
  } else if (diff < 3600) {
    // less 1 hour
    return Math.ceil(diff / 60) + '分钟前'
  } else if (diff < 3600 * 24) {
    return Math.ceil(diff / 3600) + '小时前'
  } else if (diff < 3600 * 24 * 2) {
    return '1天前'
  }
  if (option) {
    return parseTime(time, option)
  } else {
    return (
      d.getMonth() +
      1 +
      '月' +
      d.getDate() +
      '日' +
      d.getHours() +
      '时' +
      d.getMinutes() +
      '分'
    )
  }
}

export function debounce(func, wait, immediate) {
  let timeout, args, context, timestamp, result

  const later = function() {
    // 据上一次触发时间间隔
    const last = +new Date() - timestamp

    // 上次被包装函数被调用时间间隔last小于设定时间间隔wait
    if (last < wait && last > 0) {
      timeout = setTimeout(later, wait - last)
    } else {
      timeout = null
      // 如果设定为immediate===true，因为开始边界已经调用过了此处无需调用
      if (!immediate) {
        result = func.apply(context, args)
        if (!timeout) context = args = null
      }
    }
  }

  return function(...args) {
    context = this
    timestamp = +new Date()
    const callNow = immediate && !timeout
    // 如果延时不存在，重新设定延时
    if (!timeout) timeout = setTimeout(later, wait)
    if (callNow) {
      result = func.apply(context, args)
      context = args = null
    }

    return result
  }
}

/**
 * 深度复制对象
 * @param target 复制目标
 * @param source 复制源对象
 * @returns {*} target
 */
export function deepCloneObject(target, source, deleteTargetProperty = false) {
  if (deleteTargetProperty === true) {
    const aKeys = Object.keys(target)
    for (const aItem of aKeys) {
      const bKeys = Object.keys(source)
      let hasKey = false
      for (const bItem of bKeys) {
        if (aItem === bItem) {
          hasKey = true
        }
      }
      if (!hasKey) {
        delete target[aItem]
      }
    }
    Object.assign(target, source)
    return target
  }
  const result = JSON.parse(JSON.stringify(source))
  Object.assign(target, result)
  return target
}

/**
 * 小数保留位数
 * 四舍五入
 * @param num
 * @param numberOfDigits
 */
export function fractional(num, numberOfDigits) {
  let bit = 10
  for (let x = 1; x < numberOfDigits; x++) {
    bit = bit * 10
  }
  const number = num * bit
  return Math.round(number) / (bit)
}

/**
 * 把数组中所有的元素加入Set中
 * @param target Set
 * @param source Array
 */
export function pushAllToSet(target, source) {
  for (const [, item] of source.entries()) {
    target.add(item)
  }
}

/**
 * 数组去重
 * @param array
 * @return {any[]}
 */
export function arrayRemoveDuplicates(...array) {
  const arr = []
  for (const [, item] of array.entries()) {
    arr.push(...item)
  }
  return Array.from(new Set(arr))
}

/**
 * Set 转 数组
 * @param setitem
 * @return {any[]}
 */
export function setToArray(... setitem) {
  const arr = []
  for (const [, item] of setitem.entries()) {
    arr.push(...Array.from(item))
  }
  return arrayRemoveDuplicates(arr)
}

/**
 * 冒泡排序（缓存 pos、双向遍历）
 * @param {Function(arr[i],arr[i + 1])} 提供比较方法，如果为空，则使用默认规则比较
 * @param {...Object} arr
 */
export function bubbleSortPlus(compare, ... arr) {
  const _bubbleSortPlusSwap = (index1, index2, arr) => {
    const tmp = arr[index1]
    arr[index1] = arr[index2]
    arr[index2] = tmp
  }
  let start = 0
  let end = arr.length - 1
  while (start < end) {
    let endPos = 0
    let startPos = 0
    for (let i = start; i < end; i++) {
      if (typeof compare !== 'function') {
        if (arr[i] > arr[i + 1]) {
          endPos = i
          _bubbleSortPlusSwap(i, i + 1, arr)
        }
      } else {
        if (compare(arr[i], arr[i + 1])) {
          endPos = i
          _bubbleSortPlusSwap(i, i + 1, arr)
        }
      }
    }
    end = endPos
    for (let i = end; i > start; i--) {
      if (typeof compare !== 'function') {
        if (arr[i - 1] > arr[i]) {
          startPos = i
          _bubbleSortPlusSwap(i, i + 1, arr)
        }
      } else {
        if (compare(arr[i], arr[i + 1])) {
          endPos = i
          _bubbleSortPlusSwap(i, i + 1, arr)
        }
      }
    }
    start = startPos
  }
  return arr
}
