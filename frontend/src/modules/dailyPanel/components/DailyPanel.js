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
        const date = new Date()
        this.props.findRoomTables(date.getTime());
        }

    }
    render(){
        
        return (
            <div>
                <div>
                    <h4 className="h4">
                        <FormattedMessage id='project.dailyPanel.dailyPanelTable.title' /> 
                    </h4>
                </div>
                <br/>
                <FindDailyPanel />
                <br/>
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