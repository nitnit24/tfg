import React from 'react';
import PropTypes from 'prop-types';
import * as actions from '../actions';
import * as selectors from '../selectors';
import {connect} from 'react-redux';

import Image from'../image.png';


class RoomTypeItem extends React.Component {

    
    constructor(props) {
        super(props);
        this.state = {
          };
      }

    handleRemoveItem() {
       this.props.removeRoomType(this.props.item,
        () => {
            this.props.onBackendErrors(null);
        }, 
        backendErrors => {
            this.props.onBackendErrors(backendErrors);
        });
    
    }

    handleClick(){
        this.props.findRoomTypeById(this.props.item.id,
            () => this.props.history.push('/roomTypes/roomType-update'),
            errors => this.setBackendErrors(errors));

    }
    
    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }


    render() {
        const item = this.props.item;
        return (
            <tr>   
                { (!item.image) ?
                    <td> <img src = {Image}  className="img-thumbnail"  alt="Hab" /></td> 
                
                :
                    <td> <img src={item.image}  className="img-thumbnail"  alt="Hab" /></td> 
                }      
                <td>{item.name}</td>
                <td>{item.description}</td>
                <td>{item.capacity}</td>
                <td>{item.quantity}</td>
                <td>{item.minPrice}</td>
                <td>{item.maxPrice}</td>
                <td> 
                <button type="button" className="btn btn-light btn-sm"
                            onClick={() => this.handleRemoveItem()}>
                            <span className="fas fa-trash-alt"></span>
                </button>
                </td>
                <td> 

                <button type="button" className="btn btn-light btn-sm"
                            onClick={() => this.handleClick()}>
                            <span className="fas fa-edit"></span>
                </button>
                </td>
            </tr>
          
            
        );

    }

}

RoomTypeItem.propTypes = {
    item: PropTypes.object.isRequired,
    onBackendErrors: PropTypes.func,
    history: PropTypes.object.isRequired
}

const mapStateToProps = (state) => ({
    roomType : selectors.getRoomType(state)
});

const mapDispatchToProps = {
    findRoomTypeById: actions.findRoomTypeById,
    removeRoomType: actions.removeRoomType
};

export default connect(mapStateToProps, mapDispatchToProps)(RoomTypeItem);