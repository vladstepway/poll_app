import React from "react";
import {Redirect, Route} from "react-router-dom";

const PrivateRoute = ({component: Component, authnticated, ...rest}) => (
    <Route
        {...rest}
        render={props =>
            authnticated ? (
                <Component {...rest} {...props}/>
            ) : (
                <Redirect to={{
                    pathname: '/login',
                    state: {from: props.location}
                }}/>
            )}
    />
);

export default PrivateRoute;