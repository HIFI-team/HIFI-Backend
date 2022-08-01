// 지도 중심점 이동
function panTo(c){
    var moveLatLon={}
    if(c===1){
        moveLatLon = new kakao.maps.LatLng(37.5515814, 126.9249751);
    }else if(c===2){
        moveLatLon = new kakao.maps.LatLng(37.557527,126.9244669);
    }else if(c===3){
        moveLatLon = new kakao.maps.LatLng(37.5477028,126.9229477);
    }
    map.panTo(moveLatLon);
}

// 지도 확대
function zoomIn() {
    map.setLevel(map.getLevel() - 1);
}

// 지도 축소
function zoomOut() {
    map.setLevel(map.getLevel() + 1);
}