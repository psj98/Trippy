<template>
  <!-- 카카오 맵 -->
  <card class="m-0">
    <div id="map"></div>
  </card>
</template>

<script>
import { mapState } from "vuex";

const myPlanStore = "myPlanStore";

export default {
  data() {
    return {
      map: null,

      /* 검색 조건에 따른 관광지 */
      positions: [],
      markers: [],
      customOverlay: [],

      /* 선택한 관광지 */
      lines: [],
      selectedPositions: [],
      selectedMarkers: [],
      selectedCustomOverlay: [],
    };
  },

  watch: {
    attractions() {
      this.positions = [];

      /* 관광지가 없는 경우, 마커 및 오버레이 삭제 */
      if (this.attractions.length == 0) {
        this.deleteMarker();
        this.deleteCustomOverlay();
        return;
      }

      /* 관광지 별로 관광지명, 위도, 경도, 관광지 유형 저장 */
      this.attractions.forEach((attraction) => {
        let obj = {};
        obj.title = attraction.title;
        obj.latlng = new kakao.maps.LatLng(
          attraction.latitude,
          attraction.longitude
        );
        obj.contentTypeId = attraction.contentTypeId;
        obj.contentId = attraction.contentId;

        this.positions.push(obj);
      });

      this.loadMarker(); // 마커 로드

      this.addLine();

      this.selectedPositions = [];

      /* 관광지가 없는 경우, 마커 및 오버레이 삭제 */
      if (this.selectedAttractions.length == 0) {
        this.deleteSelectedMarker();
        this.deleteSelectedCustomOverlay();
        return;
      }

      /* 관광지 별로 관광지명, 위도, 경도, 관광지 유형 저장 */
      this.selectedAttractions.forEach((selectedAttraction) => {
        let obj = {};
        obj.title = selectedAttraction.title;
        obj.latlng = new kakao.maps.LatLng(
          selectedAttraction.latitude,
          selectedAttraction.longitude
        );
        obj.contentTypeId = selectedAttraction.contentTypeId;
        obj.contentId = selectedAttraction.contentId;

        this.selectedPositions.push(obj);
      });

      this.loadSelectedMarker(); // 마커 로드
    },

    selectedAttractions() {
      this.addLine();

      this.selectedPositions = [];

      /* 관광지가 없는 경우, 마커 및 오버레이 삭제 */
      if (this.selectedAttractions.length == 0) {
        this.deleteSelectedMarker();
        this.deleteSelectedCustomOverlay();
        return;
      }

      /* 관광지 별로 관광지명, 위도, 경도, 관광지 유형 저장 */
      this.selectedAttractions.forEach((selectedAttraction) => {
        let obj = {};
        obj.title = selectedAttraction.title;
        obj.latlng = new kakao.maps.LatLng(
          selectedAttraction.latitude,
          selectedAttraction.longitude
        );
        obj.contentTypeId = selectedAttraction.contentTypeId;
        obj.contentId = selectedAttraction.contentId;

        this.selectedPositions.push(obj);
      });

      this.loadSelectedMarker(); // 마커 로드
    },
  },
  mounted() {
    /* 카카오 맵 로드 */
    if (!window.kakao || !window.kakao.maps) {
      this.initMap();
    } else {
      this.loadMap();
    }

    this.addLine(); // 기존에 선택된 관광지 경로 찍기
  },
  methods: {
    /* 맵 초기화 */
    initMap() {
      const script = document.createElement("script");
      script.src =
        `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=` +
        `${process.env.VUE_APP_KAKAO_KEY}` +
        `&libraries=services,clusterer,drawing`;
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },

    /* 기존 맵 로드 */
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.500613, 127.036431),
        level: 6,
      };

      this.map = new kakao.maps.Map(container, options);

      /* 지도 확대 축소를 제어할 수 있는 줌 컨트롤 생성 */
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    },

    /* -------------------- 검색 조건에 따른 관광지 함수 -------------------- */
    /* 관광지 위치에 따른 마커 로드 */
    loadMarker() {
      this.deleteMarker(); // 기존 마커 지우기
      this.deleteCustomOverlay(); // 기존 오버레이 지우기

      /* 마커 생성 */
      this.markers = [];
      this.customOverlay = [];
      this.positions.forEach((position) => {
        /* 마커 이미지 생성 */
        var imgSrc = ""; // 이미지 위치
        var imgSize = new kakao.maps.Size(35, 35); // 마커 이미지 크기
        var contentName = ""; // 관광지 유형
        var backgroundColor = ""; // 배경색

        /* 이미지, 관광지 유형, 배경색 지정 */
        if (position.contentTypeId == "12") {
          imgSrc = require("@/assets/img/map/tripImg.png"); // 관광지
          contentName = "관광지";
          backgroundColor = "#c74c5e";
        } else if (position.contentTypeId == "14") {
          imgSrc = require("@/assets/img/map/cultureImg.png"); // 문화시설
          contentName = "문화시설";
          backgroundColor = "#9e3ca0";
        } else if (position.contentTypeId == "15") {
          imgSrc = require("@/assets/img/map/festivalImg.png"); // 축제공연행사
          contentName = "축제·공연·행사";
          backgroundColor = "#37aef6";
        } else if (position.contentTypeId == "25") {
          imgSrc = require("@/assets/img/map/pathImg.png"); // 여행코스
          contentName = "여행코스";
          backgroundColor = "#56b38f";
        } else if (position.contentTypeId == "28") {
          imgSrc = require("@/assets/img/map/sportsImg.png"); // 레포츠
          contentName = "레포츠";
          backgroundColor = "#8ea3a4";
        } else if (position.contentTypeId == "32") {
          imgSrc = require("@/assets/img/map/homeImg.png"); // 숙박
          contentName = "숙박";
          backgroundColor = "#3b7ac8";
        } else if (position.contentTypeId == "38") {
          imgSrc = require("@/assets/img/map/shoppingImg.png"); // 쇼핑
          contentName = "쇼핑";
          backgroundColor = "#a46450";
        } else if (position.contentTypeId == "39") {
          imgSrc = require("@/assets/img/map/foodImg.png"); // 음식점
          contentName = "음식점";
          backgroundColor = "#e1c53a";
        }

        var markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize); // 마커 이미지 적용

        /* 마커 생성 */
        var marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng, // 마커 위치
          title: position.title, // 마커 제목
          image: markerImage, // 마커 이미지
        });

        /* 커스텀 오버레이 생성 */
        this.loadOverlay(contentName, position, backgroundColor, marker);

        this.markers.push(marker); // 마커 배열에 저장
      });

      /* 지도 이동 -> 모든 정보가 포함된 곳으로 이동 */
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },

    /* 커스텀 오버레이 생성 */
    loadOverlay(contentName, position, backgroundColor, marker) {
      var content = `<div class="overlay_info">`;
      content += `    <a href="#/attractions/${position.contentId}">`;
      content += `        <div class="positionTitle" style="background-color: ${backgroundColor}">`;
      content += `            <strong>${position.title}</strong>`;
      content += `        </div>`;
      content += `        <div class="desc">`;
      content += `            <span class="contentName">${contentName}</span>`;
      content += `        </div>`;
      content += `    </a>`;
      content += `</div>`;

      var curMap = this.map;

      // 커스텀 오버레이 생성
      var mapCustomOverlay = new kakao.maps.CustomOverlay({
        position: position.latlng, // 마커 위치
        content: content,
        xAnchor: -0.2, // 커스텀 오버레이 x축 위치
        yAnchor: 0.7, // 커스텀 오버레이 y축 위치
        isClosed: true,
      });

      var cpyCustomOverlay = this.customOverlay;

      /* 마커 클릭 시, 오버레이 열기 & 닫기 */
      kakao.maps.event.addListener(marker, "click", function () {
        if (!mapCustomOverlay.isClosed) {
          mapCustomOverlay.setMap(null);
          mapCustomOverlay.isClosed = true;
          return;
        }

        for (var i = 0; i < cpyCustomOverlay.length; i++) {
          cpyCustomOverlay[i].setMap(null);
          cpyCustomOverlay[i].isClosed = true;
        }

        if (mapCustomOverlay.isClosed) {
          mapCustomOverlay.setMap(curMap);
          curMap.setLevel(7);
          curMap.panTo(position.latlng);
          mapCustomOverlay.isClosed = false;
        }
      });

      this.customOverlay.push(mapCustomOverlay); // 커스텀 오버레이 배열에 저장
    },

    /* 기존 마커 지우기 */
    deleteMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }
    },

    /* 기존 오버레이 지우기 */
    deleteCustomOverlay() {
      if (this.customOverlay.length > 0) {
        this.customOverlay.forEach((item) => {
          item.setMap(null);
        });
      }
    },
    /* -------------------- 검색 조건에 따른 관광지 함수 종료 -------------------- */

    /* -------------------- 선택한 관광지 함수 시작 -------------------- */
    /* 경로 추가 */
    addLine() {
      this.deleteLine(); // 기존 경로 지우기

      // 배열에 저장된 경로만 가져오기
      var linePath = [];
      this.selectedAttractions.forEach((curAtt) => {
        linePath.push(new kakao.maps.LatLng(curAtt.latitude, curAtt.longitude));
      });

      /* 현재 저장된 배열의 위도, 경도를 가지고 Polyline 생성 */
      var polyline = new kakao.maps.Polyline({
        path: linePath, // 선 좌표 배열
        strokeWeight: 4, // 선 굵기
        strokeColor: "blue", // 선 색
        strokeOpacity: 0.7, // 선 불투명도
        strokeStyle: "solid", // 선 스타일
      });

      this.lines.push(polyline); // 나중에 경로를 지우기 위해서 배열에 따로 저장
      polyline.setMap(this.map); // 지도에 경로 표시
    },

    /* 기존 경로 지우기 */
    deleteLine() {
      this.lines.forEach((line) => {
        line.setMap(null);
      });

      this.lines = [];
    },

    /* 선택한 관광지에 따른 마커 로드 */
    loadSelectedMarker() {
      this.deleteSelectedMarker(); // 기존 마커 지우기
      this.deleteSelectedCustomOverlay(); // 기존 오버레이 지우기

      /* 마커 생성 */
      this.selectedMarkers = [];
      this.selectedCustomOverlay = [];
      this.selectedPositions.forEach((position) => {
        var contentName = ""; // 관광지 유형
        var backgroundColor = ""; // 배경색

        /* 이미지, 관광지 유형, 배경색 지정 */
        if (position.contentTypeId == "12") {
          contentName = "관광지";
          backgroundColor = "#c74c5e";
        } else if (position.contentTypeId == "14") {
          contentName = "문화시설";
          backgroundColor = "#9e3ca0";
        } else if (position.contentTypeId == "15") {
          contentName = "축제·공연·행사";
          backgroundColor = "#37aef6";
        } else if (position.contentTypeId == "25") {
          contentName = "여행코스";
          backgroundColor = "#56b38f";
        } else if (position.contentTypeId == "28") {
          contentName = "레포츠";
          backgroundColor = "#8ea3a4";
        } else if (position.contentTypeId == "32") {
          contentName = "숙박";
          backgroundColor = "#3b7ac8";
        } else if (position.contentTypeId == "38") {
          contentName = "쇼핑";
          backgroundColor = "#a46450";
        } else if (position.contentTypeId == "39") {
          contentName = "음식점";
          backgroundColor = "#e1c53a";
        }

        /* 마커 생성 */
        var marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng, // 마커 위치
          title: position.title, // 마커 제목
        });

        /* 커스텀 오버레이 생성 */
        this.loadSelectedOverlay(
          contentName,
          position,
          backgroundColor,
          marker
        );

        this.selectedMarkers.push(marker); // 마커 배열에 저장
      });

      /* 지도 이동 -> 모든 정보가 포함된 곳으로 이동 */
      const bounds = this.selectedPositions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },

    /* 커스텀 오버레이 생성 */
    loadSelectedOverlay(contentName, position, backgroundColor, marker) {
      var content = `<div class="overlay_info">`;
      content += `    <a href="#/attractions/${position.contentId}">`;
      content += `        <div class="positionTitle" style="background-color: ${backgroundColor}">`;
      content += `            <strong>${position.title}</strong>`;
      content += `        </div>`;
      content += `        <div class="desc">`;
      content += `            <span class="contentName">${contentName}</span>`;
      content += `        </div>`;
      content += `    </a>`;
      content += `</div>`;

      var curMap = this.map;

      var mapCustomOverlay = new kakao.maps.CustomOverlay({
        position: position.latlng, // 마커 위치
        content: content,
        xAnchor: -0.2, // 커스텀 오버레이 x축 위치
        yAnchor: 0.7, // 커스텀 오버레이 y축 위치
        isClosed: true,
      });

      var cpyCustomOverlay = this.selectedCustomOverlay;

      /* 마커 클릭 시, 오버레이 열기 & 닫기 */
      kakao.maps.event.addListener(marker, "click", function () {
        if (!mapCustomOverlay.isClosed) {
          mapCustomOverlay.setMap(null);
          mapCustomOverlay.isClosed = true;
          return;
        }

        for (var i = 0; i < cpyCustomOverlay.length; i++) {
          cpyCustomOverlay[i].setMap(null);
          cpyCustomOverlay[i].isClosed = true;
        }

        if (mapCustomOverlay.isClosed) {
          mapCustomOverlay.setMap(curMap);
          curMap.setLevel(7);
          curMap.panTo(position.latlng);
          mapCustomOverlay.isClosed = false;
        }
      });

      this.selectedCustomOverlay.push(mapCustomOverlay); // 커스텀 오버레이 배열에 저장
    },

    /* 기존 마커 지우기 */
    deleteSelectedMarker() {
      if (this.selectedMarkers.length > 0) {
        this.selectedMarkers.forEach((item) => {
          item.setMap(null);
        });
      }
    },

    /* 기존 오버레이 지우기 */
    deleteSelectedCustomOverlay() {
      if (this.selectedCustomOverlay.length > 0) {
        this.selectedCustomOverlay.forEach((item) => {
          item.setMap(null);
        });
      }
    },
    /* -------------------- 선택한 관광지 함수 종료 -------------------- */
  },

  computed: {
    ...mapState(myPlanStore, ["attractions", "selectedAttractions"]),
  },
};
</script>

<style>
/* #map {
  height: 620px;
} */

.overlay_info {
  width: 130px;
  margin-bottom: 12px;
  float: left;
  position: relative;
  background-color: #fff;
  border-radius: 6px;
  border-bottom: 2px solid #ddd;
  box-shadow: 0px 1px 2px #888;
}

.positionTitle {
  display: block;

  /* AttractionDetail로 이동 */
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 10px center;

  text-decoration: none;
  color: #fff;
  padding: 6px 32px 6px 8px;
  font-size: 12px;
  border-radius: 6px 6px 0 0;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  word-break: break-all;
}

.overlay_info .desc {
  padding: 14px;
  position: relative;
}

.overlay_info img {
  vertical-align: top;
}

.overlay_info .contentName {
  font-size: 12px;
  font-weight: bold;
  color: #333;
  position: absolute;
  left: 8px;
  top: 7px;
  text-align: center;
  white-space: normal;
}
</style>
