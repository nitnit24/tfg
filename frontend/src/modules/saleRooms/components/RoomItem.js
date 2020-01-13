import React from 'react';
import * as actions from '../actions';
import {connect} from 'react-redux';


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

    render() {
        const room = this.props.room;
        return (
            <div>
                <hr/><hr/>
                <span> <small>{room.quantity} &nbsp; hab.</small></span>        
                <h6 ><b>{room.name}</b></h6>  
                <ul>                        
                    <li><small>{room.capacity} personas</small></li>        
                    <li><small>{room.tariff}</small></li> 
                    <li><small><b>{room.price} €</b></small></li>
                </ul>      
               
            </div>
        );

    }

}

const mapStateToProps = (state) => ({

});

const mapDispatchToProps = {
    removeSummaryRooms: actions.removeSummaryRooms
};

export default connect(mapStateToProps, mapDispatchToProps)(RoomItem);