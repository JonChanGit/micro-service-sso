import Cookies from 'js-cookie'

const TOKEN_KEY = 'tk'
const REFRESH_KEY = 'refresh'

export function getToken() {
  return Cookies.get(TOKEN_KEY)
}

export function getRefresh() {
  return Cookies.get(REFRESH_KEY)
}

export function setToken(token, refresh) {
  Cookies.set(REFRESH_KEY, refresh)
  return Cookies.set(TOKEN_KEY, token)
}

export function removeToken() {
  Cookies.remove(REFRESH_KEY)
  return Cookies.remove(TOKEN_KEY)
}
