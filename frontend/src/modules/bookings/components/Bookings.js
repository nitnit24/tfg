import React from 'react';
import PropTypes from 'prop-types';

import {FormattedMessage, FormattedDate, FormattedNumber} from 'react-intl';
import {Link} from 'react-router-dom';

const Bookings = ({bookings}) => (

    <table className="table  table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.locator'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.name'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.startDate'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.endDate'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.rooms'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.bookingDate'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.state'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.totalPrice'/>
                </th>
            </tr>
        </thead>

        <tbody>
            {bookings.map(booking => 
                <tr>
                    <td> 
                        <Link to={`/booking/booking-details/${booking.locator}`}>
                            {booking.locator}
                        </Link>     
                    </td>
                    <td>{booking.name} {booking.surname} </td>
                    <td>  <FormattedDate value={new Date(booking.startDate)}/></td>
                    <td>  <FormattedDate value={new Date(booking.endDate)}/></td>
                    <td>{booking.bookingRooms.map(room =>
                        <div className="row">{room.quantity}&nbsp;x&nbsp;{room.roomTypeName}</div>
                    )}
                    </td>
                    <td>  <FormattedDate value={new Date(booking.date)}/></td>
                    { booking.state === 'MODIFICADA' &&
                        <td className = "td-find-modificada">{booking.state}</td>
                    }
                    { booking.state === 'CONFIRMADA' &&
                        <td className = "td-find-confirmada">{booking.state}</td>
                    }
                    { booking.state === 'CANCELADA' &&
                        <td className = "td-find-cancelada">{booking.state}</td>
                    }
                    
                    <td><FormattedNumber value={booking.totalPrice}/>€</td>
                </tr>
            )}
        </tbody>

    </table>

);

Bookings.propTypes = {
    bookings: PropTypes.array.isRequired
};

export default Bookings;