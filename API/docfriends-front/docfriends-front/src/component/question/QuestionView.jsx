import React from 'react';
import { Link } from 'react-router-dom';
import Api from '../../Api';

import '../../css/common.css';
import '../../css/QuestionView.css';

class QuestionView extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      item: [],
    };

    this.getItem();
  }

  getItem() {
    const { itemKey } = this.props;
    Api.get(`/question/item/${itemKey}`).then((resp) => {
      this.setState({ item: resp.data });
    });
  }

  render() {
    const { item } = this.state;

    return (
      <div className="question-view">
        <div className="header">
          <div>
            <button
              id="btnBack"
              onClick={() => {
                window.history.back();
              }}
            ></button>
            <div className="title">상담 내용</div>
            <button id="btnShare"></button>
            <button id="btnMenu"></button>
          </div>
        </div>
        <div className="body">
          <div className="question-item">
            <div className="question-header">
              <div className="question-icon"></div>
              <div className="question-title-date">
                <div className="question-title">{item.title}</div>
                <div className="question-date">
                  상담일자: {item && item.fdate ? Api.unixTimeToDate(item.fdate) : ''}
                </div>
              </div>
            </div>
            <div className="question-content">
              {item && item.content
                ? item.content.split('\n').map((line, index) => (
                    <span key={`question-content-line-${index}`}>
                      {line}
                      <br />
                    </span>
                  ))
                : ''}
            </div>
            <div className="question-tag">
              {item && item.tag
                ? item.tag.split(',').map((tag, index) => (
                    <Link to={`/tag/${tag}`} key={`question-tag-${index}`}>
                      #{tag}
                    </Link>
                  ))
                : null}
            </div>
            {item.source ? (
              <div className="question-source">
                <span>출처</span> <a href={item.source}>{item.source}</a>
              </div>
            ) : null}
          </div>
          <div className="answer-list">
            {item.answers
              ? item.answers.map((answer, index) => (
                  <div className="answer-item" key={`answer-item-${index}`}>
                    <div className="answer-header">
                      <div
                        className="answer-profile"
                        style={{ backgroundImage: `url(${answer.answerer.image})` }}
                      >
                        <div className="answer-profile-valid">
                          <div className="answer-profile-valid-inner"></div>
                        </div>
                      </div>
                      <div className="answer-title-date">
                        <div className="answer-title">{answer.answerer.name}님의 답변</div>
                        <div className="answer-date">
                          답변일자: {Api.unixTimeToDate(answer.fdate)}
                        </div>
                      </div>
                      <button className="btn-answer-menu"></button>
                    </div>
                    <div className="answer-content">
                      {answer.content.split('\n').map((line, index2) => (
                        <span key={`answer-item-${index}-content-line-${index2}`}>
                          {line}
                          <br />
                        </span>
                      ))}
                    </div>
                  </div>
                ))
              : null}
          </div>
        </div>
      </div>
    );
  }
}

export default QuestionView;
