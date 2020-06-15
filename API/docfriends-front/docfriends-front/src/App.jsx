import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Link } from 'react-router-dom';

import QuestionList from './component/question/QuestionList';
import QuestionView from './component/question/QuestionView';
import LoginForm from './component/LoginForm';

import './App.css';

// import 'react-app-polyfill/ie9';
// import 'react-app-polyfill/stable';

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/question">
          {() => {
            var path = window.location.href;
            path = path.substring(path.indexOf('/question') + 9);
            if (path.indexOf('/' >= 0)) {
              var key = path.substring(path.indexOf('/') + 1);
              console.log('key????');
              console.log(key);
              if (key && isFinite(key)) {
                // 질문 내용
                return <QuestionView itemKey={path.substring(path.indexOf('/' + 1))} />;
              }
            }
            // 질문 목록
            return <QuestionList />;
          }}
        </Route>
        <Route path="/login">
          <LoginForm />
        </Route>
        <Route path="*">
          <div className="index">
            <Link to="/login">로그인</Link>
            <br />
            <Link to="/question">질문</Link>
          </div>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
