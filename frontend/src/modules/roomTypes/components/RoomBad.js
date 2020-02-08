import React from 'react'
import {connect} from 'react-redux';

import RoomTypeForm from './RoomTypeForm';
import RoomTypeList from './RoomTypeList';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {FormattedMessage} from 'react-intl';
import { Button } from 'react-bootstrap';
import { Modal } from 'react-bootstrap';


const initialState = {
    modal: false,
};

class RoomType extends React.Component {

    constructor(props) {

        super(props);

        this.state = initialState;
        this.toggle = this.toggle.bind(this);
    }

    
    componentDidMount() {
        this.props.findRoomTypes();
    }

    toggle() {
        this.setState({
          modal: !this.state.modal
        });
      }


    setBackendErrors(backendErrors) {
        this.setState({backendErrors});
    }

    handleErrorsClose() {
        this.setState({backendErrors: null});
    }

    render() {

return (
        <div>
            <button type="button" className="btn  btn-link btn-sm"
                    onClick={this.toggle.bind(this)}>
                     <span className="fas fa-plus" ></span> 
                      &nbsp;
                     <FormattedMessage id="project.roomtypes.RoomTypeForm.title"/>
                    </button>
            <div>
            <Modal  show={this.state.modal} size={'xl'} scrollable={'true'} onHide={this.toggle.bind(this)}
                >
                <Modal.Header closeButton>
                    <Modal.Title>Modal</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <RoomTypeForm history={this.props.history} />
                </Modal.Body>
                <Modal.Footer>
                    <button variant="secondary" onClick={this.toggle.bind(this)}>
                       Cancelar
                    </button>
                    <button variant="primary" onClick={console.log("holi")}>
                        ok
                    </button>
                </Modal.Footer>
                </Modal>
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
