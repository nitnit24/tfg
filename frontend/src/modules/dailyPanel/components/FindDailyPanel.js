import React from 'react';
import {connect} from 'react-redux';

import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';



class FindPanel extends React.Component {
    constructor(props) {
        super(props);
        this.state = {datePanel: ''};

         this.handleChange = this.handleChange.bind(this);
         this.handleSubmit = this.handleSubmit.bind(this);
      }
    
    // handleDatePanelChange(event) {
    //     this.setState({datePanel: event.target.value});
    //     this.saveDatePanel()
    // }

    handleSubmit(event) {
        event.preventDefault();
        this.saveDatePanel()
      }

    handleChange(event) {
        this.setState({datePanel: event.target.value});
        // event.preventDefault();
        // this.saveDatePanel()
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
                      <div className="input-group-prepend">
                            <div className= "input-group-text" >
                                <span className="fas fa-calendar-alt" ></span>
                            </div>
                            <input type="date" id="datePanel" className="form-control "
                                    style={{width: '60%'}}
                                    value={this.state.datePanel}  
                                    onChange={(e) => this.handleChange(e)}
                                    autoFocus
                                  
                            />    
                             <div className="offset-md-0 col-md-1">
                                    <button type="submit" className="btn btn-primary" >
                                        <span className="	fas fa-sync-alt" ></span>
                                    </button>
                                </div>
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