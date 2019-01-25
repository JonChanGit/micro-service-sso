<template>
    <div class="commodity-detail">
      <div class="banner-box">
        <div class="banner-bg" :style="{background: '#2e2e2e no-repeat center url('+imageUri+')'}"/>
        <div class="banner-content">
          <img alt="Vue logo" v-lazy="imageUri">
          <h3>title</h3>
          <h5>活动时间：2019年1月26日-2019年1月26日</h5>
        </div>
      </div>
      <div class="buy-btns">
        <mt-button size="normal" class="concern-btn">
          <img src="@/assets/icon/concern.png" height="20" width="20" slot="icon">
          关注
        </mt-button>
        <mt-button size="normal" class="buy-btn">Buy</mt-button>
      </div>
    </div>
</template>

<script>
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
      msg: 'commodity-detail',
      imageUri: 'https://cn.bing.com/az/hprichbg/rb/KukeriCostume_ZH-CN7695643694_1920x1080.jpg'
    }
  },
  computed: {},
  beforeCreate() {
  },
  mounted() {
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
.commodity-detail {
  text-align: left;
  position: relative;
  .banner-box{
    position: relative;
    height: 300px;
    .banner-bg{
      position: absolute;
      width: 100%;
      height: 300px;
      margin: auto;
      position: relative;
      filter: blur(25px);
    }
    .banner-content{
      padding: 1rem 0;
      position: absolute;
      top: 0;
      color: #cacaca;
      img{
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
  .buy-btns{
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    padding: 0;
    margin: 0;
    .concern-btn{

    }
    .buy-btn{
      position: absolute;
      right: 0.3rem;
      background-color: #ef4f4f;
      color: #f8f8f5;
    }
  }
}
</style>
