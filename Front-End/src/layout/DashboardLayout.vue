<template>
  <div class="wrapper">
    <side-bar>
      <mobile-menu slot="content"></mobile-menu>
      <!-- <sidebar-link to="/admin/overview">
        <i class="nc-icon nc-chart-pie-35"></i>
        <p>메인 페이지</p>
      </sidebar-link> -->
      <sidebar-link v-if="userInfo" to="/user/view">
        <i class="nc-icon nc-circle-09"></i>
        <p>마이페이지</p>
      </sidebar-link>
      <sidebar-link to="/board/notice">
        <i class="nc-icon nc-notification-70"></i>
        <p>공지 사항</p>
      </sidebar-link>
      <sidebar-link to="/board/free">
        <i class="nc-icon nc-notes"></i>
        <p>자유 게시판</p>
      </sidebar-link>
      <sidebar-link to="/board/question">
        <i class="nc-icon nc-bulb-63"></i>
        <p>질문 게시판</p>
      </sidebar-link>
      <sidebar-link to="/admin/attractions">
        <i class="nc-icon nc-pin-3"></i>
        <p>관광지 조회</p>
      </sidebar-link>
      <sidebar-link v-if="userInfo" to="/myplans/list">
        <i class="nc-icon nc-bullet-list-67"></i>
        <p>나만의 여행 계획</p>
      </sidebar-link>
      <sidebar-link to="/shareboards/list">
        <i class="nc-icon nc-bus-front-12"></i>
        <p>여행 계획 공유 게시판</p>
      </sidebar-link>
      <!-- <sidebar-link to="/admin/typography">
        <i class="nc-icon nc-paper-2"></i>
        <p>Typography</p>
      </sidebar-link>
      <sidebar-link to="/admin/test">
        <i class="nc-icon nc-paper-2"></i>
        <p>test</p>
      </sidebar-link>
      <sidebar-link to="/admin/icons">
        <i class="nc-icon nc-atom"></i>
        <p>Icons</p>
      </sidebar-link>
      <sidebar-link to="/admin/notifications">
        <i class="nc-icon nc-bell-55"></i>
        <p>Notifications</p>
      </sidebar-link> -->

      <!-- <template slot="bottom-links">
        <sidebar-link class="active" to="/admin/upgrade">
          <i class="nc-icon nc-alien-33"></i>
          <p>Upgrade to PRO</p>
        </sidebar-link>
      </template> -->
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click="toggleSidebar"></dashboard-content>

      <content-footer style="z-index: 1"></content-footer>
    </div>
  </div>
</template>

<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "./MobileMenu.vue";
import SideBar from "../components/SidebarPlugin/SideBar.vue";

import { mapState } from "vuex";

const userStore = "userStore";

export default {
  components: {
    TopNavbar,
    ContentFooter,
    DashboardContent,
    MobileMenu,
    SideBar,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    notifyVue(verticalAlign, horizontalAlign) {
      const color = Math.floor(Math.random() * 4 + 1);
      this.$notifications.notify({
        message: `<span>Welcome to <b>Light Bootstrap Dashboard</b> - a beautiful freebie for every web developer.</span>`,
        icon: "nc-icon nc-app",
        horizontalAlign: horizontalAlign,
        verticalAlign: verticalAlign,
        type: this.type[color],
      });
    },
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    },
  },
};
</script>

<style lang="scss"></style>