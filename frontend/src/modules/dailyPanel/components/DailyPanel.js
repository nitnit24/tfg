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
            // <div onClick={() => { this.child.getHoli(); }}>
            <div>
                <div>
                    <h4 className="h4">
                        <FormattedMessage id='project.dailyPanel.dailyPanelTable.title' /> 
                    </h4>
                </div>
                <br/>
                <FindDailyPanel />
                <br/>
                <TablePanel  />
                {/* <Prueba ref={instance => { this.child = instance; }}/> */}
            </div>
        );
    }
}

export default DailyPanel;