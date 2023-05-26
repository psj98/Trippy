<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <!-- 제목 -->
        <template slot="header">
          <h3 class="card-title" style="margin-bottom: 5px">
            나만의 여행 계획
          </h3>
          <p class="card-category">나만의 여행 계획을 만들어보세요!</p>
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
                  name: 'myplanview',
                  params: { articleNo: data.item.articleNo },
                }"
              >
                {{ data.item.subject }}
              </router-link>
            </template>
            <template #cell(shareCheck)="data">
              {{ data.item.shareCheck == 1 ? "YES" : "NO" }}
            </template>
          </b-table>
        </div>

        <!-- 글쓰기 버튼 -->
        <b-row class="m-0" style="display: -webkit-box">
          <b-col class="p-0">
            <b-pagination
              v-model="currentPage"
              :per-page="perPage"
              :total-rows="totalRows"
              aria-controls="table"
              align="center"
            ></b-pagination>
          </b-col>
          <router-link
            :to="{ name: 'myplanwrite' }"
            class="btn btn-info btn-fill"
            style="padding: 0.375rem 0.75rem"
          >
            글쓰기
          </router-link>
        </b-row>
      </card>
    </div>
  </div>
</template>

<script>
import axios from "@/api/http";
import { mapState, mapActions, mapMutations } from "vuex";

const userStore = "userStore";
const attractionStore = "attractionStore";
const myPlanStore = "myPlanStore";

export default {
  name: "MyPlanList",
  components: {},
  data() {
    return {
      tableData: [{}],
      tableColumns: [
        { key: "articleNo", label: "No" },
        { key: "subject", label: "제목" },
        { key: "registerTime", label: "작성시간" },
        { key: "hit", label: "조회수" },
        { key: "shareCheck", label: "공유 여부", value: 1 ? "O" : "X" },
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
        `/myplans?` +
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

    const API_URL = `/myplans?id=${this.userInfo.id}&key=&word=`;
    axios.get(API_URL).then(({ data }) => {
      this.tableData = data;
      this.totalRows = this.tableData.length;
    });
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style>
</style>