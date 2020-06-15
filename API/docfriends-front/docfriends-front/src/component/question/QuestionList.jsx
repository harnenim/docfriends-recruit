import React from 'react';
import { Link } from 'react-router-dom';
import Api from '../../Api';

import '../../css/common.css';
import '../../css/QuestionList.css';

class QuestionList extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      list: [],
    };

    this.getList(0);
  }

  getList(page) {
    Api.get(`/question/list?page=${page}`).then((resp) => {
      this.setState({ list: resp.data });
    });
  }

  render() {
    const { list } = this.state;

    return (
      <div className="question-list">
        <div className="header">
          <div>
            <button id="btnSearch"></button>
            <div className="title"></div>
            <button id="btnNotice"></button>
          </div>
        </div>
        <div className="body">
          {list.map((item) => (
            <Link to={`/question/${item.key}`}>
              <div className="item" key={`item-${item.key}`}>
                <div className="item-title">{item.title}</div>
                <div className="item-tag">
                  {item.tag
                    .split(',')
                    .map((tag) => {
                      return `#${tag}`;
                    })
                    .join(', ')}
                </div>
                <div className="item-content">{item.content}</div>
                {item.answercount ? (
                  <div key={`item-answer-${item.key}`} className="item-answer">
                    답변 {item.answercount}
                  </div>
                ) : null}
                <div className="item-date">{Api.unixTimeToDate(item.fdate)}</div>
              </div>
            </Link>
          ))}
        </div>
      </div>
    );
  }
}

export default QuestionList;
