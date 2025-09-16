<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '메뉴 및 음식점 관리' }" />
        <VerticalNavLink
            :item="{
                title: '음식점',
                to: '/restaurants',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '메뉴 목록 조회',
                to: '/menuLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '메뉴 상세 조회',
                to: '/menuDetails',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '주문 및 배달 관리' }" />
        <VerticalNavLink
            :item="{
                title: '주문',
                to: '/orders',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '배달',
                to: '/deliveries',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '주문 상세 조회',
                to: '/orderDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '주문 목록 조회',
                to: '/orderLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '배달원 배달 목록 조회',
                to: '/deliveryListForRiders',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '배달 상태 상세 조회',
                to: '/deliveryStatusDetails',
            }"
        />
    </ul>
</template>
