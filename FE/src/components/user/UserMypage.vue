<template>
  <b-container class="mt-4 p-4" style="border: 1px solid silver; border-radius: 20px">
    <div class="row">
      <div class="col-2 border-right">
        <!-- <b-avatar src="https://placekitten.com/300/300" size="10rem"></b-avatar> -->
        <img src="@/assets/plate.png" alt="" style="width: 100px; height: 100px" />
        <div class="text-center mt-4">
          <h4 class="font-weight-bold">
            {{ memberName }}님 <router-link :to="{ name: 'modify' }">></router-link>
          </h4>
          <div>{{ memberRole }}</div>
        </div>
        <div class="mt-4">
          <!-- <button @click="logout">로그아웃</button> -->
          <b-button
            class="btn"
            @click="logout"
            style="background-color: rgba(0, 0, 0, 0); border-color: orange; color: orange"
            >로그아웃</b-button
          >
        </div>
      </div>

      <div class="col-10">
        <h4 class="text-left mt-4 mb-4 ml-4 font-weight-bold">나의 활동</h4>
        <div class="row">
          <div
            class="col"
            style="word-break: break-all; border-right: solid 1px black; font-size: 17px"
          >
            <router-link :to="{ name: 'myPagePlan' }">작성한 플랜</router-link>
          </div>
          <div class="col" style="word-break: break-all; border-right: solid 1px black">
            <router-link :to="{ name: 'myPageArticle' }">작성한 게시글</router-link>
          </div>
          <div class="col" style="word-break: break-all; border-right: solid 1px black">
            <router-link :to="{ name: 'myPageHeartPlan' }">좋아요한 플랜 목록</router-link>
          </div>
          <div class="col" style="word-break: break-all">
            <router-link :to="{ name: 'myPageHeartArticle' }">좋아요한 게시글</router-link>
          </div>
        </div>
        <div class="border mt-4" style="border: 1px solid silver; border-radius: 20px">
          <router-view class="mt-4"></router-view>
        </div>
      </div>
    </div>
  </b-container>
</template>

<script>
import http from "@/api/http";
import router from "@/router";

export default {
  components: {},
  data() {
    return {
      message: "",
    };
  },
  computed: {
    memberId() {
      return this.$store.getters.getMemberId;
    },
    memberName() {
      return this.$store.getters.getMemberName;
    },
    memberRole() {
      return this.$store.getters.getMemberRole;
    },
  },
  created() {
    http
      .get("/member/" + this.memberId, {
        headers: {
          Authorization: "Bearer " + sessionStorage.getItem("access-token"),
        },
      })
      .then((resp) => {
        this.$store.dispatch("myPageData", resp.data);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  updated() {
    http
      .get("/member/" + this.memberId, {
        headers: {
          Authorization: "Bearer " + sessionStorage.getItem("access-token"),
        },
      })
      .then((resp) => {
        this.$store.dispatch("myPageData", resp.data);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    logout() {
      this.$store.dispatch("memberLogout");
      router.push({ name: "main" }).catch(() => {});
    },
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: orange;
}

a:hover {
  color: black;
}
</style>
