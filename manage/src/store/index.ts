import { createStore } from "vuex";

const store = createStore({
  state: {
    userName: "",
    isLogined: false,
    token: "",
    env: "",
    onLoading: false,
  },
  mutations: {
    setUserLoginInfo(state, payload) {
      const { user_name, isLogined, token } = payload;
      state.userName = user_name;
      state.isLogined = isLogined;
      state.token = token;
      window.localStorage.setItem("manage_token", token);
    },
    setEnv(state, payload) {
      state.env = payload;
    },
    setLoading(state, payload) {
      state.onLoading = payload;
    },
  },
  actions: {
    setUserLoginInfo(context, payload) {
      context.commit("setUserLoginInfo", payload);
    },
    setEnv(context, payload) {
      context.commit("setEnv", payload);
    },
    setLoading(context, payload) {
      context.commit("setLoading", payload);
    },
  },
  getters: {
    userName: function (state) {
      return state.userName;
    },
    isLogined: function (state) {
      return state.isLogined;
    },
    token: function (state) {
      return state.token;
    },
    env: function (state) {
      return state.env;
    },
    onLoading: function (state) {
      return state.onLoading;
    },
  },
});

export default store;
