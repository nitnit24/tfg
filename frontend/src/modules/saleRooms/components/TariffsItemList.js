import React from 'react';
import {connect} from 'react-redux';


import * as selectors from '../selectors';
import  backend from '../../../backend';

import TotalTariff from './TotalTariff';

import '../../styles.css';

const initialState = {
    tariffsByFreeRoom:[],
    backendErrors: null
};

class TariffsItemList extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState

    }

    componentDidMount() {
        backend.bookingService.findTariffsByFreeRoom(this.props.startDate, this.props.endDate, this.props.roomType.id,
           tariffs =>  this.setState({tariffsByFreeRoom: tariffs}));

    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }


    render() {

        const list = this.state.tariffsByFreeRoom;

        return (
            <div>
                {list.map(tariff =>
                    <div className=" m-3 row justify-content-center"> 
                        <div className= "col-4 align-self-end">
                            {tariff.name}
                        </div>
                        
                        <div className= "col-8 align-self-end">
                        <TotalTariff tariff = {tariff} roomType = {this.props.roomType}></TotalTariff>
                        </div>  
                    </div>
                )}
            </div>

        );

    }

}


TariffsItemList.propTypes = {
     //list: PropTypes.array.isRequired,
     //history: PropTypes.object.isRequired
}

const mapStateToProps = (state) => ({
    startDate: selectors.getStartDate(state),
    endDate: selectors.getEndDate(state)

});

const mapDispatchToProps = {

};


export default connect (mapStateToProps, mapDispatchToProps)(TariffsItemList);