<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hifi</title>

</head>
<body>
<!-- 지도를 표시할 div 입니다 -->
<div id="map" style="width:100%;height:350px;"></div>
<p>
    <button onclick="panTo(2)">홍대입구역</button>
    <button onclick="panTo(1)">홍익대학교</button>
    <button onclick="panTo(3)">상수역</button>
</p>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f30a369520d453bd14ddd7805991b6db&libraries=services"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(37.5515814, 126.9249751), // 지도의 중심좌표
            level: 4 // 지도의 확대 레벨
        };

    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 마커가 표시될 위치입니다
    var markerPosition  = new kakao.maps.LatLng(37.5515814, 126.9249751);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);

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
</script>
</body>
</html>