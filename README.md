파일명 : NaverSearch
네이버API 활용 검색코드.
ID, 비번은 삭제하였음.

파일명 : OpenAPI
공공데이터 (www.data.go.kr) 에서 API 를 이용하여
'국토교통부_아파트매매 실거래 상세 자료' 를 얻는 코드.
자료 주소, serviceKey 는 삭제하였음.
jsoup 의 select 함수의 인수가 한글일 때 함수 작동이 안 되었다.
parcing 을 하기 전 String 일 때의 자료 (xmlPage) 에서
찾으려는 한글 검색어를 replace 함수를 이용하여 영어로 치환하고 
select 함수를 사용하였더니 함수가 잘 작동이 되었다.
이번 데이터는 'CP949' 로 인코딩 되어있었다.

