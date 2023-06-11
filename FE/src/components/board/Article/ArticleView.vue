<template>
  <div class="container">
    <main class="mb-4">
      <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
          <div class="container List-container">
            <div class="row">
              <div class="col-1 align-self-center ml-3">
                <b-avatar
                  class="align-self-center"
                  src="https://placekitten.com/300/300"
                  size="4rem"
                ></b-avatar>
              </div>
              <div class="col">
                <div class="row mt-1 header">
                  <div class="col text-left" style="word-break: break-all">
                    <h2>{{ article.articleTitle }}</h2>
                  </div>
                </div>
                <div class="row mt-1 header">
                  <div class="col text-left" style="word-break: break-all; font-size: 20px">
                    {{ article.articleWriter }}
                  </div>
                </div>
              </div>
            </div>
            <div class="row mt-3 header">
              <div
                class="col text-left ml-3"
                style="word-break: break-all; font-size: 20px; color: silver"
              >
                {{ article.createdTime.substr(0, 10) }} | 조회수 {{ article.hit }}
              </div>
              <div class="col text-right mr-3" style="word-break: break-all; font-size: 20px">
                좋아요 {{ article.articleHeart }}
              </div>
            </div>
            <hr />
            <div class="board-container">
              <div class="row my-4">
                <b-carousel
                  id="carousel-1"
                  :interval="4000"
                  controls
                  indicators
                  background="#ababab"
                  img-width="1024"
                  img-height="480"
                  style="text-shadow: 1px 1px 2px #333"
                  class="ml-2"
                >
                  <div v-for="(imgs, idx) in imagesAddr" :key="idx">
                    <b-carousel-slide :img-src="imgs" style="width: 1024px; height: 480px">
                      <!-- <img src="imgs" class="img-fluid" style="width: 1024px; height: 480px"/> -->
                    </b-carousel-slide>
                  </div>
                </b-carousel>
              </div>
              <div class="content row my-4 ml-3" style="word-break: break-all">
                {{ article.articleContent }}
              </div>
              <div class="row align-items-center">
                <div class="col-6 text-left" style="font-weight: bold; font-size: 17px">
                  방문지 리스트
                </div>
                <div class="col-6 text-right mb-2" style="font-weight: bold; font-size: 17px">
                  <b-button
                    id="btnArea"
                    class="btn btn-sm mt-2"
                    style="background-color: orange; border-color: orange"
                    v-b-modal.modal-prevent-closing
                    @click="loadPlanList"
                    >추가</b-button
                  >
                </div>
              </div>
              <b-modal
                id="modal-prevent-closing"
                ref="modal"
                title="플랜 만들기"
                @ok="handleOk"
                ok-title="확인"
                ok-variant="primary"
                cancel-title="취소"
                cancel-variant="secondary"
              >
                <form ref="form" @submit.stop.prevent="handleSubmit">
                  <div v-for="(attTitle, idx) in Ptitle" :key="idx">
                    <div class="row" @click="choice(Pid[idx], attTitle)">
                      <div class="col">{{ attTitle }}</div>
                    </div>
                  </div>
                  <hr />
                  <div>선택된 플랜 : {{ wantPlanTitle }}</div>
                </form>
              </b-modal>
              <div
                class="p-2 align-items-center"
                style="border: 1px solid silver; border-radius: 5px"
              >
                <div v-for="(att, idx) in attractions" :key="idx">
                  <div class="row align-items-center p-2">
                    <div class="col-1">
                      <!-- <input class="form-check-input" type="checkbox" :value="att.title" /> -->
                      <b-form-checkbox :value="att.contentId" v-model="wantAddList">
                      </b-form-checkbox>
                    </div>
                    <div class="col-3 mt-1 mb-1">
                      {{ att.title }}
                    </div>

                    <div class="col-8">{{ att.addr1 }}</div>
                    <!-- <div class="col-8">
                      <label class="form-check-label" for="flexCheckDefault">
                        {{ att.addr1 }}
                      </label>
                    </div> -->
                  </div>
                </div>
              </div>
            </div>
            <div class="mt-5">
              <h5 class="text-left" style="font-weight: bold; font-size: 17px">댓글</h5>
              <div class="mb-3 mt-3">
                <b-input-group>
                  <b-form-textarea
                    class="btn btn-outline-light text-left"
                    type="text"
                    style="border-color: orange"
                    placeholder="댓글을 남겨보세요"
                    v-model="comContent"
                    rows="s"
                  ></b-form-textarea>
                  <b-input-group-append>
                    <b-button
                      id="btnArea"
                      class="btn btn-outline-light btn-lg"
                      style="background-color: orange; border-color: orange"
                      size="sm"
                      v-on:click="addComm()"
                      >등록</b-button
                    >
                  </b-input-group-append>
                </b-input-group>
              </div>
              <div v-for="(comment, idx) in article.comments" :key="idx" :com="comment">
                <div class="border-bottom p-3">
                  <div class="row mt-1 header">
                    <b-avatar
                      class="align-self-center"
                      src="https://placekitten.com/300/300"
                      size="3rem"
                    ></b-avatar>
                    <div class="col text-left" style="word-break: break-all">
                      <h4>{{ comment.commentWriter }}</h4>
                      {{ comment.createdTime }}
                    </div>
                    <div
                      class="col text-right"
                      style="word-break: break-all"
                      v-on:click="remove(comment.commentId)"
                      :v-if="memberId === comment.commentWriter"
                    >
                      삭제
                    </div>
                  </div>
                  <div class="row mt-3 header">
                    <div class="col text-left" style="word-break: break-all">
                      {{ comment.content }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="board-footer">
              <router-link :to="{ name: 'articlelist' }"
                ><b-button
                  id="btnArea"
                  class="btn btn-lg mt-3"
                  style="background-color: orange; border-color: orange"
                  >목록</b-button
                ></router-link
              >
              <!-- <router-link :to="{ name: 'articlemodify', params }"
                ><b-button
                  id="btnArea"
                  class="btn btn-lg mt-3 ml-3"
                  @click="movePage"
                  style="background-color: orange; border-color: orange"
                  :v-if="getMemberId === article.articleWriter"
                  >수정하기</b-button
                ></router-link
              > -->
              <b-button
                id="btnArea"
                class="btn btn-lg mt-3 ml-3"
                @click="movePage"
                style="background-color: orange; border-color: orange"
                :v-if="memberId === article.articleWriter"
                >수정</b-button
              >
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import http from "@/api/http";
// import ArticleComment from "./ArticleComment.vue";

export default {
  components: {
    // ArticleComment,
  },
  data() {
    return {
      uploadimageurl: [],
      imagecnt: 0,
      article: {},
      attractions: {},
      comContent: "",
      comArticleId: "",
      images: [],
      imagesAddr: [],
      writer: this.$store.getters.getMemberId,
      wantAddList: [],
      Ptitle: [],
      Pid: [],
      wantPlanId: "",
      wantPlanTitle: "",
    };
  },
  mounted() {
    http.get(`/article/view/${this.$route.params.id}`).then((resp) => {
      this.article = resp.data;
      this.attractions = resp.data.plan.attractions;
      this.images = resp.data.plan.attractions;

      for (let i = 0; i < this.images.length; i++) {
        if (this.images[i].first_image) this.imagesAddr.push(this.images[i].first_image);
      }
    });
  },
  methods: {
    handleOk() {
      console.log("try put", {
        planId: this.$route.params.id,
        memberId: this.memberId,
        planTitle: this.planTitle,
        startDate: this.planStartDate,
        endDate: this.planEndDate,
        planDescription: this.planDesc,
        attractions: this.attractionList,
        hashtags: this.planHashtag,
      });
      http
        .put(
          "/plan/update",
          {
            planId: this.wantPlanId, // yangyu,
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
          this.$router.push({ name: "planview", params: { id: this.wantPlanId } });
        })
        .catch((error) => {
          console.log(error.data);
          alert("플랜 수정을 실패하였습니다.");
        });
    },
    choice(idData, attTitle) {
      console.log(idData)
      console.log(attTitle)
      this.wantPlanId = idData;
      this.wantPlanTitle = attTitle;

      this.$store.dispatch("removeAllAttraction");

      http
        .get("/plan/" + idData)
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
          for (let i = 0; i < this.wantAddList.length; i++) {
            this.$store.dispatch("addAttraction", { contentId: this.wantAddList[i] });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 사용자 id로 마이페이지 데이터 가져오기
    loadPlanList() {
      http
        .get("/member/" + this.memberId, {
          headers: {
            Authorization: "Bearer " + sessionStorage.getItem("access-token"),
          },
        })
        .then((resp) => {
          // 마이페이지 데이터에서 나의 플랜들 모으기

          //추가할 attID 모아서 set하기
          this.$store.dispatch("addPlanData", this.wantAddList);
          console.log(this.wantAddList);

          // 플랜아이디 뽑아서 로컬스토리지에 저장
          //플랜 이름만 뽑아서 사용자에게 보여주기

          for (let i = 0; i < resp.data.planList.length; i++) {
            this.Ptitle.push(resp.data.planList[i].planTitle);
            this.Pid.push(resp.data.planList[i].planId);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    movePage() {
      // this.$router.push({ path: `/notice/view/${this.nt.noticeId}` });
      this.$router.push({ name: "articlemodify", params: { id: this.$route.params.id } });
    },
    remove(iddata) {
      http
        .delete(`/comment/${iddata}`, {
          headers: {
            Authorization: "Bearer " + sessionStorage.getItem("access-token"),
          },
        })
        .then((resp) => {
          console.log(resp);
          http.get(`/article/view/${this.$route.params.id}`).then((resp) => {
            this.article = resp.data;
          });
        })
        .catch((error) => {
          console.log(error);
          console.log(iddata);
        });
    },
    addComm() {
      http
        .post(
          `/comment/write`,
          {
            content: this.comContent,
            commentWriter: this.$store.getters.getMemberId,
            articleId: this.$route.params.id,
          },
          {
            headers: {
              Authorization: "Bearer " + sessionStorage.getItem("access-token"),
            },
          }
        )
        .then((resp) => {
          console.log(resp);
          http.get(`/article/view/${this.$route.params.id}`).then((resp) => {
            this.article = resp.data;
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  computed: {
    memberName() {
      return this.$store.getters.getMemberName;
    },
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
  },
};
</script>

<style scoped></style>
