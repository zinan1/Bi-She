import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import schoolInformation from '../views/schoolInformation .vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'schoolInformation',
    component: schoolInformation
  },
  {
    path: '/AdminConsole',
    name: 'AdminConsole',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AdminConsole.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
