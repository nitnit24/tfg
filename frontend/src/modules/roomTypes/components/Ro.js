import React, { Component } from 'react'
import Modal from 'react-bootstrap-modal';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {connect} from 'react-redux';

class RoomType extends React.Component {
	constructor(props) {
        super(props);
        this.state = {
            modal: true
        };
        this.toggle = this.toggle.bind(this);
    };

    toggle() {
        this.setState({modal: !this.state.modal});
    }

    render() {
        return (
            <div>
                <button onClick={ this.toggle}>Contact Us</button>
                <Modal isOpen={this.state.modal} fade={false}
                       toggle={this.toggle} style={{width: "200px", display: "block"}}>
                    <Modal.Header toggle={this.toggle}>
                        Modal title
                    </Modal.Header>
                    <Modal.Body>
                        Lorem ipsum dolor sit amet,
                    </Modal.Body>
                    <Modal.Footer>
                        <button onClick={this.toggle}>
                            Do Something
                        </button>{' '}
                        <button onClick={this.toggle}>Cancel</button>
                    </Modal.Footer>
                </Modal>
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