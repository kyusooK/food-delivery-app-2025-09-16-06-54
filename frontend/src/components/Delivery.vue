<template>
    <div>
        <Number
            label="DeliveryId"
            v-model="value.deliveryId"
            :editMode="editMode"
        />
        <Number
            label="DeliveryRiderId"
            v-model="value.deliveryRiderId"
            :editMode="editMode"
        />
        <Date
            label="DeliveryAcceptedAt"
            v-model="value.deliveryAcceptedAt"
            :editMode="editMode"
        />
        <Date
            label="DeliveryPickedUpAt"
            v-model="value.deliveryPickedUpAt"
            :editMode="editMode"
        />
        <Date
            label="DeliveryCompletedAt"
            v-model="value.deliveryCompletedAt"
            :editMode="editMode"
        />
        <Boolean
            label="DeliveryNotificationSent"
            v-model="value.deliveryNotificationSent"
            :editMode="editMode"
        />
        <Boolean
            label="DeliveryDuplicatePrevented"
            v-model="value.deliveryDuplicatePrevented"
            :editMode="editMode"
        />
        <DeliveryStatus
            offline
            label="DeliveryStatus"
            v-model="value.deliveryStatus"
            :editMode="editMode"
            @change="change"
        />
        <OrderId
            offline
            label="주문 ID"
            v-model="value.orderId"
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
    name: 'Delivery',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "deliveries",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
