<template>

    <v-card outlined>
        <v-card-title>
            RejectOrder
        </v-card-title>

        <v-card-text>
            <Number label="OrderId" v-model="value.orderId" :editMode="editMode"/>
            <RestaurantId offline label="RestaurantId" v-model="value.restaurantId" :editMode="editMode" @change="change"/>
            <String label="RejectionReason" v-model="value.rejectionReason" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="rejectOrder"
            >
                RejectOrder
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>

export default {
    name: 'RejectOrderCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.orderId = 0;
        this.value.restaurantId = {};
        this.value.rejectionReason = '';
    },
    watch: {
    },
    methods: {
        rejectOrder() {
            this.$emit('rejectOrder', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

