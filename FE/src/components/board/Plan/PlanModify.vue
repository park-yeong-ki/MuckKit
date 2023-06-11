<template>
    <div>
        <b-row>
            <b-col cols="2">
                <plan-modify-item-vue/>
            </b-col>
            <b-col cols="2">
                <plan-search-attraction-vue/>
            </b-col>
            <b-col cols="8">
                <plan-map-vue/>
            </b-col>
        </b-row>
    </div>
</template>

<script>
import PlanMapVue from "./plan-item/PlanMap.vue";
import PlanModifyItemVue from "./plan-item/PlanModifyItem.vue";
import PlanSearchAttractionVue from "./plan-item/PlanSearchAttraction.vue";

export default {
    components: {
        PlanMapVue,
        PlanSearchAttractionVue,
        PlanModifyItemVue
    },
    data() {
        return {
            attraction: {},
        };
    },
    computed : {
    }
    ,
    created() {
        
    },
    methods: {
    },
};
</script>

<style scoped></style>