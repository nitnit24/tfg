import React from 'react';

import BookingsFindForm from './BookingsFindForm';
import BookingsFindResult from './BookingsFindResult';

import {FormattedMessage} from 'react-intl';

const BookingsFind = ({history}) => (
    <div>
        {/* <h5 className="card-header">
            <FormattedMessage id="project.bookings.BookingsFind.bookings"/>
        </h5> */}
         <h4 className="h4">
            <FormattedMessage id="project.bookings.BookingsFind.bookings"/>
        </h4>
        <BookingsFindForm history={history}/>
        &nbsp;
       <BookingsFindResult history={history}/>
    </div>
);


export default BookingsFind;