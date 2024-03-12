import React from 'react';
import './App.css';
import {Amplify} from "aws-amplify";
import config from "./appsync-config";
import Tickets from "./components/Tickets";
import {generateClient} from "aws-amplify/api";

function App() {
  // @ts-ignore
    Amplify.configure(config);
  return (
    <div className="App">
      <Tickets></Tickets>
    </div>
  );
}

export default App;
