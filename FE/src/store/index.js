import Vue from "vue";
import Vuex from "vuex";
import jwtDecode from "jwt-decode";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    memberId: "",
    memberName: "",
    memberPhone: "",
    memberRole: "",
    planList: [],
    heartPlanList: [],
    articleList: [],
    heartArticleList: [],
    commentList: [],
    planTitle: "",
    planDesc: "",
    planEndDate: "",
    planStartDate: "",
    planHashtag: [],
    tempAttractionList: [],
    bestAttractionId: [],
    bestAttractionMemo: [],
    addAttId: [],
    addPlanId: "",
  },
  getters: {
    getMemberId(state) {
      return state.memberId;
    },
    getMemberName(state) {
      return state.memberName;
    },
    getMemberRole(state) {
      return state.memberRole;
    },
    getMemberPhone(state) {
      return state.memberPhone;
    },
    getPlanList(state) {
      console.log(state.planList);
      return state.planList;
    },
    getHeartPlanList(state) {
      return state.heartPlanList;
    },
    getArticleList(state) {
      return state.articleList;
    },
    getHeartArticleList(state) {
      return state.heartArticleList;
    },
    getCommentList(state) {
      return state.commentList;
    },
    getPlanTitle(state) {
      return state.planTitle;
    },
    getPlanDesc(state) {
      return state.planDesc;
    },
    getPlanStartDate(state) {
      return state.planStartDate;
    },
    getPlanEndDate(state) {
      return state.planEndDate;
    },
    getPlanHashtag(state) {
      return state.planHashtag;
    },
    getTempAttractionList(state) {
      return state.tempAttractionList;
    },
    getBestAttractionid(state) {
      return state.bestAttractionId;
    },
    getBestAttractionMemo(state) {
      return state.bestAttractionMemo;
    },
    getAddAttId(state) {
      return state.addAttId;
    },
    getAddPlanId(state) {
      return state.addPlanId;
    },
  },
  mutations: {
    SET_MEMBER_ID(state, payload) {
      state.memberId = payload;
    },
    SET_MEMBER_NAME(state, payload) {
      state.memberName = payload;
    },
    SET_MEMBER_PHONE(state, payload) {
      state.memberPhone = payload;
    },
    SET_MEMBER_ROLE(state, payload) {
      state.memberRole = payload;
    },
    SET_PLAN_LIST(state, payload) {
      state.planList = payload;
    },
    SET_HEART_PLAN_LIST(state, payload) {
      state.heartPlanList = payload;
    },
    SET_ARTICLE_LIST(state, payload) {
      state.articleList = payload;
    },
    SET_HEART_ARTICLE_LIST(state, payload) {
      state.heartArticleList = payload;
    },
    SET_COMMENT_LIST(state, payload) {
      state.commentList = payload;
    },
    SET_PLAN_TITLE(state, payload) {
      state.planTitle = payload;
    },
    SET_PLAN_DESC(state, payload) {
      state.planDesc = payload;
    },
    SET_PLAN_START_DATE(state, payload) {
      state.planStartDate = payload;
    },
    SET_PLAN_END_DATE(state, payload) {
      state.planEndDate = payload;
    },
    SET_PLAN_HASHTAG(state, payload) {
      state.planHashtag = payload;
    },
    ADD_TEMP_ATTRACTION(state, payload) {
      console.log(payload);
      console.log(state.tempAttractionList);
      state.tempAttractionList.push(payload);
      console.log(state.tempAttractionList);
    },
    REMOVE_TEMP_ATTRACTION(state, payload) {
      state.tempAttractionList.splice(payload, 1);
    },
    SET_TEMP_ATTRACTION(state, payload) {
      state.tempAttractionList = payload;
    },
    REMOVE_ALL_TEMP_ATTRACTION(state) {
      state.tempAttractionList = [];
    },
    SET_BEST_ATTRACTION_ID(state, payload) {
      state.bestAttractionId = payload;
    },
    SET_BEST_ATTRACTION_MEMO(state, payload) {
      state.bestAttractionMemo = payload;
    },
    SET_ADD_ATT_ID(state, payload) {
      console.log(payload);

      state.addAttId.payload;

      console.log(state.addAttId);
    },
    SET_ADD_PLAN_ID(state, payload) {
      state.addPlanId = payload;
    },
  },
  actions: {
    async memberLogin({ commit }) {
      let jwt = jwtDecode(sessionStorage.getItem("access-token"));
      let member = JSON.parse(jwt.sub);

      commit("SET_MEMBER_ID", member.memberId);
      commit("SET_MEMBER_NAME", member.name);
    },
    async memberLogout({ commit }) {
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("refresh-token");
      commit("SET_MEMBER_ID", null);
      commit("SET_MEMBER_NAME", null);
      commit("SET_MEMBER_PHONE", null);
    },
    async myPageData({ commit }, payload) {
      commit("SET_MEMBER_PHONE", payload.memberPhone);
      commit("SET_MEMBER_ROLE", payload.memberRole);
      commit("SET_PLAN_LIST", payload.planList);
      commit("SET_HEART_PLAN_LIST", payload.heartPlanList);
      commit("SET_ARTICLE_LIST", payload.articleList);
      commit("SET_HEART_ARTICLE_LIST", payload.heartArticleList);
      commit("SET_COMMENT_LIST", payload.commentsList);
    },
    async planData({ commit }, payload) {
      commit("SET_PLAN_TITLE", payload.title);
      commit("SET_PLAN_DESC", payload.description);
      commit("SET_PLAN_START_DATE", payload.startDate);
      commit("SET_PLAN_END_DATE", payload.endDate);
      commit("SET_PLAN_HASHTAG", payload.hashtag);
    },
    async addAttraction({ commit }, payload) {
      console.log(payload);
      commit("ADD_TEMP_ATTRACTION", payload);
    },
    async removeAttraction({ commit }, payload) {
      commit("REMOVE_TEMP_ATTRACTION", payload);
    },
    async removeAllAttraction({ commit }) {
      commit("REMOVE_ALL_TEMP_ATTRACTION");
    },
    async bestData({ commit }, payload) {
      commit("SET_BEST_ATTRACTION_ID", payload.bestAttractionId);
      commit("SET_BEST_ATTRACTION_MEMO", payload.bestAttractionMemo);
    },
    async setAttraction({ commit }, payload) {
      commit("SET_TEMP_ATTRACTION", payload);
    },
    async addPlanData({ commit }, payload) {
      commit("SET_ADD_ATT_ID", payload);
      // commit("SET_ADD_PLAN_ID", payload.addPlanData);
    },
  },
  modules: {},
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
