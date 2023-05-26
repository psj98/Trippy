<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <h3 class="card-title" style="margin-bottom: 20px">
          나만의 여행 계획 수정하기
        </h3>
        <div class="row">
          <div class="col-md-12">
            <base-input
              type="text"
              label="제목"
              placeholder="제목을 입력하세요."
              v-model="myplan.subject"
            >
            </base-input>
          </div>
        </div>

        <!-- 관광지 검색 창 -->
        <div class="mt-4 mb-2" style="font-size: 12px; color: #9a9a9a">
          관광지 검색
        </div>
        <my-plan-search-bar></my-plan-search-bar>

        <b-row class="mb-2">
          <!-- 카카오 맵 -->
          <b-col cols="6">
            <my-plan-map></my-plan-map>
          </b-col>

          <!-- 관광지 리스트 -->
          <b-col class="3">
            <my-plan-attraction-list></my-plan-attraction-list>
          </b-col>

          <!-- 추가 리스트 -->
          <b-col class="3">
            <my-plan-select-list></my-plan-select-list>
          </b-col>
        </b-row>

        <!-- 내용 입력 -->
        <b-row>
          <b-col>
            <div class="form-group">
              <label>내용</label>
              <textarea
                rows="10"
                class="form-control border-input"
                placeholder="내용을 입력하세요."
                v-model="myplan.content"
                style="resize: none"
              >
              </textarea>
            </div>
          </b-col>
        </b-row>

        <!-- 나만의 여행 계획 수정 버튼 -->
        <div class="d-flex justify-content-end">
          <div class="mr-2">
            <b-button
              block
              variant="info"
              style="color: white; padding: 0.375rem 0.75rem"
              @click="modifyPlan"
            >
              수정
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
        </div>
      </card>
    </div>
  </div>
</template>

<script>
import axios from "@/api/http";
import { mapState, mapActions } from "vuex";

import MyPlanMap from "@/components/myplan/MyPlanMap.vue";
import MyPlanSearchBar from "@/components/myplan/MyPlanSearchBar.vue";
import MyPlanAttractionList from "@/components/myplan/MyPlanAttractionList.vue";
import MyPlanSelectList from "@/components/myplan/MyPlanSelectList.vue";

const userStore = "userStore";
const myPlanStore = "myPlanStore";

export default {
  name: "MyPlanModify",
  components: {
    MyPlanMap,
    MyPlanSearchBar,
    MyPlanAttractionList,
    MyPlanSelectList,
  },
  data() {
    return {
      article: null,
      myplan: {
        articleNo: "",
        userId: "",
        subject: "",
        content: "",
        attractionIdList: "",
      },
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(myPlanStore, ["selectedAttractions"]),
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

    /* 가져온 리스트를 현재 myplan 변수에 저장 */
    this.myplan.articleNo = this.article.articleNo;
    this.myplan.subject = this.article.subject;
    this.myplan.content = this.article.content;
    this.myplan.userId = this.userInfo.id;
    this.myplan.attractionIdList = attractionIdList;

    for (var i = 0; i < attractionIdList.length; i++) {
      let GET_ATTRACTION_LIST = `/attractions/${attractionIdList[i]}`;
      await axios.get(GET_ATTRACTION_LIST).then(({ data }) => {
        this.selectedAttractions.push(data);
      });
    }
  },
  methods: {
    ...mapActions(myPlanStore, ["modifyMyPlan", "deleteAllAttraction"]),

    /* 여행 계획 수정 */
    modifyPlan() {
      if (this.myplan.subject === "") {
        this.notifyVue("top", "right", "제목을 입력해주세요");
        return;
      } else if (this.myplan.content === "") {
        this.notifyVue("top", "right", "내용을 입력해주세요");
        return;
      } else if (this.selectedAttractions.length == 0) {
        this.notifyVue("top", "right", "관광지를 선택해주세요");
        return;
      }

      var attractionIdListParser = "";
      for (var i = 0; i < this.selectedAttractions.length; i++) {
        attractionIdListParser += this.selectedAttractions[i].contentId;
        if (i != this.selectedAttractions.length - 1) {
          attractionIdListParser += "&"; // &로 contentId 구분
        }
      }

      this.myplan.attractionIdList = attractionIdListParser;

      this.modifyMyPlan(this.myplan);

      this.notifyVue("top", "right", "여행 계획이 수정되었습니다.");

      this.movePage(); // 목록으로 이동
    },

    /* 목록으로 이동 */
    movePage() {
      this.deleteAllAttraction();
      this.$router.push({ name: "myplanlist" });
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