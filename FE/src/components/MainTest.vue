<template>
  <div>
    <b-container class="bv-example-row text-center">
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-jumbotron class="mt-5" style="background: rgba(0, 0, 0, 0)" text-variant="dark">
            <template>
              <div style="color: white" class="text-left">
                <h2>나만의 먹킷리스트,</h2>
                <h2>현실로 만들어보세요!</h2>
                <br />
              </div>
            </template>

            <template>
              <b-input-group
                id="mainArea"
                v-for="size in ['lg']"
                :key="size"
                :size="size"
                class="mb-3"
              >
                <b-form-input
                  id="inputArea"
                  class="btn"
                  style="border-color: white; background-color: white"
                  placeholder="검색어를 입력해보세요"
                  v-model="word"
                ></b-form-input>
                <b-input-group-append>
                  <b-button
                    id="btnArea"
                    class="btn btn-outline-light btn-lg border-left"
                    style="
                      background-color: white;
                      border-color: white;
                      color: orange;
                      font-weight: bold;
                    "
                    v-on:click="search"
                    >검색</b-button
                  >
                </b-input-group-append>
              </b-input-group>
            </template>
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
    <div v-if="see">
      <section class="py-5">
        <div class="container px-4 px-lg-5 my-5">
          <attraction-view
            v-for="(attraction, idx) in attractionList"
            :key="idx"
            :att="attraction"
          ></attraction-view>
        </div>
      </section>
    </div>
    <div v-else>
      <section class="py-5 mt-3">
        <h3>⭐베스트 후기⭐</h3>
        <div class="container px-4 px-lg-5 mt-5">
          <div
            class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"
          >
            <article-list-item
              v-for="(article, idx) in articleList.slice(0, 4)"
              :key="idx"
              :at="article"
            ></article-list-item>
          </div>
        </div>
        <router-link :to="{ name: 'articlelist' }"
          ><b-button
            id="btnArea"
            class="btn btn-outline-light btn-lg"
            style="background-color: orange; border-color: orange"
            size="sm"
            >더 알아보기</b-button
          ></router-link
        >
      </section>
      <section class="py-5">
        <h3>🔥HOT 플랜🔥</h3>
        <div class="container px-12 px-lg-10 mt-5">
          <div
            class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"
          >
            <plan-list-item-vue v-for="(plan, idx) in planList.slice(0, 4)" :key="idx" :pl="plan">
              <li></li>
            </plan-list-item-vue>
          </div>
        </div>
        <router-link :to="{ name: 'planlist' }"
          ><b-button
            id="btnArea"
            class="btn btn-outline-light btn-lg"
            style="background-color: orange; border-color: orange"
            size="sm"
            >더 알아보기</b-button
          ></router-link
        >
      </section>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ArticleListItem from "./board/Article/ArticleListItem.vue";
import AttractionView from "./board/Attraction/AttractionView.vue";
import PlanListItemVue from "./board/Plan/plan-item/PlanListItem.vue";

export default {
  components: {
    ArticleListItem,
    AttractionView,
    PlanListItemVue,
  },
  data() {
    return {
      planList: [],
      articleList: [],
      word: "",
      see: false,
      attractionList: "",
    };
  },
  mounted() {
    axios.get("http://localhost/enjoytrip/plan").then((resp) => {
      this.planList = resp.data.plans;
    });
    axios.get("http://localhost/enjoytrip/article?sort=hit").then((resp) => {
      this.articleList = resp.data;
    });
  },
  methods: {
    search() {
      if (this.word == "") {
        alert("검색어를 입력하세요.");
        return;
      }
      this.see = !this.see;
      axios.get("http://localhost/enjoytrip/attraction?keyword=" + this.word).then((resp) => {
        this.attractionList = resp.data;
      });
      // this.$router.push({ name: "attractionview", params: { id: this.word } });
    },
  },
};
</script>

<style scoped>
.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}

.b-example-divider {
  height: 3rem;
  background-color: rgba(0, 0, 0, 0.1);
  border: solid rgba(0, 0, 0, 0.15);
  border-width: 1px 0;
  box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1), inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
}

.b-example-vr {
  flex-shrink: 0;
  width: 1.5rem;
  height: 100vh;
}

.bi {
  vertical-align: -0.125em;
  fill: currentColor;
}

.nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
}

.nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  margin-top: -1px;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
}
</style>
