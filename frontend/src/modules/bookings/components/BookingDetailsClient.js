import React from 'react';
import {FormattedMessage, FormattedDate,  FormattedTime, FormattedNumber} from 'react-intl';
import * as selectors from '../selectors';
import {Errors} from '../../common';

import {connect} from 'react-redux';

import  backend from '../../../backend';
import * as actions from '../actions';

import { confirmAlert } from 'react-confirm-alert'; 
import 'react-confirm-alert/src/react-confirm-alert.css' 
import '../../styles.css';

import BookingRoomItem from '../../saleRooms/components/BookingRoomItem';

class BookingDetailsClient extends React.Component {

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
                onClick: () => ( backend.bookingService.cancelBooking(this.props.bookingFound.locator,
                    this.props.bookingFound.key,

                (booking) =>{this.props.booking(booking), this.props.history.push('/booking/booking-details-client')},
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
            name : this.props.bookingFound.name,
            surname : this.props.bookingFound.surname,
            email : this.props.bookingFound.email,
            phone : this.props.bookingFound.phone,
            petition : this.props.bookingFound.petition,
            locator : this.props.bookingFound.locator,
            key : this.props.bookingFound.key
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

        const booking = this.props.bookingFound;
        const now = this.getCurrentDate();
        
        if (!booking) {
            return null;
        } 
        return (
            <div>
            <Errors errors={this.state.backendErrors} handleClose={() => this.handleErrorsClose()}/>
            <div className=" border rounded p-4 bg-white letra">
                <div >
                    {booking.state === "CONFIRMADA" &&
                    <h4 className= "text-center"><b>
                        <FormattedMessage id="project.saleRooms.BookingCompleted.title"/>
                    </b></h4>
                    }
                    {booking.state === "MODIFICADA" &&
                    <h4 className= "text-center"><b>
                        <FormattedMessage id="project.saleRooms.BookingCompleted.title3"/>
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
                                <span> <FormattedMessage id="project.saleRooms.BookingCompleted.updateDate"/></span>
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
        </div>
        );

    }

}


const mapStateToProps = (state) => ({
    bookingFound: selectors.getBooking(state)

});

const mapDispatchToProps = {
    booking: actions.booking,
    addClientData: actions.addClientData
    
};

export default connect (mapStateToProps, mapDispatchToProps)(BookingDetailsClient);