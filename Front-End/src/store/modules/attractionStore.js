import http from "@/api/http.js";

const attractionStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시도를 선택하세요" }],
    guguns: [{ value: null, text: "구군을 선택하세요" }],
    contents: [{ value: null, text: "관광지 유형을 선택하세요" }],
    contentTypes: [
      { value: 0, text: "전체" },
      { value: 12, text: "관광지" },
      { value: 14, text: "문화시설" },
      { value: 15, text: "축제공연행사" },
      { value: 25, text: "여행코스" },
      { value: 28, text: "레포츠" },
      { value: 32, text: "숙박" },
      { value: 38, text: "쇼핑" },
      { value: 39, text: "음식점" },
    ],
    attractions: [],
    attraction: null,
    sido: null,
    gugun: null,
    contentTypeId: null,
    searchTitleWord: null,
  },
  mutations: {
    /* 시도, 구군 리스트 */
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_SIDO(state, sido) {
      state.sido = sido;
    },
    SET_GUGUN(state, gugun) {
      state.gugun = gugun;
    },
    SET_CONTENT_TYPE(state, contentTypeId) {
      state.contentTypeId = contentTypeId;
    },
    SET_SEARCH_TITLE(state, searchTitleWord) {
      state.searchTitleWord = searchTitleWord;
    },

    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시도를 선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "구군을 선택하세요" }];
    },
    CLEAR_CONTENT_LIST(state) {
      state.contents = [{ value: null, text: "관광지 유형을 선택하세요" }];
      state.contentTypes.forEach((content) => {
        state.contents.push({
          value: content.value,
          text: content.text,
        });
      });
    },

    /* 검색창 초기화 */
    CLEAR_SEARCH_LIST(state) {
      state.sido = null;
      state.gugun = null;
      state.contentTypeId = null;
      state.searchTitleWord = null;
    },

    /* 관광지 리스트 */
    CLEAR_ATTRACTION_LIST(state) {
      state.attractions = [];
    },

    SET_ATTRACTION_LIST(state, attractions) {
      state.attractions = attractions;
    },
    SET_DETAIL_ATTRACTION(state, attraction) {
      state.attraction = attraction;
    },
  },
  actions: {
    /* 시도 리스트 가져오기 */
    getSido({ commit }) {
      http.get(`/attractions/sido`).then(({ data }) => {
        commit("SET_SIDO_LIST", data);
      });
    },

    /* 구군 리스트 가져오기 */
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http.get(`/attractions/gugun`, { params }).then(({ data }) => {
        commit("SET_GUGUN_LIST", data);
      });
    },

    /* 검색 조건에 따른 관광지 리스트 가져오기 */
    getAttractionList({ commit }, cityCode) {
      const SERVICE_KEY = process.env.VUE_APP_ATTRACTION_API_KEY;
      const SERVICE_URL =
        "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
        SERVICE_KEY +
        "&numOfRows=40&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

      commit("SET_SIDO", cityCode.sidoCode);
      commit("SET_GUGUN", cityCode.gugunCode);
      commit("SET_CONTENT_TYPE", cityCode.contentType);
      commit("SET_SEARCH_TITLE", cityCode.searchTitle);

      const params = {
        sido: cityCode.sidoCode,
        gugun: cityCode.gugunCode,
        contentType: cityCode.contentType,
        searchTitle: cityCode.searchTitle,
      };

      http.get(`/attractions`, { params }).then(({ data }) => {
        commit("SET_ATTRACTION_LIST", data);
      });
    },

    /* 관광지 세부 정보 저장 (?) */
    detailAttraction({ commit }, attraction) {
      commit("SET_DETAIL_ATTRACTION", attraction);
    },

    /* 검색한 관광지 지우기 */
    deleteSearchAttraction({ commit }) {
      commit("CLEAR_SIDO_LIST");
      commit("CLEAR_GUGUN_LIST");
      commit("CLEAR_CONTENT_LIST");
      commit("CLEAR_ATTRACTION_LIST");
      commit("CLEAR_SEARCH_LIST");
    },
  },
};

export default attractionStore;
