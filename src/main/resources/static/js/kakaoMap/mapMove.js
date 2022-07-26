function panTo(c) {
    var moveLatLon=new Object()
    if(c===1){
        moveLatLon = new kakao.maps.LatLng(37.5515814, 126.9249751);
    }else if(c===2){
        moveLatLon = new kakao.maps.LatLng(37.557527,126.9244669);
    }else if(c===3){
        moveLatLon = new kakao.maps.LatLng(37.5477028,126.9229477);
    }
    map.panTo(moveLatLon);
}