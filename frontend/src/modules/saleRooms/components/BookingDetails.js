import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage, FormattedDate,  FormattedTime} from 'react-intl';
import * as selectors from '../selectors';

import {connect} from 'react-redux';


import  backend from '../../../backend';
import RoomsList from './RoomsList';

import '../../styles.css';



class BookingDetails extends React.Component {

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

        return (
            <div className=" border rounded p-4">
                <div >
                    <h4 className= "text-center"><b>
                        <FormattedMessage id="project.saleRooms.BookingCompleted.title"/>
                    </b></h4>
                    &nbsp;
                    <div >
                        <div className="row justify-content-center">
                             <span> <FormattedMessage id="project.saleRooms.BookingCompleted.date"/></span>
                             <span>: <FormattedDate value={new Date(this.props.lastBooking.date)}/> - <FormattedTime value={new Date(this.props.lastBooking.date)}/></span> 
                        </div>
                        <div className="row justify-content-center">
                            <h5> <FormattedMessage id="project.saleRooms.BookingCompleted.locator"/> </h5>
                            <h5>: {this.props.lastBooking.locator}  </h5>
                        </div>
                        <div className="row justify-content-center">
                            <h5>  <FormattedMessage id="project.saleRooms.BookingCompleted.key"/>  </h5>
                            <h5>: {this.props.lastBooking.key}  </h5>
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
                             <span>: {this.props.lastBooking.name}</span>
                        </div>
                        <div className="row">
                            <span className = "ml-5"> <FormattedMessage id="project.saleRooms.BookingCompleted.surname"/></span>
                            <span>: {this.props.lastBooking.surname}</span>
                        </div>
                        <div className="row">
                            <span className = "ml-5"> <FormattedMessage id="project.saleRooms.BookingCompleted.email"/></span>
                            <span>: {this.props.lastBooking.email}</span>
                        </div>
                        <div className="row">
                             <span className = "ml-5"> <FormattedMessage id="project.saleRooms.BookingCompleted.phone"/></span>
                            <span>: {this.props.lastBooking.phone}</span>
                        </div>
                    </div>
                    <div className="col-6 border rounded p-4">
                        <h5 className= "text-center"><FormattedMessage id="project.saleRooms.BookingCompleted.BookingData"/></h5>
                        <hr/>
                        <div className="row">
                             <span className = "ml-5"> <FormattedMessage id="project.global.fields.startDate"/></span>
                             <span>: <FormattedDate value={new Date(this.props.lastBooking.startDate)}/></span> 
                        </div>
                        <div className="row">
                             <span className = "ml-5"> <FormattedMessage id="project.global.fields.endDate"/></span>
                             <span>: <FormattedDate value={new Date(this.props.lastBooking.endDate)}/></span> 
                        </div>
                        <div className="row">
                             <span className = "ml-5"> <FormattedMessage id="project.saleRooms.BookingCompleted.duration"/></span>
                             <span>: {this.props.lastBooking.duration}</span> 
                        </div>
                        <div className="row">
                            <span className = "ml-5"> <FormattedMessage id="project.saleRooms.ClientForm.comments"/></span>
                            { (this.props.lastBooking.petition === '') &&
                                <span>: No hay comentarios</span>
                            }
                            { (this.props.lastBooking.petition !== '') &&
                                <span>: {this.props.lastBooking.petition}</span>
                            }
                        </div>
                    </div>
                </div>
                &nbsp;
                <div className="row justify-content-center">
                    <button type="button" className="btn  btn-link btn-sm"
                        onClick={() => backend.bookingService.cancelBooking(this.props.lastBooking.locator,
                        this.props.lastBooking.key)}>
                        <FormattedMessage id="project.global.buttons.bookingCancel"/>
                    </button>
                    |
                    <button type="button" className="btn  btn-link btn-sm"
                        onClick={() => this.handleToggleModal()}>
                        <FormattedMessage id="project.global.buttons.bookingUpdate"/>
                    </button> 
                </div>

            </div>


        );

    }

}


const mapStateToProps = (state) => ({
    startDate: selectors.getStartDate(state),
    endDate: selectors.getEndDate(state),
    rooms: selectors.getSummaryRooms(state),
    lastBooking: selectors.getLastBooking(state)

});

const mapDispatchToProps = {
    
};

export default connect (mapStateToProps, mapDispatchToProps)(BookingDetails);