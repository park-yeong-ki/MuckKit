<template>
  <div
        class="container-fluid d-flex flex-column border flex-shrink-0 p-3 bg-light"
        id="back"
        style="width: 100%; height: 100vh;"
      >
        <h2>{{writer}}님의 플랜</h2>
        <hr />
        <div class="list-group list-group-flush border-bottom scrollarea">
            <b-row class="my-1" >
      <b-col sm="5">
        제목:
      </b-col>
      <b-col sm="7">
        {{planTitle}}
      </b-col>
      <b-col sm="5">
        설명:
      </b-col>
      <b-col sm="7">
        {{planDesc}}
      </b-col>
      <b-col sm="5">
        시작일:
      </b-col>
      <b-col sm="7">
        {{planStartDate}}
      </b-col>
      <b-col sm="5">
        종료일:
      </b-col>
      <b-col sm="7">
        {{planEndDate}}
      </b-col>
    </b-row>
    <b-modal
            id="modal-prevent-closing"
            ref="modal"
            title="플랜 수정하기"
            @show="resetModal"
            @hidden="resetModal"
            @ok="handleOk"
            ok-title="확인"
            ok-variant="primary"
            cancel-title="취소"
            cancel-variant="secondary"
          >
            <form ref="form" @submit.stop.prevent="handleSubmit">
              <b-form-group
                label="제목"
                label-for="title-input"
              >
                <b-form-input
                  id="title-input"
                  v-model="plan.title"
                  required
                ></b-form-input>
              </b-form-group>
              <b-form-group
                label="설명"
                label-for="description-input"
              >
                <b-form-input
                  id="description-input"
                  v-model="plan.description"
                  required
                ></b-form-input>
              </b-form-group>
              <b-form-group
                label="여행 출발 일자"
                label-for="startDate-input"
              >
                <b-form-input
                  id="startDate-input"
                  v-model="plan.startDate"
                  required
                  type="date"
                ></b-form-input>
              </b-form-group>
               <b-form-group
                label="여행 도착 일자"
                label-for="endDate-input"
              >
                <b-form-input
                  id="endDate-input"
                  v-model="plan.endDate"
                  required
                  type="date"
                ></b-form-input>
              </b-form-group>
              <b-form-group
                label="해시태그"
                label-for="hashtag-input"
              >
                <b-form-input
                  id="hashtag-input"
                  v-model="tag"
                  required
                  @keypress.enter="addHashtag"
                >
                </b-form-input>
              </b-form-group>
                <button class="btn btn-light rounded-pill px-2" type="button" v-for="(tag, idx) in plan.hashtag" :key="idx" @click="removeHashtag(idx)">#{{tag.tagName}}</button>
            </form>
          </b-modal>
        <b-button class="btn btn-outline-light btn-lg"
                style="background-color: orange; border-color: orange"
                size="sm"
                
                v-b-modal.modal-prevent-closing
                v-if="writer === memberId"
                >수정</b-button>
        <b-button class="btn btn-outline-light btn-lg mt-1"
                style="background-color: orange; border-color: orange"
                size="sm"
                @click="moveList"
                >목록</b-button>
        </div>
        <hr />
        <b-row class="mt-3 " v-for="(att, idx) in attractionList" :key="idx">
          <b-col>{{idx+1}}</b-col>
            <b-col class="text-center">{{att.title}}</b-col>
            <b-col><b-button
                id="btnArea"
                class="btn btn-outline-light btn-lg"
                style="background-color: orange; border-color: orange"
                size="sm"
                @click="viewDetail(att)"
                >더보기</b-button
              ></b-col>
        </b-row>
      </div>
</template>

<script>
import router from "@/router";
export default {
    components: {},
    data() {
        return {
            tag: "",
      plan: {
        title: "",
        startDate: "",
        endDate: "",
        description: "",
        hashtag: []
      },
        };
    },
    props: {
        writer: String
    },
     computed: {
        memberId(){
            return this.$store.getters.getMemberId;
        },
        planTitle(){
            return this.$store.getters.getPlanTitle;
        },
        planDesc(){
            return this.$store.getters.getPlanDesc;
        },
        planStartDate(){
            return this.$store.getters.getPlanStartDate;
        },
        planEndDate(){
            return this.$store.getters.getPlanEndDate;
        },
        planHashtag(){
            return this.$store.getters.getPlanHashtag;
        },
        attractionList(){
            return this.$store.getters.getTempAttractionList;
        }
    },
    created() {
    },
    methods: {
        removeHashtag(idx){
      this.plan.hashtag.splice(idx, 1);
    },
    addHashtag(){
      this.plan.hashtag.push({ tagName: this.tag})
      this.tag = "";
    },
        viewDetail(att){
            this.$emit("readAttraction", att);
        },
        moveList(){
            router.push({name: 'planlist'});
        },
        resetModal() {
      this.plan.title = this.planTitle;
      this.plan.description = this.planDesc;
      this.plan.startDate = this.planStartDate;
      this.plan.endDate = this.planEndDate;
      this.tag = "";
      this.plan.hashtag = this.planHashtag;
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
      if (!this.plan.title || !this.plan.description || !this.plan.startDate
      || !this.plan.endDate || this.plan.hashtag.length === 0 ) {
        alert("양식을 모두 입력해주세요.");
        return;
      }
      this.$store.dispatch("planData", this.plan);

      this.$router.push({
        name: "planmodify",
      });
    },
    },
};
</script>

<style scoped></style>