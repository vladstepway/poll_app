import React from 'react';
import './App.css';
import {Layout, notification} from "antd";
import {Route, Switch, withRouter} from "react-router-dom";
import {ACCESS_TOKEN} from "../constants";
import AppHeader from '../common/AppHeader';
import LoadingIndicator from '../common/LoadingIndicator';
import PollList from "../poll/PollList";
import Login from "../user/login/Login";
import Signup from "../user/signup/Signup";
import Profile from "../user/profile/Profile";
import PrivateRoute from "../common/PrivateRoute";
import NotFound from "../common/NotFound";
import NewPoll from "../poll/NewPoll";
import {getCurrentUser} from "../utils/APIUtils";

const {Content} = Layout;

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            currentUser: null,
            isAuthenticated: false,
            isLoading: false
        };

        this.handleLogout = this.handleLogout.bind(this);
        this.loadCurrentUser = this.loadCurrentUser.bind(this);
        this.handleLogin = this.handleLogin.bind(this);

        notification.config({
            placement: 'topRight',
            top: 70,
            duration: 3,
        })
    }

    loadCurrentUser() {
        this.setState({
            isLoading: true
        });
        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    isAuthenticated: true,
                    isLoading: false
                });
            }).catch(error => {
            this.setState({
                isLoading: false
            });
        });
    }

    componentDidMount() {
        this.loadCurrentUser();
    }

    handleLogout(redirectTo = "/",
                 notificationType = "success"
        , description = "You're successfully logged out") {
        localStorage.removeItem(ACCESS_TOKEN);
        this.setState({
            currentUser: null,
            isAuthenticated: false
        });

        this.props.history.push(redirectTo);

        notification[notificationType]({
            message: 'Polling App',
            description: description
        });
        this.loadCurrentUser();
        this.props.history.push("/");
    }

    handleLogin() {
        notification.success({
            message: 'Polling App',
            description: "You're successfully logged in.",
        });
        this.loadCurrentUser();
        this.props.history.push("/");
    }

    render() {
        if (this.state.isLoading) {
            return <LoadingIndicator/>
        }

        return (
            <Layout className="app-container">
                <AppHeader isAuthenticated={this.state.isAuthenticated}
                           currentUser={this.state.currentUser}
                           onLogout={this.handleLogout}/>
                <Content className="app-content">
                    <div className="container">
                        <Switch>
                            <Route exact path="/"
                                   render={(props) =>
                                       <PollList isAuthenticated={this.state.isAuthenticated}
                                                 currentUser={this.state.currentUser}
                                                 handleLogout={this.handleLogout}
                                                 {...props}
                                       />
                                   }/>
                            <Route path="/login"
                                   render={(props) =>
                                       <Login onLogin={this.handleLogin} {...props}/>
                                   }/>
                            <Route path="/signup" component={Signup}/>
                            <Route path="/users/:username"
                                   render={(props =>
                                       <Profile isAuthenticated={this.state.isAuthenticated}
                                                currentUser={this.state.currentUser} {...props}
                                       />)}/>
                            <PrivateRoute authenticated={this.state.isAuthenticated}
                                          path="/poll/new" component={NewPoll}
                                handleLogout={this.handleLogout}
                            />
                            <Route component={NotFound}/>
                        </Switch>
                    </div>
                </Content>
            </Layout>
        );
    }
}

export default withRouter(App);
