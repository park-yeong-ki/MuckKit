<template>
  <div id="app">
    <main-test-vue></main-test-vue>
  </div>
</template>

<script>
import MainTestVue from "@/components/MainTest.vue";

export default {
  name: "App",
  components: {
    MainTestVue,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0 auto;
  width: 100%;
  height: 40vh;
  /* background-image: url("@/assets/음식배경2.PNG"); */
  /* background-image: linear-gradient(rgba(255, 255, 255, 0.6), rgba(255, 255, 255, 0.6)),
    url("@/assets/back.jpg"); */
  background-color: orange;
  background-size: cover;
}

a.router-link-exact-active {
  color: white;
}

a.router-link-exact-active:hover {
  text-decoration: none;
}
</style>
