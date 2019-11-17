import React from 'react';
import {connect} from 'react-redux';

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
                <div className= "row justify-content-end">
                    <form>
                    <div className="row">
                                <label htmlFor="minPrice" className="col-md-3 col-form-label">
                                    {/* <FormattedMessage id="project.global.fields.minPrice"/> */}
                                    Desde:
                                </label>
                               
                                <div className="col-md-4">
                                <input type="date" id="start" classNname="form-control"
                                        // value="2018-07-22"
                                     min="2018-01-01" max="2022-12-31"/>
                                    <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.min'/>
                                    </div>
                                </div>
                               
                            </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default DailyPanel;