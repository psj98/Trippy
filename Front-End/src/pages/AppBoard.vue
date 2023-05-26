<template>
  <div class="container-fluid">
    <router-view></router-view>
  </div>
</template>

<script>
export default {};
</script>

<style>
.page-item.active .page-link {
  background-color: cadetblue;
  border-color: cadetblue;
}
.page-link:hover {
  color: dimgray;
  background-color: rgb(180, 217, 218);
  border-color: rgb(180, 217, 218);
}

.page-link {
  color: darkslategray;
}

.btn-info {
  background-color: rgb(95, 158, 160);
  border-color: rgb(95, 158, 160);
}

.btn-info:hover {
  background-color: rgb(95, 158, 160);
  border-color: rgb(95, 158, 160);
}

.btn-info.btn-fill {
  background-color: cadetblue;
  border-color: cadetblue;
}

.btn-info.btn-fill:hover {
  color: dimgray;
  background-color: rgb(180, 217, 218);
  border-color: rgb(180, 217, 218);
}

.btn-info.btn-fill:focus {
  color: dimgray;
  background-color: rgb(180, 217, 218);
  border-color: rgb(180, 217, 218);
}

.btn-info:not(:disabled):not(.disabled).active {
  background-color: rgb(95, 158, 160);
  border-color: rgb(95, 158, 160);
}
button.btn.btn-info {
  background-color: rgb(95, 158, 160);
  border-color: rgb(95, 158, 160);
}
</style>