<template>
    <b-row>
      <b-col cols="2" class="no-padding">
        <plan-attraction-list-vue />
      </b-col>
      <b-col cols="2" class="no-padding">
        <plan-search-attraction-vue />
      </b-col>
      <b-col cols="8">
        <plan-map-vue />
      </b-col>
    </b-row>
</template>

<script>
import PlanAttractionListVue from "./plan-item/PlanAttractionList.vue";
import PlanMapVue from "./plan-item/PlanMap.vue";
import PlanSearchAttractionVue from "./plan-item/PlanSearchAttraction.vue";

export default {
  components: {
    PlanAttractionListVue,
    PlanMapVue,
    PlanSearchAttractionVue,
  },
  data() {
    return {
      attraction: {},
    };
  },
  created() {
    this.$store.dispatch("removeAllAttraction");
  },
  methods: {},
};
</script>

<style scoped></style>
