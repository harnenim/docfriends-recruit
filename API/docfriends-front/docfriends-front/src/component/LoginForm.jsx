import React from 'react';

import JSEncrypt from 'node-jsencrypt';
import CryptoJS from 'crypto-js';

import Api from '../Api';

class LoginForm extends React.Component {
  constructor(props) {
    super(props);
    this.els = {
      email: React.createRef(),
      pw: React.createRef(),
    };
  }

  submitLogin(login) {
    return (e) => {
      e.preventDefault();
      let email = this.els.email.current.value;
      let pw = this.els.pw.current.value;
      this.login(email, pw);
    };
  }

  login(email, pw) {
    pw = Api.SHA(pw).toString(CryptoJS.enc.Base64);
    let data = new FormData();
    data.append('email', email);
    data.append('pw', pw);
    Api.post('/member/login', data).then((result) => {
      console.log(result);
      Api.afterPost(result.data, (data) => {
        alert('success');
      });
    });
  }

  render() {
    return (
      <>
        <div className="header">
          <button id="btnBack">←</button>
          <div className="title">로그인</div>
          <button id="btnJoin">회원가입</button>
        </div>
        <div>
          <form onSubmit={this.submitLogin().bind(this)}>
            <div>
              <input type="email" placeholder="이메일 주소를 입력해 주세요" ref={this.els.email} />
              <input type="password" placeholder="비밀번호를 입력해 주세요" ref={this.els.pw} />
              <a href="#">로그인에 문제가 있으세요?</a>
            </div>
            <div>
              <button type="submit">로그인</button>
            </div>
          </form>
        </div>
      </>
    );
  }
}

export default LoginForm;
