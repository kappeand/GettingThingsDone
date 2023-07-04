import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import Tabs from '@/views/Tabs.vue'
import Login from '@/views/Login.vue'
import ArchivePage from "@/views/ArchivePage.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/tabs/',
    component: Tabs,
    children: [
      {
        path: '',
        redirect: '/tabs/inbox'
      },
      {
        path: 'inbox',
        component: () => import('@/views/InboxPage.vue')
      },
      {
        path: 'projects',
        component: () => import('@/views/ProjectsPage.vue')
      },
      {
        path: 'archive',
        component: () => import('@/views/ArchivePage.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
