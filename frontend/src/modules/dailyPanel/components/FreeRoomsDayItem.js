import '../dailyPanel.css';
import {connect} from 'react-redux';
import * as actions from '../actions';
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
        this.setState({freeRooms: this.props.freeRooms});
    }

    add(freeRooms){
        const roomTypeId = this.props.roomTypeId;
        const date = this.props.day;

        this.props.uploadFreeRooms(roomTypeId, date, freeRooms,
             null,
             errors => this.setBackendErrors(errors))
        // backend.dailyPanelService.addSaleRoom(roomTypeId, date, freeRooms,
        // null,
        // errors => this.setBackendErrors(errors))
    }

    handleChange(event) {
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
                <input type="text" className=" border-0 table-input text-center" 
                    value={this.state.freeRooms}  
                    onChange={(e) => this.handleChange(e)} 
                    autoFocus
                    min= "0" 
                    data-toggle="tooltip" data-placement="right" title="Tooltip on right"   
                />
                </form>
            </td>

        );
    }
}

const mapStateToProps = (state) => ({

});

const mapDispatchToProps = {
    uploadFreeRooms: actions.uploadFreeRooms
};
export default connect( mapStateToProps, mapDispatchToProps)(FreeRoomsDayItem);