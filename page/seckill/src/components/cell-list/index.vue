<template>
  <div class="cell-list">
    <mt-cell
      :title="item.name"
      v-for="(item, idx) in data"
      :key="idx"
      is-link
      @click.native="handleItemClick(item)">
      <span>{{item.beginTime}}开始</span>
      <img slot="icon" src="@/assets/logo.png" width="60" height="60">
    </mt-cell>
  </div>
</template>

<script>
/**
   * index
   * @author Jon Chan
   * @date 2019/1/26 2:19
   */
export default {
  name: 'CellList',
  components: {},
  props: {
    data: {
      type: Array,
      default() {
        return []
      }
    }
  },
  data() {
    return {
      msg: 'cell-list'
    }
  },
  computed: {},
  beforeCreate() {
  },
  mounted() {
  },
  methods: {
    handleItemClick(item) {
      this.$router.push({ name: 'CommodityDetail', params: { id: item.id }})
    }
  }
}
</script>

<style lang="scss" scoped>
  .cell-list {
    text-align: left;
  }
</style>
