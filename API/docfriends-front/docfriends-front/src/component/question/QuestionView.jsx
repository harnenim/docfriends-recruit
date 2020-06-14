import React from 'react';
import { Link } from 'react-router-dom';
import Api from '../../Api';

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
      <>
        <div className="header">
          <button id="btnBack">←</button>
          <div className="title">상담 내용</div>
          <button id="btnShare">↑</button>
          <button id="btnMenu">:</button>
        </div>
        <div>
          <div>{item.title}</div>
          <div>{Api.unixTimeToDate(item.fdate)}</div>
          <div>{item.content}</div>
          <div>
            <Link to={`/tag/${item.tag}`}>{item.tag}</Link>
          </div>
          {item.source ? <div>{item.source}</div> : null}
          {item.answers
            ? item.answers.map((answer) => {
                console.log(answer);
                return (
                  <div>
                    <div>{answer.answerer.name}님의 답변</div>
                    <div>{Api.unixTimeToDate(answer.fdate)}</div>
                    <div>{answer.content}</div>
                  </div>
                );
              })
            : null}
        </div>
      </>
    );
  }
}

export default QuestionView;
