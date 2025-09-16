<template>

    <v-card outlined>
        <v-card-title>
            RegisterMenu
        </v-card-title>

        <v-card-text>
            <Number label="RestaurantId" v-model="value.restaurantId" :editMode="editMode"/>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <Number label="Price" v-model="value.price" :editMode="editMode"/>
            <String label="Description" v-model="value.description" :editMode="editMode"/>
            <String label="Image" v-model="value.image" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="registerMenu"
            >
                RegisterMenu
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
    name: 'RegisterMenuCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.restaurantId = 0;
        this.value.name = '';
        this.value.price = 0;
        this.value.description = '';
        this.value.image = '';
    },
    watch: {
    },
    methods: {
        registerMenu() {
            this.$emit('registerMenu', this.value);
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

