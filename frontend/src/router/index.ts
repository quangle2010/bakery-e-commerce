import { createRouter, createWebHistory } from "vue-router";
import { adminRoutes } from "./admin.routes";
import { userRoutes } from "./user.routes";
import { publicRoutes } from "./public.routes";
import { useAuthStore } from "../store/auth";
import Error from "../pages/user/Error.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    ...publicRoutes,
    ...userRoutes,
    ...adminRoutes,
    {
      path: "/403",
      component: Error,
    },
  ],
});

router.beforeEach(async (to, _from, next) => {
  const authStore = useAuthStore();

  if (!authStore.isInitialized) {
    await authStore.initialize();
  }

  if (to.path.startsWith("/admin")) {
    if (!authStore.isAuthenticated) {
      return next("/login");
    }
    if (authStore.user?.role !== "ROLE_ADMIN") {
      return next("/403");
    }
  }
  if (to.path.startsWith("/user")) {
    if (!authStore.isAuthenticated) {
      return next("/login");
    }
    if (authStore.user?.role !== "ROLE_USER") {
      return next("/403");
    }
  }
  if (to.path.startsWith("/cart") || to.path.startsWith("/payment")) {
    if (!authStore.isAuthenticated) {
      return next("/login");
    }
    if (authStore.user?.role !== "ROLE_USER") {
      return next("/403");
    }
  }


  next();
});

export default router;
