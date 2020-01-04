import React from 'react';
import PropTypes from 'prop-types';
import {FormattedNumber} from 'react-intl';
import {connect} from 'react-redux';

import {Errors} from '../../common';
import * as selectors from '../selectors';
import * as actions from '../actions';
import  backend from '../../../backend';

import '../../styles.css';

const initialState = {
    backendErrors: null,
    saleRoomTariffs :[],
    maxPrice: '',
    quantity: 0,
    checked: false
};

class TotalTariff extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState

    }

    componentDidMount() {
        backend.bookingService.findSaleRoomTariffsByFreeRoom(this.props.startDate, this.props.endDate, 
            this.props.roomType.id, this.props.tariff.id,
            saleRoomTariffs =>  this.setState({saleRoomTariffs: saleRoomTariffs}));
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }

    handleChangeQuantity(e) {
        this.setState({quantity: e.target.value});
     }

    componentDidUpdate(prevProps, prevState) {
        if (this.state.quantity !== 0){
            var room = {
                id: this.props.roomType.id,
                quantity: this.state.quantity,
                name: this.props.roomType.name,
                capacity: this.props.roomType.capacity,
                tariff: this.props.tariff.name,
                price: this.totalPrice()
            }
            this.props.removeRoom(room)
            this.props.addRoom(room)
        }
      }

    totalPrice(){
        var lista= this.state.saleRoomTariffs;
        const suma = lista.reduce((total, saleRoomTariff) =>
            {return total + saleRoomTariff.price},0);
       return suma;
    
    }

    mayor() {
        var lista= this.state.saleRoomTariffs;
        var mayor= lista.reduce(function(resultado,saleRoomTariffs,indice,array) {
            if (indice< array.length){
       
                console.log(saleRoomTariffs)}
                //  if (resultado<saleRoomTariffs.saleRoom.freeRooms) resultado=saleRoomTariffs.saleRoom.freeRooms;
                //  }
             return resultado;
      }, 0 )
      console.log(mayor);
    }

    render() {

        return (
            <div className=" row justify-content-center">
                <div className= "col-3 align-self-end">
                    <FormattedNumber value={this.totalPrice()}/> €
                </div>
                <div className= "col-3 h-100 align-self-end">
                        <div className= " align-self-center">
                        <span className="text-secondary small"> Cantidad habt.</span>
                        </div>
                        <div className= "col-11  align-self-center">
                            <select class="h-25 form-control " id="Quantity"  value={this.state.quantity} 
                                onChange={(e) => this.handleChangeQuantity(e)}>
                                    <option value="0">0 </option>
                                    <option value="1">1 </option>
                                    <option value="2">2 </option>
                                    <option value="3">3 </option>
                                    <option value="4">4 </option> 
                            </select>
                        </div>
                </div>
            </div>
        );

    }

}


const mapStateToProps = (state) => ({
    tariffsByFreeRoom: selectors.getTariffsByFreeRoom(state),
    startDate: selectors.getStartDate(state),
    endDate: selectors.getEndDate(state)

});

const mapDispatchToProps = {
    addTariffsByFreeRoom: actions.addTariffsByFreeRoom,
    addRoom: actions.addRoom,
    removeRoom: actions.removeRoom
};


export default connect (mapStateToProps, mapDispatchToProps)(TotalTariff);