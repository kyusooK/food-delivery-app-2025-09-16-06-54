<template>

    <v-card outlined>
        <v-card-title>
            PickupDelivery
        </v-card-title>

        <v-card-text>
            <Number label="DeliveryId" v-model="value.deliveryId" :editMode="editMode"/>
            <Number label="DeliveryRiderId" v-model="value.deliveryRiderId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="pickupDelivery"
            >
                PickupDelivery
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
    name: 'PickupDeliveryCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.deliveryId = 0;
        this.value.deliveryRiderId = 0;
    },
    watch: {
    },
    methods: {
        pickupDelivery() {
            this.$emit('pickupDelivery', this.value);
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

