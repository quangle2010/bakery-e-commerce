import ProfileLayout from "../layouts/ProfileLayout.vue";
import UserLayout from "../layouts/UserLayout.vue";
import AddAddress from "../pages/user/AddAddress.vue";
import Address from "../pages/user/Address.vue";
import ChangePassword from "../pages/user/ChangePassword.vue";
import EditAddress from "../pages/user/EditAddress.vue";
import Favorite from "../pages/user/Favorite.vue";
import OrderDetail from "../pages/user/OrderDetail.vue";
import Orders from "../pages/user/Orders.vue";
import Profile from "../pages/user/Profile.vue";
export const userRoutes = [
  {
    path: "/",
    component: UserLayout,
    children: [
      {
        path: "user",
        component: ProfileLayout,
        children: [
          { path: "profile", component: Profile },
          { path: "orders", component: Orders },
          { path: "change-password", component: ChangePassword },
          { path: "order-detail/:id", component: OrderDetail },
          { path: "favorites", component: Favorite },
          { path: "address", component: Address },
          { path: "add-address", component: AddAddress },
          { path: "edit-address/:id", component: EditAddress },
        ],
      },
    ],
  },
];
