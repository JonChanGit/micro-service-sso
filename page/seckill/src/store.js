import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    /**
     * 登录信息
     */
    token: ''
  },
  mutations: {
    setToken: (state, token) => {
      state.token = token
    }
  },
  actions: {
    SetToken({ commit }, token) {
      commit('setToken', token)
    }
  },
  getters: {
    token: state => state.token
  }
})
