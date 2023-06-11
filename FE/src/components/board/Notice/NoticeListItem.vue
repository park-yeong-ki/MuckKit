<template>
  <b-tr>
    <b-td>{{ nt.noticeTitle }}</b-td>
    <b-td v-on:click="moveView" class="stretched-link">{{ nt.noticeWriter }}</b-td>
    <b-td>{{ nt.createdTime.substr(0, 10) }}</b-td>
    <b-td>{{ nt.hit }}</b-td>
  </b-tr>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      message: "",
    };
  },
  props: {
    nt: Object,
  },
  created() {},
  methods: {
    moveView() {
      this.$router.push({ path: `/notice/view/${this.nt.noticeId}` });
      // this.$router.push({ name: "noticeview", params: { id: this.nt.noticeId } });
    },
  },
};
</script>

<style scoped></style>
