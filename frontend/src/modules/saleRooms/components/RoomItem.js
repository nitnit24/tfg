import React from 'react';
import PropTypes from 'prop-types';
import * as actions from '../actions';
import * as selectors from '../selectors';
import {connect} from 'react-redux';
import {FormattedMessage} from 'react-intl';


class RoomItem extends React.Component {

    
    constructor(props) {
        super(props);
        this.state = {
          };
      }

      componentDidMount() {
        if (this.props.room.quantity == 0){
            this.props.removeRoom(this.props.room)
        }
    }

    render() {
        const room = this.props.room;
        return (
            <div>
            { (room.quantity !== 0 ) && 
            <div>
                <hr/><hr/>
                <span> <small>{room.quantity} &nbsp; hab.</small></span>        
                <h6 ><b>{room.name}</b></h6>  
                <ul>                        
                    <li><small>{room.capacity} personas</small></li>        
                    <li><small>{room.tariff}</small></li> 
                    <li><small><b>{room.price} €</b></small></li>
                </ul>      
                <button type="button" className=" m-0 btn btn-link btn-sm "
                        onClick={() => this.props.removeRoom(room)}>
                        {/* <span className=" 	fas fa-trash" ></span> 
                        &nbsp; */}
                        <FormattedMessage id="project.global.buttons.remove"/>
                </button>
               
            </div>
            }
            </div>
        );

    }

}

const mapStateToProps = (state) => ({

});

const mapDispatchToProps = {
    removeRoom: actions.removeRoom
};

export default connect(mapStateToProps, mapDispatchToProps)(RoomItem);