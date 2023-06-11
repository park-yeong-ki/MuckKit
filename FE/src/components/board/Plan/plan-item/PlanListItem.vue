<template>
  <div>
    <!-- 틀 시작 -->
    <div class="col mb-5">
      <div class="card h-80 p-2" id="back">
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
        <img
          class="card-img-top"
          :src="pl.attractions[0].first_image"
          alt="..."
          width="100%"
          height="150vh"
        />
        <!-- Product details-->
        <div class="card-body p-6">
          <div class="text-center">
            <!-- Product name-->
            <a class="text-decoration-none link-dark" @click="moveView"
              ><h5 class="card-title mb-3">
                {{ pl.planTitle }}
              </h5></a
            >
          </div>
        </div>
        <!-- Product actions-->
        <div class="card-body row">
          <div class="col-sm-6 small">
            <div class="fw-bold text-left">좋아요 {{ heartcnt + cnt }}</div>
          </div>

          <div class="col-sm-6 small">
            <div class="fw-bold text-right">{{ pl.startDate }}</div>
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
  data() {
    return {
      heartcnt: this.pl.planHeart,
      hearted: false,
      see: true,
      cnt: 0,
    };
  },
  props: {
    pl: {
      planId: "",
      memberId: "",
      planTitle: "",
      startDate: "",
      endDate: "",
      planDescription: "",
      planHeart: 0,
      attractions: [],
    },
  },
  methods: {
    heart() {
      http
        .get(`/plan/heart/${this.pl.planId}`, {
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
    moveView() {
      this.$router.push({ name: "planview", params: { id: this.pl.planId } });
    },
  },
  created() {},
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
