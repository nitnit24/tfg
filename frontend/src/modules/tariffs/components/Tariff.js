import React from 'react';
import {connect} from 'react-redux';


import TariffForm from './TariffForm';

const Tariff =( history )=>(
    <div>
        <TariffForm history={history} />
    </div>
);

// const mapStateToProps = state => ({
//     tariff: selectors.getTariff(state)
// });

// export default connect(mapStateToProps)(Tariff);

export default connect()(Tariff);