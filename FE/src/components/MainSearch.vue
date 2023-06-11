<template>
  <b-container class="bv-example-row mt-3 text-center" id="#app">
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron style="background: rgba(0, 0, 0, 0)" text-variant="light">
          <template>
            <div style="color: black text-align:left">
              <h1>나만의 먹킷리스트,</h1>
              <h1>현실로 만들어보세요!</h1>
            </div>
          </template>

          <template>
            <b-input-group
              id="mainArea"
              v-for="size in ['lg']"
              :key="size"
              :size="size"
              class="mb-3"
            >
              <b-form-input id="inputArea" class="btn btn-outline-light"></b-form-input>
              <b-input-group-append>
                <b-button
                  id="btnArea"
                  text="검색"
                  class="btn btn-outline-light btn-lg"
                  style="background-color: orange"
                  >Button</b-button
                >
              </b-input-group-append>
            </b-input-group>
          </template>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  components: {},
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
#inputArea {
  background-color: white;
}
</style>
