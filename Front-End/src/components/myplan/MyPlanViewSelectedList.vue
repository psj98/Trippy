<template>
  <card class="content contentDiv">
    <div class="container-fluid">
      <table class="table table-hover">
        <thead>
          <tr class="row">
            <th class="col-sm-1 col-md-1 col-lg-2">이미지</th>
            <th class="col-md col-lg">관광지명</th>
            <th class="col-md col-lg">주소</th>
          </tr>
        </thead>
        <tbody>
          <!-- 관광지 리스트 아이템 -->
          <my-plan-view-selected-list-item
            v-for="attraction in selectedAttractions"
            :key="attraction.content_id"
            :attraction="attraction"
          ></my-plan-view-selected-list-item>
        </tbody>
      </table>
    </div>
  </card>
</template>

<script>
/* 관광지 정보 */
import MyPlanViewSelectedListItem from "@/components/myplan/MyPlanViewSelectedListItem.vue";
import { mapState } from "vuex";

const myPlanStore = "myPlanStore";

export default {
  name: "MyPlanViewSelectedList",
  components: {
    MyPlanViewSelectedListItem,
  },
  data() {
    return {};
  },

  computed: {
    ...mapState(myPlanStore, ["selectedAttractions"]),
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
