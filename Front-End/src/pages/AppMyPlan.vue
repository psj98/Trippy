<template>
  <div class="content">
    <div class="container-fluid">
      <card>
        <!-- 나만의 여행 계획 목록 -->
        <my-plan-list></my-plan-list>
      </card>
    </div>
  </div>
</template>

<script>
import MyPlanList from "@/components/myplan/MyPlanList.vue";

export default {
  components: {
    MyPlanList,
  },
};

<style></style>;
