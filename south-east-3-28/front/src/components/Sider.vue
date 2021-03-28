<template>
  <div class="nav sidebar flex-column nav-pills bg-light" id="v-pills-tab" style="padding-right: 0px;border-right-width: 0px;"
       role="tablist" aria-orientation="vertical">
<!--    侧边菜单-->
    <a href="#" v-for="item in sider" class="nav-link" @click="addClass(index,item.link)"
       v-bind:class="{ active:item.id==current }">
      <i class="bi bi-bar-chart-line"></i>
        {{ item.name }}
    </a>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Sider",
  props:{
    current:String,
  },

  mounted() {
    axios
      .get('http://localhost/url/sider')
      .then(response => (this.sider = response.data))
  },
  data(){
    return {
      sider:[
        {
          "id": 1,
          "name": "监控指标",
          "link": "charts"
        },
        {
          "id": 2,
          "name": "业务指标",
          "link": "business_charts"
        },
        // {
        //   "id": 3,
        //   "name": "参数设置",
        //   "link": null
        // },
        // {
        //   "id": 4,
        //   "name": "待定",
        //   "link": null
        // }
      ],
      items:[
        { message:{
            name:"监控指标",
            link:"/charts",
          } },
        { message:{
            name:"业务指标",
            link:"/business_charts",
          }},
        { message:{
            name:"参数设置",
            link:"",
          }},
        { message:{
            name:"待定",
            link:"",
          }},
          ],

    }
  },
  methods:{
    addClass:function(index,link){

      // this.current = index
      this.$router.push({path:link}).catch(err=>{
        console.log(err)

      });
    },


  },
}
</script>

<style scoped>
.sidebar{
  top: 0px;
}
.bg{
  color: #fff;
  background-color: #007bff;
}
</style>