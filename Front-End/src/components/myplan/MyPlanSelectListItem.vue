<template>
  <!-- 이미지 / 관광지명 -->
  <button class="btn detailButton" @click="remove">
    <tr class="row">
      <td class="col-sm-3 col-md-3 col-lg-4" v-if="attraction.firstImage">
        <img class="img-fluid" :src="attraction.firstImage" />
      </td>
      <td class="col-sm-3 col-md-3 col-lg-4" v-else>
        <img class="img-fluid" :src="noimage" />
      </td>
      <td class="col-md col-lg font-weight-normal text-left">
        {{ attraction.title }}
      </td>
    </tr>
  </button>
</template>

<script>
import { mapActions } from "vuex";

const myPlanStore = "myPlanStore";

export default {
  name: "MyPlanSelectListItem",
  data() {
    return {
      noimage: require("@/assets/img/noimage.jpg"),
    };
  },
  props: {
    attraction: Object,
  },
  methods: {
    ...mapActions(myPlanStore, ["detailAttraction", "removeAttraction"]),
    viewAttraction() {
      this.detailAttraction(this.attraction);
    },

    remove() {
      this.removeAttraction(this.attraction);
    },
  },
};
</script>

<style scoped>
.detailButton {
  border: none;
  padding: 0px;
  color: black;
}
</style>
