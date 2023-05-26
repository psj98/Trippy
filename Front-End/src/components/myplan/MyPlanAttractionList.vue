<template>
  <card class="content contentDiv">
    <div class="container-fluid">
      <div class="text-center blockquote font-weight-bold mb-2">
        관광지 목록
      </div>
      <table class="table table-hover">
        <tbody>
          <!-- 관광지 리스트 아이템 -->
          <my-plan-attraction-list-item
            v-for="attraction in attractions"
            :key="attraction.content_id"
            :attraction="attraction"
          ></my-plan-attraction-list-item>
        </tbody>
      </table>
    </div>
  </card>
</template>

<script>
/* 관광지 정보 */
import MyPlanAttractionListItem from "@/components/myplan/MyPlanAttractionListItem.vue";
import { mapState } from "vuex";

const myPlanStore = "myPlanStore";

export default {
  name: "MyPlanAttractionList",
  components: {
    MyPlanAttractionListItem,
  },
  data() {
    return {};
  },

  computed: {
    ...mapState(myPlanStore, ["attractions"]),
  },
};
</script>

<style scoped>
.contentDiv {
  background-color: white;
  overflow-y: auto;
  height: 647px;
}

.contentDiv::-webkit-scrollbar {
  display: none;
}

.listTitle {
  font-size: 22px;
  font-weight: bold;
}
</style>
