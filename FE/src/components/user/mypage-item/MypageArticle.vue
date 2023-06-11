<template>
  <div>
    <div class="container px-12 px-lg-10 mt-5">
      <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
        <mypage-article-item-vue
          v-for="(attraction, idx) in articleList"
          :key="idx"
          :att="attraction"
        ></mypage-article-item-vue>
      </div>
    </div>
  </div>
</template>

<script>
import MypageArticleItemVue from "./MypageArticleItem.vue";
export default {
  components: {
    MypageArticleItemVue,
  },
  data() {
    return {
      articleList: this.$store.getters.getArticleList,
    };
  },
  methods: {},
  computed: {
    myArticle() {
      return this.$store.getters.getArticleList;
    },
  },
};
</script>

<style>
a {
  text-decoration: none;
  color: black;
}

a:hover {
  color: black;
}
</style>
