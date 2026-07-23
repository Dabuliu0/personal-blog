import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import ArticlesPage from '../pages/ArticlesPage.vue'
import AboutPage from '../pages/AboutPage.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: HomePage,
    },
    {
      path: '/articles',
      component: ArticlesPage,
    },
    {
      path: '/about',
      component: AboutPage,
    },
  ],
})

export default router