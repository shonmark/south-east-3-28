<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4 offset-md-4">
        <div class="login-panel panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Please Sign In</h3>
          </div>
          <div class="panel-body">
            <form role="form">
              <fieldset>
                <div class="form-group">
                  <input v-model="username" class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                </div>
                <div class="form-group">
                  <input v-model="password" class="form-control" placeholder="Password" name="password" type="password" value="">
                </div>
                <div class="checkbox" align="left">

                  <div v-show="result" class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" @click="hide()"
                            aria-hidden="true">
                      &times;
                    </button>
                    用户名或密码错误！
                  </div>

                  <label>
                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                  </label>
                </div>

                <!-- Change this to a button or input when using this as a form -->
                <router-link to="/charts">
                  <a @click="login()" class="btn btn-lg btn-success btn-block">Login</a>
                </router-link>

              </fieldset>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import api from "@/utils/api";
export default {
  name: "login",
  data(){
    return {
      username:'',
      password:'',
      result:false,
    }
  },
  methods:{
    hide:function (){
      this.result=false
    },
    // 方法名：function(参数){方法体}，
    login:function(){
      var username = this.username
      var password = this.password
      // 请求后台的登录接口
      this.result = true
      axios({
        method:"post",
        url:"xxxxxxx",
        headers: {
          "Content-Type": "multipart/form-data"
        },
        withCredentials:true,
        data:formData
      }).then((res)=>{
        console.log(res);
      });

      // this.axios.post
    },
    reg:function(){

    }
  },
}
</script>

<style scoped>

</style>