import '../dailyPanel.css';
import  backend from '../../../backend';

import React from 'react';

const initialState = {
    freeRooms: '',

    backendErrors: null
}


class FreeRoomsDayItem extends React.Component {

    constructor(props) {
        super(props);
        this.state = initialState;
        console.log("freeRoomItem  " + this.props.day)
        this.handleChange = this.handleChange.bind(this);

    }

    componentDidMount() {
        this.find();
    }

    componentDidUpdate(prevProps, prevState) {
        if (this.props.day !== prevProps.day) {
            this.find()
        }
      }



    add(freeRooms){
        const idRoomType = this.props.roomTypeId;
        const date = this.props.day;

        backend.dailyPanelService.addSaleRoom(idRoomType, date, freeRooms,
        null,
        errors => this.setBackendErrors(errors))
    }

    find(){
        
        backend.dailyPanelService.findSaleRoom(this.props.roomTypeId, 
            this.props.day ,
            saleRoom => this.setState({freeRooms:saleRoom.freeRooms}),
            errors => { this.setBackendErrors(errors),
                    errors ?  this.setState({freeRooms:''}) : "";
            });
    }

    handleChange(event) {
        console.log("onchange")
        this.setState({freeRooms: event.target.value});
        this.add(event.target.value);
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }


    render(){
        return (
            <td className = "p-0" style={{width: '2.6%'}}>
                 <form >
                <input type="text" id="" className=" border-0 table-input text-center" 
                    value={this.state.freeRooms}  
                    onChange={(e) => this.handleChange(e)} 
                    autoFocus
                    min= "0"      
                />
                </form>
            </td>

        );
    }
}



export default FreeRoomsDayItem;