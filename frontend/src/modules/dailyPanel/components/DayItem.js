import React from 'react';
import {connect} from 'react-redux';

import {FormattedMessage} from 'react-intl';
import '../dailyPanel.css';




class DayItem extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
        };
      }

    render(){

        const item = this.props.day ;
        return (
        
                <th class = "panel" scope="col">
                       <span class = "text-panel">  {this.props.item} </span>
                </th> 

        );
    }
}



export default DayItem;