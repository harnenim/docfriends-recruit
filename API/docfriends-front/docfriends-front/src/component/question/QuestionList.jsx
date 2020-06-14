import React from 'react';
import { Link } from 'react-router-dom';
import Api from '../../Api';

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
      <>
        <div className="header">
          <button id="btnFind">🔍</button>
          <div className="title">doctalk</div>
          <button id="btnNotice">🔔</button>
        </div>
        <div>
          {list.map((item) => (
            <Link to={`/question/${item.key}`}>
              <div className="item" key={`item-${item.key}`}>
                <div className="item-title">{item.title}</div>
                <div className="item-tag">{item.tag}</div>
                <div className="item-content">{item.content}</div>
                <div className="item-answer">{item.answercount}</div>
                <div className="item-date">{Api.unixTimeToDate(item.fdate)}</div>
              </div>
            </Link>
          ))}
        </div>
      </>
    );
  }
}

export default QuestionList;
