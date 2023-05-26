<template>
  <!-- 이미지 / 관광지명 -->
  <button class="btn detailButton" @click="choice">
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
import { mapState, mapActions } from "vuex";

const myPlanStore = "myPlanStore";

export default {
  name: "MyPlanAttractionListItem",
  data() {
    return {
      noimage: require("@/assets/img/noimage.jpg"),
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
    };
  },
  props: {
    attraction: Object,
  },
  methods: {
    ...mapActions(myPlanStore, ["detailAttraction", "selectAttraction"]),
    viewAttraction() {
      this.detailAttraction(this.attraction);
    },

    /* 관광지 선택하기 */
    choice() {
      /* 개수 체크 */
      if (this.selectedAttractions.length == 10) {
        this.notifyVue(
          "top",
          "right",
          "선택할 수 있는 관광지 수는 10개입니다."
        );

        return;
      }

      /* 중복 체크 */
      var check = false;
      for (var i = 0; i < this.selectedAttractions.length; i++) {
        if (
          this.selectedAttractions[i].contentId == this.attraction.contentId
        ) {
          check = true;
          break;
        }
      }

      if (check) {
        this.notifyVue("top", "right", "선택된 관광지입니다.");

        return;
      }

      this.selectAttraction(this.attraction);

      this.notifyVue("top", "right", "관광지가 추가되었습니다.");
    },

    /* 경고 띄우기 */
    notifyVue(verticalAlign, horizontalAlign, message) {
      const color = Math.floor(Math.random() * 4 + 1);
      this.$notifications.notify({
        message: `<span><b>${message}</b></span>`,
        icon: "nc-icon nc-bulb-63",
        horizontalAlign: horizontalAlign,
        verticalAlign: verticalAlign,
        type: this.type[color],
      });
    },
  },
  computed: {
    ...mapState(myPlanStore, ["selectedAttractions"]),
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
