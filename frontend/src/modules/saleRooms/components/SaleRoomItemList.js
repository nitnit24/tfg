import React from 'react';
import {connect} from 'react-redux';

import DetailsRoom from './DetailsRoom';
import { TariffsItemLis } from '..';

import {Errors} from '../../common';

import * as selectors from '../selectors';

import '../../styles.css';

const initialState = {
    backendErrors: null
};

class RoomTypeItemList extends React.Component {

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

        return (

            <div>
                <Errors errors={this.state.backendErrors}
                    onClose={() => this.handleErrorsClose()}/>
                     {list.map(room => 
                     <div className= "border rounded ">
                        <DetailsRoom key={room.id} item={room} />
                        <TariffsItemLis></TariffsItemLis>
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


export default connect(mapStateToProps, mapDispatchToProps)(RoomTypeItemList);