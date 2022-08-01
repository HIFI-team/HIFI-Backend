// 장소 검색 객체를 생성합니다.
var ps = new kakao.maps.services.Places(map);

// 카테고리로 음식점을 검색합니다.
// 카페: CE7
ps.categorySearch('FD6', placesSearchCB, {useMapBounds:true});

// 키워드 검색 완료 시 호출되는 콜백함수 입니다.
function placesSearchCB (data, status, pagination) {
    console.log("callback")
    if (status === kakao.maps.services.Status.OK) {
        console.log(data)
    }
}