import AdminLayout from '../layouts/AdminLayout.vue'
import Dashboard from '../pages/admin/Dashboard.vue'
import Products from '../pages/admin/Products.vue'
import Orders from '../pages/admin/Orders.vue'
import Profile from '../pages/admin/Profile.vue'
import Categories from '../pages/admin/Categories.vue'
import OrderDetail from '../pages/admin/OrderDetail.vue'
export const adminRoutes = [
    {
        path: "/admin", 
        component: AdminLayout,
        meta: { requiresAuth: true, role: 'ROLE_ADMIN' },
        children: [
            { path: "", component: Dashboard },
            { path: "dashboard", component: Dashboard },
            { path: "products", component: Products },
            { path: "orders", component: Orders },
            { path: "order/:id", component: OrderDetail },
            { path: "profile", component: Profile },
            { path: "categories", component: Categories }

        ]
    }
]