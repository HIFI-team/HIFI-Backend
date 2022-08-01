var infowindow = new kakao.maps.InfoWindow({zIndex:1});
// 주소-좌표 변환 객체 생성
var geocoder = new kakao.maps.services.Geocoder();

stores.forEach((el, index) => {
    geocoder.addressSearch(el.address_name, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            displayMarker(coords,el)
        }
    });
});

function displayMarker(coords,store) {
    // 마커를 생성하고 지도에 표시합니다.
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });
    kakao.maps.event.addListener(marker, 'mouseover', function() {
        // 마커를 마우스오버 이벤트가 발생하면 장소명이 인포윈도우에 표출됩니다.
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + store.place_name + '</div>');
        infowindow.open(map, marker);
    });
    kakao.maps.event.addListener(marker, 'mouseout', function() {
        // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
        infowindow.close();
    });
    kakao.maps.event.addListener(marker, 'click', function() {
        const roadFind="https://map.kakao.com/link/to/"+store.place_uid;
        window.open(roadFind)
    });
}