import React from 'react';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import {Errors} from '../../common';
import * as actions from '../actions';

const initialState = {
    name: '',
    code: '',
    backendErrors: null
};

class TariffForm extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState;

    }

    handleNameChange(event) {
        this.setState({name: event.target.value});
    }

    handleCodeChange(event) {
        this.setState({code: event.target.value});
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
        const tariff = {
            name : this.state.name.trim(),
            code : this.state.code.trim()
        }
        
        actions.addTariff(
            tariff, 
             () => this.props.history.push("/tariffs/tariff-management"),
           // () => this.props.history.push("/"),
            errors => this.setBackendErrors(errors)
            );

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
                <Errors errors={this.state.backendErrors}
                    onClose={() => this.handleErrorsClose()}/>
                <div className="card bg-light border-dark">
                    <h5 className="card-header">
                        <FormattedMessage id="project.tariffs.TariffForm.title"/>
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
                                <label htmlFor="code" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.code"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="text" id="code" className="form-control"
                                        value={this.state.code}
                                        onChange={(e) => this.handleCodeChange(e)}
                                        required/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
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

TariffForm.propTypes = {
    //history: PropTypes.object.isRequired
};

export default TariffForm;


