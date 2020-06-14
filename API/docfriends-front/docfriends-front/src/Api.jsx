import axios from 'axios';

import CryptoJS from 'crypto-js';

const Api = axios.create({
  baseURL: 'http://localhost:8080/',
});

Api.afterPost = (data, success, fail) => {
  var message = [];
  if (data.error) {
    for (var i = 0, error; (error = data.error[i]); i++) {
      message.push(error);
    }
  }
  if (message.length > 0) {
    alert('다음과 같은 문제가 발생했습니다.\n' + message.join('\n'));
  }

  if (data.success) {
    if (success) success(data);
  } else {
    if (fail) fail(data);
  }
};
Api.unixTimeToDate = (time) => {
  var date = new Date(time * 1000);
  return `${date.getFullYear()}.${Api.numberTo2digit(date.getMonth() + 1)}.${Api.numberTo2digit(
    date.getDate(),
  )}`;
};
Api.numberTo2digit = (number, space = '0') => {
  return `${number > 9 ? '' : space}${number}`;
};

Api.SHA = (pw) => {
  return CryptoJS.SHA256(
    '서버 관리자도 RSA 복호화를 해도 알 수 없도록 함' +
      pw +
      'DB에는 랜덤 생성 salt를 붙인 후 SHA 1회 추가로 돌림',
  );
};

export default Api;
