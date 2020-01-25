import React from 'react';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import {Errors} from '../../common';
import * as actions from '../actions';
import {connect} from 'react-redux';
import FileBase64 from 'react-file-base64';
import Image from'../image.png';

const initialState = {
    image: '',
    name: '',
    description:'',
    capacity: '',
    quantity:'',
    minPrice: '',
    maxPrice: '',
    image: '',
    
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

    handleDescriptionChange(event) {
        this.setState({description: event.target.value});
    }

    handleCapacityChange(event) {
        this.setState({capacity: event.target.value});
    }

    handleQuantityChange(event) {
        this.setState({quantity: event.target.value});
    }

    handleMinPriceChange(event) {
        this.setState({minPrice: event.target.value});
    }

    handleMaxPriceChange(event) {
        this.setState({maxPrice: event.target.value});
    }

    getFile(file){
        this.setState({ image: file})
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
        const imagen = this.state.image;
        const roomType = {
            name : this.state.name.trim(),
            description : this.state.description.trim(),
            capacity : this.state.capacity.trim(),
            quantity: this.state.quantity.trim(),
            minPrice: this.state.minPrice.trim(),
            maxPrice: this.state.maxPrice.trim(),
            image: this.state.image.base64
        }
        
        this.props.addRoomType( roomType,  errors => this.setBackendErrors(errors))

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
                            <div className = "row">
                            <div className=" col-7 ">
                            <div className="form-group row">
                                <label htmlFor="name" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.name"/>
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
                                <label htmlFor="description" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.description"/>
                                </label>
                                <div className="col-md-8">
                                    <textarea type="text" id="description" className="form-control"
                                        value={this.state.description}
                                        onChange={(e) => this.handleDescriptionChange(e)}
                                        autoFocus/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.required'/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="capacity" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.capacity"/>
                                </label>
                                <div className="col-md-8">
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
                                <label htmlFor="quantity" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.quantity"/>
                                </label>
                                <div className="col-md-8">
                                    <input type="number" id="quantity" className="form-control" 
                                        value={this.state.quantity}
                                        onChange={(e) => this.handleQuantityChange(e)}
                                        min= "0"
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
                                <div className="col-md-8">
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
                                <div className="col-md-8">
                                    <input type="text" id="maxPrice" className="form-control"
                                        value={this.state.maxPrice}
                                        onChange={(e) => this.handleMaxPriceChange(e)}
                                        min= "0"/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.min'/>
                                    </div>
                                </div>
                            </div>
                            {/* <div className="form-group row">
                                <label htmlFor="imagen" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.image"/>
                                </label>
                                <div className="col-md-8">
                                    <FileBase64 multiple={ false } onDone={ this.getFile.bind(this) } />
                                </div>
                            </div> */}
                            </div>
                            <div className="col-5">
                                <div className = "col-8">
                                    { (!this.state.image) ?
                                        <td> <img src = {Image}  className="img-thumbnail"  alt="Hab" /></td> 
                                             :
                                        <td> <img src={this.state.image.base64}  className="img-thumbnail"  alt="Hab" /></td> 
                                    }
                                </div>
                                <br/>
                                <div>
                                    <FileBase64 multiple={ false } onDone={ this.getFile.bind(this) } />
                                </div>
                            </div>    
                            {/* <div className="form-group row">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn btn-primary" >
                                        <FormattedMessage id="project.global.buttons.add"/>
                                    </button>
                                </div>
                            </div> */}
                            </div>
                            <br/> 
                            <div className = " row justify-content-center ">
                            <div className=" btn-group">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn btn-primary" >
                                        <FormattedMessage id="project.global.buttons.add"/>
                                    </button>
                                </div>
                                <div className="offset-md-3 col-md-1">
                                    <button type="button" className="btn btn-danger" 
                                   onClick={() => this.props.history.goBack()}>
                                        <FormattedMessage id="project.global.buttons.cancel"/>
                                    </button>
                                </div>
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