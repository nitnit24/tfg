import React from 'react';

import '../dailyPanel.css';
import  backend from '../../../backend';

const initialState = {
    price: '',
    
    backendErrors: null
};

class TariffDayItem extends React.Component {

    constructor(props) {
        super(props);

        this.state = initialState;

        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        this.setState({price: this.props.price});
    }

    add(price){
        const day = this.props.day ;
        const roomTypeId = this.props.roomTypeId;
        const tariffId = this.props.tariffId;

        backend.dailyPanelService.uploadSaleRoomTariff(price, tariffId, roomTypeId, day,
          null,
        errors => this.setBackendErrors(errors))
    }

    handleChange(event) {
        this.setState({price: event.target.value})
        this.add(event.target.value);
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    render(){

        return (
            <td className= "p-0" style={{width: '2.6%'}}>
                <input type="text" className="border-0 table-input text-center" 
                    value={this.state.price}
                    onChange={(e) => this.handleChange(e)} 
                    min= "0"  
                />
            </td>

        );
    }
}



export default TariffDayItem;