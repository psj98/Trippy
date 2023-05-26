<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-7">
          <chart-card
            :chart-data="lineChart.data"
            :chart-options="lineChart.options"
            :responsive-options="lineChart.responsiveOptions"
          >
            <template slot="header">
              <h4 class="card-title">지역별 여행 수</h4>
              <p class="card-category">최근 일주일 간 여행 수</p>
            </template>
            <!-- <template slot="footer">
              <div class="legend">
                <i class="fa fa-circle text-info"></i> Open
                <i class="fa fa-circle text-danger"></i> Click
                <i class="fa fa-circle text-warning"></i> Click Second Time
              </div>
            </template> -->
          </chart-card>
        </div>

        <div class="col-md-5" style="height: 100%">
          <card>
            <template slot="header">
              <h4 class="card-title">오늘의 여행지</h4>
              <p class="card-category">조회수 Top 5</p>
            </template>

            <div class="table-responsive" style="margin-bottom: 0.6rem">
              <b-table id="table" :items="todaysRecord" :fields="tableColumns">
                <template #cell(rank)="data">
                  {{ data.index + 1 }}
                </template>
                <template #cell(title)="data">
                  <router-link
                    :to="{
                      name: 'attractiondetail',
                      params: {
                        contentId: data.item.content_id,
                      },
                    }"
                    >{{ data.item.title }}</router-link
                  ></template
                >
              </b-table>
            </div>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "@/api/http";
import ChartCard from "src/components/Cards/ChartCard.vue";
import StatsCard from "src/components/Cards/StatsCard.vue";
import LTable from "src/components/Table.vue";

import { mapState, mapActions } from "vuex";

const userStore = "userStore";
const attractionStore = "attractionStore";

export default {
  components: {
    LTable,
    ChartCard,
    StatsCard,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      editTooltip: "Edit Task",
      deleteTooltip: "Remove",
      pieChart: {
        data: {
          labels: ["40%", "20%", "40%"],
          series: [40, 20, 40],
        },
      },
      lineChart: {
        data: {
          labels: [
            "7일전",
            "6일전",
            "5일전",
            "4일전",
            "3일전",
            "2일전",
            "1일전",
          ],
          series: [
            [287, 385, 490, 492, 554, 586, 698],
            [67, 152, 143, 240, 287, 335, 435],
            [23, 113, 67, 108, 190, 239, 307],
          ],
        },
        options: {
          low: 0,
          high: 500,
          showArea: false,
          height: "250px",
          axisX: {
            showGrid: false,
          },
          lineSmooth: true,
          showLine: true,
          showPoint: true,
          fullWidth: true,
          chartPadding: {
            right: 50,
          },
        },
        responsiveOptions: [
          [
            "screen and (max-width: 640px)",
            {
              axisX: {
                labelInterpolationFnc(value) {
                  return value[0];
                },
              },
            },
          ],
        ],
      },
      todaysRecord: [],
      tableColumns: [
        { key: "rank", labed: "순위" },
        { key: "title", label: "관광지명" },
        { key: "sido_name", label: "시도" },
        { key: "gugun_name", label: "구군" },
        { key: "liked", label: "조회수" },
      ],
    };
  },
  methods: {
    ...mapActions(attractionStore, ["deleteSearchAttraction"]),
  },
  created() {
    this.deleteSearchAttraction();

    axios.get("/hotplace/today").then(({ data }) => {
      console.log(data);
      this.todaysRecord = data;
    });
  },
};
</script>
<style></style>
