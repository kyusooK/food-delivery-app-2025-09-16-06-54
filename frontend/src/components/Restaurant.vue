<template>
    <div>
        <Number
            label="RestaurantId"
            v-model="value.restaurantId"
            :editMode="editMode"
        />
        <Number
            label="OwnerId"
            v-model="value.ownerId"
            :editMode="editMode"
        />
        <String
            label="Name"
            v-model="value.name"
            :editMode="editMode"
        />
        <String
            label="Address"
            v-model="value.address"
            :editMode="editMode"
        />
        <String
            label="PhoneNumber"
            v-model="value.phoneNumber"
            :editMode="editMode"
        />
        <div variant="outlined" class="my-2">
            <MenuList v-model="menuListInput"
                offline
                label="MenuList"
                :editMode="editMode"
                @change="change"
            />
            <v-row class="ma-0 pa-0 mt-2">
                <v-spacer></v-spacer>
                <v-btn @click="addMenuListList">MenuList Add</v-btn>
            </v-row>
            <v-card v-if="value.menuList.length > 0" variant="outlined" class="pa-4 mt-2">
                <v-card-sub-title>
                    MenuList List
                </v-card-sub-title>
                <li v-for="(id, index) in value.menuList" :key="index">
                    {{ id && id. ? id. : '' }}
                </li>
            </v-card>
        </div>
        <Date
            label="CreatedAt"
            v-model="value.createdAt"
            :editMode="editMode"
        />
        <Date
            label="UpdatedAt"
            v-model="value.updatedAt"
            :editMode="editMode"
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
    name: 'Restaurant',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "restaurants",
        menuListInput: null,
        value: {
            menuList: [],
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
        addMenuListList() {
            if (this.menuListInput !== null && this.menuListInput !== '') {
                this.value.menuList.push(this.menuListInput);
                this.menuListInput = null; // null로 초기화
            }
        },
    },
}
</script>
