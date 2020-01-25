import React from 'react';
import {connect} from 'react-redux';

import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import  backend from '../../../backend';
import * as actions from '../actions';


const initialState = {
    locator: '',
    key: '',
    backendErrors: null
};

class BookingFindForm extends React.Component {

    constructor(props) {
        super();

        this.state = initialState;
 
    }

    handleLocatorChange(event) {
        this.setState({locator: event.target.value});
    }

    handleKeyChange(event) {
        this.setState({key: event.target.value});
    }


    handleSubmit(event) {

        event.preventDefault();

        if (this.form.checkValidity()) {
           this.find();
        } else {
            this.setBackendErrors(null);
           this.form.classList.add('was-validated');
        }

    }

    find() {
        backend.bookingService.findBookingByLocatorAndKey(this.state.locator, this.state.key,
            (booking) => {this.props.history.push('/booking/booking-details-client'),
                    this.props.booking(booking)},
                    errors => this.setBackendErrors(errors))
 
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }

    render() {

        return (
           
            <div>
            <Errors errors={this.state.backendErrors} handleClose={() => this.handleErrorsClose()}/>
           <div className="card bg-light border-dark">
               <h5 className="card-header">
                   <FormattedMessage id="project.roomtypes.BookingFindForm.title"/>
               </h5>
               <div className=" card-body">
                   <form ref={node => this.form = node}
                       className="needs-validation" noValidate 
                       onSubmit={(e) => this.handleSubmit(e)}>
                       <div className="form-group row">
                           <label htmlFor="locator" className="col-md-3 col-form-label">
                               <FormattedMessage id="project.global.fields.locator"/>
                           </label>
                           <div className="col-md-4">
                               <input type="text" id="locator" className="form-control"
                                   value={this.state.locator}
                                   onChange={(e) => this.handleLocatorChange(e)}
                                   autoFocus
                                   required/>
                               <div className="invalid-feedback">
                                   <FormattedMessage id='project.global.validator.required'/>
                               </div>
                           </div>
                       </div>
                       <div className="form-group row">
                           <label htmlFor="key" className="col-md-3 col-form-label">
                               <FormattedMessage id="project.global.fields.key"/>
                           </label>
                           <div className="col-md-4">
                               <input type="text" id="key" className="form-control" 
                                   value={this.state.key}
                                   onChange={(e) => this.handleKeyChange(e)}
                                   required/>
                               <div className="invalid-feedback">
                                   <FormattedMessage id='project.global.validator.required'/>
                               </div>
                           </div>
                       </div>
                      
                       <div className="form-group row">
                           <div className="offset-md-3 col-md-1">
                               <button type="submit" className="btn btn-primary" >
                                   <FormattedMessage id="project.global.buttons.find"/>
                               </button>
                           </div>
                       </div>
                   </form>
               </div>
           </div>
       </div>

        );

    }

}

const mapStateToProps = (state) => ({

    
});

const mapDispatchToProps = {
    booking: actions.booking

};

export default connect(mapStateToProps, mapDispatchToProps)(BookingFindForm);
