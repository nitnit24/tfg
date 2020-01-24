import React from 'react';
import {connect} from 'react-redux';

import * as selectors from '../selectors';
import * as actions from '../actions';

import '../../styles.css';

const initialState = {
    backendErrors: null,
    quantity: 0,
    checked: false
};

class QuantitySelect extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState

    }

    // componentDidMount() {
    //     this.props.cleanRooms(),
    //     this.props.cleanSummaryRooms(),
    //     backend.bookingService.findSaleRoomTariffsByFreeRoom(this.props.startDate, this.props.endDate, 
    //         this.props.roomType.id, this.props.tariff.id,
    //         saleRoomTariffs =>  this.setState({saleRoomTariffs: saleRoomTariffs}));
    // }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }

    handleChangeQuantity(e) {
        this.setState({quantity: e.target.value});
     }

    componentDidUpdate() {
        if (this.state.quantity !== 0){
            var summaryRoom = {
                id: this.props.roomTypeId,
                quantity: this.state.quantity,
                name: this.props.roomTypeName,
                capacity: this.props.capacity,
                tariff: this.props.tariff.tariffName,
                price: this.props.tariff.totalPrice
            }
            var room ={
                saleRoomTariffs: this.props.tariff.saleRoomTariffs,
                quantity: this.state.quantity
            }
            this.props.removeSummaryRooms(summaryRoom),
            this.props.addSummaryRooms(summaryRoom),
            this.props.removeRooms(room),
            this.props.addRooms(room)
        }
        if (this.state.quantity === 0 ){
            var room ={
                saleRoomTariffs: this.props.saleRoomTariffs,
                quantity: this.state.quantity
            }
            this.props.removeRooms(room)
        }
      }

    getNumberRooms (maxFreeRooms){
        let rooms = [];
        for (let i=0;(i<=maxFreeRooms); i++){
            rooms.push(i);
        } 
        return rooms;
    }
    render() {
        const maxFreeRooms = this.props.maxFreeRooms;
        const max = this.getNumberRooms(maxFreeRooms)
        return (
                <div className= "col-2 h-100 align-self-end">
                        <div className= " align-self-center">
                        <span className="text-secondary small"> Cantidad habt.</span>
                        </div>
                        <div className= " align-self-center">
                            <select class="h-25 form-control " id="Quantity"  value={this.state.quantity} 
                                onChange={(e) => this.handleChangeQuantity(e)}>
                                   { max.map (i =>
                                        <option key={i} value={i}>{i}</option>
                                    )}
                                    {/* <option value="0">0 </option>
                                    <option value="1">1 </option>
                                    <option value="2">2 </option>
                                    <option value="3">3 </option>
                                    <option value="4">4 </option>  */}

                            </select>

                        </div>
                </div>
        );

    }

}


const mapStateToProps = (state) => ({
    startDate: selectors.getStartDate(state),
    endDate: selectors.getEndDate(state)

});

const mapDispatchToProps = {
    addRooms: actions.addRooms,
    removeRooms: actions.removeRooms,
    addSummaryRooms: actions.addSummaryRooms,
    removeSummaryRooms: actions.removeSummaryRooms,
    cleanRooms: actions.cleanRooms,
    cleanSummaryRooms: actions.cleanSummaryRooms
};


export default connect (mapStateToProps, mapDispatchToProps)(QuantitySelect);