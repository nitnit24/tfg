import React from 'react';
import {FormattedMessage, FormattedDate,  FormattedTime, FormattedNumber} from 'react-intl';
import * as selectors from '../selectors';
import * as bookingSelectors from '../../bookings/selectors';

import {connect} from 'react-redux';

import  backend from '../../../backend';
import * as actions from '../actions';
import * as bookingActions from '../../bookings/actions';

import { confirmAlert } from 'react-confirm-alert'; 
import 'react-confirm-alert/src/react-confirm-alert.css' 
import '../../styles.css';

import BookingRoomItem from './BookingRoomItem';

class BookingCompleted extends React.Component {

    constructor(props) {

        super(props);

        this.state = {
            backendErrors: null
            
        }

    }

    deleteBookingNotification() {
        confirmAlert({
          title: 'Cancelar reserva',
          message: '¿Está seguro de que desea cancelar?',
          buttons: [
            {
              label: 'Si',
                onClick: () => ( backend.bookingService.cancelBooking(this.props.lastBooking.locator,
                    this.props.lastBooking.key,

                (booking) =>{this.props.booking(booking), this.props.history.push('/booking/booking-completed')},
                errors => this.setBackendErrors(errors)
                ))
              },
            {
              label: 'No',
            }
             ]
          })
        }

    handleClickUpdate(){
        const clientData = {
            name : this.props.lastBooking.name,
            surname : this.props.lastBooking.surname,
            email : this.props.lastBooking.email,
            phone : this.props.lastBooking.phone,
            petition : this.props.lastBooking.petition,
            locator : this.props.lastBooking.locator,
            key : this.props.lastBooking.key
        };
    
        this.props.addClientData(clientData),
        this.props.history.push('/saleRooms/find-saleRooms')
    
    }

    getCurrentDate(separator='-'){

        let newDate = new Date()
        let date = newDate.getDate();
        let month = newDate.getMonth();
        let year = newDate.getFullYear();

        
        let nowDay = new Date(year,month,date);
        let now = nowDay.getTime();
        return now;
    }
    
    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }

    render() {
        const now = this.getCurrentDate();
        const booking = this.props.lastBooking;

        return (
            <div className=" border rounded p-4">
                <div >
                    {booking.state === "CONFIRMADA" &&
                    <h4 className= "text-center"><b>
                        <FormattedMessage id="project.saleRooms.BookingCompleted.title"/>
                    </b></h4>
                    }
                    {booking.state === "CANCELADA" &&
                    <h4 className= "text-center"><b>
                        <FormattedMessage id="project.saleRooms.BookingCompleted.title2"/>
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
                                <span> <FormattedMessage id="project.saleRooms.BookingCompleted.cancelDate"/></span>
                                <span>: <FormattedDate value={new Date(booking.cancelDate)}/> - <FormattedTime value={new Date(booking.cancelDate)}/></span> 
                            </div>
                        }
                        <div className="row justify-content-center">
                            <h5> <FormattedMessage id="project.saleRooms.BookingCompleted.locator"/> </h5>
                            <h5>: {booking.locator}  </h5>
                        </div>
                        <div className="row justify-content-center">
                            <h5>  <FormattedMessage id="project.saleRooms.BookingCompleted.key"/>  </h5>
                            <h5>: {booking.key}  </h5>
                        </div>
                    </div>
                </div>
                &nbsp;
                <div className="row">
                    <div className="col-6 border rounded p-4">
                        <h5 className= "text-center"><FormattedMessage id="project.saleRooms.BookingCompleted.clientData"/></h5>
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
                        <h5 className= "text-center"><FormattedMessage id="project.saleRooms.BookingCompleted.BookingData"/></h5>
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
                             {booking.duration === 1 &&
                                <span>&nbsp;noche</span>
                             }
                              {booking.duration !== 1 &&
                                <span>&nbsp;noches</span>
                             }
                             
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
                        <h5 className= "text-center"><FormattedMessage id="project.saleRooms.BookingCompleted.rooms"/></h5>
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
                &nbsp;
                {((booking.state === "CONFIRMADA" || booking.state === "MODIFICADA") && (booking.startDate >= now )) &&
                <div className="row justify-content-center">
                    <button type="button" className="btn  btn-link btn-sm"
                        onClick={this.deleteBookingNotification.bind(this)}>
                        <FormattedMessage id="project.global.buttons.bookingCancel"/>
                    </button>
                    |
                    <button type="button" className="btn  btn-link btn-sm"
                             onClick={() => this.handleClickUpdate()}>
                        <FormattedMessage id="project.global.buttons.bookingUpdate"/>
                    </button> 
                </div>
                }

            </div>

        );

    }

}


const mapStateToProps = (state) => ({
    lastBooking: selectors.getLastBooking(state),
    clientData: bookingSelectors.getClientData(state)

});

const mapDispatchToProps = {
    booking: actions.booking,
    addClientData: bookingActions.addClientData
    
};

export default connect (mapStateToProps, mapDispatchToProps)(BookingCompleted);