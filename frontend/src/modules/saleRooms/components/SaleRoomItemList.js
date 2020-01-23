import React from 'react';
import {connect} from 'react-redux';

import DetailsRoom from './DetailsRoom';
import { TariffsItemList } from '..';
import {Errors} from '../../common';
import * as selectors from '../selectors';

import {FormattedMessage} from 'react-intl';

import '../../styles.css';

const initialState = {
    backendErrors: null
};

class SaleRoomItemList extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState

    }

    render() {

        const list = this.props.freeRoomTypes;

        if (!list) {
            return null;
        }

        if (list.length === 0) {
            return (
                <div className="text-center">
                 <FormattedMessage id='project.saleRooms.SaleRoomItemList.noSaleRoomsFound'/>
                </div>
                
            );
        }

        return (

            <div>
                     {list.map(room => 
                     <div>
                        <div className= "border rounded ">
                            <DetailsRoom 
                                name={room.roomTypeName} 
                                description= {room.roomTypeDescription} 
                                capacity ={room.capacity} />
                            <TariffsItemList
                                tariffs= {room.freeRoomTypeTariffs}
                                roomTypeId={room.roomTypeId}
                                maxFreeRooms = {room.maxFreeRooms}
                                capacity ={room.capacity} 
                                roomTypeName={room.roomTypeName} />
                        </div>
                        <br/>
                    </div>
                    )} 

            </div>


        );

    }

}


const mapStateToProps = (state) => ({
    freeRoomTypes: selectors.getFreeRoomTypes(state)
});

const mapDispatchToProps = {

};


export default connect(mapStateToProps, mapDispatchToProps)(SaleRoomItemList);