<template>
  <div class="home">
    <cell-list :data="cellList"/>
  </div>
</template>

<script>
import { getCommodity } from '@/api/api'
import CellList from '@/components/cell-list'

export default {
  name: 'Home',
  components: {
    CellList
  },
  beforeCreate() {
    getCommodity().then((resp) => {
      this.cellList.splice(0, this.cellList.length)
      this.cellList.push(...resp)
    })
  },
  data() {
    return {
      cellList: [
        {
          name: 'N1',
          beginTime: '2019年1月26日'
        },
        {
          name: 'N1',
          beginTime: '2019年1月26日'
        }
      ]
    }
  }
}
</script>
