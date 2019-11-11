import React from 'react';

import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import {Errors} from '../../common';

const initialState = {
    startDate: '',
    endDate: '',
    roomsNum: '',
    peopleNum: '',
    backendErrors: null
};

class BuyForm extends React.Component {

    constructor(props) {
        super();
    
        var today = new Date(),
                date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    
        //this.state = initialState;
        
        this.state = {
            date: date
        };

     
                  

    }

    handleStartDateChange(event) {
        this.setState({startDate: event.target.value});
    }

    handleEndDateChange(event) {
        this.setState({endDate: event.target.value});
    }

    handleRoomsNumChange(event) {
        this.setState({roomsNum: event.target.value});
    }

    handlePeopleNumChange(event) {
        this.setState({peopleNum: event.target.value});
    }


    handleSubmit(event) {

        event.preventDefault();

        if (this.form.checkValidity()) {
           this.findSaleRooms();
        } else {
            this.setBackendErrors(null);
            this.form.classList.add('was-validated');
        }

    }

    findSaleRooms() {

        // this.props.findSaleRooms(
        //     this.state.checkInDate.trim(),
        //     () => this.props.history.push(''),
        //     errors => this.setBackendErrors(errors));

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
                        <form ref={node => this.form = node}
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
                                                value={this.state.peopleNum} 
                                                onChange={(e) => this.handlePeopleNumChange(e)}
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
                                            {/* <input type="number" id="roomsNum"  className="form-control"
                                                value={this.state.roomsNum} 
                                                onChange={(e) => this.handleRoomsNumChange(e)}
                                                placeholder= "Rooms"
                                                min= "0" max = "7"
                                                required/> */}
                                            <select class="h-100 form-control " id="Rooms">
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

BuyForm.propTypes = {
    history: PropTypes.object.isRequired
};

export default BuyForm;
