import http from "@/api/http.js";

const api = http;

async function login(user, success, fail) {
  await api
    .post(`/users/login`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function findById(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/users/${id}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/users/refresh`, user).then(success).catch(fail);
}

async function logout(id, success, fail) {
  await api.get(`/users/logout/${id}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout };
