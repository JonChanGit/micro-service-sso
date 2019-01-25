<template>
  <div class="home">
    <cell-list :data="cellList"/>
  </div>
</template>

<script>
// @ is an alias to /src
import CellList from '@/components/cell-list'

export default {
  name: 'Home',
  components: {
    CellList
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
