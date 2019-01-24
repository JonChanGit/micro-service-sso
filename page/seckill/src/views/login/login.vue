<template>
  <div class="login">
    <mt-field label="用户名" placeholder="用户名" v-model="username"></mt-field>
    <mt-field label="密码" placeholder="密码" v-model="password"></mt-field>
    <br/>
    <router-link to="/">
      无账户？
    </router-link>
    <br/>
    <mt-field label="当前Token" placeholder="未登录" type="textarea" rows="2" v-model="token"></mt-field>
    <mt-field label="Token解码" placeholder="未登录" type="textarea" rows="6" v-model="token"></mt-field>

    <mt-button class="login-btn" size="large" type="primary" @click.native="handleLoginClick">登陆</mt-button>
  </div>
</template>

<script>
import { login } from '@/api/api'
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
      token: ''
    }
  },
  computed: {},
  beforeCreate() {
  },
  mounted() {
  },
  methods: {
    handleLoginClick() {
      login(this.username, this.password).then(resp => {
        console.log(resp)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .login {
    .login-btn{
      position: absolute;
      top: 54%;
    }
  }
</style>
