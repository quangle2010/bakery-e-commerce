import AdminLayout from '../layouts/AdminLayout.vue'
import Dashboard from '../pages/admin/Dashboard.vue'
import Products from '../pages/admin/Products.vue'
import Orders from '../pages/admin/Orders.vue'
import Profile from '../pages/admin/Profile.vue'
import Category from '../pages/admin/Category.vue'
export const adminRoutes = [
    {
        path: "/admin", 
        component: AdminLayout,
        children: [
            { path: "", component: Dashboard },
            { path: "dashboard", component: Dashboard },
            { path: "products", component: Products },
            { path: "orders", component: Orders },
            { path: "profile", component: Profile },
            { path: "categories", component: Category }

        ]
    }
]