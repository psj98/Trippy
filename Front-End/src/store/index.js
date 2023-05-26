import Vue from "vue";
import Vuex from "vuex";

import createPersistedState from "vuex-persistedstate";

import userStore from "@/store/modules/userStore";
import attractionStore from "@/store/modules/attractionStore";
import myPlanStore from "@/store/modules/myPlanStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    attractionStore,
    userStore,
    myPlanStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});
