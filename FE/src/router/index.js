import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";
// import KaKaoMap from "@/views/KaKaoMap.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/attraction",
    name: "attractionview",
    component: () =>
      import(/* webpackChunkName: "user" */ "@/components/board/Attraction/AttractionView"),
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "join",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserRegist"),
      },
      {
        path: "login",
        name: "login",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserLogin"),
      },
      {
        path: "modify",
        name: "modify",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserModify"),
      },
      {
        path: "mypage",
        name: "mypage",
        // beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserMypage"),
        redirect: "mypage/plan",
        children: [
          {
            path: "article",
            name: "myPageArticle",
            component: () =>
              import(/* webpackChunkName: "user" */ "@/components/user/mypage-item/MypageArticle"),
          },
          {
            path: "heartArticle",
            name: "myPageHeartArticle",
            component: () =>
              import(
                /* webpackChunkName: "user" */ "@/components/user/mypage-item/MypageHeartArticle"
              ),
          },
          {
            path: "plan",
            name: "myPagePlan",
            component: () =>
              import(/* webpackChunkName: "user" */ "@/components/user/mypage-item/MypagePlan"),
          },
          {
            path: "heartPlan",
            name: "myPageHeartPlan",
            component: () =>
              import(
                /* webpackChunkName: "user" */ "@/components/user/mypage-item/MypageHeartPlan"
              ),
          },
          {
            path: "comment",
            name: "myPageComment",
            component: () =>
              import(/* webpackChunkName: "user" */ "@/components/user/mypage-item/MypageComment"),
          },
        ],
      },
    ],
  },
  {
    path: "/article",
    name: "article",
    redirect: "article/list",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppArticle"),
    children: [
      {
        path: "list",
        name: "articlelist",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Article/ArticleList"),
      },
      {
        path: "write",
        name: "articlewrite",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Article/ArticleWrite"),
      },
      {
        path: "view/:id",
        name: "articleview",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Article/ArticleView"),
      },
      {
        path: "modify",
        name: "articlemodify",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Article/ArticleModify"),
      },
      {
        path: "delete",
        name: "articledelete",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Article/ArticleDelete"),
      },
    ],
  },
  {
    path: "/plan",
    name: "plan",
    redirect: "plan/list",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppPlan"),
    children: [
      {
        path: "list",
        name: "planlist",
        component: () => import(/* webpackChunkName: "user" */ "@/components/board/Plan/PlanList"),
      },
      {
        // path: "view/:planId",
        path: "view/:id",
        name: "planview",
        component: () => import(/* webpackChunkName: "user" */ "@/components/board/Plan/PlanView"),
      },
      {
        path: "write",
        name: "planwrite",
        component: () => import(/* webpackChunkName: "user" */ "@/components/board/Plan/PlanWrite"),
      },
      {
        path: "modify/:id",
        name: "planmodify",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Plan/PlanModify"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    redirect: "notice/list",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppNotice"),
    children: [
      {
        path: "list",
        name: "noticelist",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Notice/NoticeList"),
      },
      {
        path: "view/:id",
        name: "noticeview",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Notice/NoticeView"),
      },
      {
        path: "write",
        name: "noticewrite",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Notice/NoticeWrite"),
      },
      {
        path: "modify/:id",
        name: "noticemodify",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/board/Notice/NoticeModify"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
