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
    }

    handlePriceChange(event,date,roomTypeId,tariffId) {
        this.setState({price: event.target.value})
        console.log("hendleChange")
        this.add(date,roomTypeId,tariffId)
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    componentWillMount() {
        console.log("hola")
        //const SaleRoomTariff =   backend.dailyPanelService.findSaleRoomTariff(this.props.tariffId,this.props.roomTypeId, this.props.day,
          //() =>dispatch(findAllRoomTypes()),
        //errors => this.setBackendErrors(errors))
        //this.setState({price:  SaleRoomTariff.price
        //}) 
    }
    
    add(date, roomTypeId,tariffId){
        console.log("add");
        const price = this.state.price.trim();
        console.log(price),
        console.log(date),
        console.log(roomTypeId),
        console.log(tariffId)
        backend.dailyPanelService.uploadSaleRoomTariff(price, tariffId, roomTypeId, date,
          //  () =>dispatch(findAllRoomTypes()),
           errors => this.setBackendErrors(errors))
    }

    render(){

        const day = this.props.day ;
        const roomTypeId = this.props.roomTypeId;
        const tariffId = this.props.tariffId;

        return (
            <td className= "p-0" style={{width: '2.6%'}}>
                <input type="text" id="price" className="border-0 table-input text-center" 
                    value={this.state.price}
                    onChange={(e) => this.handlePriceChange(e,day,roomTypeId, tariffId)} 
                    min= "0"  
                />
            </td>

        );
    }
}



export default TariffDayItem;