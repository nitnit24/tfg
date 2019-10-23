import React from 'react';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import {Errors} from '../../common';
import * as actions from '../actions';
import {connect} from 'react-redux';

const initialState = {
    name: '',
    capacity: '',
    minPrice: '',
    maxPrice: '',
    
    backendErrors: null
};

class RoomTypeForm extends React.Component {
    
    constructor(props) {

        super(props);

        this.state = initialState;

    }

    handleNameChange(event) {
        this.setState({name: event.target.value});
    }

    handleCapacityChange(event) {
        this.setState({capacity: event.target.value});
    }

    handleMinPriceChange(event) {
        this.setState({minPrice: event.target.value});
    }

    handleMaxPriceChange(event) {
        this.setState({maxPrice: event.target.value});
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
        
        this.props.addRoomType(roomType,  errors => this.setBackendErrors(errors))

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
                        <FormattedMessage id="project.roomtypes.RoomTypeForm.title"/>
                    </h5>
                    <div className="card-body">
                        <form ref={node => this.form = node}
                            className="needs-validation" noValidate 
                            onSubmit={(e) => this.handleSubmit(e)}>
                            <div className="form-group row">
                                <label htmlFor="name" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.name"/>
                                </label>
                                <div className="col-md-4">
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
                                <label htmlFor="capacity" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.capacity"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="number" id="capacity" className="form-control" 
                                        value={this.state.capacity}
                                        onChange={(e) => this.handleCapacityChange(e)}
                                        min= "1"
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="minPrice" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.minPrice"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="text" id="minPrice" className="form-control"
                                        value={this.state.minPrice}
                                        onChange={(e) => this.handleMinPriceChange(e)}
                                        min= "0"/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.min'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="maxPrice" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.maxPrice"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="text" id="maxPrice" className="form-control"
                                        value={this.state.maxPrice}
                                        onChange={(e) => this.handleMaxPriceChange(e)}
                                        min= "0"/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.min'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn btn-primary" >
                                        <FormattedMessage id="project.global.buttons.add"/>
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

RoomTypeForm.propTypes = {
    history: PropTypes.object.isRequired
};

const mapDispatchToProps = {
    addRoomType: actions.addRoomType
}

export default connect(null, mapDispatchToProps)(RoomTypeForm);