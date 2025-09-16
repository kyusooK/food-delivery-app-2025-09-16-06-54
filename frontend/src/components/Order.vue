<template>
    <div>
        <Number
            label="OrderId"
            v-model="value.orderId"
            :editMode="editMode"
        />
        <Number
            label="CustomerId"
            v-model="value.customerId"
            :editMode="editMode"
        />
        <v-divider class="border-opacity-50 my-divider my-2"></v-divider>
        <div variant="outlined" class="my-2">
            <String
                label="MenuItems"
                v-model="menuItemsInput"
                :editMode="editMode"
                @keydown.enter="addMenuItemsList"
            />
            <v-card v-if="value.menuItems.length > 0" variant="outlined" class="pa-4 mt-2">
                <v-card-sub-title>
                    MenuItems List
                </v-card-sub-title>
                <li v-for="(name, index) in value.menuItems" :key="index">
                    {{ name }}
                </li>
            </v-card>
            <v-row class="ma-0 pa-0 mt-2">
                <v-spacer></v-spacer>
                <v-btn @click="addMenuItemsList">
                    MenuItems 추가
                </v-btn>
            </v-row>
            
            <StringDetailGrid
                label="MenuItems"
                offline
                v-model="value.menuItems"
                :editMode="editMode"
                @change="change"
            />
        </div>
        <v-divider class="border-opacity-50 my-divider my-2"></v-divider>
        <Number
            label="OrderAmount"
            v-model="value.orderAmount"
            :editMode="editMode"
        />
        <Number
            label="MinimumOrderAmount"
            v-model="value.minimumOrderAmount"
            :editMode="editMode"
        />
        <String
            label="PaymentId"
            v-model="value.paymentId"
            :editMode="editMode"
        />
        <Date
            label="OrderCreatedAt"
            v-model="value.orderCreatedAt"
            :editMode="editMode"
        />
        <Date
            label="OrderUpdatedAt"
            v-model="value.orderUpdatedAt"
            :editMode="editMode"
        />
        <Date
            label="OrderCancelledAt"
            v-model="value.orderCancelledAt"
            :editMode="editMode"
        />
        <String
            label="CancelReason"
            v-model="value.cancelReason"
            :editMode="editMode"
        />
        <String
            label="RejectionReason"
            v-model="value.rejectionReason"
            :editMode="editMode"
        />
        <OrderStatus
            offline
            label="OrderStatus"
            v-model="value.orderStatus"
            :editMode="editMode"
            @change="change"
        />
        <PaymentStatus
            offline
            label="PaymentStatus"
            v-model="value.paymentStatus"
            :editMode="editMode"
            @change="change"
        />
        <RestaurantId
            offline
            label="음식점 ID"
            v-model="value.restaurantId"
            :editMode="editMode"
            @change="change"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Order',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "orders",
        menuItemsInput: null,
        value: {
            menuItems: [],
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
        addMenuItemsList() {
            if (this.menuItemsInput !== null && this.menuItemsInput !== '') {
                this.value.menuItems.push(this.menuItemsInput);
                this.menuItemsInput = null; // null로 초기화
            }
        },
    },
}
</script>
