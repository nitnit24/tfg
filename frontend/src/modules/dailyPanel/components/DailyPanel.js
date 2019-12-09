import React from 'react';

import {FormattedMessage} from 'react-intl';


import TablePanel from './TablePanel';
import FindDailyPanel from './FindDailyPanel';


class DailyPanel extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        };
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

export default DailyPanel;