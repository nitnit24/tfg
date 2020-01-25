import React from 'react';
import {FormattedMessage} from 'react-intl';
import {NavLink} from 'react-router-dom';
import {connect } from 'react-redux';
import {withRouter} from 'react-router-dom';

import users from '../../users';

const Footer = ({user}) => (

    <div>
        <br/>
        <hr/>
        <footer>
            <p className="text-center">
                <FormattedMessage id="project.app.Footer.text"/>
            </p>
            {!user &&
             <p className="text-center"> 
                <NavLink exact className="nav-link bg-light" to="/users/login">
                        <FormattedMessage id="project.users.Login.titleAdmin"/>
                </NavLink>
            </p> 
            }
        </footer>
    </div>

);

const mapStateToProps = (state, ownProps) => ({
    user: users.selectors.getUser(state)
});


export default withRouter(connect(mapStateToProps)(Footer));
