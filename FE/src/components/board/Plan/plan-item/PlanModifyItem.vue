<template>
  <div
    class="container-fluid d-flex flex-column border flex-shrink-0 p-3 bg-light"
    id="back"
    style="width: 100%; height: 100vh"
  >
    <h2>나의 플랜</h2>
    <hr />
    <div class="list-group list-group-flush border-bottom scrollarea">
      <b-row class="my-1">
        <b-col sm="5"> 제목: </b-col>
        <b-col sm="7">
          {{ planTitle }}
        </b-col>
        <b-col sm="5"> 설명: </b-col>
        <b-col sm="7">
          {{ planDesc }}
        </b-col>
        <b-col sm="5"> 시작일: </b-col>
        <b-col sm="7">
          {{ planStartDate }}
        </b-col>
        <b-col sm="5"> 종료일: </b-col>
        <b-col sm="7">
          {{ planEndDate }}
        </b-col>
      </b-row>
      <b-button
        class="btn btn-outline-light btn-lg"
        style="background-color: orange; border-color: orange"
        size="sm"
        @click="modify"
        >수정</b-button
      >
      <b-button
        class="btn btn-outline-light btn-lg mt-1"
        style="background-color: orange; border-color: orange"
        size="sm"
        @click="removePlan"
        >삭제</b-button
      >
    </div>
    <hr />
    <b-row class="mt-3" v-for="(att, idx) in attractionList" :key="idx">
      <b-col>{{ idx + 1 }}</b-col>
      <b-col class="text-center">{{ att.title }}</b-col>
      <b-col
        ><b-button
          id="btnArea"
          class="btn btn-outline-light btn-lg"
          style="background-color: orange; border-color: orange"
          size="sm"
          @click="remove(idx)"
          >삭제</b-button
        ></b-col
      >
    </b-row>
  </div>
</template>

<script>
import http from "@/api/http";
import router from "@/router";

export default {
  components: {},
  data() {
    return {
      message: "",
    };
  },
  computed: {
    memberId() {
      return this.$store.getters.getMemberId;
    },
    planTitle() {
      return this.$store.getters.getPlanTitle;
    },
    planDesc() {
      return this.$store.getters.getPlanDesc;
    },
    planStartDate() {
      return this.$store.getters.getPlanStartDate;
    },
    planEndDate() {
      return this.$store.getters.getPlanEndDate;
    },
    planHashtag() {
      return this.$store.getters.getPlanHashtag;
    },
    attractionList() {
      return this.$store.getters.getTempAttractionList;
    },
    // addAttId() {
    //   return this.$store.getters.getAddAttId;
    // },
  },
  mounted() {
    // console.log(this.addAttId);
    // const attr = [];
    // for (let i = 0; i < this.addAttId.length; i++) {
    //   console.log(this.addAttId[i]);
    //   http
    //     .get(`/attraction/${this.addAttId[i]}`, {
    //       headers: {
    //         Authorization: "Bearer " + sessionStorage.getItem("access-token"),
    //       },
    //     })
    //     .then((resp) => {
    //       attr.push(resp.data);
    //       console.log(attr);
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //       alert("플랜 삭제를 실패하였습니다.");
    //     });
    // }
    // this.$store.dispatch("addAttraction", attr);
    // // this.attractionList.push(attr[i]);
    // console.log(this.attractionList);
  },
  methods: {
    remove(num) {
      return this.$store.dispatch("removeAttraction", num);
    },
    removePlan() {
      http
        .delete("/plan/" + this.$route.params.id, {
          headers: {
            Authorization: "Bearer " + sessionStorage.getItem("access-token"),
          },
        })
        .then((resp) => {
          console.log(resp);
          router.push({ name: "planlist" });
        })
        .catch((error) => {
          console.log(error);
          alert("플랜 삭제를 실패하였습니다.");
        });
    },
    modify() {
      http
        .put(
          "/plan/update",
          {
            planId: this.$route.params.id,
            memberId: this.memberId,
            planTitle: this.planTitle,
            startDate: this.planStartDate,
            endDate: this.planEndDate,
            planDescription: this.planDesc,
            attractions: this.attractionList,
            hashtags: this.planHashtag,
          },
          {
            headers: {
              Authorization: "Bearer " + sessionStorage.getItem("access-token"),
            },
          }
        )
        .then((resp) => {
          console.log(resp);
          router.push({ name: "planlist" });
        })
        .catch((error) => {
          console.log(error.data);
          alert("플랜 수정을 실패하였습니다.");
        });
    },
  },
};
</script>

<style scoped></style>
