import React from 'react';

import {FormattedMessage} from 'react-intl';
import {connect} from 'react-redux';

import TablePanel from './TablePanel';
import FindDailyPanel from './FindDailyPanel';
import * as selectors from '../selectors';
import * as actions from '../actions';


class DailyPanel extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
      }

    componentDidMount() {

    if (this.props.date === null){
        const now = new Date();
        const date = new Date(now.getFullYear(),(now.getMonth()),now.getDate());
        console.log(date)
        this.props.findRoomTables(date.getTime());
        }

    }
    render(){
        
        return (
            <div>
                <div>
                    <h5 className="h4">
                        <FormattedMessage id='project.dailyPanel.dailyPanelTable.title' /> 
                    </h5>
                </div>
                <FindDailyPanel />

                <TablePanel />
            </div>
        );
    }
}


const mapStateToProps = (state) => ({
    date: selectors.getDate(state),
 
});

const mapDispatchToProps = {
    findRoomTables: actions.findRoomTables
};

export default  connect( mapStateToProps, mapDispatchToProps)(DailyPanel);