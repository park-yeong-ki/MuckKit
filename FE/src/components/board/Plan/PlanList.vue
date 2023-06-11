<template>
  <div class="container">
    <h2 onclick="location.href='/plan/list';">플랜</h2>
    <section class="py-5">
      <div class="text-right">
        <div>
          <b-button
            v-b-modal.modal-prevent-closing
            style="background-color: orange; border-color: orange"
            v-if="memberId"
            >플랜 만들기</b-button
          >

          <b-modal
            id="modal-prevent-closing"
            ref="modal"
            title="플랜 만들기"
            @show="resetModal"
            @hidden="resetModal"
            @ok="handleOk"
            ok-title="확인"
            ok-variant="primary"
            cancel-title="취소"
            cancel-variant="secondary"
          >
            <form ref="form" @submit.stop.prevent="handleSubmit">
              <b-form-group label="제목" label-for="title-input">
                <b-form-input id="title-input" v-model="plan.title" required></b-form-input>
              </b-form-group>
              <b-form-group label="설명" label-for="description-input">
                <b-form-input
                  id="description-input"
                  v-model="plan.description"
                  required
                ></b-form-input>
              </b-form-group>
              <b-form-group label="여행 출발 일자" label-for="startDate-input">
                <b-form-input
                  id="startDate-input"
                  v-model="plan.startDate"
                  required
                  type="date"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="여행 도착 일자" label-for="endDate-input">
                <b-form-input
                  id="endDate-input"
                  v-model="plan.endDate"
                  required
                  type="date"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="해시태그" label-for="hashtag-input">
                <b-form-input
                  id="hashtag-input"
                  v-model="tag"
                  required
                  @keypress.enter="addHashtag"
                >
                </b-form-input>
              </b-form-group>
              <button
                class="btn btn-light rounded-pill px-2"
                type="button"
                v-for="(tag, idx) in plan.hashtag"
                :key="idx"
                @click="removeHashtag(idx)"
              >
                #{{ tag.tagName }}
              </button>
            </form>
          </b-modal>

          <div class="d-flex gap-2 justify-content-center py-5">
            <!-- 해시태그 반복문 돌릴것 10개 -->
            <button
              class="btn btn-light rounded-pill px-3 ml-3"
              type="button"
              v-for="(hashtag, idx) in hashtagList"
              :key="idx"
              @click="searchByHashtag(hashtag.hashtagId)"
            >
              #{{ hashtag.tagName }}
            </button>
          </div>
        </div>
      </div>
      <div class="container px-12 px-lg-10 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
          <plan-list-item-vue
            v-for="(plan, idx) in planList"
            :key="idx"
            :pl="plan"
          ></plan-list-item-vue>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import http from "@/api/http";
import PlanListItemVue from "./plan-item/PlanListItem.vue";

export default {
  components: {
    PlanListItemVue,
  },
  computed: {
    memberId() {
      return this.$store.getters.getMemberId;
    },
  },
  data() {
    return {
      hashtagList: [],
      planList: [],
      tag: "",
      plan: {
        title: "",
        startDate: "",
        endDate: "",
        description: "",
        hashtag: [],
      },
    };
  },
  created() {
    http
      .get("/plan")
      .then((resp) => {
        this.hashtagList = resp.data.hashtags;
        this.planList = resp.data.plans;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    removeHashtag(idx) {
      this.plan.hashtag.splice(idx, 1);
    },
    addHashtag() {
      this.plan.hashtag.push({ tagName: this.tag });
      this.tag = "";
    },
    searchByHashtag(hashtagId) {
      http
        .get("/plan/hashtag/" + hashtagId)
        .then((resp) => {
          this.hashtagList = resp.data.hashtags;
          this.planList = resp.data.plans;
        })
        .then((error) => {
          console.log(error);
        });
    },
    resetModal() {
      this.plan.title = "";
      this.plan.description = "";
      this.plan.startDate = "";
      this.plan.endDate = "";
      this.tag = "";
      this.plan.hashtag = [];
    },
    handleOk(bvModalEvent) {
      // Prevent modal from closing
      bvModalEvent.preventDefault();
      // Trigger submit handler
      // this.handleSubmit();
      // this.$router.push("view");
      this.handleSubmit();
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (
        !this.plan.title ||
        !this.plan.description ||
        !this.plan.startDate ||
        !this.plan.endDate ||
        this.plan.hashtag.length === 0
      ) {
        alert("양식을 모두 입력해주세요.");
        return;
      }
      this.$store.dispatch("planData", this.plan);

      this.$router.push({
        name: "planwrite",
      });
    },
  },
};
</script>

<style scoped></style>
