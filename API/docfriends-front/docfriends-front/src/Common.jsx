import axios from 'axios';

class Common {
  static api = 'http://localhost:8080';
  static get = (path) => {
    return axios.get(`${Common.api}${path}`);
  };
  static post = (path) => {
    return axios.post(`${Common.api}${path}`);
  };
  static afterPost = (data, success, fail) => {
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
      if (success) success();
    } else {
      if (fail) fail();
    }
  };

  static unixTimeToDate = (time) => {
    var date = new Date(time * 1000);
    return `${date.getFullYear()}.${Common.numberTo2digit(
      date.getMonth() + 1,
    )}.${Common.numberTo2digit(date.getDate())}`;
  };
  static numberTo2digit = (number, space = '0') => {
    return `${number > 9 ? '' : space}${number}`;
  };
}

export default Common;
