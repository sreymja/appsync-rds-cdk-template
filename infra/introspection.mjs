import { AppSyncClient, StartDataSourceIntrospectionCommand, GetDataSourceIntrospectionCommand } from "@aws-sdk/client-appsync";
import * as fs from "fs"; // ES Modules import
import pkg from '@aws-appsync/utils/rds.js';
const {sql, createPgStatement, select} = pkg;


// const { AppSyncClient, StartDataSourceIntrospectionCommand } = require("@aws-sdk/client-appsync"); // CommonJS import
const obj = JSON.parse(fs.readFileSync('outputs.json', 'utf8'));
const client = new AppSyncClient({});
const input = { // StartDataSourceIntrospectionRequest
    rdsDataApiConfig: { // RdsDataApiConfig
        resourceArn: obj['database-stack']['ClusterArn'], // required
        secretArn: obj['database-stack']['SecretArn'], // required
        databaseName: obj['database-stack']['DbName'], // required
    },
};
const command = new StartDataSourceIntrospectionCommand(input);
const response = await client.send(command);
console.log(response)
// { // StartDataSourceIntrospectionResponse
//   introspectionId: "STRING_VALUE",
//   introspectionStatus: "PROCESSING" || "FAILED" || "SUCCESS",
//   introspectionStatusDetail: "STRING_VALUE",
// };

var t = 500;

function waitFor(millSeconds) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve();
        }, millSeconds);
    });
}

async function retryPromiseWithDelay(promise, nthTry, delayTime) {
    try {
        const res = await promise;
        if(res['introspectionStatus'] == "SUCCESS")
            return res;
        else return Promise.reject(res)
    } catch (e) {
        if (nthTry === 1) {
            return Promise.reject(e);
        }
        console.log('retrying', nthTry, 'time');
        // wait for delayTime amount of time before calling this method again
        await waitFor(delayTime);
        return retryPromiseWithDelay(promise, nthTry - 1, delayTime);
    }
}

const getInput = { // GetDataSourceIntrospectionRequest
    introspectionId: response['introspectionId'], // required
    includeModelsSDL: true,
    // nextToken: "STRING_VALUE",
    // maxResults: Number("int"),
};
const getCommand = new GetDataSourceIntrospectionCommand(getInput);


console.log("waiting to try...")
await waitFor(10000)
console.log("trying now...")
const getResponse = await retryPromiseWithDelay(client.send(getCommand), 3, 10000);

console.log(getResponse)
fs.writeFileSync('introspectionResult.json', JSON.stringify(getResponse))

console.log(createPgStatement(select({table: 'statuses'})));