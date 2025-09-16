import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/restaurants',
      component: () => import('../components/ui/RestaurantGrid.vue'),
    },
    {
      path: '/orders',
      component: () => import('../components/ui/OrderGrid.vue'),
    },
    {
      path: '/deliveries',
      component: () => import('../components/ui/DeliveryGrid.vue'),
    },
  ],
})

export default router;
