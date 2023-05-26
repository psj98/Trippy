<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <!-- 제목 -->
        <template slot="header">
          <h3 class="card-title" style="margin-bottom: 5px">
            {{ boardInfo.boardName }} 게시판
          </h3>
          <p class="card-category">{{ boardInfo.boardIntro }}</p>
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
              @click="searchArticle"
              style="padding: 0.375rem 0.75rem"
            >
              <i class="nc-icon nc-zoom-split"></i>
            </button>
          </div>
        </div>

        <!-- 게시판 상세 보기 -->
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
                  name: 'boardview',
                  params: {
                    articleNo: data.item.articleNo,
                    boardName: boardInfo.boardName,
                  },
                }"
                >{{ data.item.subject }}</router-link
              >
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
            v-if="
              userInfo !== null &&
              (boardInfo.boardType !== 'notice' ||
                (boardInfo.boardType === 'notice' &&
                  userInfo.userGrade === '관리자'))
            "
            to="write"
            :boardName="boardInfo.boardName"
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
import { mapState, mapActions } from "vuex";

const userStore = "userStore";
const attractionStore = "attractionStore";

export default {
  name: "Board",
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      tableData: [{}],
      tableColumns: [
        { key: "articleNo", label: "No" },
        { key: "subject", label: "제목" },
        { key: "name", label: "작성자" },
        { key: "registerTime", label: "작성시간" },
        { key: "hit", label: "조회수" },
      ],
      keys: [
        { value: "", text: "검색조건" },
        { value: "article_no", text: "글번호" },
        { value: "subject", text: "제목" },
        { value: "user_id", text: "작성자" },
      ],
      searchNav: {
        key: "",
        word: "",
      },
      boardInfo: {
        boardType: "",
        boardName: "",
        boardIntro: "",
      },
      perPage: 8,
      currentPage: 1,
      numOfRows: 10,
      totalRows: 0,
    };
  },
  watch: {
    $route() {
      this.boardInfo.boardType = this.$route.params.boardType;

      // 게시판 제목 받아오기
      let API_URL = `/articles/${this.boardInfo.boardType}/name`;
      axios
        .get(API_URL)
        .then(({ data }) => {
          this.boardInfo.boardName = data.boardName;
          this.boardInfo.boardIntro = data.boardIntro;
        })
        .catch(() => {
          this.movePage();
          return;
        });

      // 글 받아오기
      API_URL = `/articles/${this.boardInfo.boardType}?key=&word=`;
      axios.get(API_URL).then(({ data }) => {
        this.tableData = data;
        this.totalRows = this.tableData.length;
      });
    },
  },
  methods: {
    ...mapActions(attractionStore, ["deleteSearchAttraction"]),

    // 글 검색
    searchArticle() {
      const API_URL = `/articles/${this.boardInfo.boardType}?key=${this.searchNav.key}&word=${this.searchNav.word}`;
      axios.get(API_URL).then(({ data }) => {
        this.tableData = data;
        this.totalRows = this.tableData.length;
      });
    },
    movePage() {
      this.$router.push({ name: "board" });
    },
  },

  async created() {
    await this.deleteSearchAttraction();

    this.boardInfo.boardType = this.$route.params.boardType;

    // 게시판 제목 받아오기
    let API_URL = `/articles/${this.boardInfo.boardType}/name`;
    axios
      .get(API_URL)
      .then(({ data }) => {
        this.boardInfo.boardName = data.boardName;
        this.boardInfo.boardIntro = data.boardIntro;
      })
      .catch(() => {
        this.movePage();
        return;
      });

    // 글 받아오기
    API_URL = `/articles/${this.boardInfo.boardType}?key=&word=`;
    axios.get(API_URL).then(({ data }) => {
      this.tableData = data;
      this.totalRows = this.tableData.length;
    });
  },
};
</script>
<style></style>
