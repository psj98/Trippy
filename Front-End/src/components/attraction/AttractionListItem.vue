<template>
  <router-link
    :to="{
      name: 'attractiondetail',
      params: { contentId: attraction.contentId },
    }"
  >
    <!-- 이미지 / 관광지명 / 관광지 유형 / 주소 -->
    <tr class="row">
      <td class="col-sm-1 col-md-1 col-lg-2" v-if="attraction.firstImage">
        <img class="img-fluid" :src="attraction.firstImage" />
      </td>
      <td class="col-sm-1 col-md-1 col-lg-2" v-else>
        <img class="img-fluid" :src="noimage" />
      </td>
      <td class="col-md col-lg">{{ attraction.title }}</td>
      <td class="col-md col-lg">
        {{ attraction.addr1 }} {{ attraction.addr2 }}
      </td>
    </tr>
  </router-link>
</template>

<script>
import { mapActions } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionListItem",
  data() {
    return {
      noimage: require("@/assets/img/noimage.jpg"),
    };
  },
  props: {
    attraction: Object,
  },
  methods: {
    ...mapActions(attractionStore, ["detailAttraction"]),
    viewAttraction() {
      this.detailAttraction(this.attraction);
    },
  },
};
</script>

<style scoped></style>
