<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <!-- 제목 -->
        <template slot="header">
          <h3 class="card-title" style="margin-bottom: 5px">여행 계획 공유</h3>
          <p class="card-category">다른 사람의 여행 계획을 참고해보세요!</p>
        </template>

        <!-- 검색 조건 -->
        <div class="my-4 d-flex justify-content-end">
          <div class="mr-3">
            <b-form-select
              class="pr-5"
              v-model="searchNav.key"
              :options="keys"
            ></b-form-select>
          </div>
          <div class="mr-3">
            <b-form-input
              v-model="searchNav.word"
              placeholder="검색어를 입력하세요"
            ></b-form-input>
          </div>
          <div>
            <button
              class="btn btn-info btn-fill"
              @click="searchMyPlan"
              style="padding: 0.375rem 0.75rem"
            >
              <i class="nc-icon nc-zoom-split"></i>
            </button>
          </div>
        </div>

        <!-- 나만의 여행 계획 상세 보기 -->
        <div class="table-responsive">
          <b-table
            id="table"
            :items="tableData"
            :per-page="perPage"
            :current-page="currentPage"
            :fields="tableColumns"
          >
            <template #cell(subject)="data">
              <router-link
                :to="{
                  name: 'shareboardview',
                  params: { articleNo: data.item.articleNo },
                }"
              >
                {{ data.item.subject }}
              </router-link>
            </template>
          </b-table>
        </div>
      </card>
    </div>
  </div>
</template>

<script>
import axios from "@/api/http";
import { mapActions, mapMutations } from "vuex";

const attractionStore = "attractionStore";
const myPlanStore = "myPlanStore";

export default {
  name: "ShareBoardList",
  data() {
    return {
      tableData: [{}],
      tableColumns: [
        { key: "articleNo", label: "No" },
        { key: "subject", label: "제목" },
        { key: "userId", label: "작성자" },
        { key: "registerTime", label: "작성시간" },
        { key: "hit", label: "조회수" },
      ],

      keys: [
        { value: "", text: "검색조건" },
        { value: "article_no", text: "글번호" },
        { value: "subject", text: "제목" },
      ],

      searchNav: {
        key: "",
        word: "",
      },

      perPage: 8,
      currentPage: 1,
      numOfRows: 10,
      totalRows: 0,
    };
  },
  methods: {
    ...mapMutations(myPlanStore, ["CLEAR_SEARCH_LIST"]),
    ...mapActions(myPlanStore, ["deleteAllAttraction", "getSido"]),
    ...mapActions(attractionStore, ["deleteSearchAttraction"]),

    searchMyPlan() {
      const API_URL =
        `/myplans/share?` +
        `key=${this.searchNav.key}` +
        `&word=${this.searchNav.word}`;
      axios.get(API_URL).then(({ data }) => {
        this.tableData = data;
        this.totalRows = this.tableData.length;
      });
    },
  },
  created() {
    this.CLEAR_SEARCH_LIST();
    this.getSido();
    this.deleteAllAttraction();
    this.deleteSearchAttraction();

    const API_URL = `/myplans/share?key=&word=`;
    axios.get(API_URL).then(({ data }) => {
      this.tableData = data;
      this.totalRows = this.tableData.length;
    });
  },
};
</script>

<style>
</style>