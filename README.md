# Instagram Clone Coding (backend)

- [instagram-frontend](https://github.com/moon9ua/instagram-frontend)
- 기간 : 2021. 09
- 사용 기술 : Java 11, Spring Boot (gradle), JPA, MySQL
- 웹 개발 프레임워크로 Java Spring 을 많이 사용하는데 어떤 프레임워크인지 이번 클론 코딩을 통해 경험하고 공부해볼 수 있었다.
- [velog](https://velog.io/@h000/%EC%9D%B8%EC%8A%A4%ED%83%80%EA%B7%B8%EB%9E%A8-%ED%81%B4%EB%A1%A0-%EC%BD%94%EB%94%A9)

## API
서버 실행 후, http://localhost:8080/swagger-ui/ 에서 확인할 수 있다.

### User
- 유저 생성, 수정, 조회, 삭제 기능

### Auth
- 로그인 기능 (Spring Security + JWT 적용)

### Post
- 포스트 생성, 삭제 기능
- 포스트 like, unlike 기능
- 특정 유저가 올린 모든 포스트 조회
- 피드 조회 (내가 팔로우한 사람들의 포스트)

### Comment
- 댓글 생성, 삭제 기능
- 댓글 like, unlike 기능
- 포스트의 모든 댓글 조회
- 대댓글 조회

### Follow
- 특정 유저 follow, unfollow 기능
- 내 followers 조회
- 내 followings 조회


## 나중에 개선할 점
- AWS EC2로 클라우드 서버 배포
- JWT refresh token 적용하기
  - access token의 유효 기간이 너무 길면 token을 탈취 당할 경우 보안에 취약해 짧게 하는 것이 좋은데 이를 보완하기 위해 refresh token을 도입할 필요가 있다.
  - 처음 로그인 했을 때 refresh token을 같이 발급하고, access token이 만료되었을 때 새로 발급해줌
  - refresh token의 유효 기간이 지나면 새로 로그인 해야함
- 회원 가입시 이메일을 보내 인증 완료시 계정 활성화
  - 지금은 아무 email이나 써도 가입 가능
- 페이징
  - 지금은 모든 이미지를 한번에 불러오게 구현되어 있음
- 이미지 서버
- DM 기능
