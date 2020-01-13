import React from 'react';

import BookingsFindForm from './BookingsFindForm';
import BookingsFindResult from './BookingsFindResult';

const BookingsFind = ({history}) => (
    <div>
        <BookingsFindForm history={history}/>
        &nbsp;
       <BookingsFindResult history={history}/>
    </div>
);


export default BookingsFind;