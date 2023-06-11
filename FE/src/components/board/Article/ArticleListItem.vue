<template>
  <div>
    <div class="col mb-5">
      <div class="card h-80 p-2">
        <!-- Sale badge-->
        <div
          class="badge text-white position-absolute"
          v-on:click="heart"
          style="top: 0.5rem; right: 0.5rem"
        >
          <button
            class="btm_image"
            id="img_btn"
            style="border: 0; background-color: rgba(0, 0, 0, 0)"
          >
            <img v-if="see" src="@/assets/blankheart.png" style="width: 22px; height: 22px" />
            <img v-else src="@/assets/filledheart.png" style="width: 22px; height: 22px" />
          </button>
        </div>
        <!-- Product image-->
        <img class="card-img-top" :src="imagesAddr" />
        <!-- Product details-->
        <div class="card-body p-6">
          <div class="text-center">
            <!-- Product name-->
            <a class="text-decoration-none link-dark" v-on:click="moveView">
              <h5 class="card-title mb-3">{{ at.articleTitle }}</h5>
            </a>
          </div>
        </div>
        <!-- Product actions-->
        <div class="card-body row">
          <div class="col-sm-5 small">
            <div class="fw-bold text-left">좋아요 {{ at.articleHeart + this.cnt }}</div>
          </div>

          <div class="col-sm-7 small">
            <div class="fw-bold text-right">{{ at.createdTime.substr(0, 10) }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  components: {},
  props: {
    at: {},
  },
  data() {
    return {
      see: true,
      cnt: 0,
      images: {},
      imagesAddr: {},
    };
  },
  mounted() {
    http.get(`/article/view/${this.at.articleId}`).then((resp) => {
      this.images = resp.data.plan.attractions;
      this.imagesAddr = this.images[0].first_image;
      // console.log(this.imagesAddr);
    });
  },
  methods: {
    moveView() {
      this.$router.push({ name: "articleview", params: { id: this.at.articleId } });
    },
    heart() {
      http
        .get(`/article/heart/${this.at.articleId}`, {
          headers: {
            Authorization: "Bearer " + sessionStorage.getItem("access-token"),
          },
        })
        .then((resp) => {
          this.see = !this.see;
          if (this.see) {
            this.cnt = 0;
          } else {
            this.cnt = 1;
          }
          console.log(resp);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  computed: {},
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}

a:hover {
  color: black;
}
</style>
