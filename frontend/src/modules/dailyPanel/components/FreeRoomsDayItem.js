import '../dailyPanel.css';
import {connect} from 'react-redux';
import * as actions from '../actions';
import React from 'react';

const initialState = {
    freeRooms: '',
    freeRoomsError: '',
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

    }

    handleChange(event) {
        this.setState({freeRooms: event.target.value});

        if( event.target.value < this.props.quantity){
            this.add(event.target.value);
        }
    }

    validateFreeRooms = () => {
        const { freeRooms } = this.state;
        this.setState({
          freeRoomsError:
            freeRooms <= this.props.quantity ? null : 'El número de habitaciones libres debe ser menor de ' + this.props.freeRooms
        });
      }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }


    render(){
        return (
            <td className = "p-0" style={{width: '2.6%'}}>
                <form >
                <input type="text" 
                   className={` border-0 table-input text-center  ${this.state.freeRoomsError ? ' bg-danger text-white' : ''}`}
                    value={this.state.freeRooms}  
                    onChange={(e) => this.handleChange(e)} 
                    onBlur={this.validateFreeRooms}
                    data-toggle="tooltip" data-placement="right" title={this.state.freeRoomsError}  
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