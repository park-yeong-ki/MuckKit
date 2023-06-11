<template>
    <div class="list-group-item list-group-item-action py-3 lh-sm" aria-current="true">
        <b-row>
            <b-col @click="addAttraction(att)"><strong class="text-center">{{ att.title }}</strong>
            </b-col>
            <b-col><b-button style="background-color: orange; border-color: orange" @click="readAttraction(att)" 
          >더보기</b-button
        ></b-col>
        </b-row>
        <b-row class="mt-3">
           <div class="col-12 mb-1 small text-left">{{ att.addr1 }}</div>
        </b-row>
  </div>
</template>

<script>
export default {
    components: {},
    data() {
        return {
        };
    },
    props: {
    att: Object,
  },
    created() {},
    methods: {
        addAttraction(attraction){
            return this.$store.dispatch("addAttraction", attraction);
        },
        readAttraction(att){
            this.$emit("readAttraction", att);
        }
    },
};
</script>

<style scoped></style>