<template>
  <div class="content mb-4">
    <div class="container-fluid">
      <card>
        <template slot="header">
          <h3 class="card-title" style="margin-bottom: 5px">관광지 조회</h3>
          <p class="card-category">관광지를 조회해보세요!</p>
        </template>

        <!-- 관광지 검색 창 -->
        <attraction-search-bar></attraction-search-bar>

        <!-- 카카오 맵 -->
        <b-row>
          <b-col>
            <attraction-map></attraction-map>
          </b-col>
          <!-- 관광지 리스트 -->
          <b-col>
            <attraction-list></attraction-list>
          </b-col>
        </b-row>
      </card>
    </div>
  </div>
</template>

<script>
import AttractionMap from "@/components/attraction/AttractionMap.vue";
import AttractionSearchBar from "@/components/attraction/AttractionSearchBar.vue";
import AttractionList from "@/components/attraction/AttractionList.vue";

export default {
  components: {
    AttractionMap,
    AttractionSearchBar,
    AttractionList,
  },
};
</script>

<style>
</style>
