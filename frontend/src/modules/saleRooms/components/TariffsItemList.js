import React from 'react';
import {connect} from 'react-redux';
import {FormattedNumber} from 'react-intl';

import * as selectors from '../selectors';
import  backend from '../../../backend';

import QuantitySelect from './QuantitySelect';

import '../../styles.css';

const initialState = {
    tariffsByFreeRoom:[],
    backendErrors: null
};

class TariffsItemList extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState

    }

    // componentDidMount() {
    //     backend.bookingService.findTariffsByFreeRoom(this.props.startDate, this.props.endDate, this.props.roomType.id,
    //        tariffs =>  this.setState({tariffsByFreeRoom: tariffs}));

    // }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }


    render() {

        const hotelId = this.props.hotelId;
        const tariffs = this.props.tariffs;
        const roomTypeId = this.props.roomTypeId;
        const  maxFreeRooms = this.props.maxFreeRooms;
        const capacity = this.props.capacity;
        const roomTypeName = this.props.roomTypeName;

        return (
            <div>
                {tariffs.map(tariff =>
                    <div className=" m-3 row justify-content-center"> 
                        <div className= "col-4 align-self-end">
                        <div className= "align-self-end">
                            <b>{tariff.tariffName}</b>
                        </div>
                        <div className= "align-self-end">
                            <small>{tariff.tariffDescription}</small>
                        </div>
                        </div>
                        <div className= "col-3 align-self-end">
                            <FormattedNumber value={tariff.totalPrice}/> €
                        </div>
                        <QuantitySelect 
                            hotelId = {hotelId}
                            roomTypeId = {roomTypeId} 
                            tariff = {tariff}
                            maxFreeRooms = { maxFreeRooms}
                            capacity = {capacity}
                            roomTypeName = {roomTypeName}
                        />
                    </div>
                )}
            </div>

        );

    }

}

const mapStateToProps = (state) => ({
    startDate: selectors.getStartDate(state),
    endDate: selectors.getEndDate(state)

});

const mapDispatchToProps = {

};


export default connect (mapStateToProps, mapDispatchToProps)(TariffsItemList);