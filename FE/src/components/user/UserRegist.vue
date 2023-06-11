<template>
  <div class="container col-xl-10 col-xxl-8 px-4 py-5">
    <div class="row align-items-center py-5">
      <!-- <div class="col-lg-7 text-center text-lg-start"></div> -->
      <div class="col-md-10 mx-auto col-lg-6">
        <h1 class="display-4 fw-bold lh-1 text-body-emphasis mb-3">회원가입</h1>
        <form class="p-4 p-md-5 border rounded-3 bg-body-tertiary">
          <b-container fluid>
            <b-row class="my-4">
              <b-col>
                <b-form-input
                  id="input-none"
                  placeholder="이메일"
                  v-model="memberId"
                  :state="validMemberId"
                ></b-form-input>
              </b-col>
              <button
                class="btn btn-sm"
                type="button"
                style="background-color: orange"
                @click="emailSend"
              >
                인증코드 받기
              </button>
            </b-row>
            <b-row class="my-4">
              <b-col>
                <b-form-input
                  id="input-none"
                  :placeholder="message"
                  v-model="authCode"
                  :state="validAuthCode"
                ></b-form-input>
              </b-col>
              <button
                class="btn btn-sm"
                style="background-color: orange"
                type="button"
                @click="verifyCode"
              >
                인증 하기
              </button>
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
                  :state="isSamePw"
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
            @click="joinMember"
            type="button"
          >
            가입하기
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
      memberId: "",
      authCode: "",
      validAuthCode: null,
      memberName: "",
      memberPw: "",
      memberPhone: "",
      confirmPw: "",
      isSamePw: null,
      message: "이메일 인증 코드",
    };
  },
  created() {},
  methods: {
    checkPassword() {
      if (this.memberPw === this.confirmPw) {
        this.isSamePw = true;
      } else {
        this.isSamePw = false;
      }
    },
    joinMember() {
      if (
        this.validMemberId &&
        this.validMemberPw &&
        this.validAuthCode &&
        this.validMemberName &&
        this.validMemberPhone
      ) {
        http
          .post("/member/join", {
            memberId: this.memberId,
            memberName: this.memberName,
            memberPw: this.memberPw,
            memberPhone: this.memberPhone,
          })
          .then((resp) => {
            console.log(resp);
            alert("회원가입이 성공되었습니다");
            router.push({ name: "main" });
          })
          .catch((error) => {
            console.log(error);
            alert("회원가입이 실패하였습니다.");
            router.push({ name: "main" });
          });
      } else {
        alert("회원가입 양식을 올바르게 입력해주세요.");
      }
    },
    emailSend() {
      http
        .post("/member/join/send-mail", {
          memberId: this.memberId,
        })
        .then((resp) => {
          console.log(resp);
          this.message = "인증 코드가 발송되었습니다.";
        })
        .catch((resp) => {
          console.log(resp);
        });
    },
    verifyCode() {
      http
        .post("/member/join/auth-code", {
          memberId: this.memberId,
          authCode: this.authCode,
        })
        .then((resp) => {
          console.log(resp);
          this.validAuthCode = true;
        })
        .catch((error) => {
          console.log(error);
          this.validAuthCode = false;
        });
    },
  },
  watch: {
    confirmPw: function () {
      this.checkPassword();
    },
  },
  computed: {
    validMemberId() {
      if (this.memberId === "") {
        return null;
      }
      let regex =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
      return regex.test(this.memberId);
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
