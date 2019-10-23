import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';


import TariffItem from './TariffItem';
import {Errors} from '../../common';

import '../../styles.css';

const initialState = {
    backendErrors: null
};

class TariffItemList extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState;

    }

    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }


    render() {

        const list = this.props.list;
        

        // if (list.items.length === 0) {
        //     return (
        //         <div className="alert alert-info" role="alert">
        //             <FormattedMessage id='project.tariff.TariffItemList.empty'/>
        //         </div>
        //     );
        // }

        return (

            <div>

                <Errors errors={this.state.backendErrors}
                    onClose={() => this.handleErrorsClose()}/>

                <h4 className="h4">
                     <FormattedMessage id='project.tariffs.TariffItemList.title' /> 
                </h4>
                <table className="table .table-hover">
                    <thead>
                        <tr>
                            <th scope="col" style={{width: '30%'}}>
                                <FormattedMessage id='project.global.fields.code'/>
                            </th>
                            <th scope="col" style={{width: '30%'}}>
                                <FormattedMessage id='project.global.fields.name'/>
                            </th>
                            <th scope="col" style={{width: '5%'}}></th>
                            <th scope="col" style={{width: '5%'}}></th>
                        </tr>
                    </thead>

                    <tbody>

                        {list.map(item => 
                            <TariffItem tariffItemListId={list.id}
                                key={item.id} item={item}
                                history={this.props.history}
                                onBackendErrors={errors => this.setBackendErrors(errors)}/>
                        )} 

                    </tbody>

                </table>

            </div>

        );

    }

}


TariffItemList.propTypes = {
     list: PropTypes.array.isRequired
}

export default TariffItemList;