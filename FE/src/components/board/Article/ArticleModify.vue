<template>
  <div class="container">
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form-group id="userid-group" label="작성자" label-for="userid">
          <b-form-input
            id="userid"
            type="text"
            required
            placeholder="작성자를 입력하세요."
            :value="getMemberId"
            :readonly="true"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="제목" label-for="subject">
          <b-form-input
            id="subject"
            type="text"
            required
            placeholder="제목을 입력하세요."
            v-model="articleTitle"
            :value="articleContent"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용" label-for="content">
          <b-form-textarea
            id="content"
            placeholder="내용을 입력하세요."
            rows="10"
            max-rows="15"
            v-model="articleContent"
          ></b-form-textarea>
        </b-form-group>
        <div class="text-right">
          <router-link :to="{ name: 'articlelist' }"
            ><b-button
              id="btn"
              class="btn btn-lg"
              style="background-color: orange; border-color: orange"
              >취소</b-button
            ></router-link
          >

          <b-button
            id="btn"
            class="btn btn-lg"
            style="background-color: orange; border-color: orange"
            @click="write"
            >수정</b-button
          >
          <b-button
            id="btn"
            class="btn btn-lg"
            style="background-color: orange; border-color: orange"
            @click="remove"
            >삭제</b-button
          >
        </div>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import http from "@/api/http";
import router from "@/router";
// import axios from "axios";

export default {
  components: {},
  data() {
    return {
      articleContent: "",
      articleTitle: "",
      planId: "",
    };
  },
  created() {
    http.get(`/article/view/${this.$route.params.id}`).then((resp) => {
      this.planId = resp.data.planId;
      this.articleTitle = resp.data.articleTitle;
      this.articleContent = resp.data.articleContent;
    });
  },
  methods: {
    write() {
      if (this.articleTitle && this.articleContent) {
        http
          .put(
            "/article/modify",
            {
              articleId: this.$route.params.id,
              articleTitle: this.articleTitle,
              articleContent: this.articleContent,
              articleWriter: this.getMemberId,
              planId: this.planId,
            },
            {
              headers: {
                Authorization: "Bearer " + sessionStorage.getItem("access-token"),
              },
            }
          )
          .then((resp) => {
            console.log(resp);
            router.push({ name: "articlelist" });
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert("올바른 입력을 해주세요.");
      }
    },
    imageUpload() {},
    remove() {
      http
        .delete("/article/" + this.$route.params.id, {
          headers: {
            Authorization: "Bearer " + sessionStorage.getItem("access-token"),
          },
        })
        .then((resp) => {
          console.log(resp);
          alert("여행 후기가 삭제되었습니다.");
          router.push({ name: "articlelist" });
        })
        .catch((error) => {
          console.log(error);
          alert("여행 후기 삭제를 실패하였습니다.");
        });
    },
  },
  computed: {
    getMemberId() {
      return this.$store.getters.getMemberId;
    },
  },
};
</script>

<style scoped>
#btn {
  margin-right: 20px;
}
</style>
