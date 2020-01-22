import React from 'react';
import {connect} from 'react-redux';

import RoomTypeForm from './RoomTypeForm';
import RoomTypeList from './RoomTypeList';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {SimpleModal} from '../../common';
import {FormattedMessage} from 'react-intl';


class RoomType extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          showModal: false
        };
      }
    
    handleToggleModal() {
        this.setState({ showModal: !this.state.showModal });
    }

    componentDidMount() {
        this.props.findRoomTypes();
    }

    render(){

        const { showModal } = this.state;

        return (
             <div>
                 <div>
                    <button type="button" className="btn  btn-link btn-sm"
                    onClick={() => this.handleToggleModal()}>
                     <span className="fas fa-plus" ></span> 
                      &nbsp;
                     <FormattedMessage id="project.roomtypes.RoomTypeForm.title"/>
                    </button>

                    {showModal &&
                    <SimpleModal onCloseRequest={() => this.handleToggleModal()}>
                        <RoomTypeForm history={this.props.history} />
                    </SimpleModal>}
                    </div>
                &nbsp;
                &nbsp;
                <RoomTypeList list={this.props.roomTypes} history={this.props.history}/> 
             
            </div>
        );
    }
}

const mapStateToProps = state => ({
    roomTypes: selectors.getRoomTypes(state)
});


const mapDispatchToProps = {
    findRoomTypes : actions.findAllRoomTypes,
    removeRoomType : actions.removeRoomType,
}

export default connect(mapStateToProps,mapDispatchToProps)(RoomType);