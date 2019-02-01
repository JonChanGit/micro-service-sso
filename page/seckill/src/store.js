/*eslint no-param-reassign: 0*/
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    /**
     * 登录信息
     */
    token: '',
    /**
     * 账户信息
     */
    account: {},
    /**
     * 需要购买的商品信息
     */
    buyCommodity: {}
  },
  mutations: {
    setToken: (state, token) => {
      state.token = token
    },
    setAccount: (state, account) => {
      Object.assign(state.account, account)
    },
    setBuyCommodity: (state, item) => {
      for (const key in item) {
        if (Object.prototype.hasOwnProperty.call(state.buyCommodity, key)) {
          delete state.buyCommodity[key]
        }
      }
      Object.assign(state.buyCommodity, item)
    }
  },
  actions: {
    SetToken({ commit }, token) {
      commit('setToken', token)
    },
    SetAccount({ commit }, account) {
      commit('setAccount', account)
    },
    SetBuyCommodity({ commit }, item) {
      commit('setBuyCommodity', item)
    }
  },
  getters: {
    token: state => state.token,
    account: state => state.account
  }
})
