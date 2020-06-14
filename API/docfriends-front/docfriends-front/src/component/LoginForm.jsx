import React from 'react';

import CryptoJS from 'crypto-js';

import Common from '../../Common';

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
      let id = this.els.id.current.value;
      let pw = this.els.pw.current.value;
    };
  }

  login(id, pw) {
    this.runAfterGetEncryptKey((c, result, encrypt) => {
      let sha256 = CryptoJS.SHA256(result.salt1 + pw + result.salt2).toString(CryptoJS.enc.Base64);
      pw = encrypt.encrypt(sha256);

      //   Common.post('/member/login', { id: id, pw: pw }).then((result) => {});
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
