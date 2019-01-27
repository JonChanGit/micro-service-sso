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
    account: {}
  },
  mutations: {
    setToken: (state, token) => {
      state.token = token
    },
    setAccount: (state, account) => {
      Object.assign(state.account, account)
    }
  },
  actions: {
    SetToken({ commit }, token) {
      commit('setToken', token)
    },
    SetAccount({ commit }, account) {
      commit('setAccount', account)
    }
  },
  getters: {
    token: state => state.token,
    account: state => state.account
  }
})
