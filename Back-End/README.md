# Enjoy Trip

## 팀원

- 소속 : 서울 18반
- 팀원 : 박성준, 양우철

## 1. ER Diagram

![ER Diagram](/uploads/9f83853b26feb57e1226ade53873591b/image.png)

<br/>

## 2. DB

![DataBase](/uploads/7c1d52b55fb005b3c1293213602e25e8/image.png)

<br/>

## 3. REST API Test

### 관광지 (Attraction)

- 검색 조건에 따른 관광지 검색 (/attractions)
![관광지 검색](/uploads/bdedc1bfac62314b9d76631e78493086/image.png)
![관광지 검색](/uploads/cfea1f70e7cf1c60e88dc3f9139aaf2e/image.png)

<br/>

- 관광지 상세 정보 (/attractions/{content_id})
![관광지 상세 정보](/uploads/2c8ea75f3cbd928b9d2019704afa00af/image.png)
![관광지 상세 정보](/uploads/3ffd4ec437c9dd764ab13bdf8abc6c61/image.png)

<hr/>

### 사용자 (USER)

- 회원 가입 (/users)
![회원 가입](/uploads/1aeba80f96b7f9fb6fbb41c7b7f3d416/user_join1.PNG)
![회원 가입](/uploads/a409c99e189241fa9cbc1abdd1e6ca67/user_join2.PNG)

<br/>

- 로그인 (/users/login)
![로그인](/uploads/cc97f7fce541c9ccb2c1d2f0d2ea1890/user_login1.PNG)
![로그인](/uploads/ba5f5bd934690fa988d466358581b22f/user_login2.PNG)

<br/>

- 로그아웃 (/users/logout)
![로그아웃](/uploads/c11ba874f5ad07d19d8670463ca9bde5/user_logout1.PNG)
![로그아웃](/uploads/c1c11d37b73c784ffb9a882c01ecfc02/user_logout2.PNG)

<br/>

- 마이페이지 (/users/{id})
![마이페이지](/uploads/a82d04d588a51cb41ee3e969532cd2ae/user_mypage1.PNG)
![마이페이지](/uploads/5a53f0eccdbd2fc839de1ad8e387b044/user_mypage2.PNG)

<br/>

- 회원 정보 수정 (/users/{id})
![회원 정보 수정](/uploads/ea6168db070c499585f95bbb9eeb58bb/user_modify1.PNG)
![회원 정보 수정](/uploads/e9367f0407f1c69029d14bb1279986c2/user_modify2.PNG)

<br/>

- 회원 탈퇴 (/users/{id})
![회원 탈퇴](/uploads/238a3dc5a1b37723b38e5ff9490e8e0f/user_delete1.PNG)
![회원 탈퇴](/uploads/45da89603dd1cac745d6eaeba8073765/user_delete2.PNG)

<hr/>

### 게시판 (Board)

- 게시판 목록 (/articles)
![게시판 목록](/uploads/2eac7a732aaa983aaeeb74cec4a7cb2c/image.png)

<br/>

- 상세 보기 (/articles/{articleNo})
![상세 보기](/uploads/c6028d783d25c132169c0771435d5ad8/board_view1.PNG)
![상세 보기](/uploads/79c76d80b76c1a2450b63344eb6bd3ef/board_view2.PNG)

<br/>

- 글 쓰기 (/articles)
![글 쓰기](/uploads/7ef2aea4c6c79528e7d22062a1d6b9eb/board_write1.PNG)
![글 쓰기](/uploads/b6bae56030e0e34896fc51391edd66fb/board_write2.PNG)

<br/>

- 글 수정 (/articles)
![글 수정](/uploads/f6e2927250554a74835d941305c4b337/board_modify1.PNG)
![글 수정](/uploads/abeb0b8a7b7229dee3e638c0c640a267/board_modify2.PNG)

<br/>

- 글 삭제 (/articles/{articleNo})
![글 삭제](/uploads/3a1aa7eeddb7a68ac3c08b8aaa4f8530/board_delete1.PNG)
![글 삭제](/uploads/57f9b1f6033839634a22a8c472ebe12a/board_delete2.PNG)
