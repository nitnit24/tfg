import React from 'react';

import '../dailyPanel.css';
import  backend from '../../../backend';

const initialState = {
    freeRooms: '',
    
    backendErrors: null
}


class FreeRoomsDayItem extends React.Component {

    constructor(props) {
        super(props);

        this.state = initialState;
    }

    componentWillMount() {
        console.log("hola")
        
        //const SaleRoom =   backend.dailyPanelService.findSaleRoom(this.props.roomTypeId, this.props.day,
          //() =>dispatch(findAllRoomTypes()),
        //errors => this.setBackendErrors(errors))
        
        //this.setState({freeRooms: SaleRoom.freeRooms.toString()}) 
    }

    handleFreeRoomsChange(event,date,roomTypeId) {
        this.setState({freeRooms: event.target.value})
        this.add(date,roomTypeId)
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }
    
    add(date, roomTypeId){
        const freeRooms = this.state.freeRooms.trim();
        backend.dailyPanelService.addSaleRoom(roomTypeId, date, freeRooms,
          //  () =>dispatch(findAllRoomTypes()),
          errors => this.setBackendErrors(errors))
    }

    render(){

        const day = this.props.day ;
        const roomTypeId = this.props.roomTypeId;

        return (
            <td className = "p-0" style={{width: '2.6%'}}>
                <input type="text" id="" className=" border-0 table-input text-center" 
                    value={this.state.freeRooms}  
                    onChange={(e) => this.handleFreeRoomsChange(e,day,roomTypeId)} 
                    min= "0"      
                />
            </td>

        );
    }
}



export default FreeRoomsDayItem;