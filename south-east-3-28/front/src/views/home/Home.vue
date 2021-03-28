<template>
  <div class="home">
    <Header :flag="username"></Header>
        <Sider></Sider>


  </div>
    <!-- html -->
    <!-- {{显示数据}} -->

</template>

<script>
import Header from "../../components/Header.vue"
import Sider from "@/components/Sider"
import api from '../../utils/api.js'
export default {
  // 名字
  name: 'Home',
  // 数据
  data(){
    return {

      age:"18",
      dog:{name:"嘟嘟"},
      lists:[],
      btn:true,
      username:'大傻瓜',
      password:''
    }
  },
  // 引入(注册)组件
  components:{
    Sider,
    Header
  },
  // 方法
  methods:{
    // 方法名：function(参数){方法体}，
    login:function(){
      var username = this.username
      var password = this.password
      // 请求后台的登录接口
     
      var path1 = api.api+"/user/login?username="+ username+"&passwword="+password
      var path = api.api+"/user/list"
      var json = "a.json"
      this.axios.get(path).then(response=>{
        console.log(response)
        this.lists = response.data
      }) 




      // this.axios.post
    },
    reg:function(){

    }
  },
  // 生命周期方法
  // 名字固定
  // 在当前页面的某个状态：自动执行
  beforeMount(){

  },
  mounted(){
    // 数据请求 当前需要的数据

  },
}
</script>

<style>
.home{

}
</style>
