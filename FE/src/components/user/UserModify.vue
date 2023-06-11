<template>
  <div class="container col-xl-10 col-xxl-8 px-4 py-5">
    <div class="row align-items-center py-5">
      <!-- <div class="col-lg-7 text-center text-lg-start"></div> -->
      <div class="col-md-10 mx-auto col-lg-6">
        <h1 class="display-4 fw-bold lh-1 text-body-emphasis mb-3">회원정보 수정</h1>
        <form class="p-4 p-md-5 border rounded-3 bg-body-tertiary">
          <b-container fluid>
            <b-row class="my-4">
              <b-col>
                <b-form-input
                  id="input-none"
                  placeholder="이메일"
                  :value="getMemberId"
                  :readonly="true"
                ></b-form-input>
              </b-col>
            </b-row>
            <b-row class="my-4">
              <b-col>
                <b-form-input
                  id="input-none"
                  :state="validMemberPw"
                  placeholder="비밀번호"
                  type="password"
                  v-model="memberPw"
                ></b-form-input>
              </b-col>
            </b-row>
            <b-row class="my-4">
              <b-col>
                <b-form-input
                  id="input-none"
                  :state="checkPassword"
                  placeholder="비밀번호 확인"
                  v-model="confirmPw"
                  type="password"
                ></b-form-input>
              </b-col>
            </b-row>
            <b-row class="my-4">
              <b-col>
                <b-form-input
                  id="input-none"
                  :state="validMemberName"
                  placeholder="이름"
                  v-model="memberName"
                ></b-form-input>
              </b-col>
            </b-row>
            <b-row class="my-4">
              <b-col>
                <b-form-input
                  id="input-none"
                  :state="validMemberPhone"
                  placeholder="연락처"
                  v-model="memberPhone"
                  type="tel"
                ></b-form-input>
              </b-col>
            </b-row>
          </b-container>
          <button
            class="w-100 btn btn-lg"
            style="background-color: orange"
            @click="modifyMember"
            type="button"
          >
            수정하기
          </button>
          <button
            class="w-100 btn btn-lg mt-2"
            style="background-color: orange"
            @click="deleteMember"
            type="button"
          >
            탈퇴하기
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import router from "@/router";

export default {
  components: {},
  data() {
    return {
      memberPw: "",
      memberName: "",
      memberPhone: "",
      confirmPw: "",
    };
  },
  created() {},
  methods: {
    deleteMember() {
      if (confirm("회원탈퇴를 진행하시겠습니까?")) {
        http
          .delete("/member/" + this.getMemberId, {
            headers: {
              Authorization: "Bearer " + sessionStorage.getItem("access-token"),
            },
          })
          .then((resp) => {
            console.log(resp);
            alert("회원 탈퇴가 완료되었습니다.");
            this.$store.dispatch("memberLogout");
            router.push({ name: "main" });
          })
          .catch((error) => {
            console.log(error);
            alert("회원 탈퇴가 실패하였습니다.");
          });
      }
    },
    modifyMember() {
      if (
        this.checkPassword &&
        this.validMemberPw &&
        this.validMemberName &&
        this.validMemberPhone
      ) {
        http
          .put(
            "/member/modify",
            {
              memberId: this.getMemberId,
              memberPw: this.memberPw,
              memberName: this.memberName,
              memberPhone: this.memberPhone,
            },
            {
              headers: {
                Authorization: "Bearer " + sessionStorage.getItem("access-token"),
              },
            }
          )
          .then((resp) => {
            console.log(resp);
            alert("회원정보가 수정되었습니다.");
            router.push({ name: "mypage" });
          })
          .catch((error) => {
            console.log(error);
            alert("회원정보 수정에 실패하였습니다.");
          });
      } else {
        alert("회원정보를 입력하세요.");
      }
    },
  },

  computed: {
    getMemberId() {
      return this.$store.getters.getMemberId;
    },
    getMemberName() {
      return this.$store.getters.getMemberName;
    },
    getMemberPhone() {
      return this.$store.getters.getMemberPhone;
    },
    checkPassword() {
      if (this.confirmPw === "") {
        return null;
      }

      return this.confirmPw === this.memberPw;
    },
    validMemberPw() {
      if (this.memberPw === "") {
        return null;
      }
      return this.memberPw.length > 3 && this.memberPw.length < 20;
    },
    validMemberName() {
      if (this.memberName === "") {
        return null;
      }
      return this.memberName.length > 0 && this.memberName.length < 10;
    },
    validMemberPhone() {
      if (this.memberPhone === "") {
        return null;
      }
      let regPhone = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
      return regPhone.test(this.memberPhone);
    },
  },
};
</script>

<style scoped></style>
