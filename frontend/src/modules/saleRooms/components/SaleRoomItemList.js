import React from 'react';
import {connect} from 'react-redux';

import DetailsRoom from './DetailsRoom';
import { TariffsItemList } from '..';
import {Errors} from '../../common';
import * as selectors from '../selectors';
import { Roller } from 'react-awesome-spinners';

import {FormattedMessage} from 'react-intl';

import '../../styles.css';

const initialState = {
    loading: true, 
    backendErrors: null
};

class SaleRoomItemList extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState

    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
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
                 {this.state.loading === true && <Roller  />}
                </div>
                
            );
        }

        return (

            <div>
                <Errors errors={this.state.backendErrors}
                    onClose={() => this.handleErrorsClose()}/>
                     {list.map(room => 
                     <div className= "border rounded ">
                        <DetailsRoom key={room.id} item={room} />
                        <TariffsItemList roomType= {room}></TariffsItemList>
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