<template>
  <div>
    <div
      class="d-flex flex-column border flex-shrink-0 p-3 bg-light"
      style="width: 100%; height: 100vh"
    >
      <div class="my-3">
        <b-input-group>
          <b-form-input
            id="inputArea"
            class="btn btn-outline-light"
            style="border-color: orange"
            placeholder="검색어를 입력하세요"
            v-model="word"
          ></b-form-input>
          <b-input-group-append>
            <b-button
              id="btnArea"
              class="btn btn-outline-light btn-lg"
              style="background-color: orange; border-color: orange"
              size="sm"
              @click="search"
              >검색</b-button
            >
          </b-input-group-append>
        </b-input-group>
      </div>

      <div
        class="list-group list-group-flush border-bottom scrollarea"
        v-if="this.attractionList.length > 0"
      >
        <b-modal
          class="text-center"
          id="modal-1"
          :title="att.title"
          size="lg"
          @ok="handleOk"
          ok-title="추가"
          ok-variant="primary"
          cancel-title="닫기"
          cancel-variant="secondary"
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
        <plan-search-item-vue
          v-for="(attraction, idx) in attractionList"
          :key="idx"
          :att="attraction"
          @readAttraction="readAttraction"
        ></plan-search-item-vue>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import PlanSearchItemVue from "./PlanSearchItem.vue";

export default {
  components: {
    PlanSearchItemVue,
  },
  data() {
    return {
      word: "",
      attractionList: [],
      att: {},
      imagesAddr: [],
    };
  },
  created() {},
  methods: {
    search() {
      if (this.word == "") {
        alert("검색어를 입력하세요.");
        return;
      }
      http.get("/attraction?keyword=" + this.word).then((resp) => {
        this.attractionList = resp.data;
      });
    },
    readAttraction(att) {
      this.imagesAddr = [];
      this.att = att;
      this.imagesAddr.push(att.first_image);
      this.imagesAddr.push(att.first_image2);
      this.$bvModal.show("modal-1");
    },
    handleOk() {
      return this.$store.dispatch("addAttraction", this.att);
    },
  },
};
</script>

<style scoped></style>
