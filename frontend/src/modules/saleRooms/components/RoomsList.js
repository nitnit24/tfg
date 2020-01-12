import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';


import RoomItem from './RoomItem';
import {Errors} from '../../common';

import '../../styles.css';

const initialState = {
   
};

class RoomsList extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState

    }

    render() {

        const list = this.props.list;
        
        return (
            <div>
                {list.map(item => 
                    <RoomItem 
                        history={this.props.history} room ={item}/>
                    )}
            </div>

        );

    }

}

export default RoomsList;