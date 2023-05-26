<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <h3 class="card-title" style="margin-bottom: 20px">게시판 글쓰기</h3>

        <!-- 제목 -->
        <div class="row">
          <div class="col-md-12">
            <base-input
              type="text"
              label="제목"
              placeholder="제목을 입력하세요."
              v-model="article.subject"
            >
            </base-input>
          </div>
        </div>

        <!-- 내용 -->
        <b-row>
          <b-col>
            <div class="form-group">
              <label>내용</label>
              <textarea
                rows="10"
                class="form-control border-input"
                placeholder="내용을 입력하세요."
                v-model="article.content"
                style="resize: none"
              >
              </textarea>
            </div>
          </b-col>
        </b-row>

        <!-- 게시판 글쓰기 버튼 -->
        <div class="d-flex justify-content-end">
          <div class="mr-2">
            <b-button
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click.prevent="createArticle"
            >
              글쓰기
            </b-button>
          </div>
          <div>
            <b-button
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click.prevent="movePage"
            >
              목록
            </b-button>
          </div>
        </div>
      </card>
    </div>
  </div>
</template>

<script>
import axios from "@/api/http";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardWrite",
  data() {
    return {
      article: {
        userId: "",
        subject: "",
        content: "",
      },
      boardType: "",
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    this.article.userId = this.userInfo.id;
    this.boardType = this.$route.params.boardType;
  },
  methods: {
    createArticle() {
      if (this.article.subject === "") {
        this.notifyVue("top", "right", "제목을 입력해주세요");
        return;
      } else if (this.article.content === "") {
        this.notifyVue("top", "right", "내용을 입력해주세요");
        return;
      }

      const API_URL = `/articles/${this.boardType}`;
      axios.post(API_URL, this.article).then(() => {
        this.notifyVue("top", "right", "게시글이 등록되었습니다.");
        this.movePage();
      });
    },

    /* 게시판 목록으로 이동 */
    movePage() {
      this.$router.push({ name: "board" });
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
};
</script>

<style scoped></style>
