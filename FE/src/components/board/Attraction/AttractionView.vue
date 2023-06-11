<template>
  <div class="row gx-4 gx-lg-5 text-center border-bottom p-4">
    <div class="row">
      <div class="col-md-6">
        <img class="card-img-top mb-5 mb-md-0" :src="att.first_image" alt="@/assets/food.png" />
      </div>
      <div class="col-md-6">
        <div class="row">
          <div class="col-6">
            <h3 class="display-5 fw-bolder text-left">{{ att.title }}</h3>
          </div>
          <div class="col-6">
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
                <img v-if="see" src="@/assets/blankheart.png" style="width: 28px; height: 28px" />
                <img v-else src="@/assets/filledheart.png" style="width: 28px; height: 28px" />
              </button>
            </div>
          </div>
        </div>
        <div class="text-left mt-2" style="color: grey; font-size: 17px">[{{ att.addr1 }}]</div>
        <div class="text-left mt-2" style="color: black; font-size: 20px">
          {{ att.overview }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
export default {
  components: {},
  data() {
    return {
      attractionList: {},
      see: true,
    };
  },
  props: { att: Object },
  created() {
    http.get(`/attraction?keyword=${this.$route.params.id}`).then((resp) => {
      this.attractionList = resp.data;
      console.log(this.attractionList);
    });
  },
  methods: {
    heart() {
      this.see = !this.see;
    },
  },
};
</script>

<style scoped></style>
