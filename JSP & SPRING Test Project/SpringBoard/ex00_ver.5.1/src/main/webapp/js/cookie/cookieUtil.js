/**
 * 쿠기 조작 함수 정의
 */
 
 // ********* 쿠키 생성하기 ********* //
 // function(쿠키이름,쿠키값, 지속기간-1000분의 1초, 저장위치)
 const setCookie = function(name,value, period, path){
    let date = new Date();
    date.setDate(date.getDate() + period);
    let Cookie = `${name}=encodeURIComponent(${value});Expires=${date.toUTCString()};path=${path}`
    document.cookie = Cookie;
}
 
 // ********* 쿠키 가져오기 ********* //
 const getCookie = function (name){
	// 쿠키 값을 가져온다. -> 유효기간이 지난것은 없는 것과 같다. 쿠키 이름이 같아야 한다. 현재 위치이거나 상위위치에 쿠키가 존재하고 있는다.
    let value = document.cookie.match(`(^|;) ?${name}=([^;]*)(;|$)`);
    return value ? value[2] : null;
}
 
 // ********* 쿠키 지우기 ********* //
const delCookie = function (name, path){
    let date = new Date();
    date.setDate(date.getDate() - 100);
    let Cookie = `${name}=;Expires=${date.toUTCString()};path=${path}`
    document.cookie = Cookie;
}