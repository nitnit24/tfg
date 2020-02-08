import React from 'react';
import {connect} from 'react-redux';

import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';


class BookingFindByLocatorForm extends React.Component {

    constructor(props) {
        super();
        
        this.state = {
            locator: ''
        };
    }
    componentDidMount() {
        this.props.clearBookingSearch();
    }


    handleLocatorChange(event) {
        this.setState({locator: event.target.value});
    }


    handleSubmit(event) {
        event.preventDefault();

        if (this.form.checkValidity()) {
            this.props.findBookingsByLocator(this.state.locator);
        } else {
            this.setBackendErrors(null);
           this.form.classList.add('was-validated');
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
                <Errors errors={this.state.backendErrors}
                    onClose={() => this.handleErrorsClose()}/>
                    <div className="container"> 
                        <form ref={node => this.form = node}
                            className="needs-validation" noValidate 
                            onSubmit={(e) => this.handleSubmit(e)}>
                                <div className="row justify-content-start">
                                    <div className= "col-2">
                                        <span> <FormattedMessage id="project.global.fields.locator"/></span>
                                    </div>
                                    <div className="col-9 input-group-prepend mb-2" >  
                                        <div class="input-group-prepend">
                                            <input type="text" id="locator" className="form-control "
                                                value={this.state.locator}
                                                onChange={(e) => this.handleLocatorChange(e)}
                                                autoFocus
                                                required/>
                                            <div className="invalid-feedback">
                                                <FormattedMessage id='project.global.validator.required'/>
                                            </div>
                                        </div>
                                    </div>                                
                                    <div className="col-md-1 ">  
                                            <button type="submit" className="btn btn-dark disabled" >
                                                <FormattedMessage id="project.global.buttons.find"/>
                                            </button>
                                    </div>
                                </div>
                        </form>
                    </div>
                </div>
       

        );

    }

}

const mapStateToProps = (state) => ({

    
});

const mapDispatchToProps = {
    findBookingsByLocator : actions.findBookingsByLocator,
    clearBookingSearch : actions.clearBookingSearch
};

export default connect(mapStateToProps, mapDispatchToProps)(BookingFindByLocatorForm);
