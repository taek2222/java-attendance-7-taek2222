# 출석 체크

## 기능 구현 목록 ⚙️

### ✅ 기능 목록 선택 출력
- [x] 오늘 날짜 기준 안내 메시지 출력 `오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.`
- [x] 기능 안내 메시지 출력
```
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
```
- [x] 기능 선택 입력 파싱
- [x] `예외처리` 기능 입력이 아닌 경우 예외가 발생한다 `잘못된 형식을 입력하였습니다.`

### ✅ 출석 파일 읽기 기능
- [x] attendances.csv 파일 읽기
- [x] 닉네임, 일정 파싱
  - [x] 새로운 크루인 경우 현재 일정 초기화
- [x] 일치하는 크루의 출석 일자 변경

### ✅ 닉네임 입력 및 파싱 기능
- [x] 닉네임 입력 안내 메시지 출력 및 파싱
- [x] `예외처리` 알맞은 크루의 이름이 아닌 경우 예외가 발생한다 `등록되지 않은 닉네임입니다.`

### ✅ 시간 입력 및 파싱 기능
- [x] 시간 입력 안내 메시지 출력 및 파싱
- [x] `예외처리` 캠퍼스 운영 시간이 아닌 경우 예외가 발생한다 `캠퍼스 운영 시간에만 출석이 가능합니다.` 
    -  캠퍼스 운영 시간 08:00~23:00
- [x] `예외처리` 잘못된 시간 입력인 경우 예외가 발생한다 `잘못된 형식을 입력하였습니다.`

### ✅ 출석 판정 기능
- [x] 시간에 따라 출석, 지각, 결석 판단
    - [x] 출석 시간 차이 계산 
      - 월요일은 (13:00~18:00), 화요일~금요일은 (10:00~18:00)
    - [x] 시작 시각 기준 5분 초과 지각
    - [x] 시작 시각 기준 30분 초과 결석
- [x] `예외처리` 교육 시간 이후 출석할 경우 예외가 발생한다 `교육 시간 이후는 출석이 불가능 합니다.`

### ✅ 출석 확인 기능
- [x] 닉네임과 등교 시간 입력 및 파싱
- [x] 크루 출석 확인 적용
- [x] 정상 출결 상황 출력
- [x] `예외처리` 이미 출석한 경우 예외가 발생한다 `이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.`
- [x] `예외처리` 주말 또는 공휴일인 경우 예외가 발생한다 `12월 14일 토요일은 등교일이 아닙니다.`
```
닉네임을 입력해 주세요.
이든
등교 시간을 입력해 주세요.
09:59

12월 05일 화요일 09:59 (출석)
```

### ✅ 출석 수정 기능
- [x] 닉네임 입력 및 파싱 `출석을 수정하려는 크루의 닉네임을 입력해 주세요.`
- [x] 수정하는 날짜(일) 입력 및 파싱 `수정하려는 날짜(일)를 입력해 주세요.`
- [x] 시간 입력 및 파싱 `언제로 변경하겠습니까?`
- [x] 날짜(일) 동일한 크루 출석 수정 
- [ ] 변경 전과 변경 후의 기록 출력 `12월 03일 화요일 10:07 (지각) -> 09:58 (출석) 수정 완료!`
- [ ] `예외처리` 잘못된 날짜(일) 입력할 경우 예외가 발생한다 `잘못된 형식을 입력하였습니다.`
- [ ] `예외처리` 미래 날짜를 수정한 경우 예외가 발생한다 `아직 수정할 수 없습니다.`
```
출석을 수정하려는 크루의 닉네임을 입력해 주세요.
빙티
수정하려는 날짜(일)를 입력해 주세요.
3
언제로 변경하겠습니까?
09:58

12월 03일 화요일 10:07 (지각) -> 09:58 (출석) 수정 완료!
```

### ✅ 크루별 출석 기록 확인 기능
- [ ] 닉네임 입력 및 파싱
- [ ] 크루 출석 기록 출력
- [ ] 출석, 지각, 결석 카운트 각각 출력
```
닉네임을 입력해 주세요.
빙티

이번 달 빙티의 출석 기록입니다.

12월 02일 월요일 13:00 (출석)
12월 03일 화요일 10:07 (지각)
12월 04일 수요일 10:02 (출석)
12월 05일 목요일 10:06 (지각)
12월 06일 금요일 10:01 (출석)
12월 09일 월요일 --:-- (결석)
12월 10일 화요일 10:03 (출석)
12월 11일 수요일 --:-- (결석)
12월 12일 목요일 --:-- (결석)
12월 13일 금요일 10:02 (출석)

출석: 3회
지각: 0회
결석: 3회

면담 대상자입니다.
```

### ✅ 제적 위험자 확인 기능
- [ ] 지각 및 결석 횟수 카운팅
- [ ] 횟수에 따라 경고, 면담, 제적 계산 (지각 3회 == 결석 1회)
    - 경고 대상자: 결석 2회 이상
    - 면담 대상자: 결석 3회 이상
    - 제적 대상자: 결석 5회 초과
- [ ] 전날까지의 크루 출석 기록을 바탕으로 제적 위험자 파악
- [ ] 제적 위험자 -> 면담 대상자 -> 경고 대상자 순서로 출력 
    - (지각 3회 == 결석 1회) + 결석 내림차순 정렬
    - 닉네임 오름차순 정렬
```
제적 위험자 조회 결과
- 빙티: 결석 3회, 지각 4회 (면담)
- 이든: 결석 2회, 지각 5회 (면담)
- 빙봉: 결석 1회, 지각 6회 (면담)
- 쿠키: 결석 2회, 지각 3회 (면담)
- 짱수: 결석 0회, 지각 6회 (경고)
```

### ✅ 실행 결과
```
오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
1

닉네임을 입력해 주세요.
이든
등교 시간을 입력해 주세요.
09:59

12월 13일 금요일 09:59 (출석)

오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
2

출석을 수정하려는 크루의 닉네임을 입력해 주세요.
빙티
수정하려는 날짜(일)를 입력해 주세요.
3
언제로 변경하겠습니까?
09:58

12월 03일 화요일 10:07 (지각) -> 09:58 (출석) 수정 완료!

오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
3

닉네임을 입력해 주세요.
빙티

이번 달 빙티의 출석 기록입니다.

12월 02일 월요일 13:00 (출석)
12월 03일 화요일 09:58 (출석)
12월 04일 수요일 10:02 (출석)
12월 05일 목요일 10:06 (지각)
12월 06일 금요일 10:01 (출석)
12월 09일 월요일 --:-- (결석)
12월 10일 화요일 10:08 (지각)
12월 11일 수요일 --:-- (결석)
12월 12일 목요일 --:-- (결석)

출석: 4회
지각: 2회
결석: 3회

면담 대상자입니다.

오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
4

제적 위험자 조회 결과
- 빙티: 결석 3회, 지각 2회 (면담)
- 이든: 결석 2회, 지각 4회 (면담)
- 쿠키: 결석 2회, 지각 2회 (경고)
- 빙봉: 결석 1회, 지각 5회 (경고)

오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
Q
```