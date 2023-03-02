# Springboot-JPA-blog
참고 강의 :  https://www.youtube.com/watch?v=6bhF5o4gAOs&list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm

git bash로 커밋 푸시 하는 방법 : https://korin-learning.tistory.com/98

mac - 터미널로 mysql 사용하는 방법
1. mysql.server start
2. mysql -uroot -p
3. 비번입력


개발자도구 콘솔을 통한 글 삭제 시도 차단
ajax done 내용 관련 참고자료 : https://developyo.tistory.com/88


스프링 시큐리티 세션 동작 원리
로그인 요청시 username, password 가 들어오면
필터가 username, password를 가로 채서 authenticationManager에게 보낸다
authenticationManager는 UsernamePasswordAuthenticationToken을 만들어서
세션 쪽 SecurityContext 안에 저장해둔다.


----

추가 수정 부분
1. 게시글 삭제 버튼 클릭시 alert 띄워서 한번 더 확인
2. 비밀글 기능 만들어서 작성한 사람만 접근 할 수 있도록 만들기
3. 조회수 session 통해서 중복 방지하기
4. 작성일 기준, 조회수 기준으로 정렬할 수 있도록 기능 추가
