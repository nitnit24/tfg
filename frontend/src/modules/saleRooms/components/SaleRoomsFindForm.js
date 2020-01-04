import React from 'react';
import {connect} from 'react-redux';

import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import {Errors} from '../../common';
import  backend from '../../../backend';
import * as actions from '../actions';


const initialState = {
    startDate: '',
    endDate: '',
    roomsNum: '',
    peopleNum: '',
    backendErrors: null
};

class SaleRoomsFindForm extends React.Component {

    constructor(props) {
        super();
    
        var today = new Date(),
                date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    
        //this.state = initialState;
        
        this.state = {
            date: date,
            startDate: '',
            endDate: '',
            rooms: '1',
            people: '1',
            date: date,
            backendErrors: null
        };
    }

    handleStartDateChange(event) {
        this.setState({startDate: event.target.value});
    }

    handleEndDateChange(event) {
        this.setState({endDate: event.target.value});
    }

    handleRoomsChange(event) {
        this.setState({rooms: event.target.value});
    }

    handlePeopleChange(event) {
        this.setState({people: event.target.value});
    }


    handleSubmit(event) {

        event.preventDefault();

        //if (this.form.checkValidity()) {
           this.findFreeRooms();
        //} else {
        //    this.setBackendErrors(null);
        //   this.form.classList.add('was-validated');
        //}

    }

    findFreeRooms() {
        this.props.cleanFreeRoomTypes();
        backend.bookingService.findFreeRooms(this.state.startDate, this.state.endDate, 
            this.state.people, this.state.rooms, 
            roomTypes =>  this.props.addFreeRoomTypes(roomTypes))
        
        this.props.addStartDate(this.state.startDate);
        this.props.addEndDate(this.state.endDate);
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }


    getCurrentDate(separator='-'){

        let newDate = new Date()
        let date = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();
     
        return `${year}${separator}${month<10?`0${month}`:`${month}`}${separator}${date<10?`0${date}`:`${date}`}`
    }

    getTomorrowDate(separator='-'){

        let newDate = new Date()
        let date = newDate.getDate() +1;
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();
     
        return `${year}${separator}${month<10?`0${month}`:`${month}`}${separator}${date<10?`0${date}`:`${date}`}`
    }

    render() {
        let today = this.getCurrentDate();
        let tomorrow = this.getTomorrowDate();
        return (
           

            <div className=" border rounded p-4">
                <Errors errors={this.state.backendErrors}
                    onClose={() => this.handleErrorsClose()}/>
                    <div className="container"> 
                        <form
                            className="needs-validation" noValidate 
                            onSubmit={(e) => this.handleSubmit(e)}>
                                <div className="row justify-content-start">
                                    <div className="col-3 input-group mb-2" >  
                                        <div class="input-group-prepend">
                                            <div className= "input-group-text" >
                                                <span className="fas fa-calendar-alt" ></span>
                                            </div>
                                            <input type="date" id="startDate" className="form-control "
                                                value={this.state.startDate} placeholder= "20-11-2019"
                                                onChange={(e) => this.handleStartDateChange(e)}
                                                autoFocus
                                                min = {today}
                                                required/>
                                        </div>
                                    </div>
                                    
                                    <div className="col-3 input-group mb-2" >  
                                        <div class="input-group-prepend">
                                            <div className= "input-group-text" >
                                                <span className="fas fa-calendar-alt" ></span>
                                            </div>
                                            <input type="date" id="endDate" className="form-control "
                                                value={this.state.endDate} placeholder="Check Out"
                                                onChange={(e) => this.handleEndDateChange(e)}
                                                autoFocus
                                                min= {tomorrow}
                                                required/>
                                        </div>
                                    </div>
                                    <div className="col-2 input-group mb-2" >  
                                        <div class="input-group-prepend">
                                            <div className= "input-group-text" >
                                                <span className="fas fa-user-friends" ></span>
                                            </div>
                                            <input type="number" id="peopleNum"  className=" form-control "
                                                value={this.state.people} 
                                                onChange={(e) => this.handlePeopleChange(e)}
                                                placeholder= "0"
                                                min= "0" max ="15"
                                                required/>  
                                        </div>
                                    </div>
                                    <div className="col-3 input-group mb-2" >  
                                        <div class="input-group-prepend">
                                            <div className= "input-group-text" >
                                                <span className="fas fa-bed" ></span>
                                            </div>
                                            <select class="h-100 form-control " id="Rooms"
                                            value={this.state.rooms} onChange={(e) => this.handleRoomsChange(e)}>
                                                <option value="1">1 Habitación</option>
                                                <option value="2">2 Habitaciones</option>
                                                <option value="3">3 Habitaciones</option>
                                                <option value="4">4 Habitaciones</option>
                                            </select>
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
    addFreeRoomTypes: actions.addFreeRoomTypes,
    cleanFreeRoomTypes: actions.cleanFreeRoomTypes,
    addStartDate: actions.addStartDate,
    addEndDate: actions.addEndDate

};

export default connect(mapStateToProps, mapDispatchToProps)(SaleRoomsFindForm);
