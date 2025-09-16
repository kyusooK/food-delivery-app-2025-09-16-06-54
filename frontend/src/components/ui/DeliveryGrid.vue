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
                <v-btn style="margin-left: 5px;" @click="acceptDeliveryDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 요청 수락
                </v-btn>
                <v-dialog v-model="acceptDeliveryDialog" width="500">
                    <AcceptDelivery
                        @closeDialog="acceptDeliveryDialog = false"
                        @acceptDelivery="acceptDelivery"
                    ></AcceptDelivery>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="pickupDeliveryDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>음식 수령
                </v-btn>
                <v-dialog v-model="pickupDeliveryDialog" width="500">
                    <PickupDelivery
                        @closeDialog="pickupDeliveryDialog = false"
                        @pickupDelivery="pickupDelivery"
                    ></PickupDelivery>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="completeDeliveryDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 완료
                </v-btn>
                <v-dialog v-model="completeDeliveryDialog" width="500">
                    <CompleteDelivery
                        @closeDialog="completeDeliveryDialog = false"
                        @completeDelivery="completeDelivery"
                    ></CompleteDelivery>
                </v-dialog>
            </div>
            <DeliveryListForRider @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></DeliveryListForRider>
            <DeliveryStatusDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></DeliveryStatusDetails>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>DeliveryRiderId</th>
                        <th>DeliveryStatus</th>
                        <th>DeliveryAcceptedAt</th>
                        <th>DeliveryPickedUpAt</th>
                        <th>DeliveryCompletedAt</th>
                        <th>DeliveryNotificationSent</th>
                        <th>DeliveryDuplicatePrevented</th>
                        <th>주문 ID</th>
                        <th>주문</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="DeliveryRiderId">{{ val.deliveryRiderId }}</td>
                            <td class="whitespace-nowrap" label="DeliveryStatus">{{ val.deliveryStatus }}</td>
                            <td class="whitespace-nowrap" label="DeliveryAcceptedAt">{{ val.deliveryAcceptedAt }}</td>
                            <td class="whitespace-nowrap" label="DeliveryPickedUpAt">{{ val.deliveryPickedUpAt }}</td>
                            <td class="whitespace-nowrap" label="DeliveryCompletedAt">{{ val.deliveryCompletedAt }}</td>
                            <td class="whitespace-nowrap" label="DeliveryNotificationSent">{{ val.deliveryNotificationSent }}</td>
                            <td class="whitespace-nowrap" label="DeliveryDuplicatePrevented">{{ val.deliveryDuplicatePrevented }}</td>
                            <td class="whitespace-nowrap" label="주문">
                                <OrderId :editMode="editMode" v-model="val.orderId"></OrderId>
                            </td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Delivery 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Delivery :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Delivery 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="DeliveryId" v-model="selectedRow.deliveryId" :editMode="true"/>
                            <Number label="DeliveryRiderId" v-model="selectedRow.deliveryRiderId" :editMode="true"/>
                            <Date label="DeliveryAcceptedAt" v-model="selectedRow.deliveryAcceptedAt" :editMode="true"/>
                            <Date label="DeliveryPickedUpAt" v-model="selectedRow.deliveryPickedUpAt" :editMode="true"/>
                            <Date label="DeliveryCompletedAt" v-model="selectedRow.deliveryCompletedAt" :editMode="true"/>
                            <Boolean label="DeliveryNotificationSent" v-model="selectedRow.deliveryNotificationSent" :editMode="true"/>
                            <Boolean label="DeliveryDuplicatePrevented" v-model="selectedRow.deliveryDuplicatePrevented" :editMode="true"/>
                            <DeliveryStatus offline label="DeliveryStatus" v-model="selectedRow.deliveryStatus" :editMode="true"/>
                            <OrderId offline label="주문 ID" v-model="selectedRow.orderId" :editMode="true"/>
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
    name: 'deliveryGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'deliveries',
        acceptDeliveryDialog: false,
        pickupDeliveryDialog: false,
        completeDeliveryDialog: false,
    }),
    watch: {
    },
    methods:{
        async acceptDelivery(params){
            try{
                var path = "acceptDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','AcceptDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.acceptDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async pickupDelivery(params){
            try{
                var path = "pickupDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PickupDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.pickupDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async completeDelivery(params){
            try{
                var path = "completeDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CompleteDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.completeDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>