<template>
  <v-container fluid>
    <v-row>
      <v-card
          v-for="(card, index) in cards"
          :key="index"
          class="mx-auto"
          style="height:300px; width:300px; margin-bottom:20px;"
          outlined
      >
        <v-list-item>
          <v-icon style="margin-top: 30%; margin-left: 37.5%;" color="primary" size="64">mdi-apps</v-icon>
        </v-list-item>
        <v-card-actions>
          <v-btn
              class="mx-auto"
              outlined
              rounded
              :to="card.link"
              style="font-weight:500; font-size:20px; border:solid 2px; max-width:250px; overflow:hidden; margin-top: 10%;"
          >
              {{ card.text }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'Index',
  data: () => ({
    cards: [
      {
          text: "음식점",
          link: "/restaurants",
      },
      {
          text: "메뉴 목록 조회",
          link: "/menuLists",
      },
      {
          text: "메뉴 상세 조회",
          link: "/menuDetails",
      },
      {
          text: "주문",
          link: "/orders",
      },
      {
          text: "배달",
          link: "/deliveries",
      },
      {
          text: "주문 상세 조회",
          link: "/orderDetails",
      },
      {
          text: "주문 목록 조회",
          link: "/orderLists",
      },
      {
          text: "배달원 배달 목록 조회",
          link: "/deliveryListForRiders",
      },
      {
          text: "배달 상태 상세 조회",
          link: "/deliveryStatusDetails",
      },
    ],
  }),
};
</script>
