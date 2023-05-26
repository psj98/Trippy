<template>
  <div>
    <b-row class="mb-4 text-center">
      <!-- 시도 검색 -->
      <b-col class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="[gugunList(), searchAttraction()]"
        ></b-form-select>
      </b-col>

      <!-- 구군 검색 -->
      <b-col class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="searchAttraction"
        ></b-form-select>
      </b-col>

      <!-- 관광지 유형 검색 -->
      <b-col class="sm-3">
        <b-form-select
          v-model="contentType"
          :options="contents"
          @change="searchAttraction"
        ></b-form-select>
      </b-col>

      <!-- 검색어 입력 폼 -->
      <b-col class="sm-3">
        <b-form-input
          v-model="searchTitle"
          @change="searchAttraction"
          placeholder="검색어를 입력하세요"
        ></b-form-input>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const myPlanStore = "myPlanStore";

export default {
  name: "MyPlanSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      contentType: null,
      searchTitle: "", // 검색어 기본값
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
    };
  },
  computed: {
    ...mapState(myPlanStore, [
      "sidos",
      "guguns",
      "contents",
      "attractions",
      "sido",
      "gugun",
      "contentTypeId",
      "searchTitleWord",
    ]),
  },
  created() {
    /* 이전에 검색한 값 초기화 */
    if (!this.sido && !this.gugun) {
      this.deleteSearchAttraction();
      this.getSido();
    } else {
      this.sidoCode = this.sido;
      this.gugunCode = this.gugun;
      this.contentType = this.contentTypeId;
      this.searchTitle = this.searchTitleWord;
      this.CLEAR_ATTRACTION_LIST();
      this.searchAttraction();
    }
  },
  methods: {
    ...mapActions(myPlanStore, [
      "getSido",
      "getGugun",
      "getAttractionList",
      "getCode",
      "deleteSearchAttraction",
    ]),
    ...mapMutations(myPlanStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_CONTENT_LIST",
      "CLEAR_ATTRACTION_LIST",
    ]),

    /* 시도 리스트 생성 검색 */
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;

      // 시도가 선택된 경우, 구군 리스트 띄우기
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },

    /* 관광지 검색 */
    searchAttraction() {
      /* 검색어가 한 글자인 경우, 경고 띄우기 */
      if (this.searchTitle.length == 1) {
        this.notifyVue("top", "right");

        var cityCode = {
          sidoCode: this.sidoCode,
          gugunCode: this.gugunCode ? this.gugunCode : 0,
          contentType: this.contentType,
          searchTitle: "NO_DATA_SEARCH_TITLE",
        };

        this.getAttractionList(cityCode);

        return;
      }

      if (
        this.sidoCode &&
        (this.searchTitle == "" || this.searchTitle.length >= 2)
      ) {
        var cityCode = {
          sidoCode: this.sidoCode,
          gugunCode: this.gugunCode ? this.gugunCode : 0,
          contentType: this.contentType,
          searchTitle: this.searchTitle != "" ? this.searchTitle : "",
        };

        this.getAttractionList(cityCode);
      }
    },

    /* 경고 띄우기 */
    notifyVue(verticalAlign, horizontalAlign) {
      const color = Math.floor(Math.random() * 4 + 1);
      this.$notifications.notify({
        message: `<span><b>검색어를 두 글자 이상 입력해주세요</b></span>`,
        icon: "nc-icon nc-bulb-63",
        horizontalAlign: horizontalAlign,
        verticalAlign: verticalAlign,
        type: this.type[color],
      });
    },
  },
};
</script>

<style></style>
