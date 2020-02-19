import React from 'react';
import {connect} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

import FileBase64 from 'react-file-base64';
import Image from'../image.png';

class RoomTypeUpdateForm extends React.Component {

    constructor(props) {

        super(props);

        this.state = {
            name: props.roomType.name,
            image: props.roomType.image,
            description: props.roomType.description,
            capacity: props.roomType.capacity,
            quantity: props.roomType.quantity,
            minPrice: props.roomType.minPrice,
            maxPrice: props.roomType.maxPrice,
            backendErrors: null
        };

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
        this.setState({ image: file.base64})
        console.log(file)
    }

    handleSubmit(event) {

        event.preventDefault();

        if (this.form.checkValidity()) {
            this.update();
        } else {
            this.setBackendErrors(null);
            this.form.classList.add('was-validated');
        }

    }

    update() {

        this.props.updateRoomType(
            {id: this.props.roomType.id,
            image: this.state.image,
            name: this.state.name.trim(),
            description: this.state.description.trim(),
            capacity: this.state.capacity,
            quantity: this.state.quantity,
            minPrice: this.state.minPrice,
            maxPrice: this.state.maxPrice,
           
        },
            () => this.props.history.push('/roomTypes/roomType-management'),
            errors => this.setBackendErrors(errors));
        
        console.log(this.state.image)
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
                <Errors errors={this.state.backendErrors} onClose={() => this.handleErrorsClose()}/>
                <div className="card bg-light border-dark">
                    <h5 className="card-header h5">
                        <FormattedMessage id="project.roomTypes.RoomTypeUpdateForm.title"/>
                    </h5>
                        <div className=" card-body">
                            <form ref={node => this.form = node} 
                                className="needs-validation" noValidate onSubmit={(e) => this.handleSubmit(e)}>
                                    <div className= "row">
                                        <div className = "col-7">  
                                            < div className="form-group row">
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
                                        <div className="form-group row h-20">
                                            <label htmlFor="description" className="col-md-3 col-form-label">
                                                <FormattedMessage id="project.global.fields.description"/>
                                            </label>
                                            <div className="col-md-8">
                                                <textarea type="text" id="name" className="form-control"
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
                                        //type="number"
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
                                        //type="number"
                                        value={this.state.maxPrice}
                                        onChange={(e) => this.handleMaxPriceChange(e)}
                                        min= "0"/>
                                        <div className="invalid-feedback">
                                            <FormattedMessage id='project.global.validator.min'/>
                                        </div>
                                 
                                </div>
                            </div>
                            
                            </div>
                            <div className="col-5">
                                <div className = "col-8">
                                    { (!this.state.image) ?
                                        <td> <img src = {Image}  className="img-thumbnail"  alt="Hab" /></td> 
                                             :
                                        <td> <img src={this.state.image}  className="img-thumbnail"  alt="Hab" /></td> 
                                    }
                                </div>
                                <br/>
                                <div>
                                    <FileBase64 multiple={ false } onDone={ this.getFile.bind(this) } />
                                </div>
                            </div>    
                        </div>
                        <br/>
                        <div className = " row justify-content-center ">
                        <div className=" btn-group">
                                <div className="offset-md-3 col-md-1">
                                    <button type="submit" className="btn-add">
                                        <FormattedMessage id="project.global.buttons.save"/>
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


const mapStateToProps = (state) => ({
    roomType : selectors.getRoomType(state)
});

const mapDispatchToProps = {
    updateRoomType: actions.updateRoomType,
    findRoomTypeById: actions.findRoomTypeById
};

export default connect(mapStateToProps, mapDispatchToProps)(RoomTypeUpdateForm);