<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-9">
          <edit-profile-form> </edit-profile-form>
        </div>
        <div class="col-md-3">
          <user-card> </user-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import EditProfileForm from "@/components/user/UserProfile/EditProfileForm.vue";
import UserCard from "@/components/user/UserProfile/UserCard.vue";

import { mapActions } from "vuex";

const attractionStore = "attractionStore";

export default {
  components: {
    EditProfileForm,
    UserCard,
  },
  methods: {
    ...mapActions(attractionStore, ["deleteSearchAttraction"]),
  },
  created() {
    this.deleteSearchAttraction();
  },
};
</script>
<style></style>
