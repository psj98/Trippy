<template>
  <card class="card-user" style="height: 90%">
    <img
      slot="image"
      src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&fm=jpg&h=300&q=75&w=400"
      alt="..."
    />
    <div class="author">
      <img class="avatar border-gray" src="img/faces/face-0.jpg" alt="..." />
      <!-- <a> -->
        <!-- <h4>ID : {{ userInfo.id }}</h4> -->
        <h4 class="title">
          <!-- {{ userInfo.name }} 님<br /> -->
          <small>{{ userInfo.userGrade }}</small>
        </h4>
      <!-- </a> -->
    </div>
    <!-- <p class="description text-center">"{{ userInfo.desc }}"</p> -->
    <div slot="footer" class="text-center d-flex justify-content-center">
      <button href="#" class="btn btn-simple">
        <i class="fa fa-facebook-square"></i>
      </button>
      <button href="#" class="btn btn-simple">
        <i class="fa fa-twitter"></i>
      </button>
      <button href="#" class="btn btn-simple">
        <i class="fa fa-google-plus-square"></i>
      </button>
    </div>
  </card>
</template>
<script>
import { mapState } from "vuex";
import Card from "src/components/Cards/Card.vue";

const userStore = "userStore";

export default {
  components: {
    Card,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      details: [
        {
          title: "12",
          subTitle: "Files",
        },
        {
          title: "2GB",
          subTitle: "Used",
        },
        {
          title: "24,6$",
          subTitle: "Spent",
        },
      ],
    };
  },
  methods: {
    getClasses(index) {
      var remainder = index % 3;
      if (remainder === 0) {
        return "col-md-3 col-md-offset-1";
      } else if (remainder === 2) {
        return "col-md-4";
      } else {
        return "col-md-3";
      }
    },
  },
};
</script>
<style></style>
