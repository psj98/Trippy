<template>
  <card class="content contentDiv mb-0">
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
          <attraction-list-item
            v-for="attraction in attractions"
            :key="attraction.content_id"
            :attraction="attraction"
          ></attraction-list-item>
        </tbody>
      </table>
    </div>
  </card>
</template>

<script>
/* 관광지 정보 */
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";
import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionList",
  components: {
    AttractionListItem,
  },
  data() {
    return {};
  },

  computed: {
    ...mapState(attractionStore, ["attractions"]),
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
</style>