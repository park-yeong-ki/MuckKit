<template>
  <div>
    <b-row>
      <b-col cols="4"
        ><plan-view-item-vue :writer="writer" @readAttraction="readAttraction"
      /></b-col>
      <b-col cols="8"><plan-map-vue /></b-col>
      <b-modal
        class="text-center"
        id="modal-1"
        :title="att.title"
        size="lg"
        ok-title="닫기"
        ok-variant="secondary"
        ok-only
      >
        <div class="">
          <b-carousel
            id="carousel-1"
            :interval="4000"
            controls
            indicators
            background="#ababab"
            img-width="1024px"
            img-height="480px"
            style="text-shadow: 1px 1px 2px #333"
            class="mb-3"
          >
            <div v-for="(imgs, idx) in imagesAddr" :key="idx">
              <b-carousel-slide :img-src="imgs" style="width: 1024px; height: 480px">
                <!-- <img src="imgs" class="img-fluid" style="width: 1024px; height: 480px"/> -->
              </b-carousel-slide>
            </div>
          </b-carousel>
        </div>
        <div class="mx-3" style="word-break: break-all">
          <b-row> 주소: {{ att.addr1 }} {{ att.addr2 }} </b-row>
          <b-row class="mt-3"> 설명: {{ att.overview }} </b-row>
        </div>
      </b-modal>
    </b-row>
  </div>
</template>

<script>
import http from "@/api/http";
import PlanMapVue from "./plan-item/PlanMap.vue";
import PlanViewItemVue from "./plan-item/PlanViewItem.vue";

export default {
  components: {
    PlanMapVue,
    PlanViewItemVue,
  },
  data() {
    return {
      imagesAddr: [],
      att: {},
      writer: "",
    };
  },
  created() {
    this.$store.dispatch("removeAllAttraction");

    http
      .get("/plan/" + this.$route.params.id)
      .then((resp) => {
        console.log(resp);
        let plan = {
          title: resp.data.planTitle,
          description: resp.data.planDescription,
          startDate: resp.data.startDate,
          endDate: resp.data.endDate,
          hashtag: resp.data.hashtags,
        };
        this.$store.dispatch("planData", plan);
        this.$store.dispatch("setAttraction", resp.data.attractions);
        this.writer = resp.data.memberId;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    readAttraction(att) {
      this.imagesAddr = [];
      this.att = att;
      this.imagesAddr.push(att.first_image);
      this.imagesAddr.push(att.first_image2);
      this.$bvModal.show("modal-1");
    },
  },
};
</script>

<style scoped></style>
