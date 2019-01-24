<template>
  <div id="app">
    <mt-header fixed title="登陆后进行下单测试→">
      <router-link to="/" slot="left">
        <mt-button icon="back">首页</mt-button>
      </router-link>
      <mt-button slot="right" @click.native="handleAccountClick">
        <img src="./assets/icom/account.png" height="20" width="20" slot="icon">
      </mt-button>
    </mt-header>
    <div id="nav" v-if="false">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <div class="content">
      <router-view/>
    </div>
  </div>
</template>

<script>
/**
   * App
   * @author Jon Chan
   * @date 2019/1/24 15:00
   */
export default {
  name: 'App',
  components: {},
  props: {},
  data() {
    return {}
  },
  computed: {},
  beforeCreate() {
  },
  mounted() {
  },
  methods: {
    handleAccountClick() {
      this.$router.push({ name: 'Login' })
    }
  }
}
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 40px;
  .content{
    margin: 0 .5rem;
  }
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
