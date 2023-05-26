<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <h3 class="card-title" style="margin-bottom: 20px">
          {{ article.subject }}
        </h3>

        <div class="mt-4 mb-2" style="font-size: 12px; color: #9a9a9a">
          여행 계획
        </div>

        <b-row class="mb-2">
          <!-- 카카오 맵 -->
          <b-col cols="6">
            <my-plan-map></my-plan-map>
          </b-col>

          <!-- 추가 리스트 -->
          <b-col class="3 selectList">
            <my-plan-view-selected-list></my-plan-view-selected-list>
          </b-col>
        </b-row>

        <!-- 내용 -->
        <b-row>
          <b-col>
            <div class="form-group">
              <label>내용</label>
              <b-card class="mb-0">
                <b-card-text>
                  {{ article.content }}
                </b-card-text>
              </b-card>
            </div>
          </b-col>
        </b-row>

        <!-- 목록 / 수정 / 삭제 버튼 -->
        <div class="d-flex justify-content-end">
          <div v-if="article.shareCheck == 0" class="mr-2">
            <b-button
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click="shareArticle"
            >
              공유
            </b-button>
          </div>
          <div v-else class="mr-2">
            <b-button
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click="noShareArticle"
            >
              공유 해제
            </b-button>
          </div>
          <div class="mr-2">
            <b-button
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click="movePage"
            >
              목록
            </b-button>
          </div>
          <div class="mr-2">
            <b-button
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click="moveModifyPage"
            >
              수정
            </b-button>
          </div>
          <div>
            <b-button
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click="deleteArticle"
            >
              삭제
            </b-button>
          </div>
        </div>
      </card>
    </div>
  </div>
</template>

<script>
import axios from "@/api/http";
import { mapState, mapActions } from "vuex";

import MyPlanMap from "@/components/myplan/MyPlanMap.vue";
import MyPlanViewSelectedList from "@/components/myplan/MyPlanViewSelectedList.vue";

const userStore = "userStore";
const myPlanStore = "myPlanStore";

export default {
  name: "MyPlanView",
  components: {
    MyPlanMap,
    MyPlanViewSelectedList,
  },
  data() {
    return {
      article: {},
      myPlanList: [],
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
    };
  },
  async created() {
    /* 게시물 내용 받아오기 */
    let API_URL = `/myplans/${this.$route.params.articleNo}`;
    await axios.get(API_URL).then(({ data }) => {
      this.article = data;
    });

    /* 관광지 리스트 가져오기 */
    this.deleteAllAttraction();
    let attractionIdList = this.article.attraction_id_list.split("&"); // 파싱해서 저장

    for (var i = 0; i < attractionIdList.length; i++) {
      let GET_ATTRACTION_LIST = `/attractions/${attractionIdList[i]}`;
      await axios.get(GET_ATTRACTION_LIST).then(({ data }) => {
        this.selectedAttractions.push(data);
      });
    }
  },
  methods: {
    ...mapActions(myPlanStore, ["deleteAllAttraction"]),

    /* 목록으로 이동 */
    movePage() {
      this.deleteAllAttraction();
      this.$router.push({ name: "myplanlist" });
    },

    /* 나만의 여행 계획 삭제 */
    async deleteArticle() {
      const API_URL = `/myplans/${this.article.articleNo}`;
      await axios.delete(API_URL).then(() => {
        this.notifyVue("top", "right", "나만의 여행 계획이 삭제되었습니다");
        this.movePage();
      });
    },

    /* 나만의 여행 계획 수정 페이지로 이동 */
    moveModifyPage() {
      this.$router.push({
        name: "myplanmodify",
        params: {
          articleNo: this.article.articleNo,
        },
      });
    },

    /* 나만의 여행 계획 공유 */
    async shareArticle() {
      const API_URL = `/myplans/share/${this.article.articleNo}`;
      await axios.put(API_URL).then(() => {
        this.notifyVue("top", "right", "나만의 여행 계획이 공유되었습니다");
        this.movePage();
      });
    },

    /* 나만의 여행 계획 공유 해제 */
    async noShareArticle() {
      const API_URL = `/myplans/noshare/${this.article.articleNo}`;
      await axios.put(API_URL).then(() => {
        this.notifyVue(
          "top",
          "right",
          "나만의 여행 계획 공유가 해제되었습니다"
        );
        this.movePage();
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
    ...mapState(myPlanStore, ["selectedAttractions"]),
  },
};
</script>

<style scoped></style>
