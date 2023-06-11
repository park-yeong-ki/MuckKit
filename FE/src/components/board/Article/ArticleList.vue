<template>
  <div class="container">
    <h2>여행 후기 둘러보기</h2>
    <section class="py-5">
      <!-- <div class="text-right">
        <router-link :to="{ name: 'articlewrite' }"
          ><b-button
            id="btnArea"
            class="btn btn-outline-light btn-lg"
            style="background-color: orange; border-color: orange"
            >후기 작성하기</b-button
          ></router-link
        >
      </div> -->
      <div class="d-flex gap-2 justify-content-center py-5">
        <!-- 해시태그 반복문 돌릴것 10개 -->
        <button
          id="hbtn"
          class="btn btn-light rounded-pill px-3 ml-3"
          type="button"
          v-for="(hashtag, idx) in hashtagList"
          :key="idx"
          @click="searchByHashtag(hashtag.hashtagId)"
        >
          #{{ hashtag.tagName }}
        </button>
      </div>

      <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
          <article-list-item
            v-for="(article, idx) in articleList"
            :key="idx"
            :at="article"
          ></article-list-item>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import axios from "axios";
import http from "@/api/http";
import ArticleListItem from "./ArticleListItem.vue";

export default {
  components: {
    ArticleListItem,
  },
  data() {
    return {
      articleList: [],
      hashtagList: [],
      planList: [],
      tag: "",
      plan: {
        title: "",
        startDate: "",
        endDate: "",
        description: "",
        hashtag: [],
      },
    };
  },
  created() {
    axios.get("http://localhost/enjoytrip/article?sort=hit").then((resp) => {
      this.articleList = resp.data;
    });
    http
      .get("/plan")
      .then((resp) => {
        this.hashtagList = resp.data.hashtags;
        this.planList = resp.data.plans;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    searchByHashtag(hashtagId) {
      http
        .get("/plan/hashtag/" + hashtagId)
        .then((resp) => {
          this.hashtagList = resp.data.hashtags;
          this.planList = resp.data.plans;

          for (let i = 0; i < this.planList.length; i++) {
            if (this.articleList.planId === this.planList.planId) {
              this.articleList = [];
              this.articleList.push(this.articleList[i]);
            }
          }
        })
        .then((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
