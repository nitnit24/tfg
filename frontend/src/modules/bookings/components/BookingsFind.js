import React from 'react';

import BookingsFindForm from './BookingsFindForm';
import BookingFindByLocatorForm from './BookingFindByLocatorForm';

import BookingsFindResult from './BookingsFindResult';

import Tabs from 'react-bootstrap/Tabs';
import Tab from 'react-bootstrap/Tab';

import {FormattedMessage} from 'react-intl';

const BookingsFind = ({history}) => (
    <div>
        {/* <h5 className="card-header">
            <FormattedMessage id="project.bookings.BookingsFind.bookings"/>
        </h5> */}
         <h4 className="h4">
            <FormattedMessage id="project.bookings.BookingsFind.bookings"/>
        </h4>
        <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
            <Tab eventKey="home" title="Buscar por parámetros">
                <BookingsFindForm history={history}/>
            </Tab>
            <Tab eventKey="profile" title="Buscar por Localizador">
                <BookingFindByLocatorForm history={history}/>
            </Tab>
        </Tabs>
        &nbsp;
       <BookingsFindResult history={history}/>
    </div>
);


export default BookingsFind;