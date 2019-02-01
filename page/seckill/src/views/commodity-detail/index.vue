<template>
  <div class="commodity-detail">
    <div class="banner-box">
      <div class="banner-bg" :style="{background: '#2e2e2e no-repeat center url('+imageUri+')'}"/>
      <div class="banner-content">
        <img alt="Vue logo" v-lazy="imageUri">
        <h3>{{detail.name}}</h3>
        <h5>活动时间：{{detail.beginTime}}-{{detail.endTime}}</h5>
        <h5>售价：{{detail.price}}</h5>
        <h5>折扣价：{{detail.discountPrice}}</h5>
        <h5>数量：{{detail.amount}}</h5>
      </div>
    </div>
    <div class="buy-btns">
      <mt-button size="normal" class="concern-btn" @click.native="handleConcernClick">
        <img src="@/assets/icon/concern.png" height="20" width="20" slot="icon">
        关注
      </mt-button>
      <mt-button size="normal" class="buy-btn" @click.native="handleBuyClick()">Buy</mt-button>
    </div>
  </div>
</template>

<script>
import { MessageBox } from 'mint-ui'
import { getCommodityById } from '@/api/api'

/**
   * commodity-detail
   * @author Jon Chan
   * @date 2019/1/26 2:34
   */
export default {
  name: 'CommodityDetail',
  components: {},
  props: {},
  data() {
    return {
      id: 0,
      msg: 'commodity-detail',
      imageUri: 'https://cn.bing.com/az/hprichbg/rb/KukeriCostume_ZH-CN7695643694_1920x1080.jpg',
      detail: {
        id: 0,
        amount: 0,
        price: 0,
        name: 0,
        beginTime: 0,
        endTime: 0,
        discountPrice: 0
      }
    }
  },
  computed: {},
  beforeCreate() {},
  created() {
    getCommodityById(this.$route.params.id).then((resp) => {
      Object.assign(this.detail, resp)
      this.id = this.$route.params.id
    })
  },
  mounted() {
  },
  methods: {
    handleConcernClick() {
      MessageBox.confirm('喜欢就在Github上Star吧').then(() => {
        window.open('https://github.com/JonChanGit/micro-service-sso')
      }).catch(() => {
      })
    },
    handleBuyClick() {
      this.$store.dispatch('SetBuyCommodity', this.detail)
      this.$router.push({ name: 'BuyCommodity', params: { id: this.id }})
    }
  }
}
</script>

<style lang="scss" scoped>
  .commodity-detail {
    text-align: left;
    position: relative;
    .banner-box {
      position: relative;
      height: 300px;
      .banner-bg {
        position: absolute;
        width: 100%;
        height: 300px;
        margin: auto;
        position: relative;
        filter: blur(25px);
      }
      .banner-content {
        padding: 1rem 0;
        position: absolute;
        top: 0;
        color: #50b5ca;
        img {
          width: 60px;
          height: 60px;
          filter: blur(0);
        }
        img[lazy=loading] {
          width: 60px;
          height: 60px;
          margin: auto;
          background-color: #2e2e2e;
        }
      }
    }
    .buy-btns {
      position: fixed;
      bottom: 0;
      left: 0;
      width: 100%;
      padding: 0;
      margin: 0;
      .concern-btn {

      }
      .buy-btn {
        position: absolute;
        right: 0.3rem;
        background-color: #ef4f4f;
        color: #f8f8f5;
      }
    }
  }
</style>
