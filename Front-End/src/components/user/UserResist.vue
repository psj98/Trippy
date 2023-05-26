<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row" style="float: none; margin: 100 auto">
        <div class="col-md-8" style="float: none; margin: 0 auto">
          <card>
            <h3 slot="header" class="card-title">회원 가입</h3>
            <form enctype="multipart/form-data">
              <div class="row">
                <div class="col-md-12">
                  <label>아이디</label>
                  <b-form-input
                    v-model="user.id"
                    :state="idState"
                    aria-describedby="id-feedback"
                    placeholder="id"
                    trim
                  ></b-form-input>
                  <b-form-invalid-feedback id="id-feedback">
                    {{ idFeedbackMessage }}
                  </b-form-invalid-feedback>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <base-input
                    class="mb-0"
                    type="text"
                    label="이름"
                    placeholder="Username"
                    v-model="user.name"
                  >
                  </base-input>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <label>비밀번호</label>
                  <b-form-input
                    type="password"
                    v-model="user.password"
                    :state="pwState"
                    aria-describedby="pw-feedback"
                    placeholder="password"
                    trim
                  ></b-form-input>
                  <b-form-invalid-feedback id="pw-feedback">
                    비밀번호는 4자 이상 20자 이하여야 합니다.
                  </b-form-invalid-feedback>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <label>비밀번호 확인</label>
                  <b-form-input
                    type="password"
                    v-model="passwordcf"
                    :state="pwConfirm"
                    aria-describedby="pw-confirm"
                    placeholder="password confirm"
                    trim
                  ></b-form-input>
                  <b-form-invalid-feedback id="pw-confirm">
                    비밀번호가 다릅니다.
                  </b-form-invalid-feedback>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6">
                  <label>이메일</label>
                  <b-form-input
                    type="text"
                    label="email"
                    placeholder="email"
                    v-model="user.emailId"
                  >
                  </b-form-input>
                </div>

                <div class="col-md-6">
                  <div class="form-group mb-0">
                    <label class="control-label">도메인</label>
                    <b-input-group prepend="@">
                      <b-form-select
                        v-model="user.emailDomain"
                        :options="domains"
                        style="height: 100%"
                      ></b-form-select>
                    </b-input-group>
                  </div>
                </div>
              </div>

              <!-- <div class="row">
                <div class="col-md-12">
                  <label>프로필 이미지</label>
                  <b-form-file
                    v-model="file"
                    :state="Boolean(file)"
                    placeholder="Choose a file or drop it here..."
                    drop-placeholder="Drop file here..."
                  ></b-form-file>
                </div>
              </div> -->

              <div class="row mb-2">
                <div class="col-md-6">
                  <label>시도</label>
                  <b-form-select
                    v-model="user.citySido"
                    :options="sidos"
                    @change="gugunList()"
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

              <div class="d-flex justify-content-end">
                <div>
                  <b-button
                    type="submit"
                    block
                    variant="info"
                    style="color: white; padding: 0.375rem 0.75rem"
                    @click.prevent="createUser"
                  >
                    회원 가입
                  </b-button>
                </div>
              </div>
            </form>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/api/http";

export default {
  name: "UserResist",
  data() {
    return {
      user: {
        id: "",
        password: "",
        name: "",
        emailId: "",
        emailDomain: null,
        citySido: null,
        cityGugun: null,
        userGrade: "",
      },
      passwordcf: "",
      domains: {
        null: "도메인 선택",
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
      idFeedbackMessage: "",
      idRow: null,
    };
  },
  watch: {
    "user.id": function () {
      this.idRow = this.idCheck();
    },
  },
  computed: {
    idState() {
      if (this.user.id.length == 0) return null;

      if (this.user.id.length < 4 || this.user.id.length > 16) {
        this.idFeedbackMessage = "아이디는 4자 이상 또는 16자 이하여야 합니다.";
        return false;
      }

      if (this.idRow === 1) {
        this.idFeedbackMessage = "중복되는 아이디입니다.";
        return false;
      }

      return true;
    },
    pwState() {
      if (this.user.password.length == 0) return null;
      return this.user.password.length >= 4 && this.user.password.length <= 20
        ? true
        : false;
    },
    pwConfirm() {
      if (this.passwordcf.length == 0) return null;
      return this.user.password === this.passwordcf;
    },
  },
  methods: {
    async createUser() {
      if (!this.idState || !this.pwState || !this.pwConfirm) {
        this.notifyVue("top", "right", "아이디 또는 비밀번호를 확인해주세요.");
        return;
      }

      const API_URL = `/users`;
      axios
        .post(API_URL, this.user)
        .then(() => {
          this.notifyVue("top", "right", "회원가입 되었습니다");
          this.$router.push("/admin/overview");
        })
        .catch(() => {
          this.notifyVue("top", "right", "회원가입 실패하였습니다");
        });
    },

    async gugunList() {
      this.user.cityGugun = null;

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

    async idCheck() {
      this.idRow = 0;
      await axios.get(`/users/idcheck/${this.user.id}`).then(({ data }) => {
        this.idRow = data;
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

<style></style>
