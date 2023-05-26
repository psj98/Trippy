<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <h3 class="card-title" style="margin-bottom: 20px">마이 페이지</h3>
        <form>
          <!-- 아이디 / 회원 등급 -->
          <div class="row">
            <div class="col-md-6">
              <base-input
                type="text"
                label="아이디"
                :disabled="true"
                placeholder="UserID"
                v-model="user.id"
              >
              </base-input>
            </div>

            <div class="col-md-6">
              <base-input
                type="text"
                label="등급"
                :disabled="true"
                placeholder="Grade"
                v-model="user.userGrade"
              >
              </base-input>
            </div>
          </div>

          <!-- 이름 / 이메일 -->
          <div class="row">
            <div class="col-md-6">
              <base-input
                type="text"
                label="이름"
                placeholder="Username"
                v-model="user.name"
              >
              </base-input>
            </div>
            <div class="col-md-3">
              <base-input
                type="text"
                label="이메일"
                placeholder="ID"
                v-model="user.emailId"
              >
              </base-input>
            </div>
            <div class="col-md-3">
              <label class="control-label">도메인</label>
              <b-input-group prepend="@">
                <b-form-select
                  v-model="user.emailDomain"
                  :options="domains"
                ></b-form-select>
              </b-input-group>
            </div>
          </div>

          <!-- 시도 / 구군 드롭 다운 -->
          <div class="row mb-2">
            <div class="col-md-6">
              <label>시도</label>
              <b-form-select
                v-model="user.citySido"
                :options="sidos"
                @change="[setCityGuGunNull(), gugunList()]"
              ></b-form-select>
            </div>
            <div class="col-md-6">
              <label>구군</label>
              <b-form-select
                v-model="user.cityGugun"
                :options="guguns"
              ></b-form-select>
            </div>
          </div>

          <!-- 비밀번호 변경 / 회원 탈퇴 / 수정 버튼 -->
          <div class="d-flex justify-content-end">
            <!-- 비밀번호 변경 -->
            <div class="mr-2">
              <b-button
                type="submit"
                block
                variant="info"
                style="color: white; padding: 0.375rem 0.75rem"
              >
                <router-link
                  :to="{ name: 'changepassword' }"
                  style="color: white"
                >
                  비밀번호 변경
                </router-link>
              </b-button>
            </div>

            <!-- 회원 탈퇴 -->
            <div class="mr-2">
              <b-button
                type="submit"
                block
                variant="info"
                style="color: white; padding: 0.375rem 0.75rem"
                @click="deleteUser"
              >
                회원 탈퇴
              </b-button>
            </div>

            <!-- 회원 정보 수정 -->
            <div>
              <b-button
                type="submit"
                block
                variant="info"
                style="color: white; padding: 0.375rem 0.75rem"
                @click="updateUser"
              >
                수정
              </b-button>
            </div>
          </div>
        </form>
      </card>
    </div>
  </div>
</template>
<script>
import axios from "@/api/http";
import { mapState, mapActions, mapMutations } from "vuex";

const userStore = "userStore";
const myPlanStore = "myPlanStore";

export default {
  name: "EditProfileForm",
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  data() {
    return {
      user: {
        id: "",
        name: "",
        emailId: "",
        emailDomain: "",
        citySido: "",
        cityGugun: "",
        userGrade: "",
      },
      domains: {
        "ssafy.com": "ssafy.com",
        "naver.com": "naver.com",
        "google.com": "google.com",
        "kakao.com": "kakao.com",
      },
      sidos: [
        { value: null, text: "시도를 선택하세요" },
        { value: 1, text: "서울" },
        { value: 2, text: "인천" },
        { value: 3, text: "대전" },
        { value: 4, text: "대구" },
        { value: 5, text: "광주" },
        { value: 6, text: "부산" },
        { value: 7, text: "울산" },
        { value: 8, text: "세종특별자치시" },
        { value: 31, text: "경기도" },
        { value: 32, text: "강원도" },
        { value: 33, text: "충청북도" },
        { value: 34, text: "충청남도" },
        { value: 35, text: "경상북도" },
        { value: 36, text: "경상남도" },
        { value: 37, text: "전라북도" },
        { value: 38, text: "전라남도" },
        { value: 39, text: "제주도" },
      ],
      guguns: [{ value: null, text: "구군을 선택하세요" }],
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
    };
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    ...mapActions(myPlanStore, ["deleteAllMyPlan"]),
    ...mapMutations(userStore, ["SET_USER_INFO"]),

    /* User 정보 수정 */
    async updateUser() {
      const API_URL = `/users/${this.user.id}`;
      await axios({
        url: API_URL,
        method: "put",
        data: this.user,
      }).then(({ data }) => {
        if (data.message === "UPDATE SUCCESS") {
          this.SET_USER_INFO(this.user);
          let accessToken = data["access-token"];
          let refreshToken = data["refresh-token"];
          sessionStorage.setItem("access-token", accessToken);
          sessionStorage.setItem("refresh-token", refreshToken);

          this.notifyVue("top", "right", "회원 정보가 수정되었습니다");

          this.$router.push("/admin/overview");
        }
      });
    },

    /* 회원 탈퇴 */
    async deleteUser() {
      /* MyPlan 데이터 삭제 */
      await this.deleteAllMyPlan(this.userInfo.id);

      /* 로그아웃 */
      await this.userLogout(this.userInfo.id);
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("refresh-token");

      const API_URL = `/users/${this.user.id}`;
      await axios({
        url: API_URL,
        method: "delete",
      }).then(() => {
        this.notifyVue("top", "right", "회원 탈퇴가 완료되었습니다.");
        this.$router.push("/admin/overview");
      });
    },

    /* 시도 구군 리스트 가져오기 */
    async setCityGuGunNull() {
      this.user.cityGugun = null;
    },
    async gugunList() {
      if (this.guguns.length > 0) this.guguns.splice(1);
      const params = { sido: this.user.citySido };

      if (this.user.citySido) {
        await axios.get(`/attractions/gugun`, { params }).then(({ data }) => {
          data.forEach((gugun) => {
            this.guguns.push({
              value: gugun.gugunCode,
              text: gugun.gugunName,
            });
          });
        });
      }
    },

    /* 경고 띄우기 */
    async notifyVue(verticalAlign, horizontalAlign, message) {
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
  async created() {
    this.user.id = this.userInfo.id;
    this.user.name = this.userInfo.name;
    this.user.emailId = this.userInfo.emailId;
    this.user.emailDomain = this.userInfo.emailDomain;
    this.user.citySido = this.userInfo.citySido;
    this.user.cityGugun = this.userInfo.cityGugun;
    this.user.userGrade = this.userInfo.userGrade;
    this.gugunList();
  },
};
</script>
<style></style>
