<template>
  <div class="content">
    <div class="container-fluid">
      <card>
        <!-- 나만의 여행 계획 목록 -->
        <share-board-list></share-board-list>
      </card>
    </div>
  </div>
</template>

<script>
import ShareBoardList from "@/components/shareboard/ShareBoardList.vue";

export default {
  components: {
    ShareBoardList,
  },
};

<style></style>;
