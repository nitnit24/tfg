import React from 'react';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import {Errors} from '../../common';
import * as actions from '../actions';
import {connect} from 'react-redux';

const initialState = {
    name: '',
    surname: '',
    email: '',
    phone: '',
    petiton: '',
    
    backendErrors: null
};

class ClientForm extends React.Component {
    
    constructor(props) {

        super(props);

        this.state = initialState;

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

        if (this.form.checkValidity()) {
            this.add();
        } else {
            this.setBackendErrors(null);
            this.form.classList.add('was-validated');
        }

    }

    add() {
        const roomType = {
            name : this.state.name.trim(),
            capacity : this.state.capacity.trim(),
            minPrice: this.state.minPrice.trim(),
            maxPrice: this.state.maxPrice.trim(),
        }
        
     //   this.props.addRoomType(roomType,  errors => this.setBackendErrors(errors))

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
                <div className=" border rounded p-4">
                    <h5>
                        <FormattedMessage id="project.saleRooms.ClientForm.title"/>
                    </h5>
                    <span> <small><em>* Los campos marcados con un asterisco (*) son obligatorios.</em> </small></span>
                    <div className="card-body">
                        <form   className="needs-validation" noValidate 
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
                                        required/>
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
                                        required/>
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
                                    <input type="text" id="petition" className="form-control" 
                                        value={this.state.petition}
                                        onChange={(e) => this.handlePetitionChange(e)}
                                        />
                                </div>
                            </div>
                            <div className="form-group row">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn btn-dark disabled" >
                                        <FormattedMessage id="project.global.buttons.booking"/>
                                    </button>
                                </div>
                            </div>
                            <span> <small><em> <FormattedMessage id='project.saleRooms.ClientForm.confirmation'/>{this.state.email}</em> </small></span>
                        </form>
                    </div>
                </div>
            </div>

        );

    }

}

ClientForm.propTypes = {
};

const mapDispatchToProps = {
}

export default connect(null, mapDispatchToProps)(ClientForm);