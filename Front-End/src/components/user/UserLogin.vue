<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row" style="float: none; margin: 0 auto">
        <div class="col-md-4" style="float: none; margin: 0 auto">
          <card>
            <h4 slot="header" class="card-title">로그인</h4>
            <form>
              <div class="row">
                <div class="col-md-12">
                  <base-input
                    type="text"
                    label="ID"
                    placeholder="ID"
                    v-model="user.id"
                  >
                  </base-input>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <base-input
                    type="password"
                    label="PassWord"
                    placeholder="Password"
                    v-model="user.password"
                  >
                  </base-input>
                </div>
              </div>
              <div class="text-center">
                <b-checkbox v-model="user.idSave" class="float-left">
                  아이디 저장
                </b-checkbox>
                <button
                  type="submit"
                  class="btn btn-info btn-fill float-right"
                  @click.prevent="login"
                  style="padding: 0.375rem 0.75rem"
                >
                  로그인
                </button>
              </div>
            </form>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        id: "",
        password: "",
        idSave: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo", "savedId"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async login() {
      await this.userConfirm(this.user);
      if (this.isLogin) {
        let token = sessionStorage.getItem("access-token");

        await this.getUserInfo(token);
        this.$router.push({ name: "main" });
      }
    },
  },
  created() {
    this.user.id = this.savedId;
    if (this.savedId) {
      this.user.idSave = true;
    }
  },
};
</script>

<style scoped></style>
