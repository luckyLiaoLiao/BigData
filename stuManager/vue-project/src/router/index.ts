import { createRouter, createWebHistory } from 'vue-router'
import HomeView from  '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // {
    //   path: '/',  // '/'表示默认进入的页面地址
    //   name: 'home', // 视图名称，也是打开视图的一种方式
    //   component: HomeView, //path 或 name对应的组件
    // },
    {
      path: '/',  // '/'表示默认进入的页面地址
      name: 'login', // 视图名称，也是打开视图的一种方式
      component: LoginView, //path 或 name对应的组件
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      // 懒加载引入组件，推荐这种方式
      component: () => import('../views/AboutView.vue'),
    },
  ],
})

export default router
