-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- 생성 시간: 20-06-13 21:46
-- 서버 버전: 10.3.8-MariaDB
-- PHP 버전: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `docfriends`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `member`
--

CREATE TABLE `member` (
  `KEY` bigint(20) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(1023) NOT NULL,
  `NAME` varchar(63) NOT NULL,
  `IMAGE` varchar(255) NOT NULL,
  `FDATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '가입일시',
  `FIP` varchar(63) NOT NULL COMMENT '가입 시 IP',
  `LUSER` bigint(20) NOT NULL COMMENT '수정자',
  `LDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '수정일시',
  `LIP` varchar(63) NOT NULL COMMENT '수정한 IP'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='회원' ROW_FORMAT=COMPACT;

--
-- 테이블의 덤프 데이터 `member`
--

INSERT INTO `member` (`KEY`, `EMAIL`, `PASSWORD`, `NAME`, `IMAGE`, `FDATE`, `FIP`, `LUSER`, `LDATE`, `LIP`) VALUES
(1, 'admin@test.com', 'BpwtFZp/UIde8mCNU+8Iuvo3W5DFt7e7BICrLW+eFzuQKocewwerBLi1zDjSqMsx4k8HEdBxQhp/e3F7CCdJiQ==', '정동원 원장', '', '2020-06-13 12:16:54', '', 0, '2020-06-13 12:16:54', '');

-- --------------------------------------------------------

--
-- 테이블 구조 `question`
--
-- 매번 태그, 답변 테이블 join해서 구해오는 건 성능상 비효율이 있어서
-- 질문 수정 or 답변 등록/삭제 시 같이 update해주도록
--

CREATE TABLE `question` (
  `KEY` bigint(20) NOT NULL,
  `TITLE` varchar(255) NOT NULL,
  `CONTENT` longtext NOT NULL,
  `TAG` varchar(255) NOT NULL,
  `SOURCE` varchar(511) NOT NULL,
  `FUSER` bigint(20) NOT NULL COMMENT '작성자',
  `FDATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '작성일시',
  `FIP` varchar(63) NOT NULL COMMENT '작성 IP',
  `LUSER` bigint(20) NOT NULL COMMENT '수정자',
  `LDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '수정일시',
  `LIP` varchar(63) NOT NULL COMMENT '수정 IP',
  `ANSWER_COUNT` int(11) NOT NULL COMMENT '답변 수'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='질문' ROW_FORMAT=COMPACT;

--
-- 테이블의 덤프 데이터 `question`
--

INSERT INTO `question` (`KEY`, `TITLE`, `CONTENT`, `TAG`, `SOURCE`, `FUSER`, `FDATE`, `FIP`, `LUSER`, `LDATE`, `LIP`, `ANSWER_COUNT`) VALUES
(1, '수원풍덕천 37/남 교통사고 한의원치료, 계속 누워있는데 어쩌고저쩌고', '교통사고로 팔 수술을 하였습니다. 현재 깁스 하면서 현재 정형회과 입원중인데 계속 누워있으니온몸이 뻐근하고 목어깨 쪽에도 통증이 와서 퇴원 후 한의원을 다녀볼까 합니다. 접수번호만 있으면 어쩌고저쩌고 뒤쪽 잘릴 예정', '한방재활의학과', '', 0, '2019-09-05 12:45:20', '', 0, '2020-06-13 12:33:33', '', 1),
(2, '울산 23/여 여드름, 사용하던 선크림이 단종돼서 다른걸 썼는데 망한 듯한...', '선크림 영향인지 모르겠지만 최근 어쩔 수 없이 선크림 사용하던 걸 바꿨는데 이런 걸로 여드름이 생길 수 있나요??? 안 바르고 다닐 수도 없고 ㅠㅠ 그렇다고 이것저것 다 써볼수도 없구요 만약 어쩌고저쩌고 재잘재잘 아아아아아아', '한방피부과', '', 0, '2019-09-03 12:45:25', '', 0, '2020-06-13 12:33:38', '', 1),
(3, '수원 24/남 여드름, 피부가 건조하면 등여드름이 잘 생기나요?', '제가 얼굴이며 몸이며, 피부가 정말 악건성인데...등여드름이 정말 자주 생겨요.\r\n\r\n바디로션도 꾸준히 바르는데, 심하진 않아도 주기적으로 자잘하게 등여드름이 생기면 어떻게 해결해야 하는거죠? (수원 20대 중반/남 여드름)', '한방피부과', 'http://miraesol.com/mi...plyonlineview?id=9194', 0, '2019-09-02 12:45:29', '', 0, '2020-06-13 12:33:37', '', 1);

-- --------------------------------------------------------

--
-- 테이블 구조 `tag`
--

CREATE TABLE `tag` (
  `KEY` bigint(20) NOT NULL,
  `NAME` varchar(31) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `tag`
--

INSERT INTO `tag` (`KEY`, `NAME`) VALUES
(1, '한방재활의학과'),
(2, '한방피부과');

-- --------------------------------------------------------

--
-- 테이블 구조 `question_tag`
--
-- 실제 태그 검색할 경우를 생각해 관계 테이블 작성
-- 과제 중에는 활용하지 않음
--

CREATE TABLE `question_tag` (
  `KEY` bigint(20) NOT NULL,
  `QUESTION` bigint(20) NOT NULL COMMENT '어느 질문의',
  `NTH` int(11) NOT NULL COMMENT '몇 번째 태그',
  `TAG` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `question_tag`
--

INSERT INTO `question_tag` (`KEY`, `QUESTION`, `NTH`, `TAG`) VALUES
(1, 1, 0, 1),
(2, 2, 1, 2),
(3, 3, 1, 2);

-- --------------------------------------------------------

--
-- 테이블 구조 `answer`
--

CREATE TABLE `answer` (
  `KEY` bigint(20) NOT NULL,
  `QUESTION` bigint(20) NOT NULL COMMENT '어느 질문인지',
  `CONTENT` longtext NOT NULL,
  `FUSER` bigint(20) NOT NULL,
  `FDATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `FIP` varchar(63) NOT NULL,
  `LUSER` bigint(20) NOT NULL,
  `LDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LIP` varchar(63) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='답변';

--
-- 테이블의 덤프 데이터 `answer`
--

INSERT INTO `answer` (`KEY`, `QUESTION`, `CONTENT`, `FUSER`, `FDATE`, `FIP`, `LUSER`, `LDATE`, `LIP`) VALUES
(1, 3, '안녕하세요, 닥톡-네이버 지식iN 상담한의사 정동원입니다.\r\n\r\n피부가 건조하면 모공의 입구가 잘 막히게 됩니다.\r\n\r\n모공의 입구가 막혀버리면 자잘한 좁쌀여드름이 되는 경우가 있습니다.\r\n\r\n이런 분들이 건조한 피부인데도 좁쌀여드름이 생기는 타입입니다.\r\n\r\n바디로션이든 알로에젤이나 수딩젤 같은 보습제를 잘 발라주시는 것은 좋습니다.\r\n\r\n하지만 이미 생긴 등여드름은 절대 뜯지 마세요\r\n\r\n자국이나 흉이 생기기 때문입니다.\r\n\r\n자잘한 등여드름은 압출해서 없애는 방법도 있습니다.\r\n\r\n단시간이 빨리 정리할 수 있기 때문입니다.\r\n\r\n그외에 땀흘리는 것이 원인일 수 있으므로, 매일 샤워를 해주시는 것도 예방에 도움이 됩니다.\r\n\r\n감사합니다.', 1, '2019-09-05 12:45:13', '', 1, '2020-06-13 12:44:54', '');

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `member`
--
-- 이메일은 중복되지 않아야 함
-- 이름으로 검색할 경우도 고려
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`KEY`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`),
  ADD KEY `NAME` (`NAME`);

--
-- 테이블의 인덱스 `question`
-- 
-- 질문 등록자별 검색 고려
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`KEY`),
  ADD KEY `FUSER` (`FUSER`);

--
-- 테이블의 인덱스 `tag`
--
-- 태그 이름으로 검색 필요
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`KEY`),
  ADD UNIQUE KEY `NAME` (`NAME`);

--
-- 덤프된 테이블의 AUTO_INCREMENT

--
-- 테이블의 인덱스 `question_tag`
--
ALTER TABLE `question_tag`
  ADD PRIMARY KEY (`KEY`),
  ADD KEY `QUESTION` (`QUESTION`,`NTH`);
--

--
-- 테이블의 인덱스 `answer`
--
-- 질문에 따른 답변 검색 인덱스 필요
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`KEY`),
  ADD KEY `QUESTION` (`QUESTION`);

--
-- 테이블의 AUTO_INCREMENT `answer`
--
ALTER TABLE `answer`
  MODIFY `KEY` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 테이블의 AUTO_INCREMENT `member`
--
ALTER TABLE `member`
  MODIFY `KEY` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 테이블의 AUTO_INCREMENT `question`
--
ALTER TABLE `question`
  MODIFY `KEY` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 테이블의 AUTO_INCREMENT `question_tag`
--
ALTER TABLE `question_tag`
  MODIFY `KEY` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 테이블의 AUTO_INCREMENT `tag`
--
ALTER TABLE `tag`
  MODIFY `KEY` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
