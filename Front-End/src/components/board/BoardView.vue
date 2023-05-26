<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <h3 class="card-title" style="margin-bottom: 20px">게시글 보기</h3>

        <!-- 제목 -->
        <b-row>
          <b-col>
            <div class="form-group">
              <label>제목</label>
              <b-card class="mb-0">
                <b-card-text>{{ article.subject }}</b-card-text>
              </b-card>
            </div>
          </b-col>
        </b-row>

        <!-- 내용 -->
        <b-row>
          <b-col>
            <div class="form-group">
              <label>내용</label>
              <b-card class="mb-0 scroll" style="height: 400px">
                <b-card-text>
                  {{ article.content }}
                </b-card-text>
              </b-card>
            </div>
          </b-col>
        </b-row>

        <!-- 목록 / 수정 / 삭제 버튼 -->
        <div class="d-flex justify-content-end">
          <div class="mr-2">
            <router-link
              v-if="userInfo !== null && userInfo.id === article.userId"
              :to="{
                name: 'boardmodify',
                params: {
                  articleNo: article.articleNo,
                },
              }"
            >
              <b-button
                type="submit"
                block
                variant="info"
                style="color: white; padding: 0.375rem 0.75rem"
              >
                수정
              </b-button>
            </router-link>
          </div>
          <div class="mr-2">
            <b-button
              v-if="userInfo !== null && userInfo.id === article.userId"
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click.prevent="deleteArticle"
            >
              삭제
            </b-button>
          </div>
          <div>
            <b-button
              type="submit"
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click.prevent="movePage"
            >
              목록
            </b-button>
          </div>
        </div>

        <!-- 댓글 -->
        <div class="table-responsive">
          <label>댓글</label>
          <b-table id="table" :items="comments" :fields="tableColumns">
            <template #cell(content)="data">
              <base-input
                class="mb-0"
                v-if="
                  targetComment !== null &&
                  targetComment.commentNo === data.item.commentNo
                "
                type="text"
                placeholder="댓글을 입력해주세요"
                v-model="targetComment.content"
              >
              </base-input>
              <span v-else>{{ data.item.content }}</span>
            </template>

            <template #cell(modify)="data">
              <div v-if="userInfo.id === data.item.userId">
                <button
                  class="btn btn-success btn-fill"
                  v-if="
                    targetComment !== null &&
                    targetComment.commentNo === data.item.commentNo
                  "
                  @click="modifyComment(data.item)"
                  style="padding: 0.375rem 0.75rem"
                >
                  <i
                    class="nc-icon nc-check-2"
                    style="vertical-align: middle"
                  ></i>
                </button>
                <button
                  v-else
                  class="btn btn-success btn-fill"
                  @click="switchMod(data.item)"
                  style="padding: 0.375rem 0.75rem"
                >
                  <i
                    class="nc-icon nc-settings-tool-66"
                    style="vertical-align: middle"
                  ></i>
                </button>
              </div>
            </template>

            <template #cell(delete)="data">
              <div v-if="userInfo.id === data.item.userId">
                <button
                  class="btn btn-danger btn-fill"
                  @click="deleteComment(data.item, $event)"
                  style="padding: 0.375rem 0.75rem"
                >
                  <i
                    class="nc-icon nc-simple-remove"
                    style="vertical-align: middle"
                  ></i>
                </button>
              </div>
            </template>
          </b-table>
        </div>

        <b-row v-if="userInfo">
          <b-col class="d-flex">
            <base-input
              class="col-11"
              type="text"
              label="댓글 작성"
              placeholder="댓글을 입력해주세요"
              v-model="comment.content"
            >
            </base-input>
            <button
              class="btn btn-primary btn-fill mt-2"
              @click="createComment"
              style="padding: 0.375rem 0.75rem; align-self: center"
            >
              <i
                class="nc-icon nc-simple-add"
                style="vertical-align: middle"
              ></i>
            </button>
          </b-col>
        </b-row>
      </card>
    </div>
  </div>
</template>

<script>
import axios from "@/api/http";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardView",
  data() {
    return {
      article: {},
      comment: {
        userId: "",
        articleNo: null,
        content: "",
      },
      boardType: "",
      targetComment: {
        commentNo: null,
        userId: "",
        articleNo: null,
        content: "",
      },
      comments: [],
      tableColumns: [
        { key: "userId", label: "아이디", thStyle: { width: "15%" } },
        { key: "content", label: "내용", thStyle: { width: "45%" } },
        { key: "registerTime", label: "작성시간", thStyle: { width: "20%" } },
        { key: "modify", label: "수정", thStyle: { width: "10%" } },
        { key: "delete", label: "삭제" },
      ],
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
    };
  },
  async created() {
    // 게시물 타입 받아오기
    this.boardType = this.$route.params.boardType;

    // 게시물 내용 받아오기
    let API_URL = `/articles/${this.boardType}/${this.$route.params.articleNo}`;
    await axios.get(API_URL).then(({ data }) => {
      this.article = data;
    });

    // 댓글 내용 받아오기
    this.getComment();

    // 현재 유저 id 및 게시물 번호 받기
    this.comment.userId = this.userInfo.id;
    this.comment.articleNo = this.article.articleNo;
  },
  methods: {
    /* 게시글 목록으로 이동 */
    movePage() {
      this.$router.push({ name: "board" });
    },

    /* 게시글 삭제 */
    async deleteArticle() {
      const API_URL = `/articles/${this.boardType}/${this.article.articleNo}`;
      await axios.delete(API_URL).then(() => {
        this.notifyVue("top", "right", "게시글이 삭제되었습니다");
        this.movePage();
      });
    },

    /* 댓글 수정 및 수정 완료 버튼 switch */
    switchMod(item) {
      if (item.userId !== this.userInfo.id) {
        return;
      }

      if (this.targetComment.commentNo === null) {
        this.targetComment.commentNo = item.commentNo;
        this.targetComment.articleNo = item.articleNo;
        this.targetComment.content = item.content;
        this.targetComment.userId = item.userId;
      } else {
        if (this.targetComment.commentNo !== item.commentNo) {
          return;
        }
        this.targetComment.articleNo = null;
        this.targetComment.commentNo = null;
        this.targetComment.content = "";
        this.targetComment.userId = "";
      }
    },

    /* 댓글 등록 */
    async createComment() {
      if (this.comment.content === "") {
        this.notifyVue("top", "right", "내용을 입력해주세요");
        return;
      }

      const API_URL = `/comments/${this.boardType}`;
      await axios.post(API_URL, this.comment).then(({ data }) => {
        if (data === "WRITE SUCCESS") {
          this.getComment();
          this.comment.content = "";
          this.notifyVue("top", "right", "댓글이 등록되었습니다");
        }
      });
    },

    /* 댓글 가져오기 */
    async getComment() {
      const API_URL = `/comments/${this.boardType}/${this.article.articleNo}`;
      await axios.get(API_URL).then(({ data }) => {
        this.comments = data;
      });
    },

    /* 댓글 수정하기 */
    async modifyComment(item) {
      if (item.userId !== this.userInfo.id) {
        return;
      }

      if (this.targetComment.content === "") {
        this.notifyVue("top", "right", "내용을 입력해주세요");
        return;
      }

      // 받아온 리스트에서 수정
      item.content = this.targetComment.content;

      // DB에서 수정
      const API_URL = `/comments/${this.boardType}`;
      await axios.put(API_URL, this.targetComment).then(() => {
        this.targetComment.articleNo = null;
        this.targetComment.commentNo = null;
        this.targetComment.content = "";
        this.targetComment.userId = "";
        this.notifyVue("top", "right", "댓글이 수정되었습니다");
      });
    },

    /* 댓글 지우기 */
    async deleteComment(item) {
      if (item.userId !== this.userInfo.id) {
        return;
      }

      const API_URL = `/comments/${this.boardType}/${item.commentNo}`;
      await axios.delete(API_URL).then(({ data }) => {
        if (data === "DELETE SUCCESS") {
          for (var i = 0; i < this.comments.length; i++) {
            if (this.comments[i].commentNo === item.commentNo) {
              this.comments.splice(i, 1);
              this.notifyVue("top", "right", "댓글이 삭제되었습니다");
              break;
            }
          }
        }
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
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style scoped></style>
