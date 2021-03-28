<template>
    <div class="navbar fixed-top navbar-expand-lg  navbar-light bg-dark" style="
    padding-left: 0px;
    padding-right: 0px;">
      <a class="navbar-brand text-white" href="#" style="margin-left: 20px">Bank</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse"
              data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
              aria-expanded="false" aria-label="Toggle navigation">
<!--        <span class="navbar-toggler-icon"></span>-->
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
<!--          <li class="nav-item active">-->
<!--            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <a class="nav-link" href="#">Link</a>-->
<!--          </li>-->
<!--          <li class="nav-item dropdown">-->
<!--            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">-->
<!--              Dropdown-->
<!--            </a>-->
<!--            <div class="dropdown-menu" aria-labelledby="navbarDropdown">-->
<!--              <a class="dropdown-item" href="#">Action</a>-->
<!--              <a class="dropdown-item" href="#">Another action</a>-->
<!--              <div class="dropdown-divider"></div>-->
<!--              <a class="dropdown-item" href="#">Something else here</a>-->
<!--            </div>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>-->
<!--          </li>-->
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-success my-2 my-sm-0" type="submit">Search</button>
        </form>
<!--        头像+用户名-->
        <div class="dropdown">
          <button class="btn btn-default dropdown-toggle" type="button" style="margin: 0px;padding: 0px;"
                  id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="false" aria-expanded="false">
            <img class="img-thumbnail rounded-circle user-img .img-fluid" src='../../static/pic/1.jpg' alt="头像"/>
            <span class="text-white"
                  style="width: 5em;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">Chris-yihao</span>
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="margin: 0px;padding: 0px;">
            <router-link to="/login">
              <a class="dropdown-item" href="#">登出</a>
            </router-link>
          </div>
        </div>

<!--        <div @click="clickUser()" @mouseleave="leaveUser()" style="color: black;margin: 0px 20px 0px 20px;">-->
<!--          <img class="img-thumbnail rounded-circle user-img .img-fluid" src='../../static/pic/1.jpg' alt="头像"/>-->
<!--          <span class="text-white" style="width: 5em;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">Chris-yihao</span>-->
<!--        </div>-->
      </div>
    </div>
</template>

<script>

export default {
  name: 'Header',
  data(){
    return {}
  },
  props:{
    flag:String
  },
  methods:{
    clickUser(){
      this.active = 'width: 48px;height: 48px;'
    },
    leaveUser(){

    },

  },
}
</script>

<style>
button:focus

{
  outline: none;
  box-shadow:none;
}

.user-img{
  width: 45px;
  height: 45px;
  margin-right: 5px;
  margin-left: 5px;
  background-position: center;
  background-size: 100% 100%;
}
</style>
