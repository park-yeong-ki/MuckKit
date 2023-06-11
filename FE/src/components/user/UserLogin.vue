<template>
  <div class="container col-xl-10 col-xxl-8 px-12 py-5">
    <div class="row align-items-center py-5">
      <!-- <div class="col-lg-7 text-center text-lg-start"></div> -->
      <div class="col-md-10 mx-auto col-lg-6">
        <h1 class="display-4 fw-bold lh-1 text-body-emphasis mb-3">로그인</h1>
        <form class="p-4 p-md-6 border rounded-3 bg-body-tertiary">
          <div class="form-floating mb-3">
            <label for="floatingInput">아이디</label>
            <input
              type="email"
              class="form-control"
              id="floatingInput"
              placeholder="name@example.com"
              v-model="memberId"
            />
          </div>

          <div class="form-floating mb-4">
            <label for="floatingPassword">비밀번호</label>
            <input
              type="password"
              class="form-control"
              id="floatingPassword"
              placeholder="Password"
              v-model="memberPw"
            />
          </div>

          <button
            class="w-100 btn btn-lg"
            style="background-color: orange"
            type="button"
            @click="login"
          >
            로그인
          </button>
          <hr class="my-4" />
          <small class="text-body-secondary">비밀번호를 잊어버리셨나요? </small>
          <small class="font-weight-bold" v-b-modal.modal-prevent-closing>비밀번호 찾기</small>
          <b-modal
            id="modal-prevent-closing"
            ref="modal"
            title="비밀번호 찾기"
            @show="resetModal"
            @hidden="resetModal"
            @ok="handleOk"
            ok-title="확인"
            ok-variant="secondary"
            cancel-title="취소"
            cancel-variant="secondary"
          >
            <form ref="form" @submit.stop.prevent="handleSubmit">
              <b-form-group
                label="이메일"
                label-for="email-input"
                invalid-feedback="이메일을 입력해주세요."
                valid-feedback="임시비밀번호가 발급되었습니다."
                :state="emailState"
              >
                <b-form-input
                  id="email-input"
                  v-model="email"
                  :state="emailState"
                  required
                ></b-form-input>
              </b-form-group>
            </form>
          </b-modal>
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
      memberPw: "",
      email: "",
      emailState: null,
    };
  },
  created() {},
  methods: {
    login() {
      http
        .post("/member/login", {
          memberId: this.memberId,
          memberPw: this.memberPw,
        })
        .then((resp) => {
          console.log(resp);
          alert("환영합니다.");

          sessionStorage.setItem("access-token", resp.headers.get("access-token"));
          sessionStorage.setItem("refresh-token", resp.headers.get("refresh-token"));

          this.$store.dispatch("memberLogin");

          router.push({ name: "main" });
        })
        .catch((error) => {
          console.log(error);
          alert("아이디와 비밀번호를 확인해주세요.");
        });
    },
    checkFormValidity() {
      let regex =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

      const valid = this.$refs.form.checkValidity();
      this.emailState = valid;
      if (this.emailState) {
        this.emailState = regex.test(this.email);
      }

      return this.emailState;
    },
    resetModal() {
      this.email = "";
      this.emailState = null;
    },
    handleOk(bvModalEvent) {
      // Prevent modal from closing
      bvModalEvent.preventDefault();
      // Trigger submit handler
      this.handleSubmit();
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return;
      }
      http
        .post("/member/find-password", {
          memberId: this.email,
        })
        .then((resp) => {
          console.log(resp);
          this.emailState = true;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
