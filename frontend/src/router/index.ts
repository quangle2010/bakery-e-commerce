import { createRouter, createWebHistory } from 'vue-router'

import { adminRoutes } from './admin.routes'
import { userRoutes } from './user.routes'
import { publicRoutes } from './public.routes'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...publicRoutes,
    ...userRoutes,
    ...adminRoutes
  ]
})

export default router
