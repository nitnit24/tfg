import React from 'react';

import SaleRoomsFindForm from './SaleRoomsFindForm';
import SaleRoomItemList from './SaleRoomItemList';
import Total from './Total';

const SaleRoom = ({history}) => (
    <div>
        <SaleRoomsFindForm history={history}/>
        &nbsp;
        &nbsp;
        <div className = "row">
            <div className= "col-9">
                <SaleRoomItemList history={history}/> 
            </div>
            <div className= "col-3">
                <Total history={history}/> 
            </div>
        </div>
    </div>
);


export default SaleRoom;