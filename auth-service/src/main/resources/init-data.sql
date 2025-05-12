-- 기존 데이터 삭제 (선택 사항)
DELETE FROM users;

-- 관리자 계정 추가
INSERT INTO users (username, password, roles)
VALUES ('admin', '1234', 'ROLE_ADMIN');

-- 일반 사용자 계정 추가
INSERT INTO users (username, password, roles)
VALUES ('user', '1234', 'ROLE_USER');