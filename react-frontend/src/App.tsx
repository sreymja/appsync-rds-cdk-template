import React from 'react';
import './App.css';
import {Amplify} from "aws-amplify";
import config from "./appsync-config";
import Dashboard from "./components/Dashboard";
import {Route, Routes, Link} from "react-router-dom";
import Layout from "./components/Layout";
import Ticket from "./components/Ticket";

function App() {
  // @ts-ignore
    Amplify.configure(config);
  return (
    <div className="App">
        <Routes>
            <Route path="/" element={<Layout />}>
                <Route index element={<Dashboard />} />
                <Route path="ticket/:id" element={<Ticket />} />
                {/*<Route path="about" element={<About />} />*/}
                {/*<Route path="dashboard" element={<Dashboard />} />*/}

                {/* Using path="*"" means "match anything", so this route
                acts like a catch-all for URLs that we don't have explicit
                routes for. */}
                <Route path="*" element={<NoMatch />} />
            </Route>
        </Routes>
    </div>
  );
}
function NoMatch() {
    return (
        <div>
            <h2>Nothing to see here!</h2>
            <p>
                <Link to="/">Go to the home page</Link>
            </p>
        </div>
    );
}

export default App;
