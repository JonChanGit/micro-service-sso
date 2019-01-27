<template>
  <div class="login">
    <mt-field label="用户名" placeholder="用户名" v-model="username"></mt-field>
    <mt-field label="密码" placeholder="密码" v-model="password"></mt-field>
    <br/>
    <router-link to="/">
      无账户？
    </router-link>
    <br/>
    <mt-field label="当前Token" placeholder="未登录" type="textarea" rows="2" :value="$store.getters.token" readonly></mt-field>
    <mt-field label="Token解码" placeholder="未登录" type="textarea" rows="8" :value="tokenStr" readonly></mt-field>
    <mt-field label="账户信息" placeholder="未登录" type="textarea" rows="8" :value="account" readonly></mt-field>

    <mt-button class="login-btn" size="large" type="primary" @click.native="handleLoginClick">登陆</mt-button>
  </div>
</template>

<script>
import JWT from 'jwt-simple'
import { login, getAccount } from '@/api/api'
/**
 * login
 * @author Jon Chan
 * @date 2019/1/24 15:00
 */
export default {
  name: 'Login',
  components: {},
  props: {},
  data() {
    return {
      msg: 'login',
      username: 'super',
      password: '12345678',
      account: ''
    }
  },
  watch: {
    '$store.state.account': {
      handler(val) {
        console.log(val)
        if (Object.getOwnPropertyNames(val).length === 0) {
          this.account = ''
        }
        this.account = JSON.stringify(val)
      },
      deep: true
    }
  },
  computed: {
    tokenStr() {
      if (this.$isEmptyString(this.$store.getters.token)) {
        return ''
      }
      const payload = JWT.decode(this.$store.getters.token, '', true)
      return JSON.stringify(payload)
    }
  },
  beforeCreate() {
  },
  mounted() {
  },
  methods: {
    handleLoginClick() {
      this.$store.dispatch('SetToken', '')
      login(this.username, this.password).then((resp) => {
        this.$store.dispatch('SetToken', resp.access_token)
        getAccount(resp.userId).then((account) => {
          this.$store.dispatch('SetAccount', account)
        })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .login {
    .login-btn{
      position: absolute;
      top: 80%;
    }
  }
</style>
