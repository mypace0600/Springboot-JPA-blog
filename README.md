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
