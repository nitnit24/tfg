import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';
import Bed from '../bed.png';
import DetailsRoom from './DetailsRoom';
import TariffsItemList from './TariffsItemList';



import {Errors} from '../../common';

import '../../styles.css';
import { TariffsItemLis } from '..';

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

        //const list = this.props.list;

        return (

            <div>
                <Errors errors={this.state.backendErrors}
                    onClose={() => this.handleErrorsClose()}/>

                <div className= "border rounded ">
                    <DetailsRoom></DetailsRoom>
                    <TariffsItemLis></TariffsItemLis>
                </div>
              
           
            </div>


        );

    }

}


RoomTypeItemList.propTypes = {
     //list: PropTypes.array.isRequired,
     history: PropTypes.object.isRequired
}

export default RoomTypeItemList;