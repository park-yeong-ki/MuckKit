<template>
  <div>
    <div class="container px-12 px-lg-10 mt-5">
      <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
        <mypage-heart-article-item-vue
          v-for="(attraction, idx) in articleList"
          :key="idx"
          :att="attraction"
        ></mypage-heart-article-item-vue>
      </div>
    </div>
  </div>
</template>

<script>
import MypageHeartArticleItemVue from "./MypageHeartArticleItem.vue";

export default {
  components: {
    MypageHeartArticleItemVue,
  },
  data() {
    return {
      articleList: this.$store.getters.getHeartArticleList,
    };
  },
  created() {},
  methods: {},
  computed: {
    myHeartArticle() {
      return this.$store.getters.getHeartArticleList;
    },
  },
};
</script>

<style scoped></style>
