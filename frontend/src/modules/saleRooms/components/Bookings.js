import React from 'react';
import {FormattedMessage} from 'react-intl';

import BookingsFindForm from './BookingsFindForm';
import SaleRoomItemList from './SaleRoomItemList';
import Total from './Total';
import {Link} from 'react-router-dom';

const SaleRoom = ({history}) => (
    <div>
        <BookingsFindForm history={history}/>
        &nbsp;
        <div className= "row  m-1 justify-content-end">
            <Link to="/booking/booking-find">
                <FormattedMessage id="project.global.buttons.showBooking"/>
            </Link>
        </div>
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