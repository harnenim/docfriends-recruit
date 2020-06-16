import React from 'react';

import CryptoJS from 'crypto-js';

import Api from '../Api';

import '../css/common.css';
import '../css/LoginForm.css';

class LoginForm extends React.Component {
  constructor(props) {
    super(props);
    this.els = {
      email: React.createRef(),
      pw: React.createRef(),
    };
    this.state = {
      isCanLogin: false,
    };
  }

  checkCanLogin() {
    let email = this.els.email.current.value;
    let pw = this.els.pw.current.value;
    this.setState({
      isCanLogin: email.length && pw.length,
    });
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
    const { isCanLogin } = this.state;

    return (
      <div className="login-form">
        <div className="header">
          <div>
            <button
              id="btnBack"
              onClick={() => {
                window.history.back();
              }}
            ></button>
            <div className="title">로그인</div>
            <button id="btnJoin">회원가입</button>
          </div>
        </div>
        <div className="body">
          <form onSubmit={this.submitLogin().bind(this)}>
            <div>
              <input
                type="email"
                placeholder="이메일 주소를 입력해 주세요"
                ref={this.els.email}
                onChange={this.checkCanLogin.bind(this)}
              />
              <input
                type="password"
                placeholder="비밀번호를 입력해 주세요"
                ref={this.els.pw}
                onChange={this.checkCanLogin.bind(this)}
              />
              <button id="btnLoginProblem" type="button">
                로그인에 문제가 있으세요?
              </button>
            </div>
            <div className="sample">
              테스트 계정
              <br />
              ID: admin@test.com
              <br />
              PW: test
            </div>
            <div>
              <button type="submit" id="btnLogin" className={isCanLogin ? 'enabled' : ''}>
                로그인
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}

export default LoginForm;
