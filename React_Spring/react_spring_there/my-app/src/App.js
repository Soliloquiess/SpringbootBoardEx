
import { useState } from 'react';
import Sub from './Sub';
import './App.css';

//0.React엔진 - 데이터 변경 감지에서 UI그려주는
//1.실행과정(index.html)-SPA
//2.JSX문법

//(1) return시에 하나의 DOM만 리턴할 수 있다.
//(2) 자바스크립트는 let혹은 const로만 해야됨
//변수 선언 시 var을 안 쓰는게 좋다. let으로만 하는게 좋다.
// var a = 10;
//(3)if 사용 불가능 x->삼항연산자(조건?값(true):값(false))
//(4)조건부 렌더링(조건&&값(true))
//(5)css디자인
//-내부 적는법
//외부 적는법
//라이브러리 사용
let a = 10;//변수
const b = 20; //상수



function App() {

const [num, setNum] = useState(4);  //최초에 4
  const [users,setUsers] = useState([])
  const download=()=>{
    let sample=[
      {id:1, name:"홍길동"},
      {id:2, name:"김길정"},
      {id:3, name:"노길동"},
    ];
    //기존 데이터 세팅
    setUsers([...sample,{id:num,name:"조자룡"}]);
    // num++;
    setNum(num+1);
    // setUsers(sample)
  }
// // let number = 1; //상태값
// const [number,setNumber]=  useState(1)
// //상태 변경되는 조건이 useState사용
//   const add = ()=>{
//     // number++;
//     setNumber(number+1); //이제 number호출이 아닌 setnumber로 호출해야한다.
//     console.log('add',number);
//     //함수안에 함수 만들기 가능(1급객체이므로)
//   }

  //렌더링시점 - 상태값 변경
  return (
    <div>
    {/* <div>숫자{number}</div> */}
    <button onClick={download}> 다운 </button>
    {users.map((u)=>(
      <h1>
        {u.id}, {u.name}
      </h1>
    ))}
    {/* <Sub></Sub> */}
    </div>
  );
}

export default App;
