import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'
import UserLayout from '../layouts/UserLayout.vue'
import Home from '../pages/user/Home.vue'
import Contact from '../pages/user/Contact.vue'
import About from '../pages/user/About.vue'
import Products from '../pages/user/Products.vue'
import Cart from '../pages/user/Cart.vue'
import Payment from '../pages/user/Payment.vue'
import Register from '../pages/user/Register.vue'
import Login from '../pages/user/Login.vue'
import ProfileLayout from '../layouts/ProfileLayout.vue'
import Profile from '../pages/user/Profile.vue'
import Orders from '../pages/user/Orders.vue'
import ChangePassword from '../pages/user/ChangePassword.vue'
import OrderDetail from '../pages/user/OrderDetail.vue'
const routes: RouteRecordRaw[] = [
    {
        path: '/',
        component: UserLayout,
        children: [
            { path: '', redirect: 'home' },
            { path: 'home', component: Home },
            { path: 'contact', component: Contact },
            { path: 'about-us', component: About },
            { path: 'products', component: Products },
            { path: 'cart', component: Cart },
            { path: 'payment', component: Payment },
            { path: 'register', component: Register },
            { path: 'login', component: Login },
            { path: 'user', component: ProfileLayout ,
                children: [
                    { path: 'profile', component: Profile },
                    { path: 'orders', component: Orders },
                    { path: 'change-password', component: ChangePassword },
                    { path: 'order-detail/:id', component: OrderDetail }
                ]
            },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
