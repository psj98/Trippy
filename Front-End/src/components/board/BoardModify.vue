<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <h3 class="card-title" style="margin-bottom: 20px">게시글 수정</h3>

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

        <!-- 수정 버튼 -->
        <div class="d-flex justify-content-end">
          <div class="mr-2">
            <b-button
              type="reset"
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click.prevent="modifyArticle"
            >
              수정
            </b-button>
          </div>
          <div class="mr-2">
            <b-button
              type="reset"
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click.prevent="movePage"
            >
              목록
            </b-button>
          </div>
        </div>
        <div class="clearfix"></div>
      </card>
    </div>
  </div>
</template>

<script>
import axios from "@/api/http";

export default {
  name: "BoardModify",
  data() {
    return {
      article: null,
      boardType: "",
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
    };
  },
  async created() {
    this.boardType = this.$route.params.boardType;
    // 게시물 내용 받아오기
    let API_URL = `/articles/${this.boardType}/${this.$route.params.articleNo}`;
    await axios.get(API_URL).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    async modifyArticle() {
      if (this.article.subject === "") {
        this.notifyVue("top", "right", "제목을 입력해주세요");
        return;
      } else if (this.article.content === "") {
        this.notifyVue("top", "right", "내용을 입력해주세요");
        return;
      }

      const API_URL = `/articles/${this.boardType}`;
      await axios.put(API_URL, this.article).then(() => {
        this.notifyVue("top", "right", "게시글이 수정되었습니다");
        this.movePage();
      });
    },

    /* 상세 보기로 이동 */
    movePage() {
      this.$router.push({
        name: "boardview",
        params: {
          boardType: this.boardType,
          articleNo: this.article.articleNo,
        },
      });
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
