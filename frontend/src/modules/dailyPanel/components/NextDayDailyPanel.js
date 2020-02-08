import React from 'react';
import {connect} from 'react-redux';

import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';

class NextDayDailyPanel extends React.Component {
    constructor(props) {
        super(props);
        this.state = {datePanel: ''};

         this.handleChange = this.handleChange.bind(this);
      }

    componentDidUpdate(){
        this.saveDatePanel();
    }

    handleChange(event) {
        this.setState({datePanel: event.target.value});
    }

    saveDatePanel(){
        const datePanelString = this.state.datePanel;
        const datePanelMillis = Date.parse(datePanelString);
        const datePanel = new Date(datePanelMillis)
        const date = new Date(datePanel.getFullYear(),(datePanel.getMonth()),datePanel.getDate());
     
        //this.props.addDate(date.getTime());
        this.props.findRoomTables(date.getTime())
    }

    render(){

        return (
        <div className="col-md-1 ">  
            <button type="submit" className="btn btn-link disabled" >
            <h3>></h3>
            </button>
        </div>                    

        );
    }
}

const mapStateToProps = (state) => ({

    
});

const mapDispatchToProps = {
    addDate: actions.addDate,
    findRoomTables: actions.findRoomTables

};

export default connect(mapStateToProps, mapDispatchToProps)(NextDayDailyPanel);