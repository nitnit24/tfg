import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage, FormattedDate} from 'react-intl';
import * as selectors from '../selectors';


import {connect} from 'react-redux';

import RoomsList from './RoomsList';

import '../../styles.css';



class BookingSummary extends React.Component {

    constructor(props) {

        super(props);

        this.state = {
            backendErrors: null,
           
        }

    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }

    getDate(date){
        var string ='';
        const day = new Date(date);
        string += this.getDayOfWeek(day);
        string += ' ';
        string += day.getDate();
        string += ' ';
        string += this.getNameMonth(day);
        string += ' ';
        string += day.getFullYear();
        return string;
    }
    getDayOfWeek(date){
       // const day = new Date(date)
        switch(date.getDay()){
            case 1: return( "lun," );
            case 2: return( "mar," );
            case 3: return( "mié," );
            case 4: return( "jue," );
            case 5: return( "vie," );
            case 6: return( "sáb," );
            case 0: return( "dom," );
        break;      
        }
    }

    getNameMonth(date){
       // const day = new Date(date)
        switch(date.getMonth()+1){
            case 1: return( "ene." );
            case 2: return( "feb." );
            case 3: return( "mar." );
            case 4: return( "abr." );
            case 5: return( "may." );
            case 6: return( "jun." );
            case 7: return( "jul." );
            case 8: return( "ago." );
            case 9: return( "sep." );
            case 10: return( "oct." );
            case 11: return( "nov." );
            case 12: return( "dec." );
        break;      
        }
    }

    totalPrice(){
        var lista= this.props.rooms;
        const suma = lista.reduce((total, room) =>
            {return total + room.price * room.quantity},0);
       return suma;
    
    }

    render() {

        return (
            <div className=" border rounded p-4">
                <h5>
                    <FormattedMessage id="project.saleRooms.BookingSummary.title"/>
                </h5>
                { (this.props.startDate && this.props.endDate ) && 
                <div className= "m-3">
                    <div className="row">
                        <div className="col-6">
                            <span>Entrada</span>
                        </div>
                        <div className="col-6">
                            <span>Salida</span>   
                        </div> 
                    </div>
                    <div className="row">
                        <div className="col-5">
                        <h5>{this.getDate(this.props.startDate)}  </h5>
                        </div>
                        <div className="col-1">
                            <i class='fas fa-arrow-right'></i>
                        </div>
                        <div className="col-6">
                        <h5>{this.getDate(this.props.endDate)}  </h5>
                        </div> 
                    </div>
                </div>
                }
                <div>
                    <div className= " row m-3 align-items-center">
                        <span> <FormattedMessage id="project.saleRooms.Total.total"/></span>
                        &nbsp;&nbsp;
                        <h4>  <b>{this.totalPrice()} € </b></h4>
                    </div>
                    <div>
                        <span> <small><em>Impuestos incluidos</em> </small></span>
                    </div>
                </div>
                <RoomsList history={this.props.history} list={this.props.rooms}/>
            </div>
                     
         

        );

    }

}


const mapStateToProps = (state) => ({
    startDate: selectors.getStartDate(state),
    endDate: selectors.getEndDate(state),
    rooms: selectors.getRooms(state)

});

const mapDispatchToProps = {
};

export default connect (mapStateToProps, mapDispatchToProps)(BookingSummary);