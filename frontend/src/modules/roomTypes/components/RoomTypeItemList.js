import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';


import RoomTypeItem from './RoomTypeItem';
import {Errors} from '../../common';

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

        const list = this.props.list;
        
        return (

            <div>

                <Errors errors={this.state.backendErrors}
                    onClose={() => this.handleErrorsClose()}/>

                <h4 className="h4">
                     <FormattedMessage id='project.roomTypes.RoomTypeItemList.title' /> 
                </h4>
                <table className="table .table-hover">
                    <thead>
                        <tr>
                            <th scope="col" style={{width: '30%'}}>
                                <FormattedMessage id='project.global.fields.name'/>
                            </th>
                            <th scope="col" style={{width: '10%'}}>
                                <FormattedMessage id='project.global.fields.capacity'/>
                            </th>
                            <th scope="col" style={{width: '10%'}}>
                                <FormattedMessage id='project.global.fields.minPrice'/>
                            </th>
                            <th scope="col" style={{width: '10%'}}>
                                <FormattedMessage id='project.global.fields.maxPrice'/>
                            </th>
                            <th scope="col" style={{width: '5%'}}></th>
                            <th scope="col" style={{width: '5%'}}></th>
                        </tr>
                    </thead>

                    <tbody>

                        {list.map(item => 
                            <RoomTypeItem roomTypeItemListId={list.id}
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


RoomTypeItemList.propTypes = {
     list: PropTypes.array.isRequired,
     history: PropTypes.object.isRequired
}

export default RoomTypeItemList;