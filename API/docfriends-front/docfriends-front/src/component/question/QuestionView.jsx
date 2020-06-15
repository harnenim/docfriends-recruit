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
            <button id="btnBack"></button>
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
                <div className="question-date">상담일자: {Api.unixTimeToDate(item.fdate)}</div>
              </div>
            </div>
            <div className="question-content">
              {item.content
                ? item.content.split('\n').map((line) => (
                    <span>
                      {line}
                      <br />
                    </span>
                  ))
                : ''}
            </div>
            <div className="question-tag">
              {item.tag
                ? item.tag.split(',').map((tag) => {
                    return (
                      <>
                        <Link to={`/tag/${tag}`}>#{tag}</Link>{' '}
                      </>
                    );
                  })
                : null}
            </div>
            {item.source ? (
              <div className="question-source">
                <span>출처</span> <Link to={item.source}>{item.source}</Link>
              </div>
            ) : null}
          </div>
          <div className="answer-list">
            {item.answers
              ? item.answers.map((answer, index) => (
                  <div className="answer-item" key={`answer-item-${index}`}>
                    <div>{answer.answerer.name}님의 답변</div>
                    <div>{Api.unixTimeToDate(answer.fdate)}</div>
                    <div>{answer.content}</div>
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
