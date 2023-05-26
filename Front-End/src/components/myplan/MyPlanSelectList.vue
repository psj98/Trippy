<template>
  <card class="content contentDiv">
    <div class="container-fluid">
      <div class="text-center blockquote font-weight-bold mb-2">
        선택한 관광지 목록
      </div>
      <table class="table table-hover">
        <tbody>
          <draggable
            :list="selectedAttractions"
            draggable=".attractionItem"
            class="drag-box"
          >
            <!-- 선택한 관광지 리스트 아이템 -->
            <my-plan-select-list-item
              class="attractionItem"
              v-for="attraction in selectedAttractions"
              :key="attraction.content_id"
              :attraction="attraction"
            ></my-plan-select-list-item>
          </draggable>
        </tbody>
      </table>
    </div>
  </card>
</template>

<script>
/* 선택한 관광지 정보 */
import MyPlanSelectListItem from "@/components/myplan/MyPlanSelectListItem.vue";
import { mapState } from "vuex";

/* 드래그 앤 드롭 */
import draggable from "vuedraggable";

const myPlanStore = "myPlanStore";

export default {
  name: "MyPlanSelectList",
  components: {
    MyPlanSelectListItem,
    draggable,
  },
  data() {
    return {};
  },

  computed: {
    ...mapState(myPlanStore, ["selectedAttractions"]),
  },

  methods: {
    // ...mapActions(myPlanStore, ["deleteAllAttraction"]),
  },

  created() {
    // this.deleteAllAttraction();
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
