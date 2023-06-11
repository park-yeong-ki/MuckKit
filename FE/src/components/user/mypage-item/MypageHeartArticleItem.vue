<template>
  <div class="col mb-5">
    <div class="card h-80 p-2 my-3 shadow" id="back">
      <img class="card-img-top" :src="imagesAddr" alt="..." />
      <!-- Product details-->
      <div class="card-body">
        <div class="text-center">
          <!-- Product name-->
          <router-link :to="{ name: 'articleview', params: { id: att.articleId } }"
            ><a class="text-decoration-none link-dark stretched-link"
              ><h6 class="card-title mb-3">
                {{ att.articleTitle }}
              </h6></a
            ></router-link
          >
        </div>
      </div>
      <!-- Product actions-->
      <div class="card-body row">
        <div class="col-sm-5" style="font-size: 11px">
          <div class="fw-bold text-left">좋아요 {{ att.articleHeart }}</div>
        </div>

        <div class="col-sm-7" style="font-size: 12px">
          <div class="fw-bold text-right">{{ att.createdTime.substr(0, 10) }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
export default {
  props: {
    att: Object,
  },
  data() {
    return {
      images: [],
      imagesAddr: {},
    };
  },
  mounted() {
    http.get(`/plan/${this.att.planId}`).then((resp) => {
      this.images = resp.data.attractions;
      this.imagesAddr = this.images[0].first_image;
    });
  },
};
</script>

<style scoped></style>
