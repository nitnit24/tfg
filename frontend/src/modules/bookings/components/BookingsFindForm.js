import React from 'react';
import {connect} from 'react-redux';

import {FormattedMessage} from 'react-intl';

import {Errors} from '../../common';
import * as actions from '../actions';


class BookingsFindForm extends React.Component {

    constructor(props) {
        super();
        
        this.state = {
            dateType: 'Entrada',
            minDate: '',
            maxDate: '',
            keywords: ''
        };
    }

    handleMinDateChange(event) {
        this.setState({minDate: event.target.value});
    }

    handleMaxDateChange(event) {
        this.setState({maxDate: event.target.value});
    }

    handleDateTypeChange(event) {
        this.setState({dateType: event.target.value});
    }

    handleKeywordsChange(event) {
        this.setState({keywords: event.target.value});
    }


    handleSubmit(event) {
        const criteria= {
            dateType : this.state.dateType,
            minDate: this.state.minDate,
            maxDate: this.state.maxDate,
            keywords: this.state.keywords.trim(),
            page: 0
        };
        console.log(this.state.dateType)
        console.log(this.state.minDate)
        console.log(this.state.maxDate),
        console.log(this.state.keywords)
        event.preventDefault();
        this.props.findBookings(criteria);
    
    }

    getCurrentDate(){
        const separator='-';
        
        let newDate = new Date(this.state.minDate);
        let date = newDate.getDate() + 1;
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();

        return `${year}${separator}${month<10?`0${month}`:`${month}`}${separator}${date<10?`0${date}`:`${date}`}`
    }

    render() {
        var untilMin = this.getCurrentDate();

        return (
            <div className=" border rounded p-4">
                <Errors errors={this.state.backendErrors}
                    onClose={() => this.handleErrorsClose()}/>
                    <div className="container"> 
                        <form
                            className="needs-validation" noValidate 
                            onSubmit={(e) => this.handleSubmit(e)}>
                                <div className="row justify-content-start">
                                    <div className= "col-2">
                                        <span>Fecha de</span>
                                    </div>
                                    <div className= "col-3">
                                        <span>Desde</span>
                                    </div>
                                    <div className= "col-3">
                                        <span>Hasta</span>
                                    </div>
                                    <div className= "col-3">
                                        <span>Palabras clave</span>
                                    </div>
                                </div>
                                <div className="row justify-content-start">
                                    <div className= "col-2 input-group mb-2">
                                        <select class=" form-control " id="dateType"  value={this.state.dateType} 
                                             onChange={(e) => this.handleDateTypeChange(e)}>
                                            <option value="Entrada">Entrada</option>
                                            <option value="Salida">Salida </option>
                                            <option value="Reserva">Reserva </option>
                                        </select>
                                    </div>
                                    <div className="col-3 input-group mb-2" >  
                                        <div class="input-group-prepend">
                                            <input type="date" id="minDate" className="form-control "
                                                value={this.state.minDate}
                                                onChange={(e) => this.handleMinDateChange(e)}
                                                autoFocus
                                                required/>
                                        </div>
                                    </div>                                
                                    <div className="col-3 input-group mb-2" >  
                                        <div class="input-group-prepend">
                                            <input type="date" id="maxDate" className="form-control "
                                                value={this.state.maxDate} 
                                                onChange={(e) => this.handleMaxDateChange(e)}
                                                autoFocus
                                                min= {untilMin}
                                                required/>
                                        </div>
                                    </div>
                                    <div className="col-3 input-group mb-2" >  
                                        <div class="input-group-prepend">
                                            <input type="text" id="peopleKeywords"  className=" form-control "
                                                value={this.state.keywords} 
                                                onChange={(e) => this.handleKeywordsChange(e)}
                                                autoFocus/>  
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
    findBookings : actions.findBookings
};

export default connect(mapStateToProps, mapDispatchToProps)(BookingsFindForm);