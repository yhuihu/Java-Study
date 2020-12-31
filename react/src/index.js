import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import ExcelUtil from './excelUtil';

class Square extends React.Component {
    render() {
        return (
            <button
                className="square"
                onClick={() => this.props.onClick()}
            >
                {this.props.value}
            </button>
        );
    }
}

class Board extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            squares: Array(9).fill(null),
            count: 0
        };
    }

    handleClick(i) {
        const squares = this.state.squares.slice();
        let count = this.state.count;
        squares[i] = this.state.count % 2 === 0 ? 'X' : 'O';
        this.setState({count: ++count})
        this.setState({squares: squares});

    }

    renderSquare(i) {
        return (
            <Square
                value={this.state.squares[i]}
                onClick={() => this.handleClick(i)}
            />
        );
    }

    render() {
        let status = 'Next player: X';
        return (
            <div>
                <div className="status">{status}</div>
                <div className="board-row">
                    {this.renderSquare(0)}
                    {this.renderSquare(1)}
                    {this.renderSquare(2)}
                </div>
                <div className="board-row">
                    {this.renderSquare(3)}
                    {this.renderSquare(4)}
                    {this.renderSquare(5)}
                </div>
                <div className="board-row">
                    {this.renderSquare(6)}
                    {this.renderSquare(7)}
                    {this.renderSquare(8)}
                </div>
            </div>
        );
    }
}

class Game extends React.Component {

    render() {
        const initColumn = [{
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
            className: 'text-monospace',
        }, {
            title: '年级',
            dataIndex: 'grade',
            key: 'grade',
        }, {
            title: '部门',
            dataIndex: 'department',
            key: 'department',
        }];


        let attendanceInfoList = [
            {
                name:"张三",
                grade:"2017级",
                department:"前端部门"

            },
            {
                name:"李四",
                grade:"2017级",
                department:"程序部门"

            }];

        return (
            <div className="game">
                <div className="game-board">
                    <Board/>
                </div>
                <div className="game-info">
                    <div>{/* status */}</div>
                    <ol>{/* TODO */}</ol>
                </div>
                <input type='file' accept='.xlsx, .xls' onChange={(e)=>{ExcelUtil.importExcel(e)} }/>
                <button type="primary" onClick={() => {ExcelUtil.exportExcel(initColumn, attendanceInfoList,"人员名单.xlsx")}}>导出</button>
            </div>
        );
    }
}

ReactDOM.render(
    <Game/>,
    document.getElementById('root')
);

function calculateWinner(squares) {
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];
    for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return squares[a];
        }
    }
    return null;
}
