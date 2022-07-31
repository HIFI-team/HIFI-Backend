// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커가 표시될 위치입니다
var markerPosition  = new kakao.maps.LatLng(37.5515814, 126.9249751);

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition,
    clickable: true
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

var iwContent = '<div style="padding:2px;">Hello World!</div>' // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    position: markerPosition,
    content : iwContent
});

kakao.maps.event.addListener(marker, 'click', function () {
    // 마커 위에 인포윈도우를 표시합니다
    infowindow.open(map, marker);
});