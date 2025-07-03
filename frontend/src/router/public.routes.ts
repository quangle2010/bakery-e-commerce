import UserLayout from "../layouts/UserLayout.vue";
import About from "../pages/user/About.vue";
import Cart from "../pages/user/Cart.vue";
import Contact from "../pages/user/Contact.vue";
import ForgotPassword from "../pages/user/ForgotPassword.vue";
import Home from "../pages/user/Home.vue";
import Login from "../pages/user/Login.vue";
import Payment from "../pages/user/Payment.vue";
import ProductDetail from "../pages/user/ProductDetail.vue";
import Products from "../pages/user/Products.vue";
import Register from "../pages/user/Register.vue";

export const publicRoutes = [
  {
    path: "/",
    component: UserLayout,
    children: [
      { path: "", redirect: "home" },
      { path: "home", component: Home },
      { path: "contact", component: Contact },
      { path: "about-us", component: About },
      { path: "products", component: Products },
      { path: "cart", component: Cart },
      { path: "payment", component: Payment },
      { path: "register", component: Register },
      { path: "login", component: Login },
      { path: "product-detail/:id", component: ProductDetail },
      { path: "forgot-password", component: ForgotPassword },
    ],
  },
];
