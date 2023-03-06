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


redis 관련 내용 참고 :
https://ssoco.tistory.com/19
https://inpa.tistory.com/entry/REDIS-%F0%9F%93%9A-Window10-%ED%99%98%EA%B2%BD%EC%97%90-Redis-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0#redis_%EC%8B%A4%ED%96%89%ED%95%98%EA%B8%B0

소스 다운시 redis 설정 필요!!

----

추가 수정 부분
1. 게시글 삭제 버튼 클릭시 alert 띄워서 한번 더 확인 - 3월 3일 ✔
2. 비밀글 기능 만들어서 작성한 사람만 접근 할 수 있도록 만들기(url 통한 접근 차단) - 3월 3일 완료 ✔
3. 조회수 중복 방지하기 - 3월 2일 완료 ✔

---
배포 계획
위치 : EC2
방법 : 
EC2 깃헙 다운
프로젝트 테스트
프로젝트 빌드
nohub으로 백그라운드 실행
오류 로그 남기기
서버 종료되면 cron으로 자동 재시작
