// main文件引入公共样式
// import './assets/main.css'
// 引入ElementPlus，配合vue3使用，ElementUI是配合vue2使用
import ElementPlus from 'element-plus'
// 这是ElementPlus的样式文件
import 'element-plus/dist/index.css'

// createApp：创建vue对象的函数
import { createApp } from 'vue'

// createPinia：创建pinia对象的函数
import { createPinia } from 'pinia'

// 引入首页
import App from './App.vue'

// 路由对象
import router from './router'

// 创建vue实例
const app = createApp(App)

// 使用插件
app.use(ElementPlus)
app.use(createPinia())
app.use(router)

// 挂载在vue-project/index.html中的 <div id="app"></div>
app.mount('#app')
