import React from 'react';
import {FormattedMessage, FormattedDate,  FormattedTime, FormattedNumber} from 'react-intl';
import * as selectors from '../selectors';

import {connect} from 'react-redux';

import * as actions from '../actions';
import BookingRoomItem from '../../saleRooms/components/BookingRoomItem';
import '../../styles.css';
import {BackLink} from '../../common';



class BookingDetails extends React.Component {

    componentDidMount() {
        const locator =this.props.match.params.locator;

        //if (!(locator)) {
            this.props.findBookingByLocator(locator);
        //}
    
    }

    
    componentWillUnmount() {
        this.props.clearBooking();
    }

    constructor(props) {

        super(props);

        this.state = {
            backendErrors: null,
           
        }

    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }

    render() {
        const booking = this.props.booking;

        if (!booking) {
            return null;
        }

        return (
            <div>
            <BackLink/>
            <div className=" border rounded p-4 bg-white letra">
                <div >
                {booking.state === "CONFIRMADA" &&
                    <h4 className= "text-center"><b>
                        <FormattedMessage id="project.saleRooms.BookingCompleted.title"/>
                    </b></h4>
                    }
                    {booking.state === "CANCELADA" &&
                    <h4 className= "text-center text-danger"><b>
                        <div className= "row  justify-content-center">
                            <i class="fa fa-exclamation-triangle" aria-hidden="true">  <FormattedMessage id="project.saleRooms.BookingCompleted.title2"/></i>
                        </div>
                    </b></h4>
                    }
                    &nbsp;
                    <div >
                    <div className="row justify-content-center">
                             <span> <FormattedMessage id="project.saleRooms.BookingCompleted.date"/></span>
                             <span>: <FormattedDate value={new Date(booking.date)}/> - <FormattedTime value={new Date(booking.date)}/></span> 
                        </div>
                        {booking.state === "MODIFICADA" &&
                            <div className="row justify-content-center">
                                <span> <FormattedMessage id="project.saleRooms.BookingCompleted.updatelDate"/></span>
                                <span>: <FormattedDate value={new Date(booking.updateDate)}/> - <FormattedTime value={new Date(booking.updateDate)}/></span> 
                            </div>
                        }
                        {booking.state === "CANCELADA" &&
                            <div className="row justify-content-center">
                                <span > <FormattedMessage id="project.saleRooms.BookingCompleted.cancelDate"/></span>
                                <span>: <FormattedDate value={new Date(booking.cancelDate)}/> - <FormattedTime value={new Date(booking.cancelDate)}/></span> 
                            </div>
                        }
                        <div className="row justify-content-center">
                            <h5> <FormattedMessage id="project.saleRooms.BookingCompleted.locator"/> </h5>
                            <h5>: {booking.locator}  </h5>
                        </div>
                    </div>
                </div>
                &nbsp;
                <div className="row">
                    <div className="col-6 border rounded p-4">
                        <div className= "card-header">
                            <h5 className= "text-center"><FormattedMessage id="project.saleRooms.BookingCompleted.clientData"/></h5>
                        </div>
                        <hr/>
                        <div className="row">
                            <span  className = "ml-5">  <FormattedMessage id="project.saleRooms.BookingCompleted.name"/></span>
                             <span>: {booking.name}</span>
                        </div>
                        <div className="row">
                            <span className = "ml-5"> <FormattedMessage id="project.saleRooms.BookingCompleted.surname"/></span>
                            <span>: {booking.surname}</span>
                        </div>
                        <div className="row">
                            <span className = "ml-5"> <FormattedMessage id="project.saleRooms.BookingCompleted.email"/></span>
                            <span>: {booking.email}</span>
                        </div>
                        <div className="row">
                             <span className = "ml-5"> <FormattedMessage id="project.saleRooms.BookingCompleted.phone"/></span>
                            <span>: {booking.phone}</span>
                        </div>
                    </div>
                    <div className="col-6 border rounded p-4">
                        <div className= "card-header">
                            <h5 className= "text-center"><FormattedMessage id="project.saleRooms.BookingCompleted.BookingData"/></h5>
                        </div>
                        <hr/>
                        <div className="row">
                             <span className = "ml-5"> <FormattedMessage id="project.global.fields.startDate"/></span>
                             <span>: <FormattedDate value={new Date(booking.startDate)}/></span> 
                        </div>
                        <div className="row">
                             <span className = "ml-5"> <FormattedMessage id="project.global.fields.endDate"/></span>
                             <span>: <FormattedDate value={new Date(booking.endDate)}/></span> 
                        </div>
                        <div className="row">
                             <span className = "ml-5"> <FormattedMessage id="project.saleRooms.BookingCompleted.duration"/></span>
                             <span>: {booking.duration}</span> 
                        </div>
                        <div className="row">
                            <span className = "ml-5"> <FormattedMessage id="project.saleRooms.ClientForm.comments"/></span>
                            { (booking.petition === '') &&
                                <span>: No hay comentarios</span>
                            }
                            { (booking.petition !== '') &&
                                <span>: {booking.petition}</span>
                            }
                        </div>
                    </div>     
                    </div>
                &nbsp;
                <div className="row">
                    <div className="col-12 border rounded p-4">
                        <div className= "card-header">
                            <h5 className= "text-center"><FormattedMessage id="project.saleRooms.BookingCompleted.rooms"/></h5>
                        </div>
                        <hr/>
                        <div>
                            {booking.bookingRooms !== undefined &&
                            <div>
                            {booking.bookingRooms.map(room =>
                                <BookingRoomItem  key={room.id} room={room} />
                            )}
                            </div>
                        }
                        </div>
                    </div>
                </div>
                &nbsp;
                <div>
                    <h5 className= "text-center">
                    <FormattedMessage id="project.saleRooms.BookingCompleted.total"/>&nbsp;
                    <FormattedNumber value={booking.totalPrice}/> €
                    </h5>
                </div>

            </div>
            </div>


        );

    }

}


const mapStateToProps = (state) => ({
    booking : selectors.getBooking(state)
});

const mapDispatchToProps = {
    findBookingByLocator: actions.findBookingByLocator,
    clearBooking: actions.clearBooking
};

export default connect (mapStateToProps, mapDispatchToProps)(BookingDetails);