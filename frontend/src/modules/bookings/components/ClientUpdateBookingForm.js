import React from 'react';
import {FormattedMessage} from 'react-intl';
import { Roller } from 'react-awesome-spinners';

import {Errors, Loader} from '../../common';
import * as actions from '../actions';
import {connect} from 'react-redux';

import  backend from '../../../backend';
import * as selectors from '../selectors';
import * as selectorsSaleRooms from '../../saleRooms/selectors'


class ClientUpdateBookingForm extends React.Component {
    
    constructor(props) {

        super(props);

        this.state = {
            name: props.clientData.name,
            surname: props.clientData.surname,
            email: props.clientData.email,
            phone: props.clientData.phone,
            petition: props.clientData.petition,
            loading: 0, 
            
            backendErrors: null
        }

    }

    handleNameChange(event) {
        this.setState({name: event.target.value});
    }

    handleSurnameChange(event) {
        this.setState({surname: event.target.value});
    }

    handleEmailChange(event) {
        this.setState({email: event.target.value});
    }

    handlePhoneChange(event) {
        this.setState({phone: event.target.value});
    }

    handlePetitionChange(event) {
        this.setState({petition: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        this.setState({loading: 1})
        if (this.form.checkValidity()) {
            this.updateBooking();
        } else {
            this.setBackendErrors(null);
            this.form.classList.add('was-validated');
        }
    }

    updateBooking() {
        const email = this.state.email.trim();
        const phone = this.state.phone.trim();
        const petition = this.state.petition.trim();
        const locator = this.props.clientData.locator;
        const key = this.props.clientData.key;
        
        console.log("updateBooking")
        backend.bookingService.updateBooking(this.props.rooms,this.props.startDate, this.props.endDate, locator, key,
             phone, email, petition,
             (booking) => {this.props.history.push('/booking/booking-details-client'),
                this.props.booking(booking)} ,      
             errors => this.setBackendErrors(errors))

    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }
    

    render() {
        const load = this.props.loading;
        return (  
            <div>
                 <Errors errors={this.state.backendErrors} handleClose={() => this.handleErrorsClose()}/>
                <div className=" border rounded p-4 bg-white">
                    <h5>
                        <FormattedMessage id="project.saleRooms.ClientForm.title"/>
                    </h5>
                    <span> <small><em>* Los campos marcados con un asterisco (*) son obligatorios.</em> </small></span>
                    <div className="card-body">
                        <form  ref={node => this.form = node}
                         className="needs-validation" noValidate 
                            onSubmit={(e) => this.handleSubmit(e)}>
                            <div className="form-group row">
                                <label className="col-md-3 col-form-label">
                                    <small>
                                        <FormattedMessage id="project.saleRooms.ClientForm.name"/>*
                                    </small>
                                </label>
                                <div className="col-md-8">
                                    <input type="text" id="name" className="form-control"  
                                        value={this.state.name}
                                        onChange={(e) => this.handleNameChange(e)}
                                        autoFocus
                                        required
                                        disabled="disabled"/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="surname" className="col-md-3 col-form-label">
                                    <small>
                                        <FormattedMessage id="project.saleRooms.ClientForm.surname"/>*
                                    </small>
                                </label>
                                <div className="col-md-8">
                                    <input type="text" id="surname" className="form-control" 
                                        value={this.state.surname}
                                        onChange={(e) => this.handleSurnameChange(e)}
                                        required
                                        disabled="disabled"/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="email" className="col-md-3 col-form-label">
                                    <small>
                                        <FormattedMessage id="project.saleRooms.ClientForm.email"/>*
                                    </small>
                                </label>
                                <div className="col-md-8">
                                    <input type="text" id="email" className="form-control"
                                        value={this.state.email}
                                        onChange={(e) => this.handleEmailChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="phone" className="col-md-3 col-form-label">
                                    <small>
                                        <FormattedMessage id="project.saleRooms.ClientForm.phone"/>*
                                    </small>
                                </label>
                                <div className="col-md-8">
                                    <input type="text" id="phone" className="form-control"
                                        value={this.state.phone}
                                        onChange={(e) => this.handlePhoneChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                          
                            <div className="form-group row">
                            <label htmlFor="comments" className="col-md-3 col-form-label">
                                    <small>
                                        <FormattedMessage id="project.saleRooms.ClientForm.comments"/>
                                    </small>
                            </label>
                            <div className="col-md-8">
                                    <textarea type="text" id="petition" className="form-control" 
                                        value={this.state.petition} 
                                        onChange={(e) => this.handlePetitionChange(e)}
                                        />
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="comments" className="col-md-3 col-form-label">
                                </label>
                                <div className="col-md-8">
                                <span> <small><em> 
                                    El establecimiento intentará atender sus peticiones, pero no podemos garantizarlo
                                </em> </small></span>
                                </div>
                            </div>
                            { (this.props.rooms.length !== 0) && 
                            <div className="form-group row">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn btn-dark disabled" >
                                        <FormattedMessage id="project.global.buttons.update"/>
                                    </button>
                                </div>
                                <br/>
                                <br/>
                            </div>
                            }
                            { ( load === 0) &&
                            <div >
                                 <Loader loading = "false" ></Loader>
                            </div>
                            }
                            <span> <small><em> <FormattedMessage id='project.saleRooms.ClientForm.confirmation'/>{this.state.email}</em> </small></span>
                        </form>
                    </div>
                </div>
            </div>

        );

    }

}

const mapStateToProps = (state) => ({
    startDate: selectorsSaleRooms.getStartDate(state),
    endDate: selectorsSaleRooms.getEndDate(state),
    rooms: selectorsSaleRooms.getRooms(state),
    clientData: selectors.getClientData(state)

});

const mapDispatchToProps = {
    booking: actions.booking,
    updateBooking: actions.updateBooking
}

export default connect(mapStateToProps, mapDispatchToProps)(ClientUpdateBookingForm);