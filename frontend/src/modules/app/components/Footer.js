import React from 'react';
import {FormattedMessage} from 'react-intl';
import {connect } from 'react-redux';
import {withRouter} from 'react-router-dom';
import {Link} from 'react-router-dom';

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
                <Link  to="/users/login">
                        <FormattedMessage id="project.users.Login.titleAdmin"/>
                </Link>
            </p>
            }
            <br/>
            <br/>
        </footer>
    </div>

);

const mapStateToProps = (state) => ({
    user: users.selectors.getUser(state)
});


export default withRouter(connect(mapStateToProps)(Footer));
