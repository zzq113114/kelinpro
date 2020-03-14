// 生产环境中注释掉以下语句
import 'sysStatic/css/theme-dark.scss'
import '../mock/index.js'

import 'babel-polyfill'
import Vue from "vue"
import ElementUI from 'element-ui'
import router from './router'
import store from './store'
// import axios from './util/ajax'
import axios from 'axios'
import i18n from './util/i18n'
import App from './index'

import './components/install'
import './plugins/install'
//---引入全局配置----
import global_ from './global';

// 注册组件到Vue
// Vue.prototype.$axios = axios
Vue.prototype.$ajax = axios
Vue.use(ElementUI, {
    i18n: (key, value) => i18n.t(key, value)
})
Vue.prototype.GLOBAL = global_;

new Vue({
    i18n,
    axios,
    router,
    store,
    render: h => h(App)
}).$mount('#app')