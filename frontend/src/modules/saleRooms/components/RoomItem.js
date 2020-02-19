import React from 'react';
import * as actions from '../actions';
import {connect} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import * as selectors from '../selectors';


class RoomItem extends React.Component {

    
    constructor(props) {
        super(props);
        this.state = {
          };
      }

      componentDidUpdate(prevProps, prevState){
        if (this.props.room.quantity === 0){
            this.props.removeSummaryRooms(this.props.room)
        }
    }

    handleClickDelete(){
        var room ={
            hotelId:  this.props.room.hotelId,
            saleRoomTariffs: this.props.room.saleRoomTariffs,
            quantity: this.props.room.quantity
        }

        this.props.removeRooms(room)
        // var summaryRoom = {
        //     id: this.props.roomTypeId,
        //     quantity: this.state.quantity,
        //     name: this.props.roomTypeName,
        //     capacity: this.props.capacity,
        //     tariff: this.props.tariff.tariffName,
        //     price: this.props.tariff.totalPrice
        // }
        this.props.removeSummaryRooms(this.props.room)
    }

    render() {
        const room = this.props.room;
        return (
            <div className=" letra">
                <hr/><hr/>
                <span> <small>{room.quantity} &nbsp; hab.</small></span>        
                <h6 ><b>{room.name}</b></h6>  
                <ul>                        
                    <li><small>{room.capacity} personas</small></li>        
                    <li><small>{room.tariff}</small></li> 
                    <li><small><b>{room.price} €</b></small></li>
                    { (this.props.freeRoomTypes.length  !== 0 ) &&
                     <button type="submit" className="btn btn-link" style={{color: "red"}}
                        onClick={() => this.handleClickDelete()} >
                        <FormattedMessage id="project.global.buttons.delete"/>
                    </button>
                }
                </ul>
            </div>
        );

    }

}

const mapStateToProps = (state) => ({
    freeRoomTypes: selectors.getFreeRoomTypes(state)

});

const mapDispatchToProps = {
    removeSummaryRooms: actions.removeSummaryRooms,
    removeRooms: actions.removeRooms,
    
};

export default connect(mapStateToProps, mapDispatchToProps)(RoomItem);