import React from 'react';
import {connect} from 'react-redux';

import TariffForm from './TariffForm';
import TariffItemList from './TariffItemList';
import * as selectors from '../selectors';
import * as actions from '../actions';


class Tariff extends React.Component {
    constructor(props) {
        super(props);
      }

    componentDidMount() {
        this.props.findTariffs();
    }

    render(){
        return (
             <div>
                <TariffForm history={this.props.history} />
                &nbsp;
                &nbsp;
                <TariffItemList list={this.props.tariffs} removeTariff= {this.props.removeTariff}/> 
            </div>
        );
    }
}

const mapStateToProps = state => ({
    tariffs: selectors.getTariffs(state)
});


const mapDispatchToProps = {
    findTariffs : actions.findAllTariffs,
    removeTariff : actions.removeTariff,
}

export default connect(mapStateToProps,mapDispatchToProps)(Tariff);
