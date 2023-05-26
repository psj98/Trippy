<template>
  <div class="content">
    <div class="container-fluid">
      <card class="px-3">
        <b-row class="my-5">
          <b-col v-if="attraction.firstImage" class="mx-1">
            <img :src="attraction.firstImage" class="img-fluid" />
          </b-col>
          <b-col v-else>
            <img :src="noimage" class="img-fluid" />
          </b-col>
          <b-col class="mx-3">
            <b-row class="mb-3 attractionTitle">
              {{ attraction.title }}
            </b-row>
            <b-row class="mb-2 attractionTel">
              {{ attraction.tel }}
            </b-row>
            <b-row class="mb-4 attractionAddress">
              {{ attraction.addr1 }} {{ attraction.addr2 }}
            </b-row>
            <b-row class="attractionDesc">
              {{ attractionDescDto.overview }}
            </b-row>
          </b-col>
        </b-row>
      </card>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "AttractionDetail",
  data() {
    return {
      attraction: Object,
      attractionDescDto: Object,
      noimage: require("@/assets/img/noimage.jpg"),

      /* CSS */
      attractionTitle: {
        "font-weight": "bold",
        "font-size": " 30px",
      },
      attractionTel: { "font-size": " 16px" },
      attractionAddress: { "font-size": " 16px" },
      attractionDesc: { "font-size": " 16px" },
    };
  },
  created() {
    http.post(`/hotplace/${this.$route.params.contentId}`);
    http
      .get(`/attractions/${this.$route.params.contentId}`)
      .then(({ data }) => {
        this.attraction = data;
        this.attractionDescDto = data.descDto;
      });
  },
};
</script>

<style scoped>
img {
  max-height: 500px;
}

.attractionTitle {
  font-size: 30px;
  font-weight: bold;
}

.attractionTel,
.attractionAddress,
.attractionDesc {
  font-size: 16px;
}
</style>
