import Vue from 'vue'
import VueRouter from 'vue-router'

// 引入views里面的.vue文件
import Home from '../views/home/Home.vue'
// import User from '../views/mine/User.vue'
import Login from '../views/login/login'
import Charts from '../views/charts/monitor_charts'
import Business from '../views/charts/business_charts'
// 配置bootstrap/echarts

import '../../static/css/sb-admin-2.css'
import '../../static/css/dashboard.css'
import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap/dist/css/bootstrap.min.css'
import 'jquery/dist/jquery.js'
import 'bootstrap/dist/js/bootstrap.js'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',//路径
    name: 'Home',//vue的名字
    component: Home,//组件的名字
  },
  {
    path: '/login',//路径
    name: 'Login',//vue的名字
    component: Login,//组件的名字
  },
  {
    path: '/charts',//路径
    name: 'Charts',//vue的名字
    component: Charts,//组件的名字
  },
  {
    path: '/business_charts',//路径
    name: 'Business_charts',//vue的名字
    component: Business//组件的名字
  },
  // {
  //   path: '/classfy',//路径
  //   name: 'Classfy',//vue的名字
  //   component: Classfy//组件的名字
  // },
  // {
  //   path: '/user',//路径
  //   name: 'User',//vue的名字
  //   component: User,//组件的名字
  //
  // },
  // {
  //   path: '/user',
  //   name: '/User',
  //   component: User,
  //   children: [
  //     {
  //       path: '/user/login',
  //       name: 'Login',
  //       component: Login
  //     },
  //     {
  //       path: '/user/reg',
  //       name: 'Reg',
  //       component: Reg
  //     },
  //     {
  //       path: '/user/changepwd',
  //       name: 'Changepwd',
  //       component: Changepwd
  //     }
  //   ]
  // },
]

const router = new VueRouter({
  routes,
})

export default router
