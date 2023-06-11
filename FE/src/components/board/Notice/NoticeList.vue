<template>
  <div>
    <h2>공지사항</h2>
    <b-container class="bv-example-row mt-5">
      <b-row class="mb-1">
        <b-col class="text-right">
          <router-link :to="{ name: 'noticewrite' }"
            ><b-button
              id="btnArea"
              class="btn btn-lg"
              style="background-color: orange; border-color: orange"
              v-if="getMemberId === 'admin'"
              >등록</b-button
            ></router-link
          >
        </b-col>
      </b-row>
      <b-row>
        <!-- <b-col v-if="articles.length"> -->
        <b-col>
          <b-table-simple hover responsive>
            <b-thead head-variant="">
              <b-tr>
                <b-th>제목</b-th>
                <b-th>작성자</b-th>
                <b-th>작성일</b-th>
                <b-th>조회수</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <notice-list-item
                v-for="(notice, idx) in noticeList"
                :key="idx"
                :nt="notice"
              ></notice-list-item>
            </tbody>
          </b-table-simple>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import NoticeListItem from "@/components/board/Notice/NoticeListItem.vue";
export default {
  components: {
    NoticeListItem,
  },
  data() {
    return {
      noticeList: [],
    };
  },
  created() {
    axios.get("http://localhost/enjoytrip/notice?sort=hit").then((resp) => {
      this.noticeList = resp.data;
    });
  },
  methods: {},
  computed: {
    getMemberId() {
      return this.$store.getters.getMemberId;
    },
  },
};
</script>

<style scoped></style>
