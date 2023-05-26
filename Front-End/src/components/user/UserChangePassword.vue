<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row" style="float: none; margin: 0 auto">
        <div class="col-md-4" style="float: none; margin: 0 auto">
          <card>
            <h4 slot="header" class="card-title mb-4">비밀번호 확인</h4>
            <form>
              <div class="row">
                <div class="col-md-12">
                  <base-input
                    type="password"
                    label="새 비밀번호"
                    placeholder="새 비밀번호를 입력해주세요"
                    v-model="user.password"
                  >
                  </base-input>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <base-input
                    type="password"
                    label="새 비밀번호 확인"
                    placeholder="새 비밀번호를 한 번 더 입력해주세요"
                    v-model="user.checkPassword"
                  >
                  </base-input>
                </div>
              </div>
              <div class="text-center">
                <button
                  type="submit"
                  class="btn btn-info btn-fill float-right"
                  @click.prevent="changePassword"
                  style="padding: 0.375rem 0.75rem"
                >
                  확인
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
import { mapState } from "vuex";
import axios from "@/api/http";

const userStore = "userStore";

export default {
  name: "UserChangePassword",
  data() {
    return {
      user: {
        password: "",
        checkPassword: "",
      },
      type: ["", "info", "success", "warning", "danger"],
      notifications: {
        topCenter: false,
      },
      newUserInfo: {},
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    async changePassword() {
      if (this.user.password.length == 0) {
        await this.notifyVue("top", "right", "새 비밀번호를 입력해주세요");
        return;
      }

      if (this.user.checkPassword.length == 0) {
        await this.notifyVue(
          "top",
          "right",
          "새 비밀번호를 한 번 더 입력해주세요"
        );
        return;
      }

      if (this.user.password != this.user.checkPassword) {
        await this.notifyVue("top", "right", "비밀번호가 일치하지 않습니다");
        return;
      }

      this.newUserInfo = this.userInfo;
      this.newUserInfo.password = this.user.password;

      await axios
        .put("/users/changepw", JSON.stringify(this.newUserInfo))
        .then(() => {
          this.$router.push("/user/view");
          this.notifyVue("top", "right", "비밀번호가 변경되었습니다");
        });
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
};
</script>

<style scoped></style>