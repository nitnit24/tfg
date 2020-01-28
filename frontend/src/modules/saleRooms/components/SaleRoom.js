import React from 'react';
import {FormattedMessage} from 'react-intl';

import SaleRoomsFindForm from './SaleRoomsFindForm';
import SaleRoomItemList from './SaleRoomItemList';
import Total from './Total';
import {Link} from 'react-router-dom';
import Reserva from'../reserva.png';
import ModificaReserva from'../modificaReserva.png';
import * as bookingSelectors from '../../bookings/selectors';
import {connect } from 'react-redux';
import {withRouter} from 'react-router-dom';
import OldBookingSummary from './OldBookingSummary';

const SaleRoom = ({history, clientData}) => (
    <div>
        {/* {!clientData ?
        <div className= "row justify-content-center">
             <div className="col-3">
                <img src = {Reserva}  className="img-fluid  mx-auto" alt="Hab" />
             </div>
        </div>
        :
        <div>
        <div className= "row justify-content-center">
            <div className="col-2">
                <img src = {ModificaReserva} className="img-fluid  mx-auto" alt="Hab" />
            </div>
            <button type="button" className="btn btn-dangerr" 
                    onClick={() => history.goBack()}>
                        <FormattedMessage id='project.global.buttons.cancel'/>
                </button>
        </div>

        </div>
        } */}
        <SaleRoomsFindForm history={history}/>

        &nbsp;
        {(clientData === null) &&
        <div className= "row  m-1 justify-content-end">
            <Link to="/booking/booking-find">
                <FormattedMessage id="project.global.buttons.showBooking"/>
            </Link>
        </div>
        }
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


const mapStateToProps = (state) => ({
    clientData: bookingSelectors.getClientData(state)
});


export default withRouter(connect(mapStateToProps)(SaleRoom));