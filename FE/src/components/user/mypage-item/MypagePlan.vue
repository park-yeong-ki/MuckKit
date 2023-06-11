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
                <!-- <div class="col-6">
                  <b-button
                    v-b-modal.modal-prevent-closing
                    class="btn btn-sm"
                    style="background-color: orange; border-color: orange"
                    @click="test(plan.planId)"
                    >여행완료</b-button
                  >
                </div> -->
              </div>
            </div>
          </div>
        </div>
        <!-- <b-modal
          id="modal-prevent-closing"
          ref="modal"
          title="내가 갔던 음식점 리스트"
          @ok="check"
          ok-title="확인"
          ok-variant="secondary"
          cancel-title="취소"
          cancel-variant="secondary"
        >
          <form ref="form" @submit.stop.prevent="handleSubmit">
            <div v-for="(att, idx) in attList" :key="idx">
              <div class="row border-bottom p-2">
                <div class="col-1">
                  <b-form-checkbox v-model="checkId" :value="attIdList[idx]"> </b-form-checkbox>
                </div>
                <div class="col-5 text-left">{{ att }}</div>
                <div class="col-6">
                  <b-form-input
                    type="text"
                    v-model="checkMemo[idx - 1]"
                    placeholder="메모를 남겨주세요"
                  ></b-form-input>
                </div>
              </div>
              <div class="row"></div>
            </div>
          </form>
        </b-modal> -->
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
      planList: this.$store.getters.getPlanList,
      attractionList: [],
      attIdList: [],
      attList: [],
      checkId: [],
      checkMemo: [],
    };
  },
  created() {},
  methods: {
    // check() {
    //   console.log(this.checkId);
    //   console.log(this.checkMemo);
    // },
    // test(idData) {
    //   console.log(idData);
    //   http.get(`/plan/${idData}`).then((resp) => {
    //     this.attractionList = resp.data.attractions;
    //     // console.log(this.attractionList[0].contentId);
    //     // attIdList에 관광지 contentId 넣기
    //     for (let i = 0; i < this.attractionList.length; i++) {
    //       this.attIdList.push(this.attractionList[i].contentId);
    //     }
    //     //attList에 관광지 이름 넣기
    //     for (let i = 0; i < this.attIdList.length; i++) {
    //       http.get(`/attraction/${this.attIdList[i]}`).then((resp) => {
    //         this.attList.push(resp.data.title);
    //       });
    //     }
    //   });
    // },
    // handleOk(bvModalEvent) {
    //   bvModalEvent.preventDefault();
    //   this.handleSubmit();
    // },
    // handleSubmit() {
    //   this.$store.dispatch("bestnData", this.checkId);
    //   this.$store.dispatch("bestnData", this.checkMemo);
    //   this.$router.push({
    //     name: "planwrite",
    //   });
    // },
  },
  computed: {
    myPlan() {
      return this.$store.getters.getPlanList;
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
