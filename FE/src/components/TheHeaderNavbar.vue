<template>
  <div>
    <b-navbar toggleable="lg" type="light" style="background: orange">
      <!-- <b-navbar toggleable="lg" type="light" style="background-color: orange"> -->
      <b-navbar-brand class="ml-3">
        <!-- <router-link :to="{ name: 'main' }"> TripPlanner </router-link> -->
        <router-link :to="{ name: 'main' }">
          <img
            src="@/assets/logo.jpg"
            alt=""
            style="width: 130px; height: 30px"
            onclick="location.href='/';"
          />
        </router-link>
      </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-navbar-nav>
            <b-nav-item>
              <router-link :to="{ name: 'article' }">여행 후기</router-link>
            </b-nav-item>
            <b-nav-item>
              <router-link :to="{ name: 'plan' }">여행 플랜</router-link>
            </b-nav-item>
            <b-nav-item>
              <router-link :to="{ name: 'notice' }">공지 사항</router-link>
            </b-nav-item>
          </b-navbar-nav>

          <b-nav-item-dropdown right v-if="memberName">
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>{{ memberName }}님</em>
            </template>
            <b-dropdown-item>
              <router-link :to="{ name: 'mypage' }" class="link" style="color: black"
                >마이페이지</router-link
              >
            </b-dropdown-item>
            <b-dropdown-item @click="logout" style="color: black">로그아웃</b-dropdown-item>
          </b-nav-item-dropdown>
          <b-nav-item-dropdown right v-else>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>시작하기</em>
            </template>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'join' }" class="link" style="color: black"
                >회원가입</router-link
              >
            </b-dropdown-item>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'login' }" class="link" style="color: black"
                >로그인</router-link
              >
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
  computed: {
    memberId() {
      return this.$store.getters.getMemberId;
    },
    memberName() {
      return this.$store.getters.getMemberName;
    },
  },
  methods: {
    logout() {
      this.$store.dispatch("memberLogout");
      router.push({ name: "main" }).catch(() => {});
    },
  },
  created() {},
  components: {},
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: white;
  font-weight: normal;
  font-size: 16px;
}

a:hover {
  color: white;
}
</style>
