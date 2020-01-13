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

        this.handleChange = this.handleChange.bind(this);

    }

    componentDidMount() {
        this.find();
    }

    componentDidUpdate(prevProps, prevState) {
        if (this.props.day !== prevProps.day) {
            this.find()
        }
        if ((this.props.day === prevProps.day) && (this.state.freeRooms !== prevState.freeRooms) &&
          (this.state.freeRooms !== ''))
            {
            this.add();
            }
      }

    add(){
        const idRoomType = this.props.roomTypeId;
        const date = this.props.day;
        const freeRooms = this.state.freeRooms;

        console.log("ADDDDDDDDDDDD " + date)
        backend.dailyPanelService.addSaleRoom(idRoomType, date, freeRooms,
        null,
        errors => this.setBackendErrors(errors))
    }

    find(){
        const day = this.props.day;
        let date = day.getDate();
        let month = day.getMonth() + 1;
        let year = day.getFullYear();

        this.setState({freeRooms:''})
        
        backend.dailyPanelService.findSaleRoom(this.props.roomTypeId, 
            year + '-'+ month + '-' + date ,
            saleRoom => this.setState({freeRooms:saleRoom.freeRooms}),
            errors => { this.setBackendErrors(errors),
                    errors ?  this.setState({freeRooms:''}) : "";
            });
    }

    handleChange(event) {
        this.setState({freeRooms: event.target.value});
    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }


    render(){
       // this.setState({day: this.props.day})
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