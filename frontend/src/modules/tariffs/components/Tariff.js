import React from 'react';
import {connect} from 'react-redux';

import TariffForm from './TariffForm';
import TariffItemList from './TariffItemList';
import * as selectors from '../selectors';

 //const Tariff =( {tariffs, history} )=> tariffs.items.length > 0 && (
const Tariff =( { tariffs, history} )=> (
    <div>
        <TariffForm history={history} />
        &nbsp;
        &nbsp;
        <TariffItemList list={tariffs} /> 
    </div>
);

const mapStateToProps = state => ({
    tariffs: selectors.getTariffs(state)
});

export default connect(mapStateToProps)(Tariff);
