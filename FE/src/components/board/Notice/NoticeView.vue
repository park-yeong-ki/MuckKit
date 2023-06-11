<template>
  <div class="container">
    <b-row class="mb-10">
      <b-col>
        <div class="font-weight-bold mb-4"><h2>공지사항</h2></div>
        <div class="text-center">
          <div class="board_wrap">
            <div class="board_view_wrap">
              <div class="board_view">
                <div class="row mt-3 text-left ml-2">
                  <div class="col-6 align-self-center font-weight-bold" style="font-size: 22px">
                    {{ notice.noticeTitle }}
                  </div>
                </div>
                <div class="row ml-2 mt-2 mr-2">
                  <div
                    class="col-6 align-self-center text-left font-weight-bold"
                    style="font-size: 17px; color: silver"
                  >
                    관리자
                  </div>
                  <div class="col-3"></div>
                  <div class="col-3 text-right align-self-center" style="font-size: 17px">
                    {{ notice.createdTime.substr(0, 10) }}
                  </div>
                </div>
                <!-- <hr style="border: solid 0.01px silver" /> -->
                <hr />
                <div class="cont">
                  {{ notice.noticeContent }}
                </div>
              </div>
            </div>
          </div>
          <b-row class="float-right mt-3 mr-4">
            <router-link :to="{ name: 'noticemodify', params: this.$route.params.id }"
              ><b-button
                id="btn"
                class="btn btn-lg mx-1"
                style="background-color: orange; border-color: orange"
                v-if="getMemberId === 'admin'"
                >수정</b-button
              ></router-link
            >
            <b-button
              id="btn"
              class="btn btn-lg mx-1"
              style="background-color: orange; border-color: orange"
              v-if="getMemberId === 'admin'"
              @click="removeNotice"
              >삭제</b-button
            >
            <router-link :to="{ name: 'notice' }"
              ><b-button
                id="btn"
                class="btn btn-lg mx-1"
                style="background-color: orange; border-color: orange"
                >목록</b-button
              ></router-link
            >
          </b-row>
        </div>
      </b-col>
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
      notice: {
        noticeTitle: "",
        noticeContent: "",
        createdTime: "",
        hit: "",
      },
    };
  },
  created() {
    http.get(`/notice/view/${this.$route.params.id}`).then((resp) => {
      this.notice = resp.data;
    });
  },
  methods: {
    removeNotice() {
      http
        .delete(`/notice/${this.$route.params.id}`, {
          headers: {
            Authorization: "Bearer " + sessionStorage.getItem("access-token"),
          },
        })
        .then((resp) => {
          console.log(resp);
          alert("공지사항을 삭제하였습니다.");
          router.push({ name: "noticelist" });
        })
        .catch((error) => {
          console.log(error);
          alert("공지사항 삭제를 실패하였습니다.");
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
.board_wrap {
  width: 100%;
  min-width: 320px;
  padding: 0 30px;
  box-sizing: border-box;
}

.board_page a {
  width: 26px;
  height: 26px;
}

.board_page a.bt {
  padding-top: 7px;
}

.board_page a.num {
  padding-top: 6px;
}

.board_view .info dl {
  width: 50%;
  padding: 0;
}

.board_view .info dl:nth-child(-n + 2) {
  margin-bottom: 5px;
}

.board_view .info dl::before {
  display: none;
}

.board_view .info dl dt,
.board_view .info dl dd {
  font-size: 10px;
}
.board_view {
  width: 100%;
  border-top: 2px solid #000;
  border-bottom: 2px solid #000;
}

.board_view .title {
  padding: 20px 15px;
  border-bottom: 1px dashed #ddd;
  font-size: 25px;
}

.board_view .info {
  padding: 15px;
  border-bottom: 1px solid #999;
  font-size: 0;
}

.board_view .info dl {
  position: relative;
  display: inline-block;
  padding: 0 20px;
}

.board_view .info dl:first-child {
  padding-left: 0;
}

.board_view .info dl::before {
  content: "";
  position: absolute;
  top: 1px;
  left: 0;
  display: block;
  width: 1px;
  height: 13px;
  background: #ddd;
}

.board_view .info dl:first-child::before {
  display: none;
}

.board_view .info dl dt,
.board_view .info dl dd {
  display: inline-block;
  font-size: 1.4rem;
}

.board_view .info dl dd {
  margin-left: 10px;
  color: #777;
}

.board_view .cont {
  padding: 15px;
  /* border-bottom: 1px solid #000; */
  line-height: 160%;
  font-size: 1.4rem;
}

.board_view .row mt-3 {
  padding: 15px;
  border-bottom: 1px solid #000;
  line-height: 160%;
}
</style>
