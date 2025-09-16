<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="rejectOrderDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 거절
                </v-btn>
                <v-dialog v-model="rejectOrderDialog" width="500">
                    <RejectOrder
                        @closeDialog="rejectOrderDialog = false"
                        @rejectOrder="rejectOrder"
                    ></RejectOrder>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="acceptOrderDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 수락
                </v-btn>
                <v-dialog v-model="acceptOrderDialog" width="500">
                    <AcceptOrder
                        @closeDialog="acceptOrderDialog = false"
                        @acceptOrder="acceptOrder"
                    ></AcceptOrder>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="prepareOrderDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 준비 완료
                </v-btn>
                <v-dialog v-model="prepareOrderDialog" width="500">
                    <PrepareOrder
                        @closeDialog="prepareOrderDialog = false"
                        @prepareOrder="prepareOrder"
                    ></PrepareOrder>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="placeOrderDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 생성
                </v-btn>
                <v-dialog v-model="placeOrderDialog" width="500">
                    <PlaceOrder
                        @closeDialog="placeOrderDialog = false"
                        @placeOrder="placeOrder"
                    ></PlaceOrder>
                </v-dialog>
            </div>
            <OrderDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></OrderDetails>
            <OrderList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></OrderList>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>CustomerId</th>
                        <th>OrderStatus</th>
                        <th>MenuItems</th>
                        <th>OrderAmount</th>
                        <th>MinimumOrderAmount</th>
                        <th>PaymentStatus</th>
                        <th>PaymentId</th>
                        <th>OrderCreatedAt</th>
                        <th>OrderUpdatedAt</th>
                        <th>OrderCancelledAt</th>
                        <th>CancelReason</th>
                        <th>RejectionReason</th>
                        <th>음식점 ID</th>
                        <th>음식점</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="CustomerId">{{ val.customerId }}</td>
                            <td class="whitespace-nowrap" label="OrderStatus">{{ val.orderStatus }}</td>
                            <td class="whitespace-nowrap" label="MenuItems">
                                <span v-for="(name, index) in val.menuItems" :key="index">
                                    {{ name }}<br>
                                </span>
                            </td>
                            <td class="whitespace-nowrap" label="OrderAmount">{{ val.orderAmount }}</td>
                            <td class="whitespace-nowrap" label="MinimumOrderAmount">{{ val.minimumOrderAmount }}</td>
                            <td class="whitespace-nowrap" label="PaymentStatus">{{ val.paymentStatus }}</td>
                            <td class="whitespace-nowrap" label="PaymentId">{{ val.paymentId }}</td>
                            <td class="whitespace-nowrap" label="OrderCreatedAt">{{ val.orderCreatedAt }}</td>
                            <td class="whitespace-nowrap" label="OrderUpdatedAt">{{ val.orderUpdatedAt }}</td>
                            <td class="whitespace-nowrap" label="OrderCancelledAt">{{ val.orderCancelledAt }}</td>
                            <td class="whitespace-nowrap" label="CancelReason">{{ val.cancelReason }}</td>
                            <td class="whitespace-nowrap" label="RejectionReason">{{ val.rejectionReason }}</td>
                            <td class="whitespace-nowrap" label="음식점">
                                <RestaurantId :editMode="editMode" v-model="val.restaurantId"></RestaurantId>
                            </td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
            <StringDetailGrid style="margin-top: 20px;" label="MenuItems" offline v-if="selectedRow" v-model="selectedRow.menuItems" :selectedRow="selectedRow"/>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Order 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Order :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Order 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="OrderId" v-model="selectedRow.orderId" :editMode="true"/>
                            <Number label="CustomerId" v-model="selectedRow.customerId" :editMode="true"/>
                            <String label="MenuItems" v-model="selectedRow.menuItems" :editMode="true"/>
                            <Number label="OrderAmount" v-model="selectedRow.orderAmount" :editMode="true"/>
                            <Number label="MinimumOrderAmount" v-model="selectedRow.minimumOrderAmount" :editMode="true"/>
                            <String label="PaymentId" v-model="selectedRow.paymentId" :editMode="true"/>
                            <Date label="OrderCreatedAt" v-model="selectedRow.orderCreatedAt" :editMode="true"/>
                            <Date label="OrderUpdatedAt" v-model="selectedRow.orderUpdatedAt" :editMode="true"/>
                            <Date label="OrderCancelledAt" v-model="selectedRow.orderCancelledAt" :editMode="true"/>
                            <String label="CancelReason" v-model="selectedRow.cancelReason" :editMode="true"/>
                            <String label="RejectionReason" v-model="selectedRow.rejectionReason" :editMode="true"/>
                            <OrderStatus offline label="OrderStatus" v-model="selectedRow.orderStatus" :editMode="true"/>
                            <PaymentStatus offline label="PaymentStatus" v-model="selectedRow.paymentStatus" :editMode="true"/>
                            <RestaurantId offline label="음식점 ID" v-model="selectedRow.restaurantId" :editMode="true"/>
                            <StringDetailGrid label="MenuItems" offline v-model="selectedRow.menuItems" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'orderGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'orders',
        rejectOrderDialog: false,
        acceptOrderDialog: false,
        prepareOrderDialog: false,
        placeOrderDialog: false,
    }),
    watch: {
    },
    methods:{
        async rejectOrder(params){
            try{
                var path = "rejectOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RejectOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.rejectOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async acceptOrder(params){
            try{
                var path = "acceptOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','AcceptOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.acceptOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async prepareOrder(params){
            try{
                var path = "prepareOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PrepareOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.prepareOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async placeOrder(params){
            try{
                var path = "placeOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PlaceOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.placeOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>