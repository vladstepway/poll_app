import React from "react";
import {Link} from "react-router-dom";
import {Button} from "antd";

class ServerError extends React.Component {
    render() {
        return (
            <div className="server-error-page">
                <h1 className="server-error-title">
                    500
                </h1>
                <div className="server-error-desc">
                    Server Error
                </div>
                <Link to="/"> <Button className="server-error-go-back-btn" type="primary" size="large">Go back</Button></Link>
            </div>
        );
    }
}

export default ServerError;