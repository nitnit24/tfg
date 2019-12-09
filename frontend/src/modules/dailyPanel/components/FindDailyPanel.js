import React from 'react';
import {connect} from 'react-redux';

import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';

class FindPanel extends React.Component {
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
        const datePanel = this.state.datePanel;
        this.props.addDate(datePanel);
    }

    render(){

        return (
            <div>
                <div className= "row justify-content-end">
                    <form  onSubmit={this.handleSubmit} >
                        <div className="p-3 input-group-prepend">
                            <label htmlFor="name" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.since"/>
                            </label>
                            <div className= "input-group-text" >
                                <span className="fas fa-calendar-alt" ></span>
                            </div>
                            <input type="date" id="datePanel" className="form-control "
                                     style={{width: '75%'}}
                                    value={this.state.datePanel}  
                                    onChange={(e) => this.handleChange(e)}
                                    autoFocus
                             />    
                        </div>                     
                        </form>
   
         
                </div>
            </div>
          
        );
    }
}

const mapStateToProps = (state) => ({

    
});

const mapDispatchToProps = {
    addDate: actions.addDate,

};

export default connect(mapStateToProps, mapDispatchToProps)(FindPanel);