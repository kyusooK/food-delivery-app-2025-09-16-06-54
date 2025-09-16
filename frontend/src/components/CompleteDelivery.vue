<template>

    <v-card outlined>
        <v-card-title>
            CompleteDelivery
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
                    @click="completeDelivery"
            >
                CompleteDelivery
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
    name: 'CompleteDeliveryCommand',
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
        completeDelivery() {
            this.$emit('completeDelivery', this.value);
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

