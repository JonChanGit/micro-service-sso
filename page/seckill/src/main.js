import Vue from 'vue'
import Mint from 'mint-ui'
import 'mint-ui/lib/style.css'
import App from './App.vue'
import router from './router'
import store from './store'
import Util from './utils/register'
import '@/styles/index.scss' // global css
import './registerServiceWorker'

Vue.use(Mint)
Vue.use(Util)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
