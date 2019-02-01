<template>
  <div class="buy-commodity">
    <mt-cell title="商品名称" :label="$store.state.buyCommodity.name"></mt-cell>
    <mt-cell title="库存" :label="$store.state.buyCommodity.amount.toString()"></mt-cell>
    <mt-cell title="开始下单时间" :label="$store.state.buyCommodity.beginTime"></mt-cell>
    <mt-cell title="结束下单时间" :label="$store.state.buyCommodity.endTime"></mt-cell>
    <mt-cell title="价格" :label="$store.state.buyCommodity.price.toString()"></mt-cell>
    <mt-cell title="折扣价" :label="$store.state.buyCommodity.discountPrice.toString()"></mt-cell>

    <br/>
    <br/>
    <br/>
    <br/>
    <mt-field label="数量" placeholder="请输入数字" v-model="buyAmount" type="number">份</mt-field>
    <mt-button type="primary" @click.native="reduceBuyAmount">-</mt-button>
    <mt-button type="primary" style="float: right" @click.native="addBuyAmount">+</mt-button>

    <mt-button
      type="primary"
      size="large"
      style="position: fixed; bottom: 0.5%; left: 0"
      @click.native="buyNow">Buy Now</mt-button>
  </div>
</template>

<script>
import { buyCommodity } from '@/api/api'
/**
 * 下单页
 * @author Jon Chan
 * @date 2019/1/31 11:16
 */
export default {
  name: 'BuyCommodity',
  components: {},
  props: {},
  data() {
    return {
      msg: 'BuyCommodity',
      commodityId: 0,
      /**
       * 本次购买数量
       */
      buyAmount: 1,
      /**
       * 商品实际库存
       */
      commodityAmount: 10
    }
  },
  watch: {
    buyAmount(val, old) {
      if (val > this.commodityAmount) {
        this.buyAmount = old
        this.$toast('已达到当前库存上限(⓿_⓿)')
      } else if (val < 1) {
        this.buyAmount = old
        this.$toast('不能再少了(ಥ _ ಥ)')
      }
    }
  },
  computed: {},
  beforeCreate() {
  },
  created() {
    this.commodityId = this.$route.params.id
    this.commodityAmount = this.$store.state.buyCommodity.amount
  },
  mounted() {
  },
  beforeDestroy() {
    this.$store.dispatch('SetBuyCommodity', {})
  },
  methods: {
    addBuyAmount() {
      this.buyAmount += 1
    },
    reduceBuyAmount() {
      this.buyAmount -= 1
    },
    buyNow() {
      this.$toast('开始下单')
      buyCommodity({
        commodity: this.commodityId,
        address: 'empty',
        amount: this.buyAmount
      }).then(resp => console.log(resp))
    }
  }
}
</script>

<style lang="scss" scoped>
  .buy-commodity {
    text-align: left;
  }
</style>
