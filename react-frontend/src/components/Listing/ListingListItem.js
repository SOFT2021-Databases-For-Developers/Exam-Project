import React from 'react';

//  Create an un-ordered list and then map each string item passed to it through props into a <li> element
const RenderWinnerHistory = (props) => {
    const makeList = props.makeList.map((item) =>
        <option key={item.name} value={item.name}>{item.name}</option>
    );
    return (
        {makeList}
    )
}

export default RenderWinnerHistory