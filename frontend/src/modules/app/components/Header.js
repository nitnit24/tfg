import React from 'react';
import {connect} from 'react-redux';
import {Link, NavLink, withRouter} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import users from '../../users';
import './Header.css';

const Header = ({user, handleLogout}) => (

    <nav className="navbar navbar-expand-lg navbar-light border">
        <Link className="navbar-brand" to="/">TFG Project</Link>
        <button className="navbar-toggler" type="button" 
            data-toggle="collapse" data-target="#navbarSupportedContent" 
            aria-controls="navbarSupportedContent" aria-expanded="false" 
            aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">

            

            {user ?
            <ul className="navbar-nav mr-auto text-uppercase">   
                <li className="nav-item">
                    <NavLink exact className="nav-link" to="/">
                        <span className="fas fa-home"></span>&nbsp;
                        <FormattedMessage id="project.app.Header.home"/>
                    </NavLink>
                </li>              
                <li className="nav-item">
                    <NavLink exact className="nav-link" to="/tariffs/tariff-management">
                        <span></span>
                        <FormattedMessage id="project.app.Header.tariff"/>
                    </NavLink>
                </li>
                <li className="nav-item">
                    <NavLink exact className="nav-link" to="/roomTypes/roomType-management">
                           <span></span>
                           <FormattedMessage id="project.app.Header.roomType"/>
                    </NavLink>
                </li>
            </ul>
            :
            <ul className="navbar-nav mr-auto text-uppercase">   
                <li className="nav-item ">
                    <NavLink exact className="nav-link" to="/saleRooms/find-saleRooms">
                        <FormattedMessage id="project.app.Header.ReserveRoom"/>
                    </NavLink>
                </li> 
            </ul>
            }
            
    
            
            
            {user ? 

            <ul className="navbar-nav">
               
                <li className="nav-item dropdown">

                    <a className="dropdown-toggle nav-link" 
                        data-toggle="dropdown">
                        <span className="fas fa-user"></span>&nbsp;
                        {user.userName}
                    </a>
                    <div className="dropdown-menu dropdown-menu-right">
                        <NavLink exact className="dropdown-item" to="/users/update-profile">
                            <FormattedMessage id="project.users.UpdateProfile.title"/>
                        </NavLink>
                        <NavLink exact className="dropdown-item" to="/users/change-password">
                            <FormattedMessage id="project.users.ChangePassword.title"/>
                        </NavLink>
                        <div className="dropdown-divider"></div>
                        <a className="dropdown-item" 
                            onClick={() => handleLogout()}>
                            <FormattedMessage id="project.app.Header.logout"/>
                        </a>
                    </div>

                </li>

            </ul>
            
            :

            <ul className="navbar-nav">
                <li className="nav-item">
                    <NavLink exact className="nav-link" to="/users/login">
                        <FormattedMessage id="project.users.Login.title"/>
                    </NavLink>
                </li>
            </ul>
            
            }

        </div>
    </nav>

);

const mapStateToProps = (state, ownProps) => ({
    user: users.selectors.getUser(state)
});

const mapDispatchToProps = (dispatch, ownProps) => ({
    handleLogout() {
        dispatch(users.actions.logout());
        ownProps.history.push('/');
    }
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Header));
