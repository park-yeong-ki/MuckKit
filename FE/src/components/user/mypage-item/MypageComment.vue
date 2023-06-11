<template>
    <div>
        {{myComment}}
    </div>
</template>

<script>
export default {
    components: {},
    data() {
        return {
            message: '',
        };
    },
    created() {},
    methods: {},
    computed: {
        myComment() {
            return this.$store.getters.getCommentList;
        }
    }
};
</script>

<style scoped></style>