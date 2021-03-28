// js入口
import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 配置elementui
// import ElementUI from 'element-ui';
// import 'element-ui/lib/theme-chalk/index.css';
// Vue.use(ElementUI);

import * as echarts from 'echarts';
//  配置Axios
import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios,echarts)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

// 添加title
Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
})