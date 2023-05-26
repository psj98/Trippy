<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid" style="justify-content: flex-start">
      <button
        type="button"
        class="navbar-toggler navbar-toggler-right"
        :class="{ toggled: $sidebar.showSidebar }"
        aria-controls="navigation-index"
        aria-expanded="false"
        aria-label="Toggle navigation"
        @click="toggleSidebar"
      >
        <span class="navbar-toggler-bar burger-lines"></span>
        <span class="navbar-toggler-bar burger-lines"></span>
        <span class="navbar-toggler-bar burger-lines"></span>
      </button>
      <!-- <a class="navbar-brand" href="#"> sdf</a> -->
      <div class="collapse navbar-collapse" style="margin: 5px 0px">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <router-link
              v-if="!isLogin"
              class="nav-link"
              href="#"
              to="/user/join"
            >
              회원가입
            </router-link>
          </li>
          <li class="nav-item">
            <a
              v-if="isLogin"
              @click.prevent="logout"
              class="nav-link"
              style="cursor: pointer"
            >
              로그아웃
            </a>
            <router-link v-else to="/user/login" class="nav-link"
              >로그인</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    },
  },
  data() {
    return {
      // user: null,
      activeNotifications: false,
    };
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    async logout() {
      // alert(this.userInfo.id);
      await this.userLogout(this.userInfo.id);
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("refresh-token");

      this.$router.push({ name: "main" });
    },
    // getUser() {
    //   if (this.user) {
    //     return true;
    //   } else {
    //     return false;
    //   }
    // },
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
  },
};
</script>

<style></style>
