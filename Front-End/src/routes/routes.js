import DashboardLayout from "@/layout/DashboardLayout.vue";
// GeneralViews
import NotFound from "@/pages/NotFoundPage.vue";

// Admin pages
import Overview from "src/pages/Overview.vue";

import Typography from "src/pages/Typography.vue";
import Icons from "src/pages/Icons.vue";
import Notifications from "src/pages/Notifications.vue";
import Test from "src/pages/TableList2";

import Upgrade from "src/pages/Upgrade.vue";

// Attractions
import AppAttractions from "@/pages/AppAttractions.vue";
import AttractionDetail from "@/components/attraction/AttractionDetail";

// User
import AppUser from "@/pages/AppUser.vue";
import UserLogin from "@/components/user/UserLogin.vue";
import UserResist from "@/components/user/UserResist.vue";
import UserProfile from "@/components/user/UserProfile.vue";
import UserChangePassword from "@/components/user/UserChangePassword.vue";

// Board
import AppBoard from "@/pages/AppBoard";
import Board from "@/components/board/Board.vue";
import BoardView from "@/components/board/BoardView.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardModify from "@/components/board/BoardModify.vue";

// MyPlan
import AppMyPlan from "@/pages/AppMyPlan";
import MyPlanList from "@/components/myplan/MyPlanList";
import MyPlanWrite from "@/components/myplan/MyPlanWrite";
import MyPlanModify from "@/components/myplan/MyPlanModify";
import MyPlanView from "@/components/myplan/MyPlanView";

// ShareBoard
import AppShareBoard from "@/pages/AppShareBoard";
import ShareBoardList from "@/components/shareboard/ShareBoardList";
import ShareBoardView from "@/components/shareboard/ShareBoardView";

import store from "@/store";

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  // console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    // console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // this.$route.push({ name: "login" });
  } else {
    // console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    component: DashboardLayout,
    name: "DashboardLayout",
    redirect: "/admin/overview",
  },

  /* 게시판 */
  {
    path: "/board/:boardType",
    component: DashboardLayout,
    redirect: "/board/:boardType/list",
    meta: {
      reload: true,
    },
    children: [
      {
        path: "list",
        name: "board",
        component: Board,
      },
      {
        path: "view/:articleNo",
        name: "boardview",
        component: BoardView,
      },
      {
        path: "write",
        name: "boardwrite",
        beforeEnter: onlyAuthUser,
        component: BoardWrite,
      },
      {
        path: "modify/:articleNo",
        name: "boardmodify",
        beforeEnter: onlyAuthUser,
        component: BoardModify,
      },
    ],
  },

  /* 사용자 */
  {
    path: "/user",
    component: DashboardLayout,
    // redirect: "/",
    children: [
      {
        path: "view",
        name: "view",
        beforeEnter: onlyAuthUser,
        component: UserProfile,
      },
      {
        path: "login",
        name: "login",
        component: UserLogin,
      },
      {
        path: "join",
        name: "join",
        component: UserResist,
      },
      {
        path: "changepw",
        name: "changepassword",
        component: UserChangePassword,
      },
    ],
  },

  /* 관광지 */
  {
    path: "/attractions",
    component: DashboardLayout,
    children: [
      {
        path: ":contentId",
        name: "attractiondetail",
        component: AttractionDetail,
      },
    ],
  },

  /* 나만의 여행 계획 */
  {
    path: "/myplans",
    component: DashboardLayout,
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "myplanlist",
        component: MyPlanList,
      },
      {
        path: "write",
        name: "myplanwrite",
        component: MyPlanWrite,
      },
      {
        path: "modify/:articleNo",
        name: "myplanmodify",
        component: MyPlanModify,
      },
      {
        path: "view/:articleNo",
        name: "myplanview",
        component: MyPlanView,
      },
    ],
  },

  /* 공유 게시판 */
  {
    path: "/shareboards",
    component: DashboardLayout,
    children: [
      {
        path: "list",
        name: "shareboardlist",
        component: ShareBoardList,
      },
      {
        path: "view/:articleNo",
        name: "shareboardview",
        component: ShareBoardView,
      },
    ],
  },

  /* 대시보드 */
  {
    path: "/admin",
    name: "main",
    component: DashboardLayout,
    redirect: "/admin/overview",
    children: [
      {
        path: "attractions",
        name: "attractions",
        component: AppAttractions,
      },
      {
        path: "myplans",
        name: "myplans",
        component: AppMyPlan,
      },
      {
        path: "shareboards",
        name: "shareboards",
        component: AppShareBoard,
      },
      {
        path: "test",
        name: "Test",
        component: Test,
      },
      {
        path: "overview",
        name: "Overview",
        component: Overview,
      },
      {
        path: "user",
        name: "User",
        component: UserProfile,
      },
      {
        path: "typography",
        name: "Typography",
        component: Typography,
      },
      {
        path: "icons",
        name: "Icons",
        component: Icons,
      },
      {
        path: "notifications",
        name: "Notifications",
        component: Notifications,
      },
      {
        path: "upgrade",
        name: "Upgrade to PRO",
        component: Upgrade,
      },
    ],
  },
  { path: "*", name: "notfound", component: NotFound },
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;
