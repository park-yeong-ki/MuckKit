<template>
  <div>
    <div class="container px-12 px-lg-10 mt-5">
      <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
        <div v-for="(plan, idx) in planList" :key="idx">
          <div class="col mb-5">
            <div class="card h-80 p-2 my-3 shadow" id="back">
              <img class="card-img-top" :src="plan.attractions[0].first_image" alt="..." />
              <!-- Product details-->
              <div class="card-body p-2">
                <div class="text-center">
                  <!-- Product name-->
                  <router-link :to="`/plan/view/` + plan.planId"
                    ><a class="text-decoration-none link-dark"
                      ><h6 class="card-title mb-3">
                        {{ plan.planTitle }}
                      </h6></a
                    ></router-link
                  >
                </div>
              </div>
              <!-- Product actions-->
              <div class="card-body row">
                <div class="col-sm-5 small">
                  <div class="fw-bold text-left" style="font-size: 11px">
                    좋아요 {{ plan.planHeart }}
                  </div>
                </div>

                <div class="col-sm-7 small">
                  <div class="fw-bold text-right" style="font-size: 12px">{{ plan.startDate }}</div>
                </div>
              </div>
              <div class="row p-1">
                <div class="col-12">
                  <router-link :to="{ name: 'articlewrite', params: { id: plan.planId } }"
                    ><b-button
                      id="btnArea"
                      class="btn btn-sm"
                      style="background-color: orange; border-color: orange"
                      >리뷰작성</b-button
                    ></router-link
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import http from "@/api/http";
export default {
  components: {},
  data() {
    return {
      planList: this.$store.getters.getHeartPlanList,
      attractionList: [],
      attIdList: [],
      attList: [],
      checkId: [],
      checkMemo: [],
    };
  },
  computed: {
    myHeartPlan() {
      return this.$store.getters.getHeartPlanList;
    },
    memberId() {
      return this.$store.getters.getMemberId;
    },
  },
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
